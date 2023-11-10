package com.example.samandar_demo.Activities;

import static android.os.Build.VERSION_CODES.O;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;


import com.airbnb.lottie.LottieAnimationView;
import com.example.samandar_demo.Gift_Fragment;
import com.example.samandar_demo.R;

import java.util.Timer;
import java.util.TimerTask;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;


public class Succes extends AppCompatActivity {


    LottieAnimationView view;
    private KonfettiView celebrate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succes);


        view = findViewById(R.id.animationView2);


celebrate = findViewById(R.id.play);











        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                celebrate.build()
                        .addColors(Color.RED, Color.MAGENTA, Color.GREEN, Color.YELLOW )
                        .setDirection(0.0 , 359.0)
                        .setSpeed(1f , 3f)
                        .setFadeOutEnabled(true)
                        .setTimeToLive(2000L)
                        .addShapes(Shape.RECT , Shape.CIRCLE)
                        .addSizes(new Size(12 , 5))
                        .setPosition(-50f , celebrate.getWidth() + 50f , -50f , -50f)
                        .streamFor(300 , 5000L);

            }
        }, 0, 5000); // 5 sekund





        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Gift_Fragment fragment = new Gift_Fragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.gift_container, fragment);
                transaction.addToBackStack(null); // Optional, to add the transaction to the back stack
                transaction.commit();








            }
        });





    }


}
