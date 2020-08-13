package com.jie.springboot_mybatis2.Service;

import com.jie.springboot_mybatis2.Bean.Room;
import com.jie.springboot_mybatis2.Bean.RoomRemark;
import com.jie.springboot_mybatis2.Mapper.RoomRemarkMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomRemarkService {
    @Autowired
    RoomRemarkMapper roomRemarkMapper;
    public List<RoomRemark> getAll(){
        return roomRemarkMapper.getAll();
    }
    public RoomRemark getByRrid(Integer rrid){
        return roomRemarkMapper.getByRrid(rrid);
    }
    public List<RoomRemark> getByRid(Integer rid){
        return roomRemarkMapper.getByRid(rid);
    }
    public boolean insert(RoomRemark roomRemark){
        try{
            roomRemarkMapper.insertRemark(roomRemark);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public void delete(Integer rrid){
        roomRemarkMapper.deleteRemark(rrid);
    }
}
