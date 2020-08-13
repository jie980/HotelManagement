package com.jie.springboot_mybatis2.Mapper;

import com.jie.springboot_mybatis2.Bean.Room;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoomMapper {

    List<Room> getAll();
    List<Room> getAllIncludeReservation();
    List<Room> getAllIncludeRemark();
    Room getRoomIncludeRemark(Integer rid);
    Room getRoomIncludeReservation(Integer rid);

    @Select("SELECT * FROM Room WHERE roomnumber=#{roomNumber}")
    Room getroomByroomNumber(Integer roomNumber);


    @Select("SELECT * FROM Room WHERE rid=#{rid}")
    Room getroom(Integer rid);

    @Insert("INSERT INTO Room(rtype,roomStatus,roomNumber) VALUES (#{rtype},#{roomStatus},#{roomNumber})")
    void addroom(Room room);

    @Delete("DELETE FROM Room WHERE rid=#{rid}")
    void deleteroom(Integer rid);

    @Update("UPDATE Room SET rtype=#{rtype}, roomstatus=#{roomStatus}, roomNumber=#{roomNumber} WHERE rid=#{rid}")
    void editroom(Room room);

    List<Map> countAvailableRoom();

    List<Room> getVroombyType(String rtype);

}
