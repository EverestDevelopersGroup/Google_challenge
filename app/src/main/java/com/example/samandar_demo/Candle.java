package com.example.samandar_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;


public class Candle extends AppCompatActivity {

    private static final int RECORD_AUDIO_PERMISSION_CODE = 1;
    private static final int AMPLITUDE_1_THRESHOLD = 5000;
    private static final int AMPLITUDE_2_THRESHOLD = 6500;
    private static final int AMPLITUDE_3_THRESHOLD = 6000;
    private static final int AMPLITUDE_4_THRESHOLD = 5500;
    private static final int AMPLITUDE_5_THRESHOLD = 5000;
    private static final int AMPLITUDE_6_THRESHOLD = 4500;
    private static final int AMPLITUDE_7_THRESHOLD = 4000;
    private static final int AMPLITUDE_8_THRESHOLD = 3500;
    private static final int AMPLITUDE_9_THRESHOLD = 3000;
    private static final int AMPLITUDE_10_THRESHOLD = 2500;
    //    private static final int AMPLITUDE_11_THRESHOLD = 10000;
    private static final int AMPLITUDE_off_THRESHOLD = 4700;

    private AudioRecord audioRecord;
    private int bufferSize;

    private ImageView candleImageView;
    private boolean isCandleOn = true;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candle);


        candleImageView = findViewById(R.id.shamust);





        bufferSize = AudioRecord.getMinBufferSize(44100, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, 44100, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSize);

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
                                if (amplitude > AMPLITUDE_1_THRESHOLD) {
                                    extinguishCandle();
                                } else if (amplitude > AMPLITUDE_2_THRESHOLD) {
                                    flickerCandle();}
                                else if (amplitude > AMPLITUDE_3_THRESHOLD) {
                                    flickerCandle();
                                } else if (amplitude > AMPLITUDE_4_THRESHOLD) {
                                    flickerCandle();}
                                else if (amplitude > AMPLITUDE_5_THRESHOLD) {
                                    flickerCandle();
                                } else if (amplitude > AMPLITUDE_6_THRESHOLD) {
                                    flickerCandle();}
                                else if (amplitude > AMPLITUDE_7_THRESHOLD) {
                                    flickerCandle();
                                } else if (amplitude > AMPLITUDE_8_THRESHOLD) {
                                    flickerCandle(); }
                                else  if (amplitude > AMPLITUDE_9_THRESHOLD) {
                                    flickerCandle();
                                } else if (amplitude > AMPLITUDE_10_THRESHOLD) {
                                    flickerCandle();}
                                else if (amplitude > AMPLITUDE_off_THRESHOLD) {
                                    extinguishCandle();}

                                else {
                                    burnCandle();
                                }
                            }
                        });
                    }
                }
            }
        }).start();
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
        // Create random flickering effect by toggling between lit and extinguished state
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        if (randomNumber > 70) {
            candleImageView.setImageResource(R.drawable.candle_10a);

        }
        if (randomNumber > 70) {
            candleImageView.setImageResource(R.drawable.candle_9a);
        }
        if (randomNumber > 70) {
            candleImageView.setImageResource(R.drawable.candle_8a);
        }
        if (randomNumber > 70) {
            candleImageView.setImageResource(R.drawable.candle_7a);
        }
        if (randomNumber > 70) {
            candleImageView.setImageResource(R.drawable.candle_6a);
        }
        if (randomNumber > 70) {
            candleImageView.setImageResource(R.drawable.candle_5a);
        }
        if (randomNumber > 70) {
            candleImageView.setImageResource(R.drawable.candle_4a);
        }
        if (randomNumber > 70) {
            candleImageView.setImageResource(R.drawable.candle_3a);
        }
        if (randomNumber > 70) {
            candleImageView.setImageResource(R.drawable.candle_2a);
        }
        if (randomNumber > 70) {
            candleImageView.setImageResource(R.drawable.candle_1a);
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
