package com.example.samandar_demo.VideoLesson;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.samandar_demo.R;
import com.example.samandar_demo.Succes;
import com.example.samandar_demo.api.RepositoryService;
import com.example.samandar_demo.repo.FileRepository;
import com.example.samandar_demo.response.FileResponse;


import org.opencv.android.CameraActivity;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressCustom;
import cc.cloudist.acplibrary.ACProgressPie;

public class VideoLessonActivity extends AppCompatActivity  {

    VideoView videoView;



    ProgressBar progressBar;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_lesson);


        videoView = findViewById(R.id.videoView);


        progressBar = findViewById(R.id.progressBar_lesson);


        String videoName = getIntent().getStringExtra("videoName");
        String videoUrl = getIntent().getStringExtra("videoUrl");

        setTitle(videoName); // Activity sarlavhasini o'zgartirish


        String videoPath = videoUrl;

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setBackgroundColor(Color.TRANSPARENT);
        videoView.setZOrderOnTop(true);
        videoView.requestFocus();

        videoView.setZOrderMediaOverlay(true);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse(videoPath));


        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // Video tayyor bo'lganda ProgressBar'ni yashirish
                progressBar.setVisibility(View.INVISIBLE);
                videoView.start();



            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {

                progressBar.setVisibility(View.INVISIBLE);
                return false;
            }

        });


//        openfolder = findViewById(R.id.showfolder);

//        openfolder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openSamandarFolder();
//            }
//        });





    }
}

