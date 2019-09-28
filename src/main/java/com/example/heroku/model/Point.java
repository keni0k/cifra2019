package com.example.heroku.model;

import lombok.*;
import org.hibernate.annotations.Table;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(appliesTo = "point")
public class Point {

    public Point() {
    }

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
    private int size;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
