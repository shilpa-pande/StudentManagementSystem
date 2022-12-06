package com.studentmanagement.viewController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(path = "/")
    public String home(Model model) {
        model.addAttribute("title","Student Management System");
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

}
