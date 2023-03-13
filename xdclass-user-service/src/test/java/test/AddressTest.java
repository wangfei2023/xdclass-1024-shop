package test;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONUtil;
import net.xdclass.UserApplication;
import net.xdclass.model.AddressDO;
import net.xdclass.service.AddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
@Slf4j
public class AddressTest {
    @Autowired
    private AddressService addressService;

    @Test
    public void testAddressDetail() {
        AddressDO addressDO = new AddressDO();
        addressDO.setId(1l);
        try {
            if (!StringUtils.isBlank(String.valueOf(addressDO.getId())))
                addressDO = addressService.detail(1l);

        } catch (Exception e) {
            log.info("输入信息有误，请重新填写");
            throw new RuntimeException("有异常");
        }
        System.out.println(addressDO.toString());
    }

    public static void main(String[] args) {
        //demo:JSON对象转化为List对象

        String jsonStr = "[\n" +
                "    {\n" +
                "        \"name\":\"张三\",\n" +
                "        \"age\":\"1\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"name\":\"李四\",\n" +
                "        \"age\":\"4\"\n" +
                "    }\n" +
                "]";
        JSON jsonObject = null;
        List lists = jsonObject.parseArray(jsonStr, Person.class);
        System.out.println(lists);

    }
}