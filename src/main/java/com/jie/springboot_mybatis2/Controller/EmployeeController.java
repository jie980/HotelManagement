package com.jie.springboot_mybatis2.Controller;

import com.jie.springboot_mybatis2.Bean.Department;
import com.jie.springboot_mybatis2.Bean.Employee;
import com.jie.springboot_mybatis2.Mapper.DepartmentMapper;
import com.jie.springboot_mybatis2.Mapper.EmployeeMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    DepartmentMapper departmentMapper;
    @GetMapping({"/emp","/emp/show"})
    public String getAll(Model model){
        List<Employee> emps= employeeMapper.getAllIncludeDepartment();

//
//        for(int i=0;i<emps.size();i++){
//            Integer did = emps.get(i).getDid();
//            Department department = departmentMapper.getDepartment(did);
//            dpts.add(department);
//        }

        model.addAttribute("emps",emps);
//        model.addAttribute("dpts",dpts);
        return "employee/emp";
    }

    @GetMapping("/emp/add")
    public String addEmp(Model model){
        model.addAttribute("departments",departmentMapper.getAll());
        return "employee/addemp";
    }
    @PostMapping("/emp/added")
    public String addedEmp(Model model,Employee employee){
        employeeMapper.insertEmployee(employee);
        return "redirect:/emp/show";
    }
    @GetMapping("/emp/edit/{eid}")
    public String editEmp(Model model,@PathVariable Integer eid){
        Employee employee= employeeMapper.getEmployee(eid);
        model.addAttribute("emp",employee);
        model.addAttribute("departments",departmentMapper.getAll());
        return "employee/editemp";
    }
    @PostMapping("/emp/edited")
    public String editedEmp(Employee employee){
        System.out.println(employee.getDid());
        employeeMapper.updateEmployee(employee);

        return "redirect:/emp/show";
    }
    @GetMapping("emp/deleted/{eid}")
    public String deletedEmp(@PathVariable Integer eid){
        employeeMapper.deleteEmployee(eid);
        return "redirect:/emp/show";
    }

    @GetMapping("emp/detail")
    public String getEmp(@RequestParam Integer eid, Model model){
        Employee employee = employeeMapper.getEmployee(eid);
        System.out.println("debug========"+employee);
        System.out.println(employee==null);
        if (employee==null){
            model.addAttribute("error","无该员工ID，请确认！");
//            //model.addAttribute("emps",employeeMapper.getAll());
            return "employee/notExist";
        }
        model.addAttribute("emp",employee);
        model.addAttribute("dpt",departmentMapper.getDepartment(employee.getDid()));
        return "employee/detailemp";
    }

}
