package com.example.heroku.model;

import lombok.AllArgsConstructor;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@Entity
@Table(appliesTo = "tube")
public class Tube {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "start")
    long start;
    @Column(name = "finish")
    long finish;
    @Column(name = "id_owners")
    long idOwners;
    @Column(name = "z_coord")
    float zCoord;
    int type = 0;
    int diameter = 100;
    int thickness = 10;
    String gost = "";
    @Column(name = "input_date")
    Date input;
    @Column(name = "output_date")
    Date output;
    @Transient
    int status;
    @Transient
    private Point startPoint;

    @Transient
    private Point endPoint;

    public Tube() {
    }

    public Tube(long start, long finish, float zCoord, long idOwners) {
        this.start = start;
        this.finish = finish;
        this.zCoord = zCoord;
        this.idOwners = idOwners;
        this.input = new Date();
        this.output = new Date();
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getzCoord() {
        return zCoord;
    }

    public void setzCoord(float zCoord) {
        this.zCoord = zCoord;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getFinish() {
        return finish;
    }

    public void setFinish(long finish) {
        this.finish = finish;
    }

    public Date getInput() {
        return input;
    }

    public void setInput(Date input) {
        this.input = input;
    }

    public Date getOutput() {
        return output;
    }

    public void setOutput(Date output) {
        this.output = output;
    }

    public long getIdOwners() {
        return idOwners;
    }

    public void setIdOwners(long idOwners) {
        this.idOwners = idOwners;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public String getGost() {
        return gost;
    }

    public void setGost(String gost) {
        this.gost = gost;
    }
}
