package com.example.databaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.databaseapp.Database.DbWorkers;
import com.example.databaseapp.Fragments.MenuFragment;
import com.example.databaseapp.Fragments.WorkerForm;
import com.example.databaseapp.Worker.Worker;
import com.example.databaseapp.Worker.WorkerBuilder;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;


public class WorkerEditActivity extends AppCompatActivity {

    long id = 1;
    Worker worker;
    DbWorkers workerDbConn;
    Button btn;

    EditText nameView;
    EditText dateView;
    EditText cvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_edit);

        workerDbConn = new DbWorkers(this);

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            id = extras.getLong("id");
        }

        initWorker(extras);
        createWorkerViewFragment(extras);
        this.initMenu();

        btn = findViewById(R.id.updateDoWorkerBtn);
        this.bindUpdateBtn();
    }


    private void initMenu() {

        MenuFragment fragment = new MenuFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.workerEditMenuWrap, fragment)
                .commit();
    }

    private void bindUpdateBtn(){
        btn.setOnClickListener(v -> {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            nameView = findViewById(R.id.workerNameInput);
            cvView   = findViewById(R.id.workerCvInput);
            dateView = findViewById(R.id.workerDateInput);

            worker.setName(nameView.getText().toString());
            worker.setDateBorn(formatter.parse(dateView.getText().toString(), new ParsePosition(0)));
            worker.setCv(cvView.getText().toString());

            AsyncTask.execute(() -> {
                this.workerDbConn.editOne(worker);

                runOnUiThread(() -> {
                    Intent intent = new Intent(WorkerEditActivity.this, MainActivity.class);
                    startActivity(intent);
                });
            });

        });
    }


    private void initWorker(Bundle extras) {

        worker = new WorkerBuilder()
                .setId(id)
                .setName(extras.getString("name"))
                .setImg(extras.getString("img"))
                .setCv(extras.getString("cv"))
                .setPositionId(extras.getLong("positionId"))
                .getWorker();
    }


    private void createWorkerViewFragment(Bundle extras) {

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