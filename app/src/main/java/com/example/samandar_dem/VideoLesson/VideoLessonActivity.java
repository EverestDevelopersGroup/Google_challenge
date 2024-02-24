package com.example.samandar_dem.VideoLesson;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.samandar_dem.R;

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

