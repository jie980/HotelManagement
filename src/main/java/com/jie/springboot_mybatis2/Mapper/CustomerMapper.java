package com.jie.springboot_mybatis2.Mapper;

import com.jie.springboot_mybatis2.Bean.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {
    List<Customer> getAllIncludeReservation();

    Customer getCustomerIncludeReservation(Integer cid);

    void addCustomer(Customer customer);

    void deleteCustomer(Long phoneNum);

    void editCustomer(Customer customer);

}
