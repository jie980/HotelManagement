package com.jie.springboot_mybatis2.Bean;

import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Reservation {
    private Integer reid;
    @Past
    private Date startDate;
    @Past
    private Date endDate;
    private String reservationStatus;
    private Integer cid;
    //一对多映射
    private Customer customer;
    //多对多映射
    private List<Room> rooms;

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public Reservation() {
    }

    public Reservation(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public Reservation(Date startDate, Date endDate,String reservationStatus,Integer cid) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.reservationStatus = reservationStatus;
        this.cid=cid;
    }

    public Integer getReid() {
        return reid;
    }

    public void setReid(Integer reid) {
        this.reid = reid;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reid=" + reid +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", reservationStatus='" + reservationStatus + '\'' +
                ", cid=" + cid +
                ", customer=" + customer +
                ", rooms=" + rooms +
                '}';
    }
}
