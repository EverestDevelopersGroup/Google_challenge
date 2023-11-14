package com.example.samandar_demo;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahmedteleb.buttons3d.Button3d;
import com.example.samandar_demo.Sqlite_KinderGarden.SqlActivity;
import com.google.android.material.card.MaterialCardView;


public class ExpertFragment extends Fragment {

   MaterialCardView portfoilo , bogcha , maktab;
   AlertDialog.Builder process;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_expert, container, false);


        portfoilo = view.findViewById(R.id.portfoilo_add);
        bogcha = view.findViewById(R.id.bogcha_add);
        maktab = view.findViewById(R.id.maktab_add);

        bogcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogOption();
                process.show();
            }
        });

        portfoilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogOption();
                process.show();
            }
        });

        maktab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogOption();
                process.show();
            }
        });




        return view;
    }

    public void bogchalar(){

      DialogOption();
        process.show();






    }


    private void DialogOption(){


        process = new AlertDialog.Builder(getActivity());
        process.setTitle(R.string.diqqat);

        process.setMessage(R.string.fragmentlar_dialog);
        // follow Step number 3 for adding image...
        process.setIcon(R.drawable.img_5);

        process.setPositiveButton(R.string.ha, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getActivity(), R.string.kerakli_bolim, Toast.LENGTH_SHORT).show();
            }
        });



    }
}