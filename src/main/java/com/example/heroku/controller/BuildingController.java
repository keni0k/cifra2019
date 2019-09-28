package com.example.heroku.controller;

import com.example.heroku.model.Building;
import com.example.heroku.repo.BuildingRepo;
import com.example.heroku.utils.UtilsForWeb;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/building")
public class BuildingController {

    private BuildingRepo buildingRepo;

    BuildingController(BuildingRepo buildingRepo){
        this.buildingRepo = buildingRepo;
    }

    @RequestMapping(method = RequestMethod.GET)
    String db(ModelMap modelMap) {
        modelMap.addAttribute("utils", new UtilsForWeb());
        modelMap.addAttribute("type", 2);
        modelMap.addAttribute("building_ed", new Building());
//        modelMap.addAttribute("message", new MessageUtil("success", "Something text"));
        modelMap.addAttribute("buildings", buildingRepo.findAll());
        return "db";
    }

    @RequestMapping("/json")
    @ResponseBody
    String json() {
        ObjectMapper obj = new ObjectMapper();
        try {
            return obj.writeValueAsString(buildingRepo.findAll());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "null";
    }

    @RequestMapping(method = RequestMethod.POST)
    String db_ins(ModelMap modelMap, String address,
                  int type, long topLeft, long botRight, long topRight, long botLeft, int countOfPeople, Date created, Date capitalFix) {
        buildingRepo.saveAndFlush(new Building(address, type, topLeft, botRight, topRight, botLeft, countOfPeople, created, capitalFix));
        return db(modelMap);
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    String db_del(ModelMap modelMap,
                  Long id) {
        buildingRepo.deleteById(id);
        return "redirect:/building/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    String db_edit(ModelMap modelMap, Long id,
                   Integer type, Long topLeft, Long botRight, Long topRight, Long botLeft, Integer countOfPeople, Date created, Date capitalFix) {
        Building building = buildingRepo.getBuildingById(id);
        // TOdo edit
        buildingRepo.save(building);
        return "redirect:/building/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    String edit(ModelMap modelMap, Long id) {
        modelMap.addAttribute("utils", new UtilsForWeb());
        modelMap.addAttribute("type", 2);
        modelMap.addAttribute("building_ed", buildingRepo.getBuildingById(id));
        modelMap.addAttribute("buildings", buildingRepo.findAll());
        return "db";
    }

}
