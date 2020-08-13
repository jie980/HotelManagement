package com.jie.springboot_mybatis2.Controller;

import com.jie.springboot_mybatis2.Bean.Administrator;
import com.jie.springboot_mybatis2.Mapper.AdminMappper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class FileController {
    @Autowired
    AdminMappper adminMappper;
    @PostMapping("/fileUpload")
    public String upload(@RequestParam MultipartFile file, HttpSession session) throws IOException {
        if (file.isEmpty()) {
            System.out.println("文件为空空");
            return "redirect:/userinfo";
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = "/Users/jie/IdeaProjects/springboot_mybatis2/src/main/resources/static/profilepic/"; // 上传后的路
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);//上传全路径
        //如果文件夹不存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        //上传图片到路径中
        file.transferTo(dest);
        Administrator admin = (Administrator) session.getAttribute("user");
        //数据库中更改用户信息
        admin.setProfilepic(fileName);
        adminMappper.updateAdmin(admin);
        //移除老的信息
        session.removeAttribute("user");
        //更新成新的信息
        session.setAttribute("user",admin);

        System.out.println("路径为："+admin.getProfilepic());
        return "redirect:/userinfo";
    }
}
