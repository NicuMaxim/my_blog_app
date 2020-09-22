package com.my_blog_app.controllers;

import com.my_blog_app.NameByEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        model.addAttribute("title", "Home Page");

        String user_name = nameByEmail.getUserName();

        model.addAttribute("name", user_name);
        return "home";
    }



    @GetMapping("/login")
    public String login() {
        return "login";
    }



}
