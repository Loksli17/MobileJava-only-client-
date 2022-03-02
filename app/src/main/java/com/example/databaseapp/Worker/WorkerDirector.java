package com.example.databaseapp.Worker;

import android.util.Log;

import java.sql.Date;  //!!! IMPORTANT THIS TYPE DIFFERS FROM java.util.date
import java.util.ArrayList;



public class WorkerDirector {

    static int amount = 10;


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

        String cv = "On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided.";

        for(int i = 0; i < amount; i++) {

            int[] randNumbers = new int[4];

            randNumbers[0] = WorkerDirector.randNum();
            randNumbers[1] = WorkerDirector.randNum();
            randNumbers[2] = WorkerDirector.randNum();
            randNumbers[3] = WorkerDirector.randNum();

            java.util.Date date = new java.util.Date(dates.get(randNumbers[2]).getTime());

            Worker worker = new WorkerBuilder()
                    .setName(names.get(randNumbers[0]))
                    .setImg(images.get(randNumbers[1]))
                    .setDateBorn(date)
                    .setCv(cv)
                    .setPositionId(positionIds.get(randNumbers[3]))
                    .getWorker();

            workers.add(worker);
        }

        return workers;
    }
}
