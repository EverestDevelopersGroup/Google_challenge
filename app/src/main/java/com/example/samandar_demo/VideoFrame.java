package com.example.samandar_demo;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.samandar_demo.api.RepositoryService;
import com.example.samandar_demo.repo.FileRepository;
import com.example.samandar_demo.response.FileResponse;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

public class VideoFrame extends AppCompatActivity implements SurfaceHolder.Callback {

    private Camera camera;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private MediaRecorder mediaRecorder;
    private TextView result;
    private File outputFile;
//    private Button openfolder;
    private File base64File;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_frame);

        surfaceView = findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        result = findViewById(R.id.result_txt);

        final Button recordButton = findViewById(R.id.recordButton);
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
                    recordButton.setText("Stop Recording");
                } else {
                    stopRecording();
                    recordButton.setText("Record");
                    convertVideoFramesToBase64();
                }
                isRecording = !isRecording;

            }
        });
    }

    private void startRecording() {
        camera = Camera.open();
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
            Toast.makeText(this, "Recording started", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, "Recording stopped", Toast.LENGTH_SHORT).show();
        }
    }

    private void convertVideoFramesToBase64() {
        File samandarDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Samandar");

        if (!samandarDirectory.exists() && !samandarDirectory.mkdirs()) {
            Toast.makeText(this, "Failed to create the directory", Toast.LENGTH_SHORT).show();
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


            Toast.makeText(this, "File saved in Samandar directory", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save: " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
}
