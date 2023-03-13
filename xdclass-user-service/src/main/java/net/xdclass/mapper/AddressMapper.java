package net.xdclass.mapper;

import net.xdclass.model.AddressDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 电商-公司收发货地址表 Mapper 接口
 * </p>
 *
 * @author 二当家小D
 * @since 2023-01-26
 */
public interface AddressMapper extends BaseMapper<AddressDO> {
    @Select({"select * from address where id=#{id}"})
    AddressDO  detail(@Param("id") long id);
}
