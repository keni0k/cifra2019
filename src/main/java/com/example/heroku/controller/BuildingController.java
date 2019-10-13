package com.example.heroku.controller;

import com.example.heroku.model.Building;
import com.example.heroku.repo.BuildingRepo;
import com.example.heroku.utils.UtilsForWeb;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/building")
public class BuildingController {

    private BuildingRepo buildingRepo;

    BuildingController(BuildingRepo buildingRepo) {
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
    String db_ins(ModelMap modelMap, @RequestBody Building building) {
        buildingRepo.saveAndFlush(building);
        return db(modelMap);
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    String db_del(ModelMap modelMap,
                  Long id) {
        buildingRepo.deleteById(id);
        return "redirect:/building/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    String db_edit(ModelMap modelMap, @RequestBody Building building) {
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
