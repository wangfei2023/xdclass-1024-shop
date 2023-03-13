package net.xdclass.service.impl;

import com.alibaba.fastjson.JSONObject;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.config.Response;
import net.xdclass.model.AddressDO;
import net.xdclass.mapper.AddressMapper;
import net.xdclass.service.AddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * <p>
 * 电商-公司收发货地址表 服务实现类
 * </p>
 *
 * @author 二当家小D
 * @since 2023-01-26
 */

@Service
@Slf4j
public class AddressServiceImpl extends ServiceImpl<AddressMapper, AddressDO> implements AddressService {
   @Autowired
   private AddressMapper addressService;
    AddressDO addressDO =null;

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    @Transactional
    public AddressDO detail(long id) {
        String addressId = String.valueOf(id);
             addressDO = addressService.selectById(id);
             if(addressDO==null){
                 Logger.getGlobal().info("地址打印");
                // logger.info("能力开通接口，开户异常，异常信息："+e);
                 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
             }
              return addressDO;
  }
  public Response testHead(HttpServletRequest request) {
      String headers=null;
      if (StringUtils.isEmpty(headers)) {
          Enumeration<String> headers1 = request.getHeaders(headers);
          if (headers1 == null) {
              return Response.message(-1, "{token}获取失败");
          }
          redisTemplate.opsForValue().set("token", headers1, 24, TimeUnit.HOURS);
      }

    return Response.msg(headers+"{token}获取成功");
  }
}
