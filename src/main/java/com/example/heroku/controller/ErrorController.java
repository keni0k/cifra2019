package com.example.heroku.controller;

import com.example.heroku.utils.MessageUtil;
import com.example.heroku.utils.UtilsForWeb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping(method = RequestMethod.GET)
    public String error(Map<String, Object> model){
        model.put("utils", new UtilsForWeb());
        model.put("message", "it isn't good!");
        return "error";
    }

}
