package com.example.samandar_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;




import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity {




    SmoothBottomBar bottomBar;
    LinearLayout toolbar;
    TextView tool_text;
    ImageView bottomsheet;






    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        tool_text = findViewById(R.id.camera_open);
        bottomBar = findViewById(R.id.bottomBar);
        toolbar = findViewById(R.id.layout_top);

//
//        tool_text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,CameraOpen.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
//            }
//        });





        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        bottomBar.setVisibility(View.VISIBLE);
        transaction.replace(R.id.framelayout_container, new ChildFragment());
        transaction.commit();






        bottomBar.setOnItemSelectedListener((OnItemSelectedListener) i -> {

            if (i == 0) {


                FragmentManager manager1 = getSupportFragmentManager();
                FragmentTransaction transaction1 = manager1.beginTransaction();
                transaction1.replace(R.id.framelayout_container, new ChildFragment());
                transaction1.commit();
                bottomBar.setBarBackgroundColor(Color.parseColor("#39ff14"));
                getWindow().setNavigationBarColor(Color.parseColor("#39ff14"));
                getWindow().setStatusBarColor(Color.parseColor("#39ff14"));
                toolbar.setBackgroundColor(Color.parseColor("#39ff14"));




            }


            if (i == 1) {


                FragmentManager manager1 = getSupportFragmentManager();
                FragmentTransaction transaction1 = manager1.beginTransaction();
                transaction1.replace(R.id.framelayout_container, new ExpertFragment());
                transaction1.commit();



            }


            if (i == 2) {


                FragmentManager manager1 = getSupportFragmentManager();
                FragmentTransaction transaction1 = manager1.beginTransaction();
                transaction1.replace(R.id.framelayout_container, new ParentFragment());
                transaction1.commit();


            }




            return false;
        });





        bottomsheet = findViewById(R.id.bottom_dialog);
        bottomsheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog();

            }
        });

    }







    private void showDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet);

        LinearLayout support = dialog.findViewById(R.id.layoutEdit);
        LinearLayout settings = dialog.findViewById(R.id.layoutShare);
        LinearLayout news_app = dialog.findViewById(R.id.layoutUpload);
        LinearLayout team_app = dialog.findViewById(R.id.layoutPrint);


        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                FragmentManager manager1 = getSupportFragmentManager();
                FragmentTransaction transaction1 = manager1.beginTransaction();
                transaction1.replace(R.id.framelayout_container, new LanguageFragment());
                transaction1.addToBackStack(null);
                transaction1.commit();


            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                FragmentManager manager1 = getSupportFragmentManager();
                FragmentTransaction transaction1 = manager1.beginTransaction();
                transaction1.replace(R.id.framelayout_container, new SettingsFragment());
                transaction1.addToBackStack(null);
                transaction1.commit();


            }
        });

        news_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();


            }
        });

        team_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                FragmentManager manager1 = getSupportFragmentManager();
                FragmentTransaction transaction1 = manager1.beginTransaction();
                transaction1.replace(R.id.framelayout_container, new Team_Fragment());
                transaction1.addToBackStack(null);
                transaction1.commit();


            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);


    }
    }

