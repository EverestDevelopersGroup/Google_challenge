package com.example.samandar_demo.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.samandar_demo.R;
import com.google.android.material.card.MaterialCardView;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        MaterialCardView englishButton = findViewById(R.id.usa);

        MaterialCardView uzbekButton = findViewById(R.id.uzb);
       @SuppressLint({"MissingInflatedId", "LocalSuppress"}) MaterialCardView oldinga = findViewById(R.id.next);

        englishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("en");
            }
        });

        uzbekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("uz");
            }
        });


        oldinga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LanguageActivity.this , MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        recreate();
    }
}
