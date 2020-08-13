package com.jie.springboot_mybatis2.Controller;

import com.jie.springboot_mybatis2.Bean.Room;

import com.jie.springboot_mybatis2.Bean.RoomRemark;
import com.jie.springboot_mybatis2.Bean.RoomReservation;
import com.jie.springboot_mybatis2.Mapper.RoomMapper;
import com.jie.springboot_mybatis2.Mapper.RoomRemarkMapper;
import com.jie.springboot_mybatis2.Mapper.RoomReservationMapper;
import com.jie.springboot_mybatis2.Service.RoomRemarkService;
import com.jie.springboot_mybatis2.Service.RoomService;
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
    RoomService roomService;
    @Autowired
    RoomReservationMapper roomReservationMapper;
    @Autowired
    RoomRemarkService roomRemarkService;


    @GetMapping({"room","room/show"})
    public String showAll(Model model, @RequestParam(required = false,value = "error") String error){
        List<Room> rlist = roomService.getAll(1);
        List<Map> roomStat = roomService.countAvailableRoom();
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
        Room room = roomService.getroom(rid,3);
        String rstatus = room.getRoomStatus();
        System.out.println(rstatus);
        if( rstatus.equals("V")){
            room.setRoomStatus("OCC");
        }
        else{
            room.setRoomStatus("V");
        }
        roomService.editroom(room);
        return "redirect:/room/show";
    }
    @GetMapping("room/addform")
    public String addroom(Model model){
        return "room/addroom";
    }
    @PostMapping("room/added")
    public String addedroom(Model model, Room room){
        List<Room> rooms= roomService.getAll(2);
        for(Room r:rooms){
            if(r.getRoomNumber().equals(room.getRoomNumber())){
                model.addAttribute("error","房间号已存在!");
                return "room/addroom";
            }
        }
        roomService.addroom(room);
        return "redirect:/room/show";
    }
    @GetMapping("room/deleted/{rid}")
    public String deletedroom(@PathVariable Integer rid, Model model, RedirectAttributes redirectAttributes){
        List<RoomReservation> roomReservations= roomReservationMapper.getRRbyRid(rid);
        List<RoomRemark> roomRemarks = roomRemarkService.getByRid(rid);
        if(!roomReservations.isEmpty()){
            redirectAttributes.addAttribute("error","该房间还存在预定无法删除");

        }

        else{
            //先删除所有房间备注
            for(RoomRemark roomRemark:roomRemarks){
                roomRemarkService.delete(roomRemark.getRrid());
            }
            roomService.deleteroom(rid);
        }

        return "redirect:/room/show";
    }

    @GetMapping("room/{rtype}")
    public String showVacantRoomByType(@PathVariable String rtype, Model model){
        List<Room>  rooms = roomService.getVroombyType(rtype);
        model.addAttribute("rooms",rooms);
        //Map<房间类型，剩余数量>
        List<Map> roomStat = roomService.countAvailableRoom();
        model.addAttribute("roomStat",roomStat);
        return "room/rooms";

    }
    @GetMapping("room/detail")
    public String Roomdetail(@RequestParam(required = false) Integer roomNumber,
                             @RequestParam(required = false) Integer rid,
                             Model model){
        if(roomNumber!=null){
            Room room = roomService.getroomByroomNumber(roomNumber);
            System.out.println(room);
            if(room==null){
                model.addAttribute("error","该Room Number 不存在！");
                return "room/notExist";
            }
            rid = room.getRid();

        }
        Room room = roomService.getroom(rid,0);
        Room moreinfo = roomService.getroom(rid,1);
        if(room==null){
            model.addAttribute("error","该Room Number 不存在！");
            return "room/notExist";
        }


        model.addAttribute("room",room);
        model.addAttribute("moreinfo",moreinfo);
        List<Map> roomStat = roomService.countAvailableRoom();
        model.addAttribute("roomStat",roomStat);
        return "room/detail";
    }

}
