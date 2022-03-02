package com.example.databaseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.databaseapp.Database.DbWorkers;
import com.example.databaseapp.Fragments.WorkerView;
import com.example.databaseapp.Worker.Worker;

public class WorkerViewActivity extends AppCompatActivity {


    Worker     worker;
    DbWorkers  workersDbConn;
    WorkerView workerView;
    long       id = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_view);

        try {
            workersDbConn = new DbWorkers(WorkerViewActivity.this);
        } catch (Exception e){
            e.printStackTrace();
        }

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            id = extras.getLong("id");
        }

        getWorker();
    }


    private void getWorker() {
        AsyncTask.execute(() -> {

            Worker workerDb;

            try {
                workerDb = workersDbConn.getOne(id);

                runOnUiThread(() -> {
                    Toast.makeText(WorkerViewActivity.this, "Worker with id = " + id + " was downloaded", Toast.LENGTH_SHORT).show();

                    worker = workerDb;
                    createWorkerViewFragment();
                });
            } catch (Exception e){
                e.printStackTrace();
            }
        });
    }


    private void createWorkerViewFragment(){

        Bundle bundle = new Bundle();

        bundle.putLong("id", id);
        bundle.putString("name", worker.getName());
        bundle.putString("img", worker.getImg());
        bundle.putString("position", "Position");
        bundle.putString("cv",  worker.getCv());
        bundle.putString("date", worker.getDbDateBorn());
        bundle.putLong("positionId", worker.getPositionId());

        workerView = new WorkerView();
        workerView.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.WorkerOneView, workerView)
                .commit();
    }
}