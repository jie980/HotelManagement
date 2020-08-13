package com.jie.springboot_mybatis2.Mapper;

import com.jie.springboot_mybatis2.Bean.Room;
import com.jie.springboot_mybatis2.Bean.RoomRemark;
import com.jie.springboot_mybatis2.Bean.RoomReservation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RoomRemarkMapper {
    List<RoomRemark> getAll();

    List<RoomRemark> getByRid(Integer rid);
    RoomRemark getByRrid(Integer rrid);
    void insertRemark(RoomRemark roomRemark);
    void updateRemark(RoomRemark roomRemark);
    void deleteRemark(Integer rrid);
}
