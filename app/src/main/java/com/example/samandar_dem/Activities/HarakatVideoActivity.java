package com.example.samandar_dem.Activities;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.samandar_dem.R;

public class HarakatVideoActivity extends AppCompatActivity {


    private VideoView videoView;

    private static final int CAMERA_PERMISSION_CODE = 101;
    ProgressBar bar_harakat;
    ImageView restart_harakat;
    ImageView play_pause;

    private boolean isPaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harakat_video);


        videoView = findViewById(R.id.videoView_harakat_video);
        bar_harakat = findViewById(R.id.progressBar_harakat);
        restart_harakat = findViewById(R.id.restart_harakat);
        play_pause = findViewById(R.id.playpause_harakat);


        // VideoView ayarları
        String videoName = getIntent().getStringExtra("videoName");
        String videoUrl = getIntent().getStringExtra("videoUrl");

        setTitle(videoName); // Activity başlığını değiştirme
        String videoPath = videoUrl;
        videoView.setZOrderMediaOverlay(true);
        videoView.setVideoURI(Uri.parse(videoPath));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // Video tayyor bo'lganda ProgressBar'ni yashirish
                bar_harakat.setVisibility(View.INVISIBLE);
                videoView.start();


            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {

                bar_harakat.setVisibility(View.INVISIBLE);
                return false;
            }

        });

        play_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPaused) {
                    // Agar video pause bo'lsa, uning davom etishini boshlash
                    videoView.start();
                    isPaused = false;
                    play_pause.setImageResource(R.drawable.pause);

                } else {
                    // Agar video davom etmoqda bo'lsa, uningni pause qilish
                    videoView.pause();
                    isPaused = true;
                    play_pause.setImageResource(R.drawable.play);

                }
            }
        });


        restart_harakat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.start();
                play_pause.setImageResource(R.drawable.pause);

            }
        });
    }


}

