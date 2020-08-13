package com.jie.springboot_mybatis2.Controller;

import com.jie.springboot_mybatis2.Bean.Customer;
import com.jie.springboot_mybatis2.Mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class CustomerController {
    @Autowired
    CustomerMapper customerMapper;
    @GetMapping("/customer")
    public String showCustomers(Model model){
        List<Customer> l = customerMapper.getAllIncludeReservation();
        model.addAttribute("customers",l);
        return "customer/customers";
    }
    @GetMapping("/customer/{cid}")
    public String showdetail(Model model,@PathVariable Integer cid){

        Customer c = customerMapper.getCustomerIncludeReservation(cid);
        System.out.println(c);
        model.addAttribute("customer",c);
        return "customer/detail";
    }

    @GetMapping("/customer/add")
    public String addCustomer(Model model){
        return "customer/addCustomer";
    }
    @PostMapping("/customer/added")
    public String addedCustomer(Model model,Customer customer){
        //验证手机号是否正确
        Long lphone = customer.getPhoneNum();
        String phone = lphone.toString();
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        if(phone.length() != 11){
            model.addAttribute("error","手机号应为11位数");
            return "customer/addCustomer";
        }else{
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if(!isMatch){
                model.addAttribute("error","该手机号格式错误！");
                return "customer/addCustomer";
            }
        }
        //手机号验证正确
        List<Customer> customers = customerMapper.getAllIncludeReservation();
        //不能添加重复电话,和邮箱
        for(Customer c:customers){
            if(c.getPhoneNum().equals(customer.getPhoneNum())){
                model.addAttribute("error","该电话号码已存在对应顾客!");
                return "customer/addCustomer";
            }
            else if(c.getEmail().equals(customer.getEmail())){
                model.addAttribute("error","该邮箱已存在对应顾客!");
                return "customer/addCustomer";
            }
        }
        customerMapper.addCustomer(customer);
        return "redirect:/customer";
    }
    @GetMapping("/customer/edit/{cid}")
    public String editCustomer(Model model,@PathVariable Integer cid){
        Customer c = customerMapper.getCustomerIncludeReservation(cid);
        model.addAttribute("customer",c);
        return "customer/editCustomer";
    }
    @PostMapping("/customer/edited")
    public String editedCustomer(Model model, Customer customer){
        customerMapper.editCustomer(customer);
        return "redirect:/customer";
    }
}
