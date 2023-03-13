package net.xdclass.controller;

import net.xdclass.Response.Response;
import net.xdclass.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addressDo")
public class MailController {
    @Autowired
    private MailService iMailService;
    @GetMapping("/sendMail")
    public String sendHtmlMail(@RequestParam("to") String to,
                                @RequestParam("subject") String subject,
                               @RequestParam("content") String content
    ) {
        iMailService.sendHtmlMail(to,subject,content);
        return "邮箱发送成功";
    }
}
