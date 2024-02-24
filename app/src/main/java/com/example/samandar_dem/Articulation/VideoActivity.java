package com.example.samandar_dem.Articulation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.example.samandar_dem.R;
import java.io.IOException;

public class VideoActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private Camera camera;
    private boolean isPaused = false;

    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private VideoView videoView;
    ProgressBar videobar;
    ImageView restart_video , playpause_main;

    private static final int CAMERA_PERMISSION_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_frame);

        surfaceView = findViewById(R.id.surfaceView_video);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        videoView = findViewById(R.id.videoView_video);
        videobar = findViewById(R.id.progressBar_video);
        restart_video = findViewById(R.id.restart_main);
        playpause_main = findViewById(R.id.playpause_main);

        // Kamera iznini iste
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_CODE);
        } else {
            // Kamera izni verilmişse, kamera önizlemesini başlat
            startCameraPreview();
        }

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
                videobar.setVisibility(View.INVISIBLE);
                videoView.start();



            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {

                videobar.setVisibility(View.INVISIBLE);
                return false;
            }

        });

        playpause_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPaused) {
                    // Agar video pause bo'lsa, uning davom etishini boshlash
                    videoView.start();
                    isPaused = false;
                    playpause_main.setImageResource(R.drawable.pause);

                } else {
                    // Agar video davom etmoqda bo'lsa, uningni pause qilish
                    videoView.pause();
                    isPaused = true;
                    playpause_main.setImageResource(R.drawable.play);

                }
            }
        });
        restart_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.start();

            }
        });

    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Kamera önizlemesini başlat
        startCameraPreview();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Handle surface changes if needed
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    private void startCameraPreview() {
        try {
            camera = Camera.open(1);
            camera.setDisplayOrientation(90);
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Kamera izni verildi, kamera önizlemesini başlat
                startCameraPreview();
            } else {
                // Kamera izni reddedildi, kullanıcıya bilgi ver
            }
        }
    }
}