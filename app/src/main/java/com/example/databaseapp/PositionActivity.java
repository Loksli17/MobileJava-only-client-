package com.example.databaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.databaseapp.Database.DbPositions;
import com.example.databaseapp.Position.Position;

import java.util.ArrayList;


public class PositionActivity extends AppCompatActivity {

    DbPositions positionsDbConn;
    ArrayList<Position> positions = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position);

        try {
            positionsDbConn = new DbPositions(PositionActivity.this);
        } catch (Exception e){
            e.printStackTrace();
        }

        getPositions();

//        ArrayList<Position> positions = new ArrayList<>();
//
//        positions.add(new Position(2,"junior programmer"));
//        positions.add(new Position(3,"middle programmer"));
//        positions.add(new Position(4,"senior programmer"));
//
//        AsyncTask.execute(() -> {
//            try {
//                long[] res = positionsDbConn.insertMany(positions);
//
//                runOnUiThread(() -> {
//                    Log.d("res", String.valueOf(res[0]));
//                    Toast.makeText(PositionActivity.this, "position", Toast.LENGTH_SHORT);
//                });
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//        });
    }


    public void getPositions(){

        AsyncTask.execute(() -> {

            try {
                positions.addAll(positionsDbConn.getMany());
            } catch (Exception e){
                e.printStackTrace();
            }

            runOnUiThread(() -> {
                Toast.makeText(PositionActivity.this, "Positions was dowloaded", Toast.LENGTH_SHORT);
            });
        });
    }
}