package com.example.samandar_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {



                Intent i = new Intent(SplashScreen.this, Register.class);


                startActivity(i);
                Animatoo.INSTANCE.animateDiagonal(SplashScreen.this);

                finish();






            }
        }, 3000);









    }
}