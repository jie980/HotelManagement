package com.jie.springboot_mybatis2;


import org.assertj.core.error.ShouldBeAfterYear;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootTest
public class MailTest {
    @Autowired
    JavaMailSenderImpl mailSender;

    public boolean sendmail(String subject, String content, String tomail){
        try{
            SimpleMailMessage mailMessage= new SimpleMailMessage();
            mailMessage.setSubject(subject);
            mailMessage.setText(content);
            mailMessage.setFrom("1343688691@qq.com");
            mailMessage.setTo(tomail);
            mailSender.send(mailMessage);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @Test
    public void testsendmail(){
        String a = RandomStringUtil.getRandString(6);
        Boolean b = sendmail("Verification Code","Your verification code is "+a,"1343688691@qq.com");
        System.out.println(b);
    }

}
