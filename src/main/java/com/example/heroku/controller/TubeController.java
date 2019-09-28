package com.example.heroku.controller;

import com.example.heroku.model.Point;
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
import org.springframework.web.bind.annotation.RequestParam;
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
        List<Tube> tubes = tubeRepo.findAll();
        tubes.forEach(tube -> tube.setStartPoint(pointRepo.getPointById(tube.getStart())));
        tubes.forEach(tube -> tube.setEndPoint(pointRepo.getPointById(tube.getFinish())));
        modelMap.addAttribute("tubes", tubes);

        return "db";
    }

    @RequestMapping("/json")
    @ResponseBody
    String json(@RequestParam(required = false)
                        Integer type) {
        ObjectMapper obj = new ObjectMapper();
        try {
            List<Tube> tubes;
            if (type == null)
                tubes = tubeRepo.findAll();
            else
                tubes = tubeRepo.getTubesByType(type);
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

    @RequestMapping(value = "/with_points", method = RequestMethod.POST)
    @ResponseBody
    String dbs_ins(ModelMap modelMap,
                   double p1_lat, double p1_lon,
                   double p2_lat, double p2_lon,
                   int size1, int size2,
                   String desc1, String desc2,
                   float z_coord, long id_owners) {
        Point p1 = new Point(desc1, p1_lat, p1_lon, size1);
        Point p2 = new Point(desc2, p2_lat, p2_lon, size2);
        pointRepo.save(p1);
        pointRepo.save(p2);
        Tube tube = new Tube(p1.getId(), p2.getId(), z_coord, id_owners);
        tubeRepo.save(tube);
        return "OK";
    }

    @RequestMapping(method = RequestMethod.POST)
    String db_ins(ModelMap modelMap,
                  long start, long finish,
                  float z_coord, int type, long id_owners) {
        tubeRepo.saveAndFlush(new Tube(start,
                finish, z_coord, type, id_owners));
        return db(modelMap);
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    String db_del(ModelMap modelMap,
                  Long id) {
        tubeRepo.deleteById(id);
        return "redirect:/tube/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    String db_edit(ModelMap modelMap,
                   @RequestParam(value = "id", required = false) Long id,
                   @RequestParam(value = "start", required = false) Integer start,
                   @RequestParam(value = "finish", required = false) Integer finish,
                   @RequestParam(value = "z_coord", required = false) Float z_coord,
                   @RequestParam(value = "type", required = false) Integer type,
                   @RequestParam(value = "id_owners", required = false) Long id_owners) {
        Tube tube = tubeRepo.getTubeById(id);
        if (start != null)
            tube.setStart(start);
        if (finish != null)
            tube.setFinish(finish);
        if (z_coord != null)
            tube.setzCoord(z_coord);
        if (type != null)
            tube.setType(type);
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

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    String update(ModelMap modelMap) {
        for (Tube tube : tubeRepo.findAll()) {
            tube.setType(0);
            tube.setDiameter(100);
            tube.setGost("ГОСТ 3262-75");
            tube.setThickness(10);
            tubeRepo.save(tube);
        }
        return "db";
    }

}
