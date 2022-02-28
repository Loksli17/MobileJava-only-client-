package com.example.databaseapp.Worker;

import java.io.Serializable;
import java.util.Date;


public class Worker implements Serializable {

    private long id         = 1;
    private String name     = "";
    private Date dateBorn   = new Date();
    private long positionId = 1;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDateBorn(Date dateBorn) {
        this.dateBorn = dateBorn;
    }

    public void setPositionId(long positionId) {
        this.positionId = positionId;
    }

    public String getName() {
        return name;
    }

    public Date getDateBorn() {
        return dateBorn;
    }

    public long getId() {
        return id;
    }

    public long getPositionId() {
        return positionId;
    }
}

