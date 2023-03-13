package net.xdclass.service;

import net.xdclass.Response.Response;
import org.springframework.stereotype.Service;

public interface MailService {
     void sendSimpleMail(String to, String subject, String content);

     void sendHtmlMail(String to, String subject, String content);

     void sendAttachmentsMail(String to, String subject, String content, String filePath);

     void sendModelMail(String to, String subject, String contect,String fileName,Object model);
}
