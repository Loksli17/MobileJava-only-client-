package com.example.databaseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.databaseapp.Adapters.WorkerAdapter;
import com.example.databaseapp.Database.DbWorkers;
import com.example.databaseapp.Worker.Worker;
import com.example.databaseapp.Worker.WorkerBuilder;
import com.example.databaseapp.Worker.WorkerDirector;

import java.util.ArrayList;
import java.util.Date;



public class MainActivity extends AppCompatActivity {

    DbWorkers workersDbConn;
    ArrayList<Worker> workers = new ArrayList<>();
    WorkerAdapter adapter;

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

        adapter = new WorkerAdapter(this, workers);
        RecyclerView recyclerView = findViewById(R.id.WorkerAdapterView);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        adapter.notifyDataSetChanged();

        this.getWorkers();
        this.bindCreateWorkersBtn();
    }


    private void bindCreateWorkersBtn(){

        Button btn = findViewById(R.id.createWorkersBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createWorkers();
            }
        });
    }


    private void createWorkers(){

        AsyncTask.execute(() -> {
            try {
                ArrayList<Worker> newWorkers = WorkerDirector.execute();
                workersDbConn.insertMany(newWorkers);
                workers.addAll(newWorkers);

                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "New workers was created", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                });
            } catch (Exception e){
                e.printStackTrace();
            }
        });
    }


    private void getWorkers() {

        AsyncTask.execute(() -> {
            try {
                ArrayList<Worker> workersDb = workersDbConn.getMany(0, take);
                workers.addAll(workersDb);

                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "Workers were downloaded", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}