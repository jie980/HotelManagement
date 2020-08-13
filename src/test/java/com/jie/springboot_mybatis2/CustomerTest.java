package com.jie.springboot_mybatis2;

import com.jie.springboot_mybatis2.Bean.Customer;
import com.jie.springboot_mybatis2.Bean.Reservation;
import com.jie.springboot_mybatis2.Mapper.CustomerMapper;
import com.jie.springboot_mybatis2.Mapper.ReservationMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
public class CustomerTest {
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    ReservationMapper reservationMapper;
    @Test
    public void testgetall(){
        List<Customer> cs = customerMapper.getAllIncludeReservation();
        for(Customer c:cs){
            System.out.println(c.getPhoneNum());
        }
    }
    @Test
    public void testgetCustomer(){
        Integer cid=1;
        Customer c = customerMapper.getCustomerIncludeReservation(cid);
        System.out.println(c.getCname());
        System.out.println(c.getPhoneNum());
        System.out.println(c.getEmail());

    }
    @Test
    public void testeditCustomer(){
        Customer newc = new Customer();
        newc.setCname("qi");
        newc.setEmail("qi@gmai.com");
        newc.setPhoneNum(13122339831L);

        customerMapper.editCustomer(newc);
    }
    @Test
    public void testphoneRegExp(){
        Long lphone = 19111888560L;
        String phone = lphone.toString();
        System.out.println(phone);
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        if(phone.length() != 11){
            System.out.println("手机号应为11位数");
        }else{
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if(isMatch){
                System.out.println("您的手机号" + phone + "是正确格式");
            } else {
                System.out.println("您的手机号" + phone + "是错误格式");
            }
        }
    }

}
