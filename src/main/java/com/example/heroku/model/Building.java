package com.example.heroku.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Table;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(appliesTo = "building")
public class Building extends MapObject{

    public Building(long id) {
        super(id);
    }
//
    public Building(String address, int type, Point topLeft, Point botRight, Point topRight, Point botLeft, int peopleCount, String created, String capitalFix) {
        this.type = type;
        this.address = address;
        this.topLeft = topLeft;
        this.botRight = botRight;
        this.topRight = topRight;
        this.botLeft = botLeft;
        this.peopleCount = peopleCount;
        this.created = created;
        this.capitalFix = capitalFix;
    }

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    long id;
    String address;
    int type;
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Point.class)
    @JoinColumn(name = "top_left_point_id", nullable = false)
    Point topLeft;
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Point.class)
    @JoinColumn(name = "bot_right_point_id", nullable = false)
    Point botRight;
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Point.class)
    @JoinColumn(name = "top_right_point_id", nullable = false)
    Point topRight;
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Point.class)
    @JoinColumn(name = "bot_left_point_id", nullable = false)
    Point botLeft;
    int peopleCount;
    long owner;
    String created;
    String capitalFix;


}
