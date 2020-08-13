package com.jie.springboot_mybatis2.Service;

import com.jie.springboot_mybatis2.Bean.Administrator;
import com.jie.springboot_mybatis2.Mapper.AdminMappper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminMappper adminMappper;
    //0 email被使用
    //1 用户名被使用
    //2 成功
    public int insertadmin(Administrator administrator) throws NoSuchAlgorithmException {
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
                return 0;
            }
            //如果用户名已经被使用
            else if(name.equals(admin.getUsername())){

                return 1;
            }

        }
        adminMappper.InsertAdmin(administrator);
        return 2;
    }
    public List<Administrator> getAll(){
        return adminMappper.getAll();
    }
    public Administrator getbyaid(Integer aid){
        return adminMappper.getAdmin(aid);
    }
}
