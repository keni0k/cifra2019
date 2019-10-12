package com.example.heroku.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(appliesTo = "tube")
public class Tube {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "start")
    private long start;
    @Column(name = "finish")
    private long finish;
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

    @Transient
    private int status;
    @Transient
    private Point startPoint;
    @Transient
    private Point endPoint;

    public Tube(long start, long finish, float zCoord, long idOwners) {
        this.start = start;
        this.finish = finish;
        this.zCoord = zCoord;
        this.idOwners = idOwners;
        this.input = new Date();
        this.output = new Date();
    }

    public Tube(long start, long finish, float zCoord, long idOwners, String gost) {
        this.start = start;
        this.finish = finish;
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

    public Tube(long start, long finish, float zCoord, long idOwners,
                int type, int diameter, int thickness, Date input,
                Date output, String gost) {
        this.start = start;
        this.finish = finish;
        this.zCoord = zCoord;
        this.idOwners = idOwners;
        this.input = input;
        this.output = output;
        this.type = type;
        this.diameter = diameter;
        this.thickness = thickness;
        this.gost = gost;
    }

    public Tube(long start, long finish, float zCoord, int type, long idOwners) {
        this.start = start;
        this.finish = finish;
        this.zCoord = zCoord;
        this.type = type;
        this.idOwners = idOwners;
        this.input = new Date();
        this.output = new Date();
    }
}
