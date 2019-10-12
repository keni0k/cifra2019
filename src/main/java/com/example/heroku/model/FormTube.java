package com.example.heroku.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FormTube {
    private Float p1_lat, p1_lon;
    private Float p2_lat, p2_lon;
    private int size1, size2;
    private String desc1, desc2;
    private int z_coord;
    private int type;
    private int id_owners;
    private String gost;
    private String name, comment;
}
