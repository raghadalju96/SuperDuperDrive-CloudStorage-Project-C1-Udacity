package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {


    @GetMapping()
    public String home(){
        return "home";
    }

    @PostMapping("/addNote")
    public String addNote(){
        System.out.println("raghad meeeej");
        return "home";
    }
}