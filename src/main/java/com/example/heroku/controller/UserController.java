package com.example.heroku.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
@Controller
public class UserController {

    @RequestMapping("/login")
    String user(){
        return "login";
    }

}
