package com.example.samandar_demo.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.samandar_demo.R;
import com.google.android.material.card.MaterialCardView;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if the language is already selected
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences.contains("language")) {
            String selectedLanguage = preferences.getString("language", "default_language_code");
            setLocale(selectedLanguage);
            startMainActivity();
        } else {
            setContentView(R.layout.activity_language);
            initializeLanguageSelection();
        }
    }

    private void initializeLanguageSelection() {
        MaterialCardView englishButton = findViewById(R.id.usa);
        MaterialCardView uzbekButton = findViewById(R.id.uzb);
        MaterialCardView rusButton = findViewById(R.id.russia);
        MaterialCardView oldinga = findViewById(R.id.next);

        englishButton.setOnClickListener(v -> setLocale("en"));
        uzbekButton.setOnClickListener(v -> setLocale("uz"));
        rusButton.setOnClickListener(v -> setLocale("ru"));
        oldinga.setOnClickListener(v -> startMainActivity());
    }

    private void setLocale(String languageCode) {
        // ... (your existing setLocale method)
        Locale locale = new Locale(languageCode);
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().putString("language", languageCode).apply();
        recreate();
    }

    private void startMainActivity() {
        Intent intent = new Intent(LanguageActivity.this, SplashScreen.class);
        startActivity(intent);
        finish(); // Finish this activity to prevent it from being shown again.
    }
}
