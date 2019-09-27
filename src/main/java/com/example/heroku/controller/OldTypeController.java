package com.example.heroku.controller;

import com.example.heroku.model.Message;
import com.example.heroku.repo.MessageRepo;
import com.example.heroku.utils.MessageUtil;
import com.example.heroku.utils.UtilsForWeb;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class OldTypeController {

    @Value("${spring.datasource.url}")
    private String dbUrl;
    private MessageRepo messageRepo;

    public OldTypeController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @RequestMapping("/")
    String index(Map<String, Object> model) {
        model.put("utils", new UtilsForWeb());
        return "index";
    }

    @RequestMapping("/db")
    String db(ModelMap modelMap) {
        modelMap.addAttribute("utils", new UtilsForWeb());
        modelMap.addAttribute("message", new MessageUtil("danger", "Something wrong..."));
        modelMap.addAttribute("records", messageRepo.findAll());
        messageRepo.saveAndFlush(new Message("MESSAGE " + messageRepo.findAll().size()));
        return "db";
    }

}
