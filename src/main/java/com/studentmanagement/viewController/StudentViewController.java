package com.studentmanagement.viewController;


import com.studentmanagement.config.CustomStudentDetails;
import com.studentmanagement.entity.Student;
import com.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentViewController {

    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping("/index")
    private String studentHome() {
        return "student/index";
    }

    @RequestMapping("/viewStudentProfile")
    private String viewHr(@AuthenticationPrincipal CustomStudentDetails customUserDetails, Model model) {
        String userEmail=customUserDetails.getUsername();
        System.out.println(userEmail);
        Student student=studentRepository.getStudentByStudentEmail(userEmail);
        model.addAttribute("student", student);
        System.out.println(student);
        return "student/viewStudentProfile";
    }

    @RequestMapping("/viewAttendance")
    public String viewAttendance(@AuthenticationPrincipal CustomStudentDetails customUserDetails, Model model) {
        String userEmail=customUserDetails.getUsername();
        System.out.println(userEmail);
        Student student=studentRepository.getStudentByStudentEmail(userEmail);
        model.addAttribute("student", student);
        System.out.println(student);
        return "student/viewAttendance";
    }


}


