package com.jie.springboot_mybatis2.Mapper;

import com.jie.springboot_mybatis2.Bean.Room;
import com.jie.springboot_mybatis2.Bean.RoomReservation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoomReservationMapper {
    @Insert("INSERT INTO RoomReservation VALUES (#{rid},#{reid})")
    void addRoomReservation(RoomReservation roomReservation);
    @Delete("DELETE FROM RoomReservation WHERE reid=#{reid}")
    void deleteRoomReservation(Integer reid);
    @Select("SELECT * FROM RoomReservation WHERE reid=#{reid}")
    List<RoomReservation> getRRbyReid(Integer reid);

    @Select("SELECT * FROM RoomReservation WHERE rid=#{rid}")
    List<RoomReservation> getRRbyRid(Integer rid);
}
