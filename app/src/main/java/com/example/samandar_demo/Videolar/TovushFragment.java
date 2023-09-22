package com.example.samandar_demo.Videolar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.samandar_demo.MimikaActivity;
import com.example.samandar_demo.R;
import com.example.samandar_demo.Tovushlar.TovushActivity;

import de.hdodenhof.circleimageview.CircleImageView;


public class TovushFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tovush, container, false);



        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CircleImageView mashq2 = view.findViewById(R.id.mashq1_t);

        mashq2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(getActivity(), TovushActivity.class);
                startActivity(intent);



            }
        });



        return view;
    }
}