package com.my_blog_app.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home Page");

        Authentication current_user = SecurityContextHolder.getContext().getAuthentication();
        String user_name = current_user.getName();

        model.addAttribute("name", user_name);
        return "home";
    }



    @GetMapping("/login")
    public String login() {
        return "login";
    }



}
