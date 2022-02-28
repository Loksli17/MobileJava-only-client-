package com.example.databaseapp.Worker;

import java.util.Date;


public class WorkerBuilder {

    private Worker worker;

    public WorkerBuilder setId(long id){
        this.worker.setId(id);
        return this;
    }

    public WorkerBuilder setName(String name){
        this.worker.setName(name);
        return this;
    }

    public WorkerBuilder setDateBorn(Date dateBorn){
        this.worker.setDateBorn(dateBorn);
        return this;
    }

    public WorkerBuilder setPositionId(long id){
        this.worker.setPositionId(id);
        return this;
    }

    public Worker getWorker(){
        return this.worker;
    }
}
