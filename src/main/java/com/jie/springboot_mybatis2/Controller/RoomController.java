package com.jie.springboot_mybatis2.Controller;

import com.jie.springboot_mybatis2.Bean.Room;

import com.jie.springboot_mybatis2.Bean.RoomRemark;
import com.jie.springboot_mybatis2.Bean.RoomReservation;
import com.jie.springboot_mybatis2.Mapper.RoomMapper;
import com.jie.springboot_mybatis2.Mapper.RoomRemarkMapper;
import com.jie.springboot_mybatis2.Mapper.RoomReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class RoomController {
    @Autowired
    RoomMapper roomMapper;
    @Autowired
    RoomReservationMapper roomReservationMapper;
    @Autowired
    RoomRemarkMapper roomRemarkMapper;


    @GetMapping({"room","room/show"})
    public String showAll(Model model, @RequestParam(required = false,value = "error") String error){
        List<Room> rlist = roomMapper.getAllIncludeRemark();
        List<Map> roomStat = roomMapper.countAvailableRoom();
//        for(Map r:remainroom){
//            System.out.println(r.get("rtype"));
//            System.out.println(r.get("count"));
//        }

//        System.out.println(rlist);
        model.addAttribute("error",error);
        model.addAttribute("roomStat",roomStat);
        model.addAttribute("rooms", rlist);
        return "room/rooms";
    }
    @GetMapping("room/edited/{rid}")
    public String editedroom(Model model, @PathVariable Integer rid){
        Room room = roomMapper.getroom(rid);
        String rstatus = room.getRoomStatus();
        System.out.println(rstatus);
        if( rstatus.equals("V")){
            room.setRoomStatus("OCC");
        }
        else{
            room.setRoomStatus("V");
        }
        roomMapper.editroom(room);
        return "redirect:/room/show";
    }
    @GetMapping("room/addform")
    public String addroom(Model model){
        return "room/addroom";
    }
    @PostMapping("room/added")
    public String addedroom(Model model, Room room){
        List<Room> rooms= roomMapper.getAll();
        for(Room r:rooms){
            if(r.getRoomNumber().equals(room.getRoomNumber())){
                model.addAttribute("error","房间号已存在!");
                return "room/addroom";
            }
        }
        roomMapper.addroom(room);
        return "redirect:/room/show";
    }
    @GetMapping("room/deleted/{rid}")
    public String deletedroom(@PathVariable Integer rid, Model model, RedirectAttributes redirectAttributes){
        List<RoomReservation> roomReservations= roomReservationMapper.getRRbyRid(rid);
        List<RoomRemark> roomRemarks = roomRemarkMapper.getByRid(rid);
        if(!roomReservations.isEmpty()){
            redirectAttributes.addAttribute("error","该房间还存在预定无法删除");

        }

        else{
            //先删除所有房间备注
            for(RoomRemark roomRemark:roomRemarks){
                roomRemarkMapper.deleteRemark(roomRemark.getRrid());
            }
            roomMapper.deleteroom(rid);}

        return "redirect:/room/show";
    }

    @GetMapping("room/{rtype}")
    public String showVacantRoomByType(@PathVariable String rtype, Model model){
        List<Room>  rooms = roomMapper.getVroombyType(rtype);
        model.addAttribute("rooms",rooms);
        //Map<房间类型，剩余数量>
        List<Map> roomStat = roomMapper.countAvailableRoom();
        model.addAttribute("roomStat",roomStat);
        return "room/rooms";

    }
    @GetMapping("room/detail")
    public String Roomdetail(@RequestParam(required = false) Integer roomNumber,
                             @RequestParam(required = false) Integer rid,
                             Model model){
        if(roomNumber!=null){
            Room room = roomMapper.getroomByroomNumber(roomNumber);
            if(room==null){
                model.addAttribute("error","该Room Number 不存在！");
                return "room/notExist";
            }
            rid = room.getRid();

        }
        Room room = roomMapper.getRoomIncludeReservation(rid);
        Room moreinfo = roomMapper.getRoomIncludeRemark(rid);
        if(room==null){
            model.addAttribute("error","该Room Number 不存在！");
            return "room/notExist";
        }


        model.addAttribute("room",room);
        model.addAttribute("moreinfo",moreinfo);
        List<Map> roomStat = roomMapper.countAvailableRoom();
        model.addAttribute("roomStat",roomStat);
        return "room/detail";
    }

}
