package com.example.samandar_dem.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samandar_dem.R;

import pl.droidsonroids.gif.GifImageView;

public class PinWheel extends AppCompatActivity {

    private static final int RECORD_AUDIO_PERMISSION_CODE = 1;
    private static final int AMPLITUDE_1_THRESHOLD = 5000;
    private Thread thread;

    private AudioRecord audioRecord;
    private boolean isRecording = false;
    private int bufferSize;

    private GifImageView candleImageView;
    TextView amplitudeResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_wheel);

        candleImageView = findViewById(R.id.pinwheel);
        amplitudeResult = findViewById(R.id.amplituda_result);

        bufferSize = AudioRecord.getMinBufferSize(44100, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, RECORD_AUDIO_PERMISSION_CODE);
        } else {
            startBlowingDetection();
        }
    }

    private void startBlowingDetection() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                short[] audioBuffer = new short[bufferSize];
                if (ActivityCompat.checkSelfPermission(PinWheel.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling ActivityCompat#requestPermissions here
                    return;
                }

                if (audioRecord == null) {
                    audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, 44100, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSize);
                    audioRecord.startRecording();
                }

                while (!Thread.currentThread().isInterrupted()) {
                    int numRead = audioRecord.read(audioBuffer, 0, bufferSize);
                    if (numRead >= 0) {
                        double amplitude = calculateAmplitude(audioBuffer, numRead);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (amplitude > AMPLITUDE_1_THRESHOLD) {
                                    startPinWheel();
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
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startBlowingDetection();
            } else {
                Toast.makeText(this, "Ovozni aniqlash uchun mikrofon ruxsati zarur!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
