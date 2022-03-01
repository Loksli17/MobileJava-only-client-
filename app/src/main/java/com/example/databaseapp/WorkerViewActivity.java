package com.example.databaseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.databaseapp.Fragments.WorkerView;

public class WorkerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_view);

        WorkerView workerView = new WorkerView();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.WorkerOneView, workerView)
                .commit();

    }
}