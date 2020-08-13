package com.jie.springboot_mybatis2.Controller;

import com.jie.springboot_mybatis2.Bean.RoomRemark;
import com.jie.springboot_mybatis2.Mapper.RoomMapper;
import com.jie.springboot_mybatis2.Mapper.RoomRemarkMapper;
import com.jie.springboot_mybatis2.Service.RoomRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
public class RoomRemarkController {
    @Autowired
    RoomRemarkService roomRemarkService;
    @Autowired
    RoomMapper roomMapper;
    @GetMapping("/room/{rid}/remark")
    public String remarkUnderRoom(@PathVariable Integer rid){
        List<RoomRemark> roomRemarks= roomRemarkService.getByRid(rid);
        return "roomremark/remarks";
    }
    @GetMapping("/room/{rid}/remark/add")
    public String addRemark(@PathVariable Integer rid, Model model){
        model.addAttribute("rid",rid);
        model.addAttribute("room",roomMapper.getroom(rid));
        return "roomremark/addremark";
    }
    @PostMapping("/remark/added")
    public String addedRemark(RoomRemark roomRemark){
        System.out.println(roomRemark);
        roomRemark.setCreateDate(new Date());
        Boolean flag= roomRemarkService.insert(roomRemark);
        if (!flag){//插入失败
            System.out.println("insert fail");
        }
        return "redirect:/room/detail/?rid="+roomRemark.getRid();
    }
    @GetMapping("/remark/deleted/{rrid}")
    public String deleteRemark(@PathVariable Integer rrid){
        RoomRemark r = roomRemarkService.getByRrid(rrid);
        roomRemarkService.delete(rrid);
        return "redirect:/room/detail/?rid="+r.getRid();
    }
}
