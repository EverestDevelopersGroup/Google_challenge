package com.example.samandar_dem;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.google.android.material.card.MaterialCardView;


public class ParentFragment extends Fragment {

    MaterialCardView expert_list , consultation_list , bogcha_list , maktab_list;
    AlertDialog.Builder process;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_parent, container, false);

        expert_list = view.findViewById(R.id.portfoilo_list);
        consultation_list = view.findViewById(R.id.xususiy_markaz_list);
        bogcha_list = view.findViewById(R.id.bogcha_list);
        maktab_list = view.findViewById(R.id.maktab_list);


        bogcha_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogOption();
                process.show();
            }
        });

        expert_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogOption();
                process.show();
            }
        });

        consultation_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogOption();
                process.show();
            }
        });

        maktab_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogOption();
                process.show();
            }
        });


        return view;
    }

    private void DialogOption(){


        process = new AlertDialog.Builder(getActivity());
        process.setTitle(R.string.diqqat);

        process.setMessage(R.string.fragmentlar_dialog);
        // follow Step number 3 for adding image...
        process.setIcon(R.drawable.img_5);

        process.setPositiveButton("ok", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getActivity(), R.string.kerakli_bolim, Toast.LENGTH_SHORT).show();
            }
        });



    }
}