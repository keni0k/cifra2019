package com.example.heroku.controller;

import com.example.heroku.model.Check1;
import com.example.heroku.repo.CheckRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/checklist")
public class CheckController {

    CheckRepo checkRepo;

    public CheckController(CheckRepo checkRepo){
        this.checkRepo = checkRepo;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    String db(ModelMap modelMap, long user_id) {
        List<Check1> check1s = checkRepo.findAll();
        check1s.removeIf(check1 -> check1.getUserId()!=user_id);
        StringBuilder stringBuilder = new StringBuilder();
        for (Check1 check1 : check1s){
            stringBuilder.append(check1.getObjId());
            stringBuilder.append("_");
        }
        return stringBuilder.toString();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    String add_check(ModelMap modelMap, long user_id, long obj_id) {
        checkRepo.saveAndFlush(new Check1(user_id, obj_id));
        return "OK";
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @ResponseBody
    String set_check(ModelMap modelMap, long id, boolean check) {
        Check1 check1 = checkRepo.getCheckById(id);
        check1.setChecked(check);
        check1.setVisited(true);
        checkRepo.saveAndFlush(check1);
        return "OK";
    }

}
