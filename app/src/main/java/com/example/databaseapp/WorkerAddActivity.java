package com.example.databaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.databaseapp.Database.DbWorkers;
import com.example.databaseapp.Fragments.MenuFragment;
import com.example.databaseapp.Fragments.WorkerForm;
import com.example.databaseapp.Worker.Worker;
import com.example.databaseapp.Worker.WorkerBuilder;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;


public class WorkerAddActivity extends AppCompatActivity {

    DbWorkers workerDbConn;
    Button btn;

    EditText nameView;
    EditText dateView;
    EditText cvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_add);

        workerDbConn = new DbWorkers(this);

        this.initMenu();
        this.createWorkerViewFragment();
        btn = findViewById(R.id.workerAddDoBtn);

        this.bindCreateBtn();
    }


    private void createWorkerViewFragment(){

        Bundle bundle = new Bundle();
        bundle.putString("action", "add");

        WorkerForm form = new WorkerForm();
        form.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.addFormWorkerWrap, form)
                .commit();
    }


    private void initMenu() {

        MenuFragment fragment = new MenuFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.workerAddMenuWrap, fragment)
                .commit();
    }

    private void bindCreateBtn(){
        btn.setOnClickListener(v -> {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            nameView = findViewById(R.id.workerNameInput);
            cvView   = findViewById(R.id.workerCvInput);
            dateView = findViewById(R.id.workerDateInput);

            Worker worker = new WorkerBuilder()
                    .setDateBorn(formatter.parse(dateView.getText().toString(), new ParsePosition(0)))
                    .setCv(cvView.getText().toString())
                    .setName(nameView.getText().toString())
                    .setImg("image4")
                    .setPositionId(1)
                    .getWorker();


            AsyncTask.execute(() -> {
                this.workerDbConn.insert(worker);

                runOnUiThread(() -> {
                    Intent intent = new Intent(WorkerAddActivity.this, MainActivity.class);
                    startActivity(intent);
                });
            });

        });
    }


}