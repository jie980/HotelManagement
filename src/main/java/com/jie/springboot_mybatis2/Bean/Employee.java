package com.jie.springboot_mybatis2.Bean;

import java.util.Date;

public class Employee {
    private Integer eid;
    private String email;
    private String ename;
    private Integer gender;
    private Integer did;
    private Department adepartment;

    public Department getDepartment() {
        return adepartment;
    }

    public void setDepartment(Department adepartment) {
        this.adepartment = adepartment;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eid=" + eid +
                ", email='" + email + '\'' +
                ", ename='" + ename + '\'' +
                ", gender=" + gender +
                ", did=" + did +
                ", department=" + adepartment +
                '}';
    }


}
