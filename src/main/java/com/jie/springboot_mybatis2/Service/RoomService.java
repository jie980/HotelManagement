package com.jie.springboot_mybatis2.Service;

import com.jie.springboot_mybatis2.Bean.Room;
import com.jie.springboot_mybatis2.Mapper.RoomMapper;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoomService {
    @Autowired
    RoomMapper roomMapper;

    //0 room+对应预定
    //1 room+对应remark
    //其余数字 只有room
    public List<Room> getAll(int state){
        if(state==0){
            return roomMapper.getAllIncludeReservation();
        }
        else if(state==1){
            return roomMapper.getAllIncludeRemark();
        }
        else{
            return roomMapper.getAll();
        }
    }
    public List<Map> countAvailableRoom(){
        return roomMapper.countAvailableRoom();
    }
    //0 room+对应预定
    //1 room+对应remark
    //其余数字 只有room
    public Room getroom(Integer rid, Integer state){
        if (state==0){
            return roomMapper.getRoomIncludeReservation(rid);
        }
        else if(state==1){
            return roomMapper.getRoomIncludeRemark(rid);
        }
        return roomMapper.getroom(rid);
    }
    public Room getroomByroomNumber(Integer roomnumber){
        Room r =  roomMapper.getroomByroomNumber(roomnumber);
        if(r==null){
            return null;
        }
        return r;
    }

    public void editroom(Room room){
        roomMapper.editroom(room);
    }
    public void addroom(Room room){
        roomMapper.addroom(room);
    }
    public void deleteroom(Integer rid){
        roomMapper.deleteroom(rid);
    }
    public List<Room> getVroombyType(String type){
        return roomMapper.getVroombyType(type);
    }
}
