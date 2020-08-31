package com.jie.springboot_mybatis2.Controller;

import com.jie.springboot_mybatis2.Bean.Administrator;
import com.jie.springboot_mybatis2.Mapper.AdminMappper;
import com.jie.springboot_mybatis2.Service.AdminService;
import com.jie.springboot_mybatis2.Tool.RandomStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


@Controller
public class AdminController {

    @Autowired
    JavaMailSenderImpl mailSender;
    @Autowired
    AdminService adminService;



    @GetMapping({"/login","/"})
    public String login(){
        return "login";
    }
    @PostMapping("/logined") //fixed 加密密码
    public String logined(Administrator administrator, Model model, HttpSession session) throws NoSuchAlgorithmException {
        String uname = administrator.getUsername();
        String pwd = administrator.getPwd();
        //加密密码对比
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pwd.getBytes());
        BigInteger digest = new BigInteger(md.digest());
        String encodepwd = digest.toString();

        System.out.println(uname);
        System.out.println(encodepwd);
        List<Administrator> ulist = adminService.getAll();
        //查询所有user
        for(Administrator u:ulist){
            //如果用户名相同
            if(uname.equals(u.getUsername())){
                //密码相同验证成功
                if(encodepwd.equals(u.getPwd())){
                    session.setAttribute("user",u);
                    String pic = u.getProfilepic();
                    return "redirect:/userinfo";
                    //return "admin/info";
                }
                else{
                    model.addAttribute("error","Password is not correct");
                    return "login";
                }
            }
            else{
                //System.out.println("=========debug added?");
                model.addAttribute("error","This username does not exist");
            }
        }
        return "login";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){

        session.removeAttribute("user");

        return "redirect:/login";
    }
    @RequestMapping("/sessionout")
    public String sessionout(){
        return "sessionout";
    }
    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @RequestMapping("/signedup")
    public String signedup(Administrator administrator,Model model) throws NoSuchAlgorithmException {
        int state = adminService.insertadmin(administrator);
        if (state==0){
            model.addAttribute("emailError","This email has already been used!");
            return "signup";
        }
        else if(state==1) {
            model.addAttribute("nameError", "This username has already been used");
            return "signup";
        }
        else{
            model.addAttribute("success","Created! You can login now!");
            return "login";
        }

    }
    @GetMapping("/userinfo")
    public String personalInfo(){
        return "admin/info";
    }

//    @Async
//    @PostMapping("/sendmail")
//    public String sendmail(){
//        String code = RandomStringUtil.getRandString(6);
//        sendmailtool("Verification code","Your verification code is "+code,
//                email);
//        return null;
//    }
//
//    public boolean sendmailtool(String subject, String content, String tomail){
//        try{
//            SimpleMailMessage mailMessage= new SimpleMailMessage();
//            mailMessage.setSubject(subject);
//            mailMessage.setText(content);
//            mailMessage.setFrom("1343688691@qq.com");
//            mailMessage.setTo(tomail);
//            mailSender.send(mailMessage);
//            return true;
//        }catch (Exception e){
//            return false;
//        }
//    }
}
