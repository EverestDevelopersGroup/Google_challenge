package com.example.samandar_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import pl.droidsonroids.gif.GifImageView;

public class PinWheel extends AppCompatActivity {


    private static final int RECORD_AUDIO_PERMISSION_CODE = 1;
    private static final int AMPLITUDE_1_THRESHOLD = 5000;
//    private static final int AMPLITUDE_2_THRESHOLD = 3500;

    private AudioRecord audioRecord;
    private int bufferSize;

    private GifImageView candleImageView;
    private boolean isCandleOn = true;
    TextView amplituda_result;



    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_wheel);






        candleImageView = findViewById(R.id.pinwheel);
        amplituda_result = findViewById(R.id.amplituda_result);


        bufferSize = AudioRecord.getMinBufferSize(44100, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, 44100, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSize);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, RECORD_AUDIO_PERMISSION_CODE);
        } else {
            startBlowingDetection();
        }

        // Set an OnClickListener to the layout so that when the user clicks on the screen,
        // the app will reset and everything starts again.

    }

    private void startBlowingDetection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                short[] audioBuffer = new short[bufferSize];
                audioRecord.startRecording();

                while (!Thread.currentThread().isInterrupted()) {
                    int numRead = audioRecord.read(audioBuffer, 0, bufferSize);
                    if (numRead > 0) {
                        double amplitude = calculateAmplitude(audioBuffer, numRead);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (amplitude >= AMPLITUDE_1_THRESHOLD) {
                                    amplituda_result.setText(String.valueOf(amplitude));
                                    startPinWheel();

                                }

                            }
                        });
                    }
                }
            }
        }).start();
    }

    private double calculateAmplitude(short[] audioBuffer, int numRead) {
        int amplitude = 0;

        for (int i = 0; i < numRead; i++) {
            amplitude += Math.abs(audioBuffer[i]);

        }

        if (numRead > 0) {
            amplitude /= numRead;

        }

        return amplitude;
    }

    private void startPinWheel() {
        candleImageView.setImageResource(R.drawable.firfirak);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                candleImageView.setImageResource(R.drawable.fir10_prev_ui);



            }
        }, 7000);



        isCandleOn = false;
    }






    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (audioRecord != null) {
            audioRecord.stop();
            audioRecord.release();
            audioRecord = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RECORD_AUDIO_PERMISSION_CODE) {
            if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startBlowingDetection();
            } else {
                Toast.makeText(this, "Ovozni aniqlash uchun mikrofon ruxsati zarur!", Toast.LENGTH_SHORT).show();


            }
        }
    }
}
