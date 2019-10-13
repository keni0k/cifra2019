package com.example.heroku.controller;

import com.example.heroku.model.FormTube;
import com.example.heroku.model.Point;
import com.example.heroku.model.Tube;
import com.example.heroku.repo.PointRepo;
import com.example.heroku.repo.TubeRepo;
import com.example.heroku.utils.UtilsForWeb;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
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

    @RequestMapping("/json")
    @ResponseBody
    String json(@RequestParam(required = false) Integer type,
                @RequestParam(required = false) Long id) {
        ObjectMapper obj = new ObjectMapper();
        try {
            List<Tube> tubes;
            if (id != null) {
                tubes = new ArrayList<>();
                tubes.add(tubeRepo.getTubeById(id));
            } else if (type != null)
                tubes = tubeRepo.getTubesByType(type);
            else tubes = tubeRepo.findAll();
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

    @RequestMapping(value = "/add_with_2_points", method = RequestMethod.GET)
    @ResponseBody
    String dbs_ins(double p1_lat, double p1_lon,
                   double p2_lat, double p2_lon,
                   int size1, int size2,
                   String desc1, String desc2,
                   float z_coord, long id_owners,
                   String gost,
                   @RequestParam(value = "name", required = false) String name,
                   @RequestParam(value = "comment", required = false) String comment) {
        Point p1 = new Point(desc1, p1_lat, p1_lon, size1);
        Point p2 = new Point(desc2, p2_lat, p2_lon, size2);
        p1 = pointRepo.saveAndFlush(p1);
        p2 = pointRepo.saveAndFlush(p2);
        Tube tube = new Tube(p1, p2, z_coord, id_owners, gost);
        tube.setName(name);
        tube.setComment(comment);
        tubeRepo.saveAndFlush(tube);
        return "OK";
    }

    @RequestMapping(value = "/add2", method = RequestMethod.POST)
    @ResponseBody
    String dbs_ins2(@RequestBody FormTube formTube){
        Point p1 = new Point(formTube.getDesc1(), formTube.getP1_lat(), formTube.getP1_lon(), formTube.getSize1());
        Point p2 = new Point(formTube.getDesc2(), formTube.getP2_lat(), formTube.getP2_lon(), formTube.getSize2());
        p1 = pointRepo.saveAndFlush(p1);
        p2 = pointRepo.saveAndFlush(p2);
        Tube tube = new Tube(p1, p2, formTube.getZ_coord(), formTube.getId_owners(), formTube.getGost());
        tube.setName(formTube.getName());
        tube.setType(formTube.getType());
        tube.setComment(formTube.getComment());
        tubeRepo.saveAndFlush(tube);
        return json(formTube.getType(), null);
    }

    @RequestMapping(value = "/with_points", method = RequestMethod.POST)
    @ResponseBody
    String dbs_ins2(ModelMap modelMap,
                    Double p1_lat, Double p1_lon,
                   Double p2_lat, Double p2_lon,
                   Integer size1, Integer size2,
                   String desc1, String desc2,
                   Float z_coord, Long id_owners,
                   String gost,
                   @RequestParam(value = "name", required = false) String name,
                   @RequestParam(value = "comment", required = false) String comment) {
        Point p1 = new Point(desc1, p1_lat, p1_lon, size1);
        Point p2 = new Point(desc2, p2_lat, p2_lon, size2);
        p1 = pointRepo.saveAndFlush(p1);
        p2 = pointRepo.saveAndFlush(p2);
        Tube tube = new Tube(p1, p2, z_coord, id_owners, gost);
        tube.setName(name);
        tube.setComment(comment);
        tubeRepo.saveAndFlush(tube);
        return "OK";
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    String db_del(ModelMap modelMap,
                  Long id) {
        tubeRepo.deleteById(id);
        return "redirect:/tube/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    String db_edit(@RequestBody Tube tube) {
        tubeRepo.save(tube);
        return json(null, null);
    }

}
