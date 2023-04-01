package net.xdclass.service;

import net.xdclass.model.UserDO;
import com.baomidou.mybatisplus.extension.service.IService;
import net.xdclass.request.UserRegisterRequest;
import net.xdclass.util.JsonData;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 我是author
 * @since 2023-03-19
 */
public interface UserService extends IService<UserDO> {
  public JsonData register(UserRegisterRequest registerRequest);
}
