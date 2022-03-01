package com.example.databaseapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.databaseapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorkerView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkerView extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "id";
    private static final String ARG_PARAM2 = "name";
    private static final String ARG_PARAM3 = "img";
    private static final String ARG_PARAM4 = "position";
    private static final String ARG_PARAM5 = "cv";

    // TODO: Rename and change types of parameters
    private long id;
    private String name;
    private String img;
    private String position;
    private String cv;

    ImageView imageView;
    TextView nameView;
    TextView positionView;
    TextView cvView;

    public WorkerView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WorkerView.
     */
    // TODO: Rename and change types and number of parameters
    public static WorkerView newInstance(String param1, String param2) {
        WorkerView fragment = new WorkerView();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_worker_view, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView    = view.findViewById(R.id.workerViewImage);
        nameView     = view.findViewById(R.id.wokerViewName);
        positionView = view.findViewById(R.id.workerViewPosition);
        cvView       = view.findViewById(R.id.workerViewCV);

        this.pushDataInFragment();
    }


    private void pushDataInFragment(){
        imageView.setImageResource(R.drawable.image1);
        nameView.setText(name);
        positionView.setText(position);
        cvView.setText(cv);
    }
}