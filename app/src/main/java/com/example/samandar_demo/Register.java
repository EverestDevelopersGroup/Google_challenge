package com.example.samandar_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.ahmedteleb.buttons3d.Button3d;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


public class Register extends AppCompatActivity {


    private ShimmerFrameLayout shimmerContainer2;
    Button3d button;
    ImageView view;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_register);


        button = findViewById(R.id.register);
        view = findViewById(R.id.image_register);
        shimmerContainer2 = findViewById(R.id.shimmer_register);
        MaterialCardView cardView = findViewById(R.id.cardview);







        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        Intent i = new Intent(Register.this, MainActivity.class);


                        startActivity(i);
                        Animatoo.INSTANCE.animateDiagonal(Register.this);

                        finish();


                    }
                }, 400);


            }
        });


        startShimmer(); // Shimmer effektini boshlash

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/img_9.png?alt=media&token=70fe7693-8fda-408d-8725-4aad3f2876ec")
                .placeholder(R.drawable.loading)
                .into(view, new Callback() {
                    @Override
                    public void onSuccess() {
                        shimmerContainer2.stopShimmer();
                        shimmerContainer2.setShimmer(null);
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(Register.this, "Xatolik", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void startShimmer() {
        shimmerContainer2.startShimmer();
    }

//    private void stopShimmer() {
//        shimmerContainer2.stopShimmer();
//        shimmerContainer2.setVisibility(View.GONE);
//    }


}