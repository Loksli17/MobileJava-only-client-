package com.example.databaseapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
    }


    public long insert(Worker worker){
        ContentValues cv = new ContentValues();
        cv.put("name", worker.getName());
        cv.put("positionId", worker.getPositionId());
        cv.put("dateBorn", worker.getDbDateBorn());
        return db.insert("worker", null, cv);
    }


    public ArrayList<Worker> getMany(int skip, int take){

        Cursor            cursor  = db.query(DbWorkers.tableName, null, null, null, null, null, "id DESC", skip + ", " + take);
        ArrayList<Worker> workers = new ArrayList<>();

        cursor.moveToFirst();

        if(!cursor.isAfterLast()){
            do {
                long   id         = cursor.getLong(0);
                String name       = cursor.getString(1);
                Date   dateBorn   = new Date(cursor.getString(2));
                long   positionId = cursor.getLong(3);
                String img        = cursor.getString(4);

                Worker worker = new WorkerBuilder().setId(id).setDateBorn(dateBorn).setImg(img).setName(name).setPositionId(positionId).getWorker();
                workers.add(worker);
            } while (cursor.moveToNext());
        }

        return workers;
    }

}
