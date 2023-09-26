package com.example.samandar_demo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ahmedteleb.buttons3d.Button3d;


public class SettingsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button button = view.findViewById(R.id.langugaee);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                language();
            }
        });




        return view;
    }

    public void language(){

        Intent intent = new Intent(getActivity(), LanguageActivity.class);
        startActivity(intent);





    }



}