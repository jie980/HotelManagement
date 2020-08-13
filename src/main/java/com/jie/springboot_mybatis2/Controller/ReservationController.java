package com.jie.springboot_mybatis2.Controller;

import com.jie.springboot_mybatis2.Bean.Customer;
import com.jie.springboot_mybatis2.Bean.Reservation;
import com.jie.springboot_mybatis2.Bean.Room;
import com.jie.springboot_mybatis2.Bean.RoomReservation;
import com.jie.springboot_mybatis2.Mapper.CustomerMapper;
import com.jie.springboot_mybatis2.Mapper.ReservationMapper;
import com.jie.springboot_mybatis2.Mapper.RoomMapper;
import com.jie.springboot_mybatis2.Mapper.RoomReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Past;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ReservationController {
    @Autowired
    ReservationMapper reservationMapper;
    @Autowired
    RoomMapper roomMapper;
    @Autowired
    RoomReservationMapper roomReservationMapper;
    @Autowired
    CustomerMapper customerMapper;
    //显示所有订单
    @GetMapping("reservation")
    public String getAll(Model model){
        List<Reservation> reservations = reservationMapper.getAllIncludeRoomCustomer();
        model.addAttribute("res",reservations);
        if(reservations.isEmpty()){
            return "reservation/reservations";
        }
        //求出预定id号和所预定的房间数
        List<Map> reservation_roomNum= reservationMapper.countRoomUnderReservations();
        model.addAttribute("reservation_roomNum",reservation_roomNum);

        return "reservation/reservations";
    }


    //显示订单详细信息
    @GetMapping("reservation/detail")
    public String getReservation(@RequestParam Integer reid, Model model){
        //包含rid,和房间类型和对应订单号
        Reservation reservation = reservationMapper.getReservationIncludeRoomCustomer(reid);;
        if(reservation==null){
            model.addAttribute("error","该Reservation ID不存在！");
            return "reservation/reservations";
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String sdate = df.format(reservation.getStartDate());
        String edate = df.format(reservation.getEndDate());
        model.addAttribute("reservation",reservation);
        model.addAttribute("sdate",sdate);
        model.addAttribute("edate",edate);

        return "reservation/reservationDetail";
    }
    //第一个form，用于提交日期和房间数
    @GetMapping("reservation/add")
    public String addReservation(Model model) {
        //DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");


        return "reservation/addreservation";
    }
    //第二个form，获取上个form日期房间数，并提交所有预定信息
    @RequestMapping("reservation/adding")
    public String addingReservation(Model model,
                                    @RequestParam Date startDate,
                                    @RequestParam Date endDate,
                                    @RequestParam int roomNum, HttpServletRequest httpServletRequest){

        //如果为get请求，则表明是redirect过来的，需要提示用户错误
        if (httpServletRequest.getMethod().equals("GET")){
            model.addAttribute("error","请不要选择相同房间!");
        }

        int dateflag=  endDate.compareTo(startDate);
        System.out.println(dateflag);
        //错误，如果结束日期小于等于开始日期
        //错误，如果房间数小于1
        if (dateflag<=0 || roomNum<1){
            model.addAttribute("error2","结束日期要大于开始日期并且至少预定一间房!");
            //返回到初始页面
            return "reservation/addreservation";
        }
        //求所有当前日期可用房间
        List<Room> rooms = reservationMapper.searchAvailableRoom(startDate,endDate);
        //错误，如果想预定房间数大于剩余房间数
        if(rooms.size()<roomNum){
            model.addAttribute("error","非常抱歉，当前日期剩余房间不足。");
            return "reservation/addreservation";
        }



        model.addAttribute("customers",customerMapper.getAllIncludeReservation());
        model.addAttribute("startDate",startDate);
        model.addAttribute("endDate",endDate);
        model.addAttribute("roomNum",roomNum);
        model.addAttribute("availableRooms",rooms);
        return "reservation/addreservation2";
    }
    //创建订单
    @PostMapping("reservation/added")
    public String addedReservation(@RequestParam Date startDate, @RequestParam Date endDate,
                                   @RequestParam List<Integer> selectedroom,
                                   @RequestParam Integer mycustomer,
                                   RedirectAttributes redirectAttributes){

        long count = selectedroom.stream().distinct().count();
        //错误，有选择重复房间
        if(count<selectedroom.size()){
            redirectAttributes.addAttribute("startDate",startDate);
            redirectAttributes.addAttribute("endDate",endDate);
            redirectAttributes.addAttribute("roomNum",selectedroom.size());
            //创建失败
            return "redirect:/reservation/adding";
        }
        //无重复房间创建reservation
        Reservation reservation =new Reservation(startDate,endDate,"未入住",mycustomer);
        reservationMapper.addReservation(reservation);
        Integer reid =reservation.getReid();
        //创建room-reservation关系表到数据库
        for(Integer r:selectedroom){
            roomReservationMapper.addRoomReservation(new RoomReservation(r,reid));
        }
        return "redirect:/reservation";
    }

    @GetMapping("reservation/deleted/{reid}")
    public String deletedReservation(@PathVariable int reid){
        //删除房间与订单关联
        roomReservationMapper.deleteRoomReservation(reid);
        //删除订单
        reservationMapper.deleteReservation(reid);
        return "redirect:/reservation";
    }
    @GetMapping("reservation/edit/{reid}")
    //修改房间的表单
    public String editReservation(@PathVariable int reid,Model model){
        Reservation re = reservationMapper.getReservationIncludeRoom(reid);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String sdate = df.format(re.getStartDate());
        String edate = df.format(re.getEndDate());
        model.addAttribute("reid",reid);
        model.addAttribute("startDate",sdate);
        model.addAttribute("endDate",edate);
        model.addAttribute("reservationStatus",re.getReservationStatus());
        model.addAttribute("cid",re.getCid());
        return "reservation/editreservation";
    }
    @PostMapping("reservation/edited")
    //修改了房间后台信息处理
    public String editedReservationStatus(Reservation reservation){
        reservationMapper.editReservation(reservation);
        //当订单进入入住状态，修改其房间的状态
        if(reservation.getReservationStatus().equals("已入住")){
            //获取该订单号下所有房间
            List<RoomReservation> rrs = roomReservationMapper.getRRbyReid(reservation.getReid());
            for (RoomReservation rr:rrs){
                //修改订单下房间状态
                Room room = roomMapper.getroom(rr.getRid());
                room.setRoomStatus("OCC");
                roomMapper.editroom(room);
            }
        }
        //当订单进入退房状态，修改房间状态
        else if(reservation.getReservationStatus().equals("已退房")||
                reservation.getReservationStatus().equals("未入住")){
            //获取该订单号下所有房间
            List<RoomReservation> rrs = roomReservationMapper.getRRbyReid(reservation.getReid());
            for (RoomReservation rr:rrs){
                //修改订单下房间状态
                Room room = roomMapper.getroom(rr.getRid());
                room.setRoomStatus("V");
                roomMapper.editroom(room);
            }
        }
        return "redirect:/reservation/detail/?reid="+reservation.getReid();

    }



}

