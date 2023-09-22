package com.example.samandar_demo.Videolar;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.samandar_demo.Articulation.Artikulatsiya;
import com.example.samandar_demo.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class ArtikulatsionFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_artikulatsion, container, false);

        CircleImageView mashq1 = view.findViewById(R.id.mashq1_a);
        CircleImageView mashq2 = view.findViewById(R.id.mashq2_a);

        mashq1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    Intent intent = new Intent(getActivity(), Artikulatsiya.class);
                    startActivity(intent);



            }
        });




        return view;
    }
}