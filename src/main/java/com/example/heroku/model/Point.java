package com.example.heroku.model;

import lombok.*;
import org.hibernate.annotations.Table;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(appliesTo = "point")
public class Point extends MapObject{


//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;

    @Column(name = "description")
    private String description = "";

    @Column(name = "lat", nullable = false)
    private double lat;

    @Column(name = "lon", nullable = false)
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
