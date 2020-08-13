package com.jie.springboot_mybatis2.Mapper;

import com.jie.springboot_mybatis2.Bean.Department;
import org.apache.ibatis.annotations.*;

import javax.swing.*;
import java.util.Collection;
import java.util.List;

//i.e. DAO
@Mapper
public interface DepartmentMapper {
    @Select("SELECT * FROM Department WHERE did=#{did}")
    public Department getDepartment(Integer did);

    @Select("SELECT * FROM Department ORDER BY did")
    public List<Department> getAll();

    public List<Department> getAllIncludeEmps();

    public Department getDepartmentIncludeEmps(Integer did);


    @Insert("INSERT INTO Department(dname) VALUES (#{dname})")
    public void InsertDepartment(Department department);

    @Delete("DELETE FROM Department WHERE did=#{did}")
    public void deleteDepartment(Integer did);

    @Update("UPDATE Department SET dname=#{dname} WHERE did=#{did}")
    public void updateDepartment(Department department);




}
