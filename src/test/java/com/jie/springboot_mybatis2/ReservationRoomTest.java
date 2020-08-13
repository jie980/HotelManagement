package com.jie.springboot_mybatis2;

import com.jie.springboot_mybatis2.Bean.Reservation;
import com.jie.springboot_mybatis2.Bean.Room;
import com.jie.springboot_mybatis2.Mapper.ReservationMapper;
import com.jie.springboot_mybatis2.Mapper.RoomMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class ReservationRoomTest {
    @Autowired
    ReservationMapper reservationMapper;
    @Autowired
    RoomMapper roomMapper;
    @Test
    public void testfindAllReservation(){
        List<Reservation> l = reservationMapper.getAll();
        for(Reservation r:l){
            System.out.println(r);

        }
    }
    @Test
    public void testfindAllRoom(){
        List<Room> l = roomMapper.getAll();
        for(Room r:l){
            System.out.println(r);

        }
    }
    @Test
    public void testfindAllReservationIncludeRoom(){
        List<Reservation> l = reservationMapper.getAllIncludeRoom();
        for(Reservation r:l){
            System.out.println(r);
            System.out.println(r.getRooms());

        }
    }
    @Test
    public void testfindALl(){
        List<Reservation> reservations = reservationMapper.getAllIncludeRoomCustomer();
        for(Reservation r:reservations){
            System.out.println(r);


        }
    }
    @Test
    public void testfindReservationIncludeRoom(){
        Reservation r = reservationMapper.getReservationIncludeRoom(4);
        System.out.println(r);
        System.out.println(r.getRooms());

    }
    @Test
    public void testfindReservationIncludeRoomCustomer(){
        Reservation r = reservationMapper.getReservationIncludeRoomCustomer(4);
        System.out.println(r);
        System.out.println(r.getRooms());
        System.out.println(r.getCustomer());

    }
    @Test
    public void testcountAvailableRoom(){
        List<Map> mps = roomMapper.countAvailableRoom();
        for(Map m:mps){
            System.out.println(m);
        }
    }

    @Test
    public void testgetAllIncludeReservation(){
        List<Room> l = roomMapper.getAllIncludeReservation();
        for(Room r:l){
            System.out.println(r);
            System.out.println(r.getReservations());

        }
    }
    @Test
    public void testgetRoomIncludeReservation(){
        Room r = roomMapper.getRoomIncludeReservation(5);
        System.out.println(r);
        System.out.println(r.getReservations());


    }
    @Test
    public void testgetRoomIncludeRemark(){
        Room r = roomMapper.getRoomIncludeRemark(25);
        Room r2 = roomMapper.getRoomIncludeRemark(5);

        System.out.println(r);
        System.out.println(r2.getRoomRemarks());
    }
    @Test
    public void testgetAllincludeRemarks(){
        List<Room> rs = roomMapper.getAllIncludeRemark();
        for(Room r:rs){
            System.out.println(r.getRoomRemarks().isEmpty());
        }
    }
}
