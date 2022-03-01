package com.example.databaseapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.databaseapp.Worker.OpenHelper;
import com.example.databaseapp.Worker.Worker;
import com.example.databaseapp.Worker.WorkerBuilder;

import java.util.ArrayList;
import java.util.Date;


public class DbWorkers {

    private final static String tableName = "worker";
    private SQLiteDatabase db;

    public DbWorkers(Context ctx){
        OpenHelper openHelper = new OpenHelper(ctx);
        this.db = openHelper.getWritableDatabase();
//        openHelper.onUpgrade(this.db, 1, 2); //! for db update. You should uncommit this code for update db
    }


    public long insert(Worker worker){
        ContentValues cv = new ContentValues();
        cv.put("name", worker.getName());
        cv.put("positionId", worker.getPositionId());
        cv.put("dateBorn", worker.getDbDateBorn());
        cv.put("img", worker.getImg());
        return db.insert("worker", null, cv);
    }


    public long[] insertMany(ArrayList<Worker> workers) {

        long[] numbers = new long[workers.size()];

        workers.forEach((Worker worker) -> {
            this.insert(worker);
        });

        return numbers;
    }


    public ArrayList<Worker> getMany(int skip, int take){

        Cursor            cursor  = db.query(DbWorkers.tableName, null, null, null, null, null, "id DESC", skip + ", " + take);
        ArrayList<Worker> workers = new ArrayList<>();

        cursor.moveToFirst();

        if(!cursor.isAfterLast()){
            do {
                long   id         = cursor.getLong(0);
                String name       = cursor.getString(1);
//                Date   dateBorn   = new Date(cursor.getString(2));
                long   positionId = cursor.getLong(3);
                String img        = cursor.getString(4);

                //It is OOP pattern Builder. Why pattern? Because I wanna flex.
                Worker worker = new WorkerBuilder()
                        .setId(id)
                        .setDateBorn(new Date())
                        .setImg(img).setName(name)
                        .setPositionId(positionId)
                        .getWorker();

                workers.add(worker);
            } while (cursor.moveToNext());
        }

        return workers;
    }


    public Worker getOne(long id) {

        Cursor cursor = db.rawQuery("Select * from " + DbWorkers.tableName + " where id = " + id, null);
        Worker worker = new Worker();

        cursor.moveToFirst();

        String name       = cursor.getString(1);
//                Date   dateBorn   = new Date(cursor.getString(2));
        long   positionId = cursor.getLong(3);
        String img        = cursor.getString(4);

        worker = new WorkerBuilder()
                .setId(id)
                .setDateBorn(new Date())
                .setImg(img).setName(name)
                .setPositionId(positionId)
                .getWorker();

        return worker;
    }

}
