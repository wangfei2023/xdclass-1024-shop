package net.xdclass.controller;


import net.xdclass.config.Response;
import net.xdclass.mapper.AddressMapper;
import net.xdclass.model.AddressDO;
import net.xdclass.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * <p>
 * 电商-公司收发货地址表 前端控制器
 * </p>
 *
 * @author 二当家小D
 * @since 2023-01-26
 */
@RestController
@RequestMapping("/addressDo")
public class AddressController {
    @Autowired
    private AddressService addressMapper;

 @GetMapping("/wo")
   public void list(@RequestParam("id") long id,HttpServletRequest request){
     AddressDO addressDO = addressMapper.detail(id);


 }
 @GetMapping("/testHead")
    public Response testHead(HttpServletRequest request){
     return addressMapper.testHead(request);
  }


}

