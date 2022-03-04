package com.example.databaseapp.Fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.databaseapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        Button btn = view.findViewById(R.id.toggleMenuBtn);

        btn.setOnClickListener(v -> {

            ConstraintLayout layout = view.findViewById(R.id.contentMenu);

            int visibility = layout.getVisibility();

            Transition transition = new Slide(Gravity.RIGHT);
            transition.setDuration(200);
            transition.addTarget(R.id.contentMenu);

            TransitionManager.beginDelayedTransition(view.findViewById(R.id.rootMenuContainer), transition);
            layout.setVisibility(visibility == View.VISIBLE ? View.GONE : View.VISIBLE);
        });

    }
}