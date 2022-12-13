package com.studentmanagement.viewController;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherViewController {

    @RequestMapping("/index")
    private String teacherHome() {
        return "teacher/index";
    }

}
