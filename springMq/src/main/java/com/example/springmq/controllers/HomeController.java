package com.example.springmq.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/home")
public class HomeController {
    @RequestMapping( value = {"","/"},method = RequestMethod.GET)
    public String getHomePage(){
        return "public/Home";
    }
}
