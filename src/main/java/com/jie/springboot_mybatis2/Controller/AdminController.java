package com.jie.springboot_mybatis2.Controller;

import com.jie.springboot_mybatis2.Bean.Administrator;
import com.jie.springboot_mybatis2.Mapper.AdminMappper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


@Controller
public class AdminController {
    @Autowired
    AdminMappper adminMappper;


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
        List<Administrator> ulist = adminMappper.getAll();
        //查询所有user
        for(Administrator u:ulist){
            //如果用户名相同
            if(uname.equals(u.getUsername())){
                //密码相同验证成功
                if(encodepwd.equals(u.getPwd())){
                    session.setAttribute("user",u);
                    String pic = u.getProfilepic();
                    return "redirect:/dpt";
                }
                else{
                    model.addAttribute("error","Password is not correct");
                    return "login";
                }
            }
            else{
                System.out.println("=========debug added?");
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
        String name = administrator.getUsername();
        String email = administrator.getEmail();
        String pwd = administrator.getPwd();

        //加密密码储存
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pwd.getBytes());
        BigInteger digest = new BigInteger(md.digest());
        String encodepwd = digest.toString();
        System.out.println(encodepwd);
        administrator.setPwd(encodepwd);
        administrator.setProfilepic("default.JPG");
        List<Administrator> admins= adminMappper.getAll();
        for(Administrator admin:admins){
            //如果email已经被使用
            if(email.equals(admin.getEmail())){
                model.addAttribute("emailError","This email has already been used!");
                return "signup";
            }
            //如果用户名已经被使用
            else if(name.equals(admin.getUsername())){
                model.addAttribute("nameError","This username has already been used");
                return "signup";
            }


        }
        model.addAttribute("success","Created! You can login now!");
        adminMappper.InsertAdmin(administrator);
        return "login";
    }
    @GetMapping("/userinfo")
    public String personalInfo(){
        return "admin/info";
    }
}
