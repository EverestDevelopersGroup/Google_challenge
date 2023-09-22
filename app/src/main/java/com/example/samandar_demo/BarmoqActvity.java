package com.example.samandar_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.samandar_demo.Articulation.Videoactivity;
import com.example.samandar_demo.R;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class BarmoqActvity extends AppCompatActivity {

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;
    private static final int STORAGE_PERMISSION_REQUEST_CODE = 101;

    private VideoView videoView;
    private SurfaceView surfaceView;
    Timer timer;
    MediaPlayer mediaPlayer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barmoq_actvity);

        videoView = findViewById(R.id.videoView_b);
        surfaceView = findViewById(R.id.surface_view_b);

        mediaPlayer = MediaPlayer.create(this, R.raw.succes);

        // Kamera va videoga huquq berishni tekshirish
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
        } else {
            // Kamera huquqi berilgan, kamerani ochish
            openCamera();
        }

        // Video fayliga yozish huquqini tekshirish
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_REQUEST_CODE);
        } else {
            // Fayllarga yozish huquqi berilgan, video avtomatik boshlangan
            playVideo();
        }
    }

    // Kamerani ochish uchun metod
    private void openCamera() {
        SurfaceView surfaceView = findViewById(R.id.surface_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();

        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                // Kamera ochilganida
                try {
                    android.hardware.Camera camera = android.hardware.Camera.open(1);
                    camera.setDisplayOrientation(90); // Kamerani 90 gradusga o'ngaytiring (vertical)
                    camera.setPreviewDisplay(holder);
                    camera.startPreview();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                // Surface o'lchamini o'zgartirganda
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                // Surface bekor qilinayotganda
            }
        });
    }

    // Video avtomatik boshlash uchun metod
    private void playVideo() {
        // VideoView uchun video manzilini o'rnating
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.tabassum; // R.raw.video_resource video manzili

        // VideoViewga video manzilini o'rnating va avtomatik boshlang
        videoView.setVideoPath(videoPath);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(false); // Video qaytariladigan bo'lishi uchun
                mediaPlayer.start(); // Video avtomatik boshlanadi

                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(BarmoqActvity.this , Succes.class);
                        startActivity(intent);
                        Animatoo.INSTANCE.animateDiagonal(BarmoqActvity.this);
                        // Musiqani boshlash
                        mediaPlayer.start();
                        finish();
                    }
                },13000);
            }
        });
    }

    // Huquq so'rovlari javobini qabul qilish uchun ushbu metodni qo'shing
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Kamera huquqi berilgan, kamerani ochish
                openCamera();
            }
        }

        if (requestCode == STORAGE_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Fayllarga yozish huquqi berilgan, video avtomatik boshlanadi
                playVideo();
            }
        }
    }
}
