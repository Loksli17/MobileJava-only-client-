package com.example.databaseapp.Fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.databaseapp.Database.DbWorkers;
import com.example.databaseapp.MainActivity;
import com.example.databaseapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkerView extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "id";
    private static final String ARG_PARAM2 = "name";
    private static final String ARG_PARAM3 = "img";
    private static final String ARG_PARAM4 = "position";
    private static final String ARG_PARAM5 = "cv";
    private static final String ARG_PARAM6 = "date";

    // TODO: Rename and change types of parameters
    private long id;
    private String name;
    private String img;
    private String position;
    private String cv;
    private String date;

    ImageView imageView;
    TextView nameView;
    TextView positionView;
    TextView cvView;
    TextView dateView;
    TextView idView;

    Button deleteBtn;

    LayoutInflater inflater;

    public WorkerView() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id       = getArguments().getLong(ARG_PARAM1);
            name     = getArguments().getString(ARG_PARAM2);
            img      = getArguments().getString(ARG_PARAM3);
            position = getArguments().getString(ARG_PARAM4);
            cv       = getArguments().getString(ARG_PARAM5);
            date     = getArguments().getString(ARG_PARAM6);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.inflater = inflater;
        return inflater.inflate(R.layout.fragment_worker_view, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView    = view.findViewById(R.id.workerViewImage);
        nameView     = view.findViewById(R.id.wokerViewName);
        positionView = view.findViewById(R.id.workerViewPosition);
        cvView       = view.findViewById(R.id.workerViewCV);
        dateView     = view.findViewById(R.id.workerViewDate);
        idView       = view.findViewById(R.id.workerViewId);

        deleteBtn    = view.findViewById(R.id.deleteWorkerBtn);

        deleteBtn.setOnClickListener(v -> {

            DbWorkers workersDbConn = new DbWorkers(inflater.getContext());

            AsyncTask.execute(() -> {

                try {
                    workersDbConn.removeOne(id);

                    getActivity().runOnUiThread(() -> {
                        Toast.makeText(inflater.getContext(), "Worker was deleted", Toast.LENGTH_SHORT);
                        Intent intent = new Intent(inflater.getContext(), MainActivity.class);
                        startActivity(intent);
                    });
                } catch (Exception e){
                    e.printStackTrace();
                }
            });
        });

        this.pushDataInFragment();
    }


    private void pushDataInFragment(){

        String uri = "@drawable/" + img;
        int imageResource = getResources().getIdentifier(uri, null, getContext().getPackageName());
        Drawable res = getResources().getDrawable(imageResource);

        Log.d("img", img);

        imageView.setImageDrawable(res);

        nameView.setText(name);
        positionView.setText(position);
        cvView.setText(cv);
        dateView.setText(date);
        idView.setText(String.valueOf(id));
    }
}