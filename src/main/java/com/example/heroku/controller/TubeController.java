package com.example.heroku.controller;

import com.example.heroku.model.Tube;
import com.example.heroku.repo.PointRepo;
import com.example.heroku.repo.TubeRepo;
import com.example.heroku.utils.UtilsForWeb;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

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
        modelMap.addAttribute("tube_ed", new Tube());
        modelMap.addAttribute("points", pointRepo.findAll());
//        modelMap.addAttribute("message", new MessageUtil("success", "Something text"));
        modelMap.addAttribute("tubes", tubeRepo.findAll());
        return "db";
    }

    @RequestMapping("/json")
    @ResponseBody
    String json() {
        ObjectMapper obj = new ObjectMapper();
        try {
            List<Tube> tubes = tubeRepo.findAll();
            tubes.forEach(tube -> tube.setStartPoint(pointRepo.getPointById(tube.getStart())));
            tubes.forEach(tube -> tube.setEndPoint(pointRepo.getPointById(tube.getFinish())));
            Date date = new Date();
            for (Tube t : tubes) {
                float red = (float) (t.getOutput().getTime() - date.getTime()) / (date.getTime() - t.getInput().getTime());
                red *= 10;
                red = (float) 1 / red;
                t.setStatus(Math.round(red));
            }

            return obj.writeValueAsString(tubes);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "null";
    }

    @RequestMapping(method = RequestMethod.POST)
    String db_ins(ModelMap modelMap,
                  long start, long finish,
                  float z_coord, long id_property, long id_owners) {
        tubeRepo.saveAndFlush(new Tube(start,
                finish, z_coord, id_property, id_owners));
        return db(modelMap);
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    String db_del(ModelMap modelMap,
                  Long id) {
        tubeRepo.deleteById(id);
        return "redirect:/tube/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    String db_edit(ModelMap modelMap, Long id,
                   Integer start, Integer finish,
                   Float z_coord, Long id_property, Long id_owners) {
        Tube tube = tubeRepo.getTubeById(id);
        if (start != null)
            tube.setStart(start);
        if (finish != null)
            tube.setFinish(finish);
        if (z_coord != null)
            tube.setzCoord(z_coord);
        if (id_property != null)
            tube.setIdProperty(id_property);
        if (id_owners != null)
            tube.setIdOwners(id_owners);
        //todo: rasshirit
        tubeRepo.save(tube);
        return "redirect:/tube/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    String edit(ModelMap modelMap, Long id) {
        modelMap.addAttribute("utils", new UtilsForWeb());
        modelMap.addAttribute("type", 1);
        modelMap.addAttribute("tube_ed", tubeRepo.getTubeById(id));
        modelMap.addAttribute("tubes", tubeRepo.findAll());
        modelMap.addAttribute("points", pointRepo.findAll());
        return "db";
    }

}
