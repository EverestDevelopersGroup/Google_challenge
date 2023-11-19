package com.example.samandar_demo;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmedteleb.buttons3d.Button3d;
import com.example.samandar_demo.Activities.Picovoice;
import com.example.samandar_demo.Tovushlar.ImagetoVoice;


public class DifferenceFragment extends Fragment {

Button3d rasm1 , rasm2;
MediaPlayer differ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_difference, container, false);

        rasm1 = view.findViewById(R.id.rasm1);
        rasm2 = view.findViewById(R.id.rasm2);


        rasm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               intent1();
            }
        });


        rasm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               intent2();
            }
        });



        return view;
    }


    public void intent1() {

        Intent intent = new Intent(getActivity(), FindDifference.class);
        startActivity(intent);
        differ = MediaPlayer.create(getActivity(), R.raw.yaxhi2); // O'zgarish kiritilsin
        differ.start();


    }

    public void intent2() {

        Intent intent = new Intent(getActivity(), Picovoice.class);
        startActivity(intent);
        differ = MediaPlayer.create(getActivity(), R.raw.yaxhi2); // O'zgarish kiritilsin
        differ.start();


    }
}