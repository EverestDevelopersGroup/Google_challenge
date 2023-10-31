package com.example.samandar_demo.Articulation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
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

public class VideoActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    VideoView videoView;

    Timer timer;
    MediaPlayer mediaPlayer;
    ProgressBar progressBar;

    private Camera camera;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private MediaRecorder mediaRecorder;
    private TextView result;
    private File outputFile;
    //    private Button openfolder;
    private File base64File;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_camera);




        videoView = findViewById(R.id.videoView);

        mediaPlayer = MediaPlayer.create(this, R.raw.wrong);
        progressBar = findViewById(R.id.progressBar);

        surfaceView = findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        result = findViewById(R.id.result_txt);

        final Button recordButton = findViewById(R.id.recordButton);

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
                }, 24500);
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




        recordButton.setOnClickListener(new View.OnClickListener() {
            boolean isRecording = false;

            @Override
            public void onClick(View v) {
                if (!isRecording) {
                    startRecording();
                    recordButton.setText("Videoni boshlash");
                } else {
                    stopRecording();
                    recordButton.setText("To`xtatish");
                    convertVideoFramesToBase64();
                }
                isRecording = !isRecording;

            }
        });
    }

    private void startRecording() {
        camera = Camera.open(1);
        mediaRecorder = new MediaRecorder();


        camera.unlock();
        mediaRecorder.setCamera(camera);

        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));

        outputFile = new File(getExternalFilesDir(null), "sample_video.mp4");
        mediaRecorder.setOutputFile(outputFile.getAbsolutePath());

        mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface());

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
            Toast.makeText(this, "Mashq bajarishni boshlang", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopRecording() {
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder = null;
            camera.lock();
            camera.release();
            Toast.makeText(this, "Video yozish to`xtatildi. Iltimos kuting..", Toast.LENGTH_SHORT).show();

            ACProgressPie dialog = new ACProgressPie.Builder(this)
                    .ringColor(Color.WHITE)
                    .pieColor(Color.WHITE)
                    .updateType(ACProgressConstant.PIE_AUTO_UPDATE)
                    .build();
            dialog.show();



        }
    }

    private void convertVideoFramesToBase64() {
        File samandarDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Samandar");

        if (!samandarDirectory.exists() && !samandarDirectory.mkdirs()) {
            Toast.makeText(this, "Saqlashda xatolik", Toast.LENGTH_SHORT).show();
            return;
        }

        File matrixFile = new File(samandarDirectory, "matrix.txt");

        try (FileOutputStream fos = new FileOutputStream(matrixFile, false);
             FileInputStream fis = new FileInputStream(outputFile)) {

            FileWriter writer = new FileWriter(matrixFile);

            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                String base64 = Base64.encodeToString(buffer, 0, bytesRead, Base64.DEFAULT);
                bufferedWriter.write(base64);
                bufferedWriter.append('/');

                bufferedWriter.newLine();




            }

            bufferedWriter.close();
            writer.close();


            Toast.makeText(this, "Saqlandi va yuborildi", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Saqlashda xatolik" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        FileRepository repository = new FileRepository();

        repository.setDataListener(new RepositoryService() {
            @Override
            public void loadTroops(FileResponse response) {

                result.setText(response.toString());

            }

            @Override
            public void loadError(String message) {
                result.setText(message.toString());

            }
        });

        repository.requestFileToApi(matrixFile,this);

    }


//    private void openSamandarFolder() {
//        File samandarDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Samandar");
//
//        if (samandarDirectory.exists() && samandarDirectory.isDirectory()) {
//            // Create an Intent to open a file manager app
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            Uri uri = Uri.fromFile(samandarDirectory);
//            intent.setDataAndType(uri, "resource/folder");
//            intent.addCategory(Intent.CATEGORY_DEFAULT);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//            try {
//                startActivity(intent);sdjjsoowndjf
//            } catch (Exception e) {
//                e.printStackTrace();
//                // Handle any exceptions that may occur when opening the folder
//            }
//        } else {
//            Toast.makeText(this, "Samandar folder not found", Toast.LENGTH_SHORT).show();
//        }}







    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}

