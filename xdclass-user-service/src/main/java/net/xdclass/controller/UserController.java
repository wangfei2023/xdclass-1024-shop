package net.xdclass.controller;


import com.google.code.kaptcha.Producer;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 二当家小D
 * @since 2023-01-26
 */
@ApiOperation("用户端的使用介绍")
@RestController
@RequestMapping("/userDO")
@Slf4j
public class UserController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private Producer captchaProducer;
    private static final long CAPTCHA_CODE_EXPIRED = 60 * 1000 * 10;
    @ApiOperation("获取图形验证码")
    @GetMapping("/captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response){
        String captchaText = captchaProducer.createText();
        log.info("图形验证码:{}",captchaText);
        redisTemplate.opsForValue().set(getCaptchaKey(request),captchaText,CAPTCHA_CODE_EXPIRED, TimeUnit.MILLISECONDS);

        BufferedImage bufferedImage = captchaProducer.createImage(captchaText);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            ImageIO.write(bufferedImage,"jpg",outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            log.error("获取验证码失败:{}",e);
        }
    }



    private String getCaptchaKey(HttpServletRequest request) {
           return "1";
    }
}


