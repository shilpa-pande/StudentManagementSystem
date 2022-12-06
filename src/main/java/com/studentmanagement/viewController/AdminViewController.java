package com.studentmanagement.viewController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminViewController {

    @RequestMapping("/index")
    private String adminHome() {

        return "admin/index";
    }

    @RequestMapping("/viewStudent")
    private String viewHr() {
        return "admin/viewStudent";
    }

    @RequestMapping("/addStudent")
    public String signup() {
        return "admin/addStudent";
    }


    @RequestMapping("/viewClass")
    public String viewClass() {
        return "admin/viewClass";
    }


    @RequestMapping("/addClass")
    public String addClass() {
        return "admin/addClass";
    }

    @RequestMapping("/viewTeacher")
    public String viewTeacher() {
        return "admin/viewTeacher";
    }

    @RequestMapping("/addTeacher")
    public String addTeacher() {
        return "admin/addTeacher";
    }




}
