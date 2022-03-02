package com.example.databaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.databaseapp.Fragments.WorkerForm;
import com.example.databaseapp.Worker.Worker;
import com.example.databaseapp.Worker.WorkerBuilder;


public class WorkerEditActivity extends AppCompatActivity {

    long id = 1;
    Worker worker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_edit);

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            id = extras.getLong("id");
        }

        initWorker(extras);
        createWorkerViewFragment(extras);
    }


    private void initWorker(Bundle extras){

        worker = new WorkerBuilder()
                .setId(id)
                .setName(extras.getString("name"))
                .setImg(extras.getString("img"))
                .setCv(extras.getString("cv"))
                .setPositionId(extras.getInt("positionId"))
                .getWorker();
    }


    private void createWorkerViewFragment(Bundle extras){

        Bundle bundle = new Bundle();

        bundle.putLong("id", id);
        bundle.putString("name", worker.getName());
        bundle.putString("img", worker.getImg());
        bundle.putString("position", "Position");
        bundle.putString("cv",  worker.getCv());
        bundle.putString("date", extras.getString("date"));
        bundle.putString("action", "edit");

        WorkerForm form = new WorkerForm();
        form.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.editFormWorkerWrap, form)
                .commit();
    }
}