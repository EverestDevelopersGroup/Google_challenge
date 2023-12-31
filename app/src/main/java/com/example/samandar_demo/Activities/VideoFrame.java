package com.example.samandar_demo.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.ahmedteleb.buttons3d.Button3d;
import com.example.liveness.LivenessActivity;
import com.example.samandar_demo.R;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class VideoFrame extends AppCompatActivity implements SurfaceHolder.Callback  {
    Timer timer = new Timer();
    CountDownTimer mCountDownTimer;
    int time = 51000;

    private boolean isPaused = false;
    private MediaPlayer attention;
    private Button3d playButton;
//    private KonfettiView celebrate;
    private Camera camera;
//    private boolean isPaused = false;
    private SurfaceView surfaceView;
    TextView startmashq;
    ProgressBar bar;
    boolean isUserWaited = false; // Boshlang'ich holati
    ImageView restart_nashq , playpause_artic;
    long waitingTime = 45000;
    private SurfaceHolder surfaceHolder;
    private VideoView videoView;
    private static final int CAMERA_PERMISSION_CODE = 101;
    private static final int YOUR_REQUEST_CODE = 120;


    private int[] musicResources = {R.raw.yaxhi2}; // Musiqa resurslari

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_camera);


        LivenessActivity livenessActivity = new LivenessActivity();

        // Kameraga murojaat qilish

        playButton = findViewById(R.id.btnplay);
//        celebrate = findViewById(R.id.play);

        surfaceView = findViewById(R.id.surfaceView);
        bar = findViewById(R.id.progressBar_articulation);
        playpause_artic = findViewById(R.id.playpause_articulation);
        surfaceHolder = surfaceView.getHolder();
        startmashq = findViewById(R.id.startmashq);
        restart_nashq = findViewById(R.id.restart_articulation);
        surfaceHolder.addCallback(this);
        videoView = findViewById(R.id.videoView);


MediaPlayer start = MediaPlayer.create(this , R.raw.tayyormisan);







        // Kamera iznini iste
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_CODE);
        } else {
            // Kamera izni verilmişse, kamera önizlemesini başlat
            startCameraPreview();
        }

        // VideoView ayarları
        String videoName = getIntent().getStringExtra("videoName");
        String videoUrl = getIntent().getStringExtra("videoUrl");

        setTitle(videoName); // Activity başlığını değiştirme
        String videoPath = videoUrl;
        videoView.setZOrderMediaOverlay(true);
        videoView.setVideoURI(Uri.parse(videoPath));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // Video tayyor bo'lganda ProgressBar'ni yashirish
                bar.setVisibility(View.INVISIBLE);
                videoView.start();



            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {

                bar.setVisibility(View.INVISIBLE);
                return false;
            }

        });


        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // Video tugaganida, MediaPlayer orqali musiqa boshlash
                attention = MediaPlayer.create(getApplicationContext(), R.raw.tayyormisan);
                playButton.setEnabled(true);
                // O'zgarish kiritilsin
                attention.start();
                playButton.setButtonColor(Color.GREEN);


            }
        });

        playpause_artic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPaused) {
                    // Agar video pause bo'lsa, uning davom etishini boshlash
                    videoView.start();
                    playButton.setEnabled(false);
                    isPaused = false;
                    playpause_artic.setImageResource(R.drawable.pause);

                } else {
                    // Agar video davom etmoqda bo'lsa, uningni pause qilish
                    videoView.pause();
                    isPaused = true;
                    playButton.setEnabled(true);
                    playpause_artic.setImageResource(R.drawable.play);

                }
            }
        });
        restart_nashq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.start();
                playpause_artic.setImageResource(R.drawable.pause);

            }
        });



//
//        Button pauseProcess = findViewById(R.id.pausemashq);
//        pauseProcess.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isPaused) {
//                    // Jarayonni davom ettirish
//                    isPaused = false;
//                    mediaPlayer.start(); // Musiqa davom etadi
////                    randomBackgroundWithMusic(); // Fon almashishni davom ettiradi
//                } else {
//                    // Jarayonni pauza qilish
//                    isPaused = true;
//                    mediaPlayer.pause(); // Musiqa pauza qilinadi
//                }
//            }
//        });





        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrate();
                randomBackgroundWithMusic();
                playButton.setEnabled(false);
