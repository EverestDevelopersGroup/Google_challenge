package com.example.samandar_demo.Articulation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
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

public class VideoActivity extends CameraActivity {

    VideoView videoView;
    CameraBridgeViewBase cameraBridgeViewBase;
    Timer timer;
    MediaPlayer mediaPlayer;
    ProgressBar progressBar;
    @Override
    protected List<? extends CameraBridgeViewBase> getCameraViewList() {
        return Collections.singletonList(cameraBridgeViewBase);
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_camera);





        videoView = findViewById(R.id.videoView);
        cameraBridgeViewBase = findViewById(R.id.camera_view);
        mediaPlayer = MediaPlayer.create(this, R.raw.succes);
        progressBar = findViewById(R.id.progressBar);


        String videoName = getIntent().getStringExtra("videoName");
        String videoUrl = getIntent().getStringExtra("videoUrl");

        setTitle(videoName); // Activity sarlavhasini o'zgartirish


        String videoPath = videoUrl;

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setBackgroundColor(Color.TRANSPARENT);
        videoView.setZOrderOnTop(true);

        videoView.setZOrderMediaOverlay(true);
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
                        mediaPlayer.start();
                        Intent intent = new Intent(VideoActivity.this, Succes.class);
                        startActivity(intent);
                        Animatoo.INSTANCE.animateDiagonal(VideoActivity.this);

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


        cameraBridgeViewBase.setCvCameraViewListener(new CameraBridgeViewBase.CvCameraViewListener2() {
            @Override
            public void onCameraViewStarted(int width, int height) {

            }

            @Override
            public void onCameraViewStopped() {
            }

            public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
                Mat rgba = inputFrame.rgba();
                Mat rotated = new Mat();

                // Matni 90 gradusga burish
                Core.transpose(rgba, rotated);
                Core.flip(rotated, rotated, -1);

                return rotated;
            }
        });

        getPermission();

        if (OpenCVLoader.initDebug()) {
            cameraBridgeViewBase.enableView();
        }
    }


    void getPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 101);
                }
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            getPermission();
        }
    }
}

