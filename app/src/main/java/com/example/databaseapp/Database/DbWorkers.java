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
//        openHelper.onUpgrade(this.db, 1, 2);
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

        Log.d("QUERY", "GetMany start");

        Cursor            cursor  = db.query("worker", null, null, null, null, null, "id DESC", skip + ", " + take);
        ArrayList<Worker> workers = new ArrayList<>();

        Log.d("QUERY", "kek");

        cursor.moveToFirst();

        if(!cursor.isAfterLast()){
            do {
                long   id         = cursor.getLong(0);
                String name       = cursor.getString(1);
//                Date   dateBorn   = new Date(cursor.getString(2));
                long   positionId = cursor.getLong(3);
                String img        = cursor.getString(4);

                Worker worker = new WorkerBuilder().setId(id).setDateBorn(new Date()).setImg(img).setName(name).setPositionId(positionId).getWorker();
                workers.add(worker);
            } while (cursor.moveToNext());
        }

        return workers;
    }

}
