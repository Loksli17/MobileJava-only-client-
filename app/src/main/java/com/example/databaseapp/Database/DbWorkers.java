package com.example.databaseapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.databaseapp.Worker.OpenHelper;
import com.example.databaseapp.Worker.Worker;
import com.example.databaseapp.Worker.WorkerBuilder;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class DbWorkers {

    private final static String tableName = "worker";
    private SQLiteDatabase db;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


    public DbWorkers(Context ctx){
        OpenHelper openHelper = new OpenHelper(ctx);
        this.db = openHelper.getWritableDatabase();
//        openHelper.onUpgrade(this.db, 2, 3); //! for db update. You should uncommit this code for update db and change db versions
    }


    public long insert(Worker worker){
        ContentValues cv = new ContentValues();

        cv.put("name", worker.getName());
        cv.put("positionId", worker.getPositionId());
        cv.put("dateBorn", worker.getDbDateBorn());
        cv.put("img", worker.getImg());
        cv.put("cv", worker.getCv());

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

        Cursor cursor = db.rawQuery("select * from " + DbWorkers.tableName + " order by id desc " + " limit " + skip + ", " + take, null);
        ArrayList<Worker> workers = new ArrayList<>();

        cursor.moveToFirst();

        if(!cursor.isAfterLast()){
            do {
                long   id         = cursor.getLong(0);
                String name       = cursor.getString(1);
                Date   dateBorn   = formatter.parse(cursor.getString(2), new ParsePosition(0));
                long   positionId = cursor.getLong(3);
                String img        = cursor.getString(4);

                //It is OOP-pattern Builder. Why pattern? Because I wanna flex.
                Worker worker = new WorkerBuilder()
                        .setId(id)
                        .setDateBorn(dateBorn)
                        .setImg(img).setName(name)
                        .setPositionId(positionId)
                        .getWorker();

                workers.add(worker);
            } while (cursor.moveToNext());
        }

        return workers;
    }


    public Worker getOne(long queryId) {

        Cursor cursor = db.rawQuery("Select * from " + DbWorkers.tableName + " where id = " + queryId, null);
        Worker worker;

        cursor.moveToFirst();

        long   id         = cursor.getLong(0);
        String name       = cursor.getString(1);
        Date   dateBorn   = formatter.parse(cursor.getString(2), new ParsePosition(0));
        long   positionId = cursor.getLong(3);
        String img        = cursor.getString(4);
        String cv         = cursor.getString(5);

        Log.d("date", dateBorn.toString());

        worker = new WorkerBuilder()
                .setId(id)
                .setCv(cv)
                .setDateBorn(dateBorn)
                .setImg(img).setName(name)
                .setPositionId(positionId)
                .getWorker();

        return worker;
    }


    public void removeOne(long id){
       Cursor cursor = db.rawQuery("Delete from " + DbWorkers.tableName + " where id = " + id, null);
       cursor.moveToFirst();
    }

    public int getCount(){
        Cursor cursor = db.rawQuery("Select Count(*) from " + DbWorkers.tableName + "", null);
        cursor.moveToNext();

        return cursor.getInt(0);
    }


    public void editOne(Worker w) {

    }
}
