package com.jie.springboot_mybatis2;

import com.jie.springboot_mybatis2.Bean.Department;
import com.jie.springboot_mybatis2.Bean.Employee;
import com.jie.springboot_mybatis2.Mapper.DepartmentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DepartmentTest {
    @Autowired
    DepartmentMapper departmentMapper;
    @Test
    void testgetAll(){
        List<Department> dpts = departmentMapper.getAll();
        for(Department d:dpts){
            System.out.println(d);
        }
    }

    @Test
    void testgetAllIncludEmps(){
        List<Department> dpts = departmentMapper.getAllIncludeEmps();
        for(Department d:dpts){
            System.out.println(d);
            System.out.println(d.getEmps());
        }
    }
    @Test
    void testgetDepartmentIncludEmps(){
        Department department = departmentMapper.getDepartmentIncludeEmps(1);
        System.out.println(department);
        System.out.println(department.getEmps());

    }


}
