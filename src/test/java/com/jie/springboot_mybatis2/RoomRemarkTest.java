package com.jie.springboot_mybatis2;

import com.jie.springboot_mybatis2.Bean.RoomRemark;
import com.jie.springboot_mybatis2.Mapper.RoomRemarkMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RoomRemarkTest {
    @Autowired
    RoomRemarkMapper roomRemarkMapper;
    @Test
    public void testinsert(){
        RoomRemark roomRemark = new RoomRemark();
        roomRemark.setTopic("马桶坏了");
        roomRemark.setContentDetail("如上");
        roomRemark.setRid(25);
        roomRemarkMapper.insertRemark(roomRemark);
    }
    @Test
    public void getAll(){
        List<RoomRemark> l =  roomRemarkMapper.getAll();
        System.out.println(l);

    }
}
