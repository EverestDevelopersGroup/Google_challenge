package com.example.samandar_demo.Tovushlar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.example.samandar_demo.R;
import com.example.samandar_demo.VoiceAnalyzer.network.api.RepositoryService;
import com.example.samandar_demo.VoiceAnalyzer.network.response.TextResponse;
import com.example.samandar_demo.VoiceAnalyzer.recorder.AudioRecorder;
import com.example.samandar_demo.VoiceAnalyzer.repository.AudioRepository;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class TovushVideoActivity extends AppCompatActivity  {


    private VideoView videoView;
    ProgressBar tovushbar;

    private static final int RECORD_AUDIO_PERMISSION_CODE = 123;

    AudioRecorder audioRecorder;

    private ImageView imageView , restart;
    private String[] words = {"randa","ramda", "arra", "qor", "sariq", "ra'no", "kaptar", "xayr", "sayr", "burgut" , "e'lon" , "alo" , "olim" , "uzum" , "o'rdak"  , "ilon" , "malina" , "muz" , "mashina" , "maymun"};
    private int[] imageResources = {R.drawable.randa,R.drawable.randa, R.drawable.arra, R.drawable.qor, R.drawable.yellow, R.drawable.rano, R.drawable.kaptar, R.drawable.xayr, R.drawable.sayr, R.drawable.burgut , R.drawable.elon , R.drawable.alo , R.drawable.olim , R.drawable.uzum , R.drawable.ordak , R.drawable.ilon , R.drawable.malina ,R.drawable.muz, R.drawable.mashina , R.drawable.maymun};

    private Map<String, Integer> wordImageMap;
    private MediaPlayer eslatma , correct , incorrect;




    TextView startstop ;
    AudioRepository audioRepository = new AudioRepository();

    private static final int CAMERA_PERMISSION_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String outputFile = getExternalCacheDir().getAbsolutePath() + "/audio.3gp";
        audioRecorder = new AudioRecorder(outputFile);
        setContentView(R.layout.activity_tovush_video);


        videoView = findViewById(R.id.videoView_tovush);
        CircleImageView btn = findViewById(R.id.StartRecord);
        TextView responseTxt = findViewById(R.id.responseData);
        tovushbar = findViewById(R.id.progressBar_tovush);
        startstop = findViewById(R.id.startmashq);
//        startstop2 = findViewById(R.id.startmashq2);
        restart = findViewById(R.id.restart);



        imageView = findViewById(R.id.imageView_tovush);

        String videoName = getIntent().getStringExtra("videoName");
        String videoUrl = getIntent().getStringExtra("videoUrl");

        Intent receivedIntent = getIntent();

        if (receivedIntent != null) {
            if (receivedIntent.hasExtra("Unlilar")) {
                String unliLar = receivedIntent.getStringExtra("Unlilar");
                startstop.setText(unliLar);

                // 'Unlilar' deb nomlangan matn boshqacha aktivitetdan kelgan ma'lumot
                // uni foydalanish
            } else if (receivedIntent.hasExtra("M")) {
                String mWords = receivedIntent.getStringExtra("M");
                startstop.setText(mWords);
                // 'M' deb nomlangan matn boshqacha aktivitetdan kelgan ma'lumot
                // uni foydalanish
            } else if (receivedIntent.hasExtra("R")) {
                String rWords = receivedIntent.getStringExtra("R");
                startstop.setText(rWords);
                // 'R' deb nomlangan matn boshqacha aktivitetdan kelgan ma'lumot
                // uni foydalanish
            }}



        setTitle(videoName); // Activity başlığını değiştirme
        String videoPath = videoUrl;
        videoView.setZOrderMediaOverlay(true);
        videoView.setVideoURI(Uri.parse(videoPath));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // Video tayyor bo'lganda ProgressBar'ni yashirish
                tovushbar.setVisibility(View.INVISIBLE);
                videoView.start();



            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {

                tovushbar.setVisibility(View.INVISIBLE);
                return false;
            }

        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // Video tugaganida, MediaPlayer orqali musiqa boshlash
                eslatma = MediaPlayer.create(getApplicationContext(), R.raw.mikrofonnibos); // O'zgarish kiritilsin
                eslatma.start();

            }
        });


restart.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        videoView.start();
    }
});

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
                                    correct = MediaPlayer.create(getApplicationContext(), R.raw.yaxhi2); // O'zgarish kiritilsin
                                    correct.start();
                                } else {
                                    // Agar topilmagan so'z bo'lsa, "wrong" nomli rasmni imageviewga joylashtir
                                    imageView.setImageResource(R.drawable.cross);
                                    incorrect = MediaPlayer.create(getApplicationContext(), R.raw.wrong); // O'zgarish kiritilsin
                                    incorrect.start();
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

        // Kamera iznini iste
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.CAMERA},
//                    CAMERA_PERMISSION_CODE);
//        } else {
//            // Kamera izni verilmişse, kamera önizlemesini başlat
//            startCameraPreview();
//        }

        // VideoView ayarları

    }


//    @Override
//    public void surfaceCreated(SurfaceHolder holder) {
//        // Kamera önizlemesini başlat
//        startCameraPreview();
//    }
//
//    @Override
//    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//        // Handle surface changes if needed
//    }
//
//    @Override
//    public void surfaceDestroyed(SurfaceHolder holder) {
//        if (camera != null) {
//            camera.stopPreview();
//            camera.release();
//            camera = null;
//        }
//    }
//
//    private void startCameraPreview() {
//        try {
//            camera = Camera.open(1);
//            camera.setDisplayOrientation(90);
//            camera.setPreviewDisplay(surfaceHolder);
//            camera.startPreview();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        if (requestCode == CAMERA_PERMISSION_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Kamera izni verildi, kamera önizlemesini başlat
//                startCameraPreview();
//            } else {
//                // Kamera izni reddedildi, kullanıcıya bilgi ver
//            }
//        }
//    }
}