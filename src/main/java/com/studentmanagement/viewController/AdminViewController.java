package com.studentmanagement.viewController;
import com.studentmanagement.entity.Student;
import com.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminViewController {


    @Autowired
    StudentRepository studentRepository;

    @RequestMapping("/index")
    private String adminHome() {

        return "admin/index";
    }

    @RequestMapping("/viewStudent")
    private String viewHr() {
        return "admin/viewStudent";
    }


    @RequestMapping("/addDoc/{studentId}")
    public String addDoc(Model model,@PathVariable("studentId") Integer studentId) {
        Student student=studentRepository.getById(studentId);

        model.addAttribute("student", student);
        return "admin/addDoc";
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
