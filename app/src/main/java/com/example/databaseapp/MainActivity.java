package com.example.databaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.databaseapp.Database.DbWorkers;
import com.example.databaseapp.Worker.Worker;
import com.example.databaseapp.Worker.WorkerBuilder;

import java.util.Date;


public class MainActivity extends AppCompatActivity {

    DbWorkers workersDbConn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            workersDbConn = new DbWorkers(MainActivity.this);
        } catch (Exception e){
            e.printStackTrace();
        }

        this.createWorker();
    }

    
    private void createWorker(){

        Worker worker = new WorkerBuilder()
                .setId(1)
                .setName("Ivan Kuharenko")
                .setImg("kuhar")
//                .setDateBorn(new Date("1989-09-05"))
                .setPositionId(1)
                .getWorker();

        AsyncTask.execute(() -> {
            try {
                workersDbConn.insert(worker);
                System.out.print(worker.getId());
            } catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}