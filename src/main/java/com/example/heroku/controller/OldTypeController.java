package com.example.heroku.controller;

import com.example.heroku.model.Message;
import com.example.heroku.repo.MessageRepo;
import com.example.heroku.utils.MessageUtil;
import com.example.heroku.utils.UtilsForWeb;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class OldTypeController {

    private MessageRepo messageRepo;

    public OldTypeController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @RequestMapping("/")
    String db(ModelMap modelMap) {
        modelMap.addAttribute("utils", new UtilsForWeb());
        modelMap.addAttribute("message", new MessageUtil("success", "Something text"));
        modelMap.addAttribute("records", messageRepo.findAll());
//        messageRepo.saveAndFlush(new Message("MESSAGE " + messageRepo.findAll().size()));
        return "db";
    }

    @RequestMapping("/json")
    @ResponseBody
    String json(){
        ObjectMapper obj = new ObjectMapper();
        try {
            return obj.writeValueAsString(messageRepo.findAll());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "null";
    }

    @RequestMapping(value = "/db", method = RequestMethod.POST)
    String db_ins(ModelMap modelMap,
                  String text, int category) {
        messageRepo.saveAndFlush(new Message(text, category));
        return db(modelMap);
    }
    @RequestMapping(value = "/del_mes", method = RequestMethod.GET)
    String db_del(ModelMap modelMap,
                  Long id) {
        messageRepo.deleteById(id);
        return "redirect:/";
    }
}
