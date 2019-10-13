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
@RequestMapping("/point")
public class PointController {

    private TubeRepo tubeRepo;
    private PointRepo pointRepo;

    public PointController(PointRepo pointRepo, TubeRepo tubeRepo) {
        this.pointRepo = pointRepo;
        this.tubeRepo = tubeRepo;
    }

    @RequestMapping(method = RequestMethod.GET)
    String db(ModelMap modelMap) {
        modelMap.addAttribute("utils", new UtilsForWeb());
        modelMap.addAttribute("type", 0);
        modelMap.addAttribute("point_ed", new Point());
//        modelMap.addAttribute("message", new MessageUtil("success", "Something text"));
        modelMap.addAttribute("points", pointRepo.findAll());
        return "db";
    }

    @RequestMapping("/json")
    @ResponseBody
    String json() {
        ObjectMapper obj = new ObjectMapper();
        try {
            return obj.writeValueAsString(pointRepo.findAll());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "null";
    }

    @RequestMapping(method = RequestMethod.POST)
    String db_ins(ModelMap modelMap, String description,
                  double lat, double lon,
                  int size) {
        pointRepo.saveAndFlush(new Point(description, lat, lon, size));
        return db(modelMap);
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    String db_del(ModelMap modelMap,
                  Long id) {
        for (Tube tube : tubeRepo.findAll()) {
            if (tube.getStartPoint().getId() == id || tube.getEndPoint().getId() == id){
                tubeRepo.delete(tube);
            }
        }
        pointRepo.deleteById(id);
        return "redirect:/point/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    String db_edit(ModelMap modelMap, Long id,
                   String description, Double lat, Double lon,
                   Integer size) {
        Point point = pointRepo.getPointById(id);
        if (description != null)
            point.setDescription(description);
        if (lat != null)
            point.setLat(lat);
        if (lon != null)
            point.setLon(lon);
        if (size != null)
            point.setSize(size);
        pointRepo.save(point);
        return "redirect:/point/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    String edit(ModelMap modelMap, Long id) {
        modelMap.addAttribute("utils", new UtilsForWeb());
        modelMap.addAttribute("type", 0);
        modelMap.addAttribute("point_ed", pointRepo.getPointById(id));
        modelMap.addAttribute("points", pointRepo.findAll());
        return "db";
    }

}
