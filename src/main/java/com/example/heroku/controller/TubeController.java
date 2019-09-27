package com.example.heroku.controller;

import com.example.heroku.model.Point;
import com.example.heroku.model.Tube;
import com.example.heroku.repo.PointRepo;
import com.example.heroku.repo.TubeRepo;
import com.example.heroku.utils.MessageUtil;
import com.example.heroku.utils.UtilsForWeb;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tube")
public class TubeController {

    private TubeRepo tubeRepo;
    private PointRepo pointRepo;

    public TubeController(TubeRepo tubeRepo, PointRepo pointRepo) {
        this.pointRepo = pointRepo;
        this.tubeRepo = tubeRepo;
    }

    @RequestMapping(method = RequestMethod.GET)
    String db(ModelMap modelMap) {
        modelMap.addAttribute("utils", new UtilsForWeb());
        modelMap.addAttribute("type", 1);

        modelMap.addAttribute("message", new MessageUtil("success", "Something text"));
        modelMap.addAttribute("tubes", tubeRepo.findAll());
        return "db";
    }

    @RequestMapping("/json")
    @ResponseBody
    String json() {
        ObjectMapper obj = new ObjectMapper();
        try {
            return obj.writeValueAsString(tubeRepo.findAll());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "null";
    }

    @RequestMapping(method = RequestMethod.POST)
    String db_ins(ModelMap modelMap,
                  int start, int finish,
                  float z_coord) {
        tubeRepo.saveAndFlush(new Tube(start,
                finish, z_coord));
        return db(modelMap);
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    String db_del(ModelMap modelMap,
                  Long id) {
        tubeRepo.deleteById(id);
        return "redirect:/tube/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    String db_edit(ModelMap modelMap, Long id,
                   Integer start, Integer finish) {
        Tube tube = tubeRepo.getTubeById(id);
        if (start != null)
            tube.setStart(start);
        if (finish != null)
            tube.setFinish(finish);
        //todo: rasshirit
        tubeRepo.save(tube);
        return "saved";
    }

}
