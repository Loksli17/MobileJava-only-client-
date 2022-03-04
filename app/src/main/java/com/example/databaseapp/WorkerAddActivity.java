package com.example.databaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.databaseapp.Fragments.MenuFragment;
import com.example.databaseapp.Fragments.WorkerForm;


public class WorkerAddActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_add);

        this.createWorkerViewFragment();
    }


    private void createWorkerViewFragment(){

        Bundle bundle = new Bundle();
        bundle.putString("action", "add");

        WorkerForm form = new WorkerForm();
        form.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.addFormWorkerWrap, form)
                .commit();

        this.initMenu();
    }


    private void initMenu() {

        MenuFragment fragment = new MenuFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.workerAddMenuWrap, fragment)
                .commit();
    }


}