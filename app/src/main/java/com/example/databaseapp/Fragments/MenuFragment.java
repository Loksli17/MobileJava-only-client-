package com.example.databaseapp.Fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
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
            Log.d("toggle", String.valueOf(visibility));

            if(visibility != View.VISIBLE){
                layout.setVisibility(View.VISIBLE);
            } else {
                layout.setVisibility(View.INVISIBLE);
            }

//            view.animate()
////                .translationY(layout.getHeight())
////                .alpha(0.0f)
////                .setDuration(300)
//                .setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        super.onAnimationEnd(animation);
//
//                        if(visibility != View.VISIBLE){
//                            layout.setVisibility(View.VISIBLE);
//                        } else {
//                            layout.setVisibility(View.INVISIBLE);
//                        }
//                    }
//                });
        });

//        view.animate()
//                .translationY(view.getHeight())
//                .alpha(0.0f)
//                .setDuration(300)
//                .setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        super.onAnimationEnd(animation);
//                        view.setVisibility(View.GONE);
//                    }
//                });
    }
}