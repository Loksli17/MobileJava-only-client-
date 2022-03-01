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
        bundle.putString("cv", "On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains.");

        workerView = new WorkerView();
        workerView.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.WorkerOneView, workerView)
                .commit();
    }
}