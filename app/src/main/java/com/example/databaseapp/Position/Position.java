package com.example.databaseapp.Position;


public class Position {

    private long id;
    private String name;

    public Position(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
