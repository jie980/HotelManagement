package com.jie.springboot_mybatis2.Bean;

import java.util.List;

public class Room {
    private Integer rid;
    private String rtype;
    private String roomStatus;
    private Integer roomNumber;
    //多对多关系映射
    private List<Reservation> reservations;
    //多对一关系映射
    private List<RoomRemark> roomRemarks;

    public List<RoomRemark> getRoomRemarks() {
        return roomRemarks;
    }

    public void setRoomRemarks(List<RoomRemark> roomRemarks) {
        this.roomRemarks = roomRemarks;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRtype() {
        return rtype;
    }

    public void setRtype(String rtype) {
        this.rtype = rtype;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "Room{" +
                "rid=" + rid +
                ", rtype='" + rtype + '\'' +
                ", roomStatus='" + roomStatus + '\'' +
                ", roomNumber=" + roomNumber +
                ", reservations=" + reservations +
                ", roomRemarks=" + roomRemarks +
                '}';
    }
}

