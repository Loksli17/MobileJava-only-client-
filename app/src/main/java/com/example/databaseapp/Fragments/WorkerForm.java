package com.example.databaseapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.databaseapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkerForm extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "id";
    private static final String ARG_PARAM2 = "name";
    private static final String ARG_PARAM3 = "img";
    private static final String ARG_PARAM4 = "position";
    private static final String ARG_PARAM5 = "cv";
    private static final String ARG_PARAM6 = "date";
    private static final String ARG_PARAM7 = "action";

    private long id;
    private String name;
    private String img;
    private String position;
    private String cv;
    private String date;

    private String action;

    EditText workerNameInput;
    EditText workerCvInput;
    EditText workerDateInput;

    LayoutInflater inflater;

    public WorkerForm() {
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
            action   = getArguments().getString(ARG_PARAM7);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.inflater = inflater;
        return inflater.inflate(R.layout.fragment_worker_form, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        workerNameInput = view.findViewById(R.id.workerNameInput);
        workerCvInput   = view.findViewById(R.id.workerCvInput);
        workerDateInput = view.findViewById(R.id.workerDateInput);

        switch (action){
            case "edit":
                pushDataInFragment();
                break;
            case "add":
                break;
        }
    }

    private void pushDataInFragment(){
        Log.d("cv", cv);
        workerCvInput.setText(cv);
        workerNameInput.setText(name);
        workerDateInput.setText(date);
    }
}