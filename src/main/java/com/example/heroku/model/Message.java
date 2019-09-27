package com.example.heroku.model;

import lombok.*;
import org.hibernate.annotations.Table;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(appliesTo = "message")
public class Message {

    public Message() {
    }

    public Message(String text, int category) {
        this.text = text;
        this.category = category;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Transient
    private int tableHaveNotGotIt;

    @Column(name = "text")
    private String text;

    @Column(name = "category")
    private int category;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
