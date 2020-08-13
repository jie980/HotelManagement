package com.jie.springboot_mybatis2.Bean;

public class RoomReservation {
    Integer rid;
    Integer reid;
    public RoomReservation(){}
    public RoomReservation(Integer rid, Integer reid) {
        this.rid = rid;
        this.reid = reid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getReid() {
        return reid;
    }

    public void setReid(Integer reid) {
        this.reid = reid;
    }
}
