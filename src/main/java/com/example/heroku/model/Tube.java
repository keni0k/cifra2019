package com.example.heroku.model;

import lombok.*;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(appliesTo = "tube")
public class Tube extends MapObject{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
    @Column(name = "id_owners")
    private long idOwners;
    @Column(name = "z_coord")
    private float zCoord;
    @Column(name = "type")
    private int type;
    @Column(name = "diameter")
    private int diameter;
    @Column(name = "thickness")
    private int thickness;
    @Column(name = "gost")
    private String gost = "";

    @Column(name = "name")
    private String name = "";
    @Column(name = "comment")
    private String comment = "";
    @Column(name = "input_date")
    private Date input;
    @Column(name = "output_date")
    private Date output;
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Point.class)
    @JoinColumn(name = "start_point_id", nullable = false)
    private Point startPoint;
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Point.class)
    @JoinColumn(name = "end_point_id", nullable = false)
    private Point endPoint;

    @Transient
    private int status;

    public Tube(Point start, Point finish, float zCoord, long idOwners) {
        this.startPoint = start;
        this.endPoint = finish;
        this.zCoord = zCoord;
        this.idOwners = idOwners;
        this.input = new Date();
        this.output = new Date();
    }

    public Tube(Point start, Point finish, float zCoord, long idOwners, String gost) {
        this.startPoint = start;
        this.endPoint = finish;
        this.zCoord = zCoord;
        this.idOwners = idOwners;
        this.gost = gost;
        this.input = new Date();
        this.output = new Date();
        Random random = new Random();
        this.diameter = random.nextInt(100) + 50;
        this.thickness = random.nextInt(5) + 5;
        this.type = random.nextInt(5);
    }

    public Tube(Point start, Point finish, float zCoord, long idOwners,
                int type, int diameter, int thickness, Date input,
                Date output, String gost) {
        this.startPoint = start;
        this.endPoint = finish;
        this.zCoord = zCoord;
        this.idOwners = idOwners;
        this.input = input;
        this.output = output;
        this.type = type;
        this.diameter = diameter;
        this.thickness = thickness;
        this.gost = gost;
    }

    public Tube(Point start, Point finish, float zCoord, int type, long idOwners) {
        this.startPoint = start;
        this.endPoint = finish;
        this.zCoord = zCoord;
        this.type = type;
        this.idOwners = idOwners;
        this.input = new Date();
        this.output = new Date();
    }
}
