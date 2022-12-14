package com.studentmanagement.viewController;


import com.studentmanagement.config.CustomStudentDetails;
import com.studentmanagement.config.CustomTeacherDetails;
import com.studentmanagement.entity.Attendance;
import com.studentmanagement.entity.Class;
import com.studentmanagement.entity.Student;
import com.studentmanagement.entity.Teacher;
import com.studentmanagement.repository.AttendanceRepository;
import com.studentmanagement.repository.ClassRepository;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacherView")
public class TeacherViewController {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    ClassRepository classRepository;


    @Autowired
    StudentRepository studentRepository;


    @Autowired
    AttendanceRepository attendanceRepository;

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

    @RequestMapping("/viewClass")
    private String viewClass(@AuthenticationPrincipal CustomTeacherDetails customTeacherDetails, Model model) {
        String userEmail=customTeacherDetails.getUsername();
        Teacher teacher=teacherRepository.getTeacherByEmail(userEmail);
        model.addAttribute("teacher", teacher);
        System.out.println(teacher);
        return "teacher/viewClass";
    }

    @RequestMapping("/viewStudent/{classId}")
    public String addDoc(Model model,@PathVariable("classId") Integer classId) {
        Class aClass= classRepository.getById(classId);
        System.out.println(aClass);
        model.addAttribute("aClass", aClass);
        return "teacher/viewStudent";
    }

    @RequestMapping("/addAttendance/{studentId}")
    public String addAttendance(Model model,@PathVariable("studentId") Integer studentId) {
        Student student=studentRepository.getById(studentId);
        model.addAttribute("student", student);
        return "teacher/addAttendance";
    }





}
