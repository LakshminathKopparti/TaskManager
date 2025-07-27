package com.lakshminath.taskManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController { //#Verifyed

    @GetMapping("")//TESTED
    public String homePage(){
        return "Welcome to Task Manager Application";
    }
}
