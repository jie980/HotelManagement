package com.jie.springboot_mybatis2.Bean;

import java.util.List;

public class Customer {
    private Integer cid;
    private Long phoneNum;
    private String cname;
    private String email;

    //多对一映射
    private List<Reservation> reservations;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Long getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Long phoneNum) {
        this.phoneNum = phoneNum;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "phoneNum=" + phoneNum +
                ", cname='" + cname + '\'' +
                ", email='" + email + '\'' +
                ", reservations=" + reservations +
                '}';
    }
}
