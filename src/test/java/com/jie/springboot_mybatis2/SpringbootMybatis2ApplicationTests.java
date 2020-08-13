package com.jie.springboot_mybatis2;

import com.jie.springboot_mybatis2.Bean.Employee;
import com.jie.springboot_mybatis2.Bean.QueryVo;
import com.jie.springboot_mybatis2.Mapper.DepartmentMapper;
import com.jie.springboot_mybatis2.Mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SpringbootMybatis2ApplicationTests {

    @Autowired
    DataSource dataSource;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    DepartmentMapper departmentMapper;
    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();

        System.out.println(connection);
        connection.close();
    }
    @Test
    void testEmpgetBycondition(){
        Employee emp = new Employee();
        emp.setEname("li");
        List<Employee> emps = employeeMapper.getEmpByCondition(emp);
        for(Employee e:emps){
            System.out.println(e.toString());
        }
    }
    @Test
    void testEmpgetBycondition2(){
        Employee emp = new Employee();
        emp.setGender(1);
        List<Employee> emps = employeeMapper.getEmpByCondition(emp);
        for(Employee e:emps){
            System.out.println(e.toString());
        }
    }
    @Test
    void testEmpforeach(){
        QueryVo vo =new QueryVo();
        List<Integer> alist = new ArrayList<>();
        alist.add(1);
        alist.add(16);
        vo.setIdlist(alist);
        List<Employee> emps = employeeMapper.findEmpInIdlist(vo);
        for(Employee e:emps){
            System.out.println(e.toString());
        }
    }
    @Test
    void testgetAllincludeDpt(){
        List<Employee> emps = employeeMapper.getAllIncludeDepartment();
        for(Employee e:emps){
            System.out.println(e);
        }
    }
    @Test
    void testgetAll(){
        List<Employee> emps = employeeMapper.getAll();
        for(Employee e:emps){
            System.out.println(e);
        }
    }
}
