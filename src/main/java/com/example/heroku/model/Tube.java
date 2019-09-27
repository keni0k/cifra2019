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
    @Column(name = "z_coord")
    float zCoord;
    @Column(name = "input_date")
    Date input;
    @Column(name = "output_date")
    Date output;

    public Tube() {
    }

    public Tube(long start, long finish, float zCoord) {
        this.start = start;
        this.finish = finish;
        this.zCoord = zCoord;
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
}
