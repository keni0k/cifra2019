package com.example.heroku.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Table;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(appliesTo = "building")
public class Building {
    public Building(String address, int type, long topLeft, long botRight, long topRight, long botLeft, int peopleCount, Date created, Date capitalFix) {
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

    long id;
    String address;
    int type;
    long topLeft;
    long botRight;
    long topRight;
    long botLeft;
    int peopleCount;
    Date created;
    Date capitalFix;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(long topLeft) {
        this.topLeft = topLeft;
    }

    public long getBotRight() {
        return botRight;
    }

    public void setBotRight(long botRight) {
        this.botRight = botRight;
    }

    public long getTopRight() {
        return topRight;
    }

    public void setTopRight(long topRight) {
        this.topRight = topRight;
    }

    public long getBotLeft() {
        return botLeft;
    }

    public void setBotLeft(long botLeft) {
        this.botLeft = botLeft;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getCapitalFix() {
        return capitalFix;
    }

    public void setCapitalFix(Date capitalFix) {
        this.capitalFix = capitalFix;
    }
}
