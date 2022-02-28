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
import com.example.databaseapp.Worker.WorkerDirector;

import java.util.ArrayList;
import java.util.Date;



public class MainActivity extends AppCompatActivity {

    DbWorkers workersDbConn;
    ArrayList<Worker> workers = new ArrayList<>();

    int take = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            workersDbConn = new DbWorkers(MainActivity.this);
        } catch (Exception e){
            e.printStackTrace();
        }

        this.getWorkers();

//        this.createWorkers();
    }


    private void createWorkers(){

        AsyncTask.execute(() -> {
            try {
                ArrayList<Worker> workers = WorkerDirector.execute();
                workersDbConn.insertMany(workers);
            } catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    private void getWorkers() {

        AsyncTask.execute(() -> {
            try {
                ArrayList<Worker> workersDb = workersDbConn.getMany(0, take);

                runOnUiThread(() -> {
                    workers.addAll(workersDb);
                    System.out.print(workers.size());
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}