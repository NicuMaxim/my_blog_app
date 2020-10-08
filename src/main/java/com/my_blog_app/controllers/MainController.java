package com.my_blog_app.controllers;

import com.my_blog_app.util_classes.NameByEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@ComponentScan
@Controller
public class MainController {

    @Autowired
    private NameByEmail nameByEmail;

    @GetMapping("/")
    public String home(Model model) {
        String userName = nameByEmail.getUserName();
        model.addAttribute("name", userName);
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
