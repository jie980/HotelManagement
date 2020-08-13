package com.jie.springboot_mybatis2.Mapper;

import com.jie.springboot_mybatis2.Bean.Reservation;
import com.jie.springboot_mybatis2.Bean.Room;
import com.jie.springboot_mybatis2.Bean.RoomReservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;
@Mapper
public interface ReservationMapper {
    @Select("SELECT * FROM Reservation ORDER BY startDate,reid")
    List<Reservation> getAll();


    List<Reservation> getAllIncludeRoom();
    List<Reservation> getAllIncludeRoomCustomer();

    Reservation getReservationIncludeRoom(Integer reid);

    Reservation getReservationIncludeRoomCustomer(Integer reid);
    List<Map> countRoomUnderReservations();


    int addReservation(Reservation reservation);

    List<Room> searchAvailableRoom(Date startDate, Date endDate);
//
    void editReservation(Reservation reservation);
//

    void deleteReservation(Integer reid);
}
