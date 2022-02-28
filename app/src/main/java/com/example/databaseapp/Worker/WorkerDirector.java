package com.example.databaseapp.Worker;

import java.sql.Date;  //!!! IMPORTANT THIS TYPE DIFFERS FROM java.util.date
import java.util.ArrayList;



public class WorkerDirector {

    static int amount = 20;


    private static int randNum() {
        return (int) ((Math.random() * (5 - 0)));
    }


    private static ArrayList<String> initNames(){
        ArrayList<String> names = new ArrayList<>();

        names.add("Ivan Kuharenko");
        names.add("Lesha Rukavshnikov");
        names.add("Kirill You");
        names.add("Evan You");
        names.add("Viktor Cheba");

        return names;
    }

    private static ArrayList<String> initImages (){
        ArrayList<String> imgs = new ArrayList<>();

        imgs.add("image1");
        imgs.add("image2");
        imgs.add("image3");
        imgs.add("image4");
        imgs.add("image5");

        return imgs;
    }

    private static ArrayList<Integer> initPositions () {
        ArrayList<Integer> positionIds = new ArrayList<>();

        positionIds.add(1);
        positionIds.add(2);
        positionIds.add(3);
        positionIds.add(4);
        positionIds.add(5);

        return positionIds;
    }

    //! this another date!!!! It is not a java.util.date
    private static ArrayList<Date> initDates() {
        ArrayList<Date> dates = new ArrayList<>();

        dates.add(Date.valueOf("1999-06-25"));
        dates.add(Date.valueOf("1979-11-01"));
        dates.add(Date.valueOf("1991-06-04"));
        dates.add(Date.valueOf("1996-08-21"));
        dates.add(Date.valueOf("2001-02-10"));

        return dates;
    }


    public static ArrayList<Worker> execute () {

        ArrayList<Worker> workers = new ArrayList<>();

        ArrayList<String>  names       = WorkerDirector.initNames();
        ArrayList<String>  images      = WorkerDirector.initImages();
        ArrayList<Integer> positionIds = WorkerDirector.initPositions();
        ArrayList<Date>    dates       = WorkerDirector.initDates();

        for(int i = 0; i < amount; i++) {

            int[] randNumbers = new int[4];

            randNumbers[0] = WorkerDirector.randNum();
            randNumbers[1] = WorkerDirector.randNum();
            randNumbers[2] = WorkerDirector.randNum();
            randNumbers[3] = WorkerDirector.randNum();

            Worker worker = new WorkerBuilder()
                    .setId(1)
                    .setName(names.get(randNumbers[0]))
                    .setImg(images.get(randNumbers[1]))
                    .setDateBorn(dates.get(randNumbers[2]))
                    .setPositionId(positionIds.get(randNumbers[3]))
                    .getWorker();

            workers.add(worker);
        }

        return workers;
    }
}
