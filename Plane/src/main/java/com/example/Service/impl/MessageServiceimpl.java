package com.example.Service.impl;

import com.example.Service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageServiceimpl implements MessageService {
    private static final Logger log = LoggerFactory.getLogger(MessageServiceimpl.class);
    @Resource
    JavaMailSender javaMailSender;

    //读取配置文件中username的值并赋值给setPeople
    @Value("${spring.mail.username}")
    String setPeople;



    public boolean sendMail(String code, String getPeople) {
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件发送者
        message.setFrom(setPeople);
        // 设置邮件接收者
        message.setTo(getPeople);
        // 设置邮件的主题
        message.setSubject("登录验证码");

        // 设置邮件的正文内容
        String text = "您的验证码为：" + code + ",60秒之后失效,请勿泄露给他人。";
        message.setText(text);
        // 发送邮件
        try {
            javaMailSender.send(message);
            return true;
        } catch (MailException e) {
           log.error("邮件发送失败");
        }
        return false;
    }
}
