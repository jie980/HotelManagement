package com.jie.springboot_mybatis2.Mapper;

import com.jie.springboot_mybatis2.Bean.Employee;
import com.jie.springboot_mybatis2.Bean.QueryVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper

public interface EmployeeMapper {
   // @Select("SELECT * FROM Employee ORDER BY eid")
    List<Employee> getAll();

//    @Select("SELECT * FROM Employee WHERE eid=#{eid}")
    Employee getEmployee(Integer eid);

    //@Select("SELECT * FROM Employee WHERE did=#{did}")
    List<Employee> getEmployeeBydepartment(Integer did);

    //@Insert("INSERT INTO Employee(email,ename,gender,did) VALUES(#{email},#{ename},#{gender},#{did})")
    void insertEmployee(Employee employee);

    //@Update("UPDATE Employee SET email=#{email}, ename=#{ename}, gender=#{gender}, did=#{did} WHERE eid=#{eid}")
    void updateEmployee(Employee employee);
    //@Delete("DELETE FROM Employee WHERE eid=#{eid}")
    void deleteEmployee(Integer eid);

    List<Employee> getEmpByCondition(Employee employee);

    List<Employee> findEmpInIdlist(QueryVo vo);

    List<Employee> getAllIncludeDepartment();
}
