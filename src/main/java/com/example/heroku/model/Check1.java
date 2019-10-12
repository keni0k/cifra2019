package com.example.heroku.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Table;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(appliesTo = "check1")
public class Check1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    private int type;
    private long userId;
    private long objId;
    private Boolean checked;
    private Boolean visited;

    public Check1(long userId, long objId) {
        this.userId = userId;
        this.objId = objId;
        checked = false;
        visited = false;
        type = 0;
    }

}
