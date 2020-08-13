package com.jie.springboot_mybatis2.Controller;

import com.jie.springboot_mybatis2.Bean.Department;
import com.jie.springboot_mybatis2.Bean.Employee;
import com.jie.springboot_mybatis2.Mapper.DepartmentMapper;
import com.jie.springboot_mybatis2.Mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class DepartmentController {
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @GetMapping({"dpt","dpt/show"})
    public String showDpts(Model model){
        List<Department> dpts = departmentMapper.getAllIncludeEmps();
        model.addAttribute("dpts",dpts);
        return "department/dpts";
    }
    @GetMapping("dpt/{did}")
    public String showDpts(@PathVariable int did,Model model){
        Department dpt = departmentMapper.getDepartmentIncludeEmps(did);
        model.addAttribute("dpt",dpt);
        return "department/detail";

    }
    @GetMapping("dpt/addform")
    public String addDpt(Model model){
        return "department/adddpt";
    }
    @PostMapping("dpt/added")
    public String addedDpt(Model model,Department department){
        departmentMapper.InsertDepartment(department);
        return "redirect:/dpt/show";
    }
    @GetMapping("dpt/edit/{did}")
    public String editDpt(Model model, @PathVariable Integer did){
        Department department = departmentMapper.getDepartment(did);
        System.out.println(department.getDname());
        model.addAttribute("dpt",department);
        return "department/editdpt";
    }
    @PostMapping("dpt/edited")
    public String editedDpt(Department department){
        departmentMapper.updateDepartment(department);
        return "redirect:/dpt/show";
    }
    @GetMapping("dpt/deleted/{did}") //修改foreign key先删除下属employee
    public String deletedDpt(@PathVariable Integer did){
        List<Employee> emps= employeeMapper.getEmployeeBydepartment(did);
        System.out.println(emps);
        for(Employee e:emps) {
            employeeMapper.deleteEmployee(e.getEid());
        }
        departmentMapper.deleteDepartment(did);
        return "redirect:/dpt/show";
    }

}