//                start.start();
                startmashq.setText(R.string.kameraga_qarab_mashq);

//               startAutomaticCelebrate();

                createCountDownTimer(time);




            }
        });
    }



//    private void playRandomMusic() {
//
//
//        int randomInterval = new Random().nextInt(6000) + 1000; // 1-6 sekund oralig'ida tasodifiy intervalida
//        int randomIndex = new Random().nextInt(musicResources.length); // Random musiqa tanlash
//        mediaPlayer = MediaPlayer.create(this, musicResources[randomIndex]);
//
//        mediaPlayer.start();
//
//        // Biron vaqt intervalida yangi musiqa ijro etish uchun
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                playRandomMusic();
//            }
//        }, randomInterval); // Tasodifiy vaqt oralig'ida yangi musiqa
//    }

    private void randomBackgroundWithMusic() {
        LinearLayout yourLinearLayout = findViewById(R.id.colorful_linear);

        int[] backgrounds = {R.drawable.oval_shape, R.drawable.oval_shape2 , R.drawable.oval_shape3};
        final int[] currentIndex = {0};
        int randomInterval = new Random().nextInt(6000) + 1000; // 1-7 sekund oralig'ida tasodifiy intervalida

        int[] musicResources = {R.raw.yaxhi2 , R.raw.wrong}; // Musiqa resurslari
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, musicResources[0]);
        final MediaPlayer mediaPlayer2 = MediaPlayer.create(this, musicResources[1]);





        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int nextBackground = backgrounds[currentIndex[0]];
                        yourLinearLayout.setBackgroundResource(nextBackground);
                        currentIndex[0] = (currentIndex[0] + 1) % backgrounds.length;

                        if (nextBackground == R.drawable.oval_shape2) {
                            mediaPlayer.start(); // Musiqa ijro etish





                        }
                         if (nextBackground == R.drawable.oval_shape3){
                            mediaPlayer2.start();
                        }

                    }
                });
            }
        }, randomInterval, randomInterval);





    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == YOUR_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            // Your logic after returning from the Success activity
//            finish(); // To close this activity and return to the previous one
//        }
//    }



    private void createCountDownTimer(int timeC) {
        final int[] times = {timeC};
        mCountDownTimer = new CountDownTimer(times[0], 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                times[0] -= 1000;
                time -= 1000;



            }

            @Override
            public void onFinish() {
                //Do what you want
                times[0] = 0;
                times[0] = 0;
                Intent intentResult = new Intent(VideoFrame.this, Succes.class);

                intentResult.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentResult);
                finish();


            }
        }.start();

    }


    private void pauseTime() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }



    private void Vibrate(){
        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        final VibrationEffect vibrationEffect5;


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {


            vibrationEffect5 = VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK);

            vibrator.cancel();

            vibrator.vibrate(vibrationEffect5);
        }

    }





    @Override
    protected void onPause() {
        super.onPause();
        if (mCountDownTimer != null) {
            pauseTime();
        }
        if (timer != null) {
            timer.cancel();
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
        pauseTime();
    }



//    private void celebrateUSer(){
//        celebrate.build()
//                .addColors(Color.RED, Color.MAGENTA, Color.GREEN, Color.YELLOW )
//                .setDirection(0.0 , 359.0)
//                .setSpeed(1f , 3f)
//                .setFadeOutEnabled(true)
//                .setTimeToLive(2000L)
//                .addShapes(Shape.RECT , Shape.CIRCLE)
//                .addSizes(new Size(12 , 5))
//                .setPosition(-50f , celebrate.getWidth() + 50f , -50f , -50f)
//                .streamFor(300 , 5000L);
//
//    }
//
//    private void startAutomaticCelebrate() {
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                celebrateUSer();
//            }
//        }, 0, 5000); // 5 sekund
//
//
//
//
//
//
//
//
//
//
//
//}

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Kamera önizlemesini başlat
        startCameraPreview();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Handle surface changes if needed
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    private void startCameraPreview() {
        try {
            camera = Camera.open(1);
            camera.setDisplayOrientation(90);
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Kamera izni verildi, kamera önizlemesini başlat
                startCameraPreview();
            } else {
                // Kamera izni reddedildi, kullanıcıya bilgi ver
            }
        }
    }






}
