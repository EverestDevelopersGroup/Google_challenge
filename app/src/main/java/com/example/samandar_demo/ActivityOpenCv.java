package com.example.samandar_demo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;

import android.os.Bundle;
import android.os.Environment;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;

import android.media.MediaRecorder;

import android.widget.Button;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class ActivityOpenCv extends AppCompatActivity {

    private MediaRecorder mediaRecorder;
    private Button recordButton;
    private TextView resultTextView;
    private boolean isRecording = false;


    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final String AUDIO_FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio.3gp";
    private static final String API_KEY = "29c9b786-3b20-4d2a-a1b0-e5df8a51fadd:1ac6a5cb-2a24-4052-a2a1-05913898a757"; // API kaliti

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_cv);

        recordButton = findViewById(R.id.record_button);
        resultTextView = findViewById(R.id.result_text_view);
        mediaRecorder = new MediaRecorder();

        if (checkPermissions()) {
            setupRecorder();
        } else {
            requestPermissions();
        }

        recordButton.setOnClickListener(view -> {
            if (!isRecording) {
                startRecording();
                isRecording = true;
                recordButton.setText("Stop Recording");
            } else {
                stopRecording();
                isRecording = false;
                recordButton.setText("Start Recording");
                sendAudioToAPI();
            }
        });
    }

    private boolean checkPermissions() {
        int audioPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        int storagePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return audioPermission == PackageManager.PERMISSION_GRANTED && storagePermission == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        String[] permissions = {Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                setupRecorder();
            } else {
                Toast.makeText(this, "Permission denied. You can't record audio without permissions.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setupRecorder() {
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(AUDIO_FILE_PATH);
    }

    private void startRecording() {
        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopRecording() {
        mediaRecorder.stop();
        mediaRecorder.reset();
    }

    private void sendAudioToAPI() {
        try {
            URL url = new URL("https://studio.mohir.ai/api/v1/stt");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", API_KEY);
            connection.setRequestProperty("Content-Type", "multipart/form-data");
            connection.setDoOutput(true);

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            FileInputStream fileInputStream = new FileInputStream(new File(AUDIO_FILE_PATH));
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            outputStream.close();
            fileInputStream.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Serverdan kelgan ma'lumotni o'qish
                // Natija server tomonidan qaytarilgan matn bo'ladi
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                showResult(result.toString());
            } else {
                System.out.println("HTTP Error: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showResult(String result) {
        resultTextView.setText(result);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isRecording) {
            stopRecording();
        }
        mediaRecorder.release();
    }
}
