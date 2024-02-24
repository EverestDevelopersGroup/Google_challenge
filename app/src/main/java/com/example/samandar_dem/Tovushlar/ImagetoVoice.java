package com.example.samandar_dem.Tovushlar;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.samandar_dem.Activities.MainActivity;
import com.example.samandar_dem.R;
import com.example.samandar_dem.VoiceAnalyzer.network.api.RepositoryService;
import com.example.samandar_dem.VoiceAnalyzer.network.response.TextResponse;
import com.example.samandar_dem.VoiceAnalyzer.recorder.AudioRecorder;
import com.example.samandar_dem.VoiceAnalyzer.repository.AudioRepository;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ImagetoVoice extends AppCompatActivity  {


    private VideoView videoView;
    ProgressBar tovushbar;
    ImageView playpause_tovush;
    private boolean isPaused = false;

    private static final int RECORD_AUDIO_PERMISSION_CODE = 123;

    AudioRecorder audioRecorder;

    private ImageView  images_list ;
    private String[] words = { "mashina" , "o'rdak"   ,  "quyon" , "maymun"};
    private int[] imageResources = {R.drawable.car,R.drawable.ordak,R.drawable.img_22,R.drawable.monkey};

    private Map<String, Integer> wordImageMap;
    private MediaPlayer  correct , incorrect;




    TextView startstop ;
    AudioRepository audioRepository = new AudioRepository();

    private static final int CAMERA_PERMISSION_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String outputFile = getExternalCacheDir().getAbsolutePath() + "/audio.3gp";
        audioRecorder = new AudioRecorder(outputFile);
        setContentView(R.layout.activity_imageto_voice);




        CircleImageView btn = findViewById(R.id.StartRecord_voice);
        TextView responseTxt = findViewById(R.id.responseData);


//        startstop2 = findViewById(R.id.startmashq2);





        images_list = findViewById(R.id.imageView_voice);

//        String videoName = getIntent().getStringExtra("videoName");
//        String videoUrl = getIntent().getStringExtra("videoUrl");

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


//
//        setTitle(videoName); // Activity başlığını değiştirme
//        String videoPath = videoUrl;
//        videoView.setZOrderMediaOverlay(true);
//        videoView.setVideoURI(Uri.parse(videoPath));
//        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                // Video tayyor bo'lganda ProgressBar'ni yashirish
//                tovushbar.setVisibility(View.INVISIBLE);
//                videoView.start();
//
//
//
//            }
//        });
//
//        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
//            @Override
//            public boolean onError(MediaPlayer mp, int what, int extra) {
//
//                tovushbar.setVisibility(View.INVISIBLE);
//                return false;
//            }
//
//        });
//
//        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                // Video tugaganida, MediaPlayer orqali musiqa boshlash
//                eslatma = MediaPlayer.create(getApplicationContext(), R.raw.mikrofonnibos); // O'zgarish kiritilsin
//                eslatma.start();
//
//            }
//        });
//
//        playpause_tovush.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isPaused) {
//                    // Agar video pause bo'lsa, uning davom etishini boshlash
//                    videoView.start();
//                    isPaused = false;
//                    playpause_tovush.setImageResource(R.drawable.pause);
//
//                } else {
//                    // Agar video davom etmoqda bo'lsa, uningni pause qilish
//                    videoView.pause();
//                    isPaused = true;
//                    playpause_tovush.setImageResource(R.drawable.play);
//
//                }
//            }
//        });
//restart.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        videoView.start();
//    }
//});

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
                                // Bu qisimda ProgressBar ni layoutda qo'shing
                                ProgressBar progressBar = findViewById(R.id.progressBar_voice);

                                if (wordImageMap.containsKey(currentWord)) {
                                    // Agar so'z topilsa, mos rasmni imageviewga joylashtir
                                    int imageResource = wordImageMap.get(currentWord);
                                    images_list.setImageResource(imageResource);

                                    correct = MediaPlayer.create(getApplicationContext(), R.raw.yaxhi2);
                                    correct.start();

                                    // ProgressDialog ni yaratish va uni chiqarish
                                    ProgressDialog progressDialog = new ProgressDialog(ImagetoVoice.this);
                                    progressDialog.setMessage("Please wait.."); // O'zingizga mos habarni qo'shing
                                    progressDialog.setCancelable(false); // Foydalanuvchidan bekor qilinmasligini so'rang
                                    progressDialog.show();

                                    // Keyingi rasmga o'tish uchun indeksni hisoblash
                                    int currentIndex = Arrays.asList(words).indexOf(currentWord);
                                    int nextIndex = (currentIndex + 1) % words.length; // Agar massiv oxiriga yetib kelgan bo'lsa, boshiga qaytadi

                                    // Keyingi rasmni ImageView ga joylashtirish
                                    int nextImageResource = imageResources[nextIndex];
                                    images_list.setImageResource(nextImageResource);

                                    // ProgressDialog ni yopish
                                    progressDialog.dismiss();

                                    // 5chi almashish bo'lganidan keyin
                                    if (nextIndex >= 3) {
                                        // ProgressDialog ni yaratish va uni chiqarish

                                        ProgressDialog finalProgressDialog = new ProgressDialog(ImagetoVoice.this);
                                        finalProgressDialog.setMessage("Please wait.."); // O'zingizga mos habarni qo'shing
                                        finalProgressDialog.setCancelable(false); // Foydalanuvchidan bekor qilinmasligini so'rang
                                        finalProgressDialog.show();


                                        // 5chi almashish bo'lganida, 2 sekund o'z ichida AlertDialog chiqarish
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                // AlertDialog ni yaratish
                                                correct = MediaPlayer.create(getApplicationContext(), R.raw.ajoyib);
                                                correct.start();
                                                AlertDialog.Builder builder = new AlertDialog.Builder(ImagetoVoice.this);
                                                builder.setTitle(R.string.tabriklaymiz);
                                                builder.setMessage(R.string.Siz_barcha_rasmlarni);
                                                builder.setPositiveButton(R.string.asosiy_bolimga, new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                       Intent intent = new Intent(ImagetoVoice.this , MainActivity.class);
                                                       startActivity(intent);
                                                       finish();
                                                    }
                                                });

                                                // AlertDialog ni ko'rsatish
                                                builder.show();

                                                // ProgressDialog ni yopish
                                                finalProgressDialog.dismiss();
                                            }
                                        }, 2000); // 2 sekunddan so'ng ishga tushiriladi
                                    }
                                } else {
                                    incorrect = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
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