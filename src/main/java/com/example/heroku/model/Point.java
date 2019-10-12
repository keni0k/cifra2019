package com.example.heroku.model;

import lombok.*;
import org.hibernate.annotations.Table;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(appliesTo = "point")
public class Point {


    public Point(String description, double lat, double lon, int size) {
        this.description = description;
        this.lat = lat;
        this.lon = lon;
        this.size = size;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description")
    private String description = "";

    @Column(name = "lat")
    private double lat;

    @Column(name = "lon")
    private double lon;

    @Column(name = "size")
    private int size = 1;

    public String getCoord(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%.5f", lat))
                     .append(' ')
                .append(String.format("%.5f", lon));
        return stringBuilder.toString();
    }
}
