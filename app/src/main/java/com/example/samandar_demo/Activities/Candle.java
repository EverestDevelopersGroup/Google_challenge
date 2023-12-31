package com.example.samandar_demo.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.samandar_demo.R;

import java.util.Random;


public class Candle extends AppCompatActivity {

    private static final int RECORD_AUDIO_PERMISSION_CODE = 1;
    private static final int AMPLITUDE_1_THRESHOLD = 5000;

    private static final int AMPLITUDE_6_THRESHOLD = 4500;
    private static final int AMPLITUDE_7_THRESHOLD = 4000;
    private static final int AMPLITUDE_8_THRESHOLD = 3500;
    private static final int AMPLITUDE_9_THRESHOLD = 3000;
    private static final int AMPLITUDE_10_THRESHOLD = 2500;
    //    private static final int AMPLITUDE_11_THRESHOLD = 10000;
    private static final int AMPLITUDE_off_THRESHOLD = 2700;

    private Thread thread;
    private int bufferSize;
    AudioRecord audioRecord;
    private ImageView candleImageView;
    private boolean isCandleOn = true;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candle);


        candleImageView = findViewById(R.id.shamust);


        bufferSize = AudioRecord.getMinBufferSize(44100, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);


        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, RECORD_AUDIO_PERMISSION_CODE);
        } else {
            startBlowingDetection();
        }

        // Set an OnClickListener to the layout so that when the user clicks on the screen,
        // the app will reset and everything starts again.
        findViewById(android.R.id.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetCandle();
            }
        });
    }

    private void startBlowingDetection() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                short[] audioBuffer = new short[bufferSize];
                if (ActivityCompat.checkSelfPermission(Candle.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling ActivityCompat#requestPermissions here
                    return;
                }

                if (audioRecord == null) {
                    audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, 44100, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSize);
                    audioRecord.startRecording();
                }

                while (!Thread.currentThread().isInterrupted()) {
                    int numRead = audioRecord.read(audioBuffer, 0, bufferSize);
                    if (numRead > 0) {
                        double amplitude = calculateAmplitude(audioBuffer, numRead);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (amplitude > AMPLITUDE_1_THRESHOLD) {
                                    extinguishCandle();
                                } else if (amplitude > AMPLITUDE_6_THRESHOLD) {
                                    flickerCandle();
                                } else if (amplitude > AMPLITUDE_7_THRESHOLD) {
                                    flickerCandle();
                                } else if (amplitude > AMPLITUDE_8_THRESHOLD) {
                                    flickerCandle();
                                } else if (amplitude > AMPLITUDE_9_THRESHOLD) {
                                    flickerCandle();
                                } else if (amplitude > AMPLITUDE_10_THRESHOLD) {
                                    flickerCandle();
                                } else {
                                    burnCandle();
                                }
                            }
                        });
                    }
                }
            }
        });
        thread.start();
    }


    private double calculateAmplitude(short[] audioBuffer, int numRead) {
        double amplitude = 0;

        for (int i = 0; i < numRead; i++) {
            amplitude += Math.abs(audioBuffer[i]);
        }

        if (numRead > 0) {
            amplitude /= numRead;
        }

        return amplitude;
    }

    private void extinguishCandle() {
        candleImageView.setImageResource(R.drawable.smoke);
        isCandleOn = false;
    }

    private void flickerCandle() {
        Random random = new Random();
        int randomNumber = random.nextInt(100);

        if (randomNumber > 70) {
            int randomImageIndex = random.nextInt(10) + 1; // 1 to 10 inclusive
            int resourceId = getResources().getIdentifier("candle_" + randomImageIndex + "a", "drawable", getPackageName());
            candleImageView.setImageResource(resourceId);
        } else {
            candleImageView.setImageResource(R.drawable.candle_11);
        }

        isCandleOn = true;
    }


    private void burnCandle() {
        if (!isCandleOn) {
            candleImageView.setImageResource(R.drawable.smoke_1);
            isCandleOn = true;
        }
    }

    private void resetCandle() {
        candleImageView.setImageResource(R.drawable.candle_11);
        isCandleOn = true;
    }

    @Override
    protected void onDestroy() {
        if (audioRecord != null) {
            audioRecord.stop();
            audioRecord.release();
        }
        super.onDestroy();
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
