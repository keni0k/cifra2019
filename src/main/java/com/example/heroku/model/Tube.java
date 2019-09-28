package com.example.heroku.model;

import lombok.AllArgsConstructor;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;

@AllArgsConstructor
@Entity
@Table(appliesTo = "tube")
public class Tube {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "start")
    Long start;
    @Column(name = "finish")
    Long finish;
    @Column(name = "id_owners")
    Long idOwners;
    @Column(name = "z_coord")
    float zCoord;
    @Column(name = "type")
    Integer type;
    @Column(name = "diameter")
    Integer diameter;
    @Column(name = "thickness")
    Integer thickness;
    @Column(name = "gost")
    String gost = "";
    @Column(name = "input_date")
    Date input;
    @Column(name = "output_date")
    Date output;
    @Transient
    Integer status;
    @Transient
    private Point startPoint;

    @Transient
    private Point endPoint;

    public Tube() {
    }

    public Tube(Long start, Long finish, float zCoord, Long idOwners) {
        this.start = start;
        this.finish = finish;
        this.zCoord = zCoord;
        this.idOwners = idOwners;
        this.input = new Date();
        this.output = new Date();
    }

    public Tube(Long start, Long finish, float zCoord, Long idOwners, String gost) {
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

    public Tube(Long start, Long finish, float zCoord, Long idOwners,
                Integer type, Integer diameter, Integer thickness, Date input,
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

    public Tube(Long start, Long finish, float zCoord, Integer type, Long idOwners) {
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

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getFinish() {
        return finish;
    }

    public void setFinish(Long finish) {
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

    public Long getIdOwners() {
        return idOwners;
    }

    public void setIdOwners(Long idOwners) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDiameter() {
        return diameter;
    }

    public void setDiameter(Integer diameter) {
        this.diameter = diameter;
    }

    public Integer getThickness() {
        return thickness;
    }

    public void setThickness(Integer thickness) {
        this.thickness = thickness;
    }

    public String getGost() {
        return gost;
    }

    public void setGost(String gost) {
        this.gost = gost;
    }
}
