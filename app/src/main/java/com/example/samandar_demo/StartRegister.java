package com.example.samandar_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ahmedteleb.buttons3d.Button3d;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class StartRegister extends AppCompatActivity {


    Button3d boshlash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_register);

        boshlash = findViewById(R.id.boshlash);

        boshlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartRegister.this , VideoFrame.class);
                startActivity(intent);
                Animatoo.INSTANCE.animateSlideDown(StartRegister.this);
            }
        });

    }
}