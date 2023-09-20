package com.example.samandar_demo;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmedteleb.buttons3d.Button3d;
import com.example.samandar_demo.Sqlite_KinderGarden.SqlActivity;


public class ExpertFragment extends Fragment {

   Button3d portfoilo , bogcha , maktab;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_expert, container, false);


        portfoilo = view.findViewById(R.id.portfolio);
        bogcha = view.findViewById(R.id.bogcha);
        maktab = view.findViewById(R.id.maktab);

        bogcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bogchalar();
            }
        });




        return view;
    }

    public void bogchalar(){

        Intent intent = new Intent(getActivity(), SqlActivity.class);
        startActivity(intent);





    }
}