package com.jie.springboot_mybatis2.Bean;

import java.util.List;

public class QueryVo {
    private Employee employee;
    private List<Integer> idlist; //用于存放emp的idlist

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Integer> getIdlist() {
        return idlist;
    }

    public void setIdlist(List<Integer> idlist) {
        this.idlist = idlist;
    }
}
