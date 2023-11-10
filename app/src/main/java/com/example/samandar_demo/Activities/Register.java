package com.example.samandar_demo.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ahmedteleb.buttons3d.Button3d;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.samandar_demo.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


public class Register extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_CAMERA_AND_MICROPHONE_AND_NOTIFICATION = 1001;
    private ShimmerFrameLayout shimmerContainer2;
    Button3d button;
    ImageView view;
    EditText ism , number , password;
SharedPreferences preferences;

    @RequiresApi(api = Build.VERSION_CODES.M)
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

        ism = findViewById(R.id.name);
        number = findViewById(R.id.number);
        password = findViewById(R.id.password);

        ism.requestFocus();
        number.requestFocus();
        password.requestFocus();


        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO)
                        != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.CAMERA,
                            Manifest.permission.RECORD_AUDIO,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },
                    PERMISSIONS_REQUEST_CAMERA_AND_MICROPHONE_AND_NOTIFICATION);
        } else {
            // Ruxsatlar olinishi mumkin
            Toast.makeText(this, "Ruxsatlar olinishi mumkin", Toast.LENGTH_SHORT).show();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "My Notification Channel";
            String description = "Description of My Notification Channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channel_id", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }






        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String name = ism.getText().toString();
               String nomer = number.getText().toString();
                String pasword = password.getText().toString();


                if (name.isEmpty() || nomer.isEmpty() || pasword.isEmpty()){

                    Toast.makeText(Register.this, "Iltimos ma'lumotlarni to'liq kiriting", Toast.LENGTH_SHORT).show();


                }
                else {
                    Intent intent = new Intent(Register.this , MainActivity.class);

                    startActivity(intent);
                    Animatoo.INSTANCE.animateSwipeLeft(Register.this);
                    finish();
                }


            }
        });


        startShimmer(); // Shimmer effektini boshlash

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/img_15.png?alt=media&token=24413f6c-f125-4048-9fd9-f693bcd7e085")
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