package net.xdclss.service.impl;

import net.xdclass.model.UserDO;
import net.xdclass.mapper.UserMapper;
import net.xdclass.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 我是author
 * @since 2023-03-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

}
