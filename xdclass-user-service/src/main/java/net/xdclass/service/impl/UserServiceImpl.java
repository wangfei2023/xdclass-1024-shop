package net.xdclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.enums.BizCodeEnum;
import net.xdclass.enums.SendCodeEnum;
import net.xdclass.model.UserDO;
import net.xdclass.mapper.UserMapper;
import net.xdclass.request.UserRegisterRequest;
import net.xdclass.service.NotifyService;
import net.xdclass.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.xdclass.util.CommonUtil;
import net.xdclass.util.JsonData;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 我是author
 * @since 2023-03-19
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
    @Autowired
    private NotifyService notifyService;
    @Resource
    private UserMapper userMapper;
    @Override
    public JsonData register(UserRegisterRequest registerRequest) {
        boolean checkCode = false;
        if (registerRequest.getMail()!=null){
            checkCode=notifyService.checkCode(SendCodeEnum.USER_REGISTER,registerRequest.getMail(),registerRequest.getCode());
        }
        if (!checkCode|| Objects.isNull(checkCode)){
            return JsonData.buildResult(BizCodeEnum.CODE_ERROR);
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(registerRequest,userDO);
        userDO.setCreateTime(new Date());
        userDO.setSlogan("人生需要动态规划，学习需要贪心算法");

//        //设置密码 生成秘钥 盐
//        userDO.setSecret("$1$" + CommonUtil.getStringNumRandom(8));
//
//        //密码+盐处理
//        String cryptPwd = Md5Crypt.md5Crypt(registerRequest.getPwd().getBytes(), userDO.getSecret());
//        userDO.setPwd(cryptPwd);

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(registerRequest.getPwd().getBytes());
            String hash = bytesToHex(digest);
            userDO.setPwd(hash);
            //System.out.println("MD5 hash of " + message + " is " + hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (checkUnique(userDO.getMail())) {

            int rows = userMapper.insert(userDO);
            log.info("rows:{},注册成功:{}", rows, userDO.toString());

//            //新用户注册成功，初始化信息，发放福利等 TODO
//            userRegisterInitTask(userDO);


            //模拟异常
            //int b = 1/0;

        } else {
            return JsonData.buildResult(BizCodeEnum.ACCOUNT_REPEAT);
        }
        return JsonData.buildSuccess();
    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            hexChars[i * 2] = HEX_ARRAY[v >>> 4];
            hexChars[i * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

        //账号唯一性检查 794666918@qq.com



    /**
     * 校验用户账号唯一
     *
     * @param mail
     * @return
     */
    private boolean checkUnique(String mail) {

        QueryWrapper queryWrapper = new QueryWrapper<UserDO>().eq("mail", mail);

        List<UserDO> list = userMapper.selectList(queryWrapper);

        return list.size() > 0 ? false : true;

    }


}
