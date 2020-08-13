package com.jie.springboot_mybatis2.Mapper;

import com.jie.springboot_mybatis2.Bean.Administrator;
import com.jie.springboot_mybatis2.Bean.Department;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface AdminMappper {
    @Select("SELECT * FROM Administrator WHERE aid=#{aid}")
    public Administrator getAdmin(Integer aid);
    @Select("SELECT * FROM Administrator")
    public List<Administrator> getAll();

    @Insert("INSERT INTO Administrator(username, email, pwd, profilepic) VALUES (#{username},#{email},#{pwd},#{profilepic})")
    public void InsertAdmin(Administrator administrator);

    @Update("UPDATE Administrator SET profilepic=#{profilepic} WHERE aid=#{aid}")
    public void updateAdmin(Administrator administrator);
}
