package com.example.samandar_demo.Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.example.samandar_demo.R;
import com.example.samandar_demo.VoiceAnalyzer.network.api.RepositoryService;
import com.example.samandar_demo.VoiceAnalyzer.network.response.TextResponse;
import com.example.samandar_demo.VoiceAnalyzer.recorder.AudioRecorder;
import com.example.samandar_demo.VoiceAnalyzer.repository.AudioRepository;
import com.google.android.material.button.MaterialButton;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityOpenCv extends AppCompatActivity {
    private static final int RECORD_AUDIO_PERMISSION_CODE = 123;

    AudioRecorder audioRecorder;

    private ImageView imageView;
    private String[] words = {"randa", "arra", "qor", "sariq", "rano", "kaptar", "xayr", "sayr", "burgut", "darra"};
    private int[] imageResources = {R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4, R.drawable.img_5, R.drawable.img_6, R.drawable.img_7, R.drawable.img_8, R.drawable.img_9, R.drawable.img_10};

    private Map<String, Integer> wordImageMap;
    private MediaPlayer mediaPlayer1, mediaPlayer2;



//    TextView startstop;
    AudioRepository audioRepository = new AudioRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String outputFile = getExternalCacheDir().getAbsolutePath() + "/audio.3gp";

        audioRecorder = new AudioRecorder(outputFile);
        setContentView(R.layout.activity_open_cv);
        CircleImageView btn = findViewById(R.id.StartRecord);
        TextView responseTxt = findViewById(R.id.responseData);



        imageView = findViewById(R.id.imageView);

        // So'zlar va rasmlar o'rtasidagi aloqani saqlash uchun HashMap yaratamiz
        wordImageMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            wordImageMap.put(words[i], imageResources[i]);
        }

        // textview bosilganda

//        mediaPlayer1 = MediaPlayer.create(this, R.raw.yaxhi2); // Musiqa 1
//        mediaPlayer2 = MediaPlayer.create(this, R.raw.celebrate); // Musiqa 2


//        startstop = findViewById(R.id.startstop);






        btn.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        RECORD_AUDIO_PERMISSION_CODE);
            } else {
                audioRecorder.startRecording();
//                startstop.setText("Ovoz yozish to`xtatildi");
                new Handler().postDelayed(() -> {
                    audioRecorder.stopRecording();
                    String filePath = audioRecorder.getOutputFile();
                    try {
                        audioRepository.requestApiToAudio(new File(filePath), this);
                        audioRepository.setDataListener(new RepositoryService() {
                            @Override
                            public void loadResponseData(TextResponse response) {
                                System.out.println(response.status);
                                responseTxt.setText(response.result.text.toString());

                                String currentWord = responseTxt.getText().toString().toLowerCase();

                                if (wordImageMap.containsKey(currentWord)) {
                                    // Agar so'z topilsa, mos rasmni imageviewga joylashtir
                                    int imageResource = wordImageMap.get(currentWord);
                                    imageView.setImageResource(imageResource);
                                } else {
                                    // Agar topilmagan so'z bo'lsa, "wrong" nomli rasmni imageviewga joylashtir
                                    imageView.setImageResource(R.drawable.img_40);
                                }
//                                startstop.setText("Ovoz yozish boshlandi");
                                audioRecorder.deleteRecording();
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }, 3000);
            }

        });

    }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RECORD_AUDIO_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                audioRecorder.startRecording();
            } else {
                Toast.makeText(this, "Permission Denied :(", Toast.LENGTH_SHORT).show();
            }
        }
    }
}