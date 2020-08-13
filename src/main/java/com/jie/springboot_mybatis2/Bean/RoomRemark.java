package com.jie.springboot_mybatis2.Bean;

import java.util.Date;

public class RoomRemark {
    private Integer rrid;
    private String topic;
    private String contentDetail;
    private Integer rid;
    private Date createDate;

    private Room room;

    public RoomRemark(String topic, String contentDetail, Integer rid) {
        this.topic = topic;
        this.contentDetail = contentDetail;
        this.rid = rid;
    }

    public RoomRemark() {
    }

    public Integer getRrid() {
        return rrid;
    }

    public void setRrid(Integer rrid) {
        this.rrid = rrid;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContentDetail() {
        return contentDetail;
    }

    public void setContentDetail(String contentDetail) {
        this.contentDetail = contentDetail;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "RoomRemark{" +
                "rrid=" + rrid +
                ", topic='" + topic + '\'' +
                ", contentDetail='" + contentDetail + '\'' +
                ", rid=" + rid +
                ", createDate=" + createDate +
                ", room=" + room +
                '}';
    }
}
