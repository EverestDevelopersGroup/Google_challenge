package com.example.samandar_demo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.samandar_demo.R;
import com.example.samandar_demo.Succes;

import org.opencv.android.CameraActivity;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HarakatVideoActivity extends AppCompatActivity {

    VideoView videoView;

    Timer timer;
    MediaPlayer mediaPlayer;
    ProgressBar progressBar;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harakat_video);





        videoView = findViewById(R.id.videoView_harakat);

        mediaPlayer = MediaPlayer.create(this, R.raw.succes);
        progressBar = findViewById(R.id.progressBar);


        String videoName = getIntent().getStringExtra("videoName");
        String videoUrl = getIntent().getStringExtra("videoUrl");

        setTitle(videoName); // Activity sarlavhasini o'zgartirish


        String videoPath = videoUrl;

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse(videoPath));

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // Video tayyor bo'lganda ProgressBar'ni yashirish
                progressBar.setVisibility(View.INVISIBLE);
                videoView.start();


                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(HarakatVideoActivity.this, Succes.class);
                        startActivity(intent);
                        Animatoo.INSTANCE.animateDiagonal(HarakatVideoActivity.this);
                        mediaPlayer.start();
                        finish();

                    }
                }, 20000);
            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {

                progressBar.setVisibility(View.INVISIBLE);
                return false;
            }
        });



    }
}
