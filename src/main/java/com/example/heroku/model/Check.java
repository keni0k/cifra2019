package com.example.heroku.model;

import org.hibernate.annotations.Table;

import javax.persistence.*;

@Entity
@Table(appliesTo = "check")
public class Check {

    public Check(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    private int type;
    private long userId;
    private long objId;
    private boolean checked;
    private boolean visited;

    public Check(long userId, long objId) {
        this.userId = userId;
        this.objId = objId;
        checked = false;
        visited = false;
        type = 0;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getObjId() {
        return objId;
    }

    public void setObjId(long objId) {
        this.objId = objId;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
