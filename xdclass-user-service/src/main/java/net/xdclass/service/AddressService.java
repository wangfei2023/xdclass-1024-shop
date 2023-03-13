package net.xdclass.service;

import io.swagger.models.auth.In;
import net.xdclass.config.Response;
import net.xdclass.model.AddressDO;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 电商-公司收发货地址表 服务类
 * </p>
 *
 * @author 二当家小D
 * @since 2023-01-26
 */
public interface AddressService extends IService<AddressDO>   {

   AddressDO  detail(long id);

   Response testHead(HttpServletRequest request);
}
