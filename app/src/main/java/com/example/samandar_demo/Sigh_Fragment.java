package com.example.samandar_demo;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmedteleb.buttons3d.Button3dCircle;



public class Sigh_Fragment extends Fragment {

    Button3dCircle sham , parrak;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_sigh_, container, false);

        sham = view.findViewById(R.id.sham_pufla);
        parrak = view.findViewById(R.id.parrak_aylan);


        sham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           candle();

            }
        });


        parrak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinWhell();
            }
        });





        return view;

    }



public void candle(){
    Intent intent = new Intent(getActivity(), Candle.class);
    startActivity(intent);
}







    public void pinWhell(){

        Intent intent = new Intent(getActivity(), PinWheel.class);
        startActivity(intent);





    }}
