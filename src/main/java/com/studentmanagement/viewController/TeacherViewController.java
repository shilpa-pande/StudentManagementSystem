package com.studentmanagement.viewController;


import com.studentmanagement.config.CustomStudentDetails;
import com.studentmanagement.config.CustomTeacherDetails;
import com.studentmanagement.entity.Student;
import com.studentmanagement.entity.Teacher;
import com.studentmanagement.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherViewController {

    @Autowired
    TeacherRepository teacherRepository;

    @RequestMapping("/index")
    private String teacherHome() {
        return "teacher/index";
    }

    @RequestMapping("/viewTeacherProfile")
    private String viewHr(@AuthenticationPrincipal CustomTeacherDetails customTeacherDetails, Model model) {
        String userEmail=customTeacherDetails.getUsername();
        System.out.println(userEmail);
        Teacher teacher=teacherRepository.getTeacherByEmail(userEmail);
        model.addAttribute("teacher", teacher);
        System.out.println(teacher);
        return "teacher/viewTeacherProfile";
    }

}
