package com.my_blog_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(@RequestParam(name="name", required=false, defaultValue="Максим") String name, Model model) {
        model.addAttribute("title", "Главная страница");
        model.addAttribute("name", name);
        return "home";
    }



    @GetMapping("/login")
    public String login() {
        return "login";
    }



}
