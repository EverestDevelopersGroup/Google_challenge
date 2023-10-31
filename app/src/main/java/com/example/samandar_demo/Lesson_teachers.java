package com.example.samandar_demo;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.samandar_demo.VideoLesson.Lessons;

import de.hdodenhof.circleimageview.CircleImageView;


public class Lesson_teachers extends Fragment {

  CircleImageView logoped1 , logoped2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_lesson_teachers, container, false);

        logoped1 = view.findViewById(R.id.logoped1);
        logoped1 = view.findViewById(R.id.logoped2);


        logoped1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Intent intent = new Intent(getActivity(), Lessons.class);
                    startActivity(intent);
            }
        });

        logoped2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), Lessons.class);
                startActivity(intent);

            }
        });



        return view;
    }
}