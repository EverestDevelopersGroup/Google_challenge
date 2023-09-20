package com.example.samandar_demo;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmedteleb.buttons3d.Button3d;
import com.example.samandar_demo.Sqlite_KinderGarden.activity.FoodListActivity;


public class ParentFragment extends Fragment {

    Button3d expert_list , consultation_list , bogcha_list , maktab_list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_parent, container, false);

        expert_list = view.findViewById(R.id.experts_list);
        consultation_list = view.findViewById(R.id.consultation_list);
        bogcha_list = view.findViewById(R.id.bogcha_list);
        maktab_list = view.findViewById(R.id.maktab_list);


        bogcha_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bogchalar_list();
            }
        });


        return view;
    }

    public void bogchalar_list(){

        Intent intent = new Intent(getActivity(), FoodListActivity.class);
        startActivity(intent);





    }
}