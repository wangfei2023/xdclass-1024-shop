package net.xdclass.AddressTest;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.model.AddressDO;
import net.xdclass.service.AddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class AddressTest {
    @Autowired
    private AddressService addressService;
    @Test
    public void detail(){
        AddressDO addressDO = addressService.getById(1L);
        log.info(addressDO.toString());
    }
}
