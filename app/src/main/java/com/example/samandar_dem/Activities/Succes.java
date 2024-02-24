package com.example.samandar_dem.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;


import com.airbnb.lottie.LottieAnimationView;
import com.example.samandar_dem.R;

import java.util.Timer;
import java.util.TimerTask;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;


public class Succes extends AppCompatActivity {


    LottieAnimationView view;
    private KonfettiView celebrate;

    MediaPlayer urra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succes);





        celebrate = findViewById(R.id.play);


        urra = MediaPlayer.create(getApplicationContext(), R.raw.ajoyib); // O'zgarish kiritilsin
        urra.start();


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                celebrate.build()
                        .addColors(Color.RED, Color.MAGENTA, Color.GREEN, Color.YELLOW)
                        .setDirection(0.0, 359.0)
                        .setSpeed(1f, 3f)
                        .setFadeOutEnabled(true)
                        .setTimeToLive(2000L)
                        .addShapes(Shape.RECT, Shape.CIRCLE)
                        .addSizes(new Size(12, 5))
                        .setPosition(-50f, celebrate.getWidth() + 50f, -50f, -50f)
                        .streamFor(300, 5000L);

            }
        }, 0, 5000); // 5 sekund


    }

    @Override
    public void onBackPressed() {
        // Create the object of AlertDialog Builder class
        super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(Succes.this);

        // Set the message show for the Alert time
        builder.setMessage(R.string.asosiy_bolimga);

        // Set Alert Title
        builder.setTitle(R.string.app_name);

        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton(R.string.ha, (DialogInterface.OnClickListener) (dialog, which) -> {
            Intent intent = new Intent(Succes.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setNegativeButton(R.string.yoq, (DialogInterface.OnClickListener) (dialog, which) -> {
            // If user click no then dialog box is canceled.
            dialog.cancel();
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }


}
