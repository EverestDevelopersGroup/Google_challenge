package com.example.samandar_dem.Activities;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.samandar_dem.R;

public class SplashScreen extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String FIRST_TIME_KEY = "firstTime";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean firstTime = preferences.getBoolean(FIRST_TIME_KEY, true);

        new Handler().postDelayed(() -> {
            Intent intent;
            if (firstTime) {
                intent = new Intent(SplashScreen.this, StartRegister.class);
                preferences.edit().putBoolean(FIRST_TIME_KEY, false).apply(); // Set the firstTime flag to false after first entry
            } else {
                intent = new Intent(SplashScreen.this, MainActivity.class); // Direct to MainActivity after the first entry
            }

            startActivity(intent);
            Animatoo.INSTANCE.animateDiagonal(SplashScreen.this);
            finish();
        }, 3000);
    }




}
