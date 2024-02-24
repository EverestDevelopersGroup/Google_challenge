package com.example.samandar_dem.Activities;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.samandar_dem.R;

public class Picovoice extends AppCompatActivity {
    private int foundDifferencesCount = 0;
    MediaPlayer tabrik;
    private static final int TOTAL_DIFFERENCES = 5; // Umumiy farqlar soni

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picovoice);



        ImageView ans31 = findViewById(R.id.ans31_pico);
        ImageView ans32 = findViewById(R.id.ans32_pico);
        ImageView ans41 = findViewById(R.id.ans41_pico);
        ImageView ans42 = findViewById(R.id.ans42_pico);
        ImageView ans51 = findViewById(R.id.ans51_pico);
        ImageView ans52 = findViewById(R.id.ans52_pico);
        ImageView ans53 = findViewById(R.id.ans53_pico);
        ImageView ans54 = findViewById(R.id.ans54_pico);
        ImageView ans532 = findViewById(R.id.ans53_pico2);
        ImageView ans542 = findViewById(R.id.ans54_pico2);




        //region 1st
        setClickListenerForDifferences(ans542, ans54);
        setClickListenerForDifferences(ans532, ans53);
        //endregion

        //region 2nd

        //endregion

        //region 3rd
        setClickListenerForDifferences(ans31, ans32);
        //endregion

        //region 4th
        setClickListenerForDifferences(ans41, ans42);
        //endregion

        //region 5th
        setClickListenerForDifferences(ans51, ans52);
        //endregion
    }

    private void setClickListenerForDifferences(ImageView img1, ImageView img2) {
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img1.setImageResource(R.drawable.circlestyle3);
                img2.setImageResource(R.drawable.circlestyle3);
                foundDifferencesCount++;
                checkAllDifferencesFound();
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img1.setImageResource(R.drawable.circlestyle3);
                img2.setImageResource(R.drawable.circlestyle3);
                foundDifferencesCount++;
                checkAllDifferencesFound();
            }
        });
    }

    private void checkAllDifferencesFound() {
        if (foundDifferencesCount == TOTAL_DIFFERENCES) {
            // Barcha farqlar topilgan bo'lsa, tabriknoma chiqaring
            tabrik = MediaPlayer.create(getApplicationContext(), R.raw.ajoyib); // O'zgarish kiritilsin
            tabrik.start();
            showCongratulationsDialog();
        } else {
            // Har bir farq topilganda yangi Toast chiqarib borish
//            Toast.makeText(this, foundDifferencesCount + R.string.ta_farq, Toast.LENGTH_SHORT).show();
            tabrik = MediaPlayer.create(getApplicationContext(), R.raw.yaxhi2); // O'zgarish kiritilsin
            tabrik.start();
        }
    }

    private void showCongratulationsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.tabriklaymiz);
        builder.setMessage(R.string.barcha_farqlar);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Picovoice.this, R.string.Mashqlarni_bajarish, Toast.LENGTH_SHORT).show();
                resetGame();
                finish();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xato!");
        builder.setMessage( foundDifferencesCount + "ta farqni topdingiz");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // O'yinni qayta boshlash
                resetGame();
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
        finish();
    }

    private void resetGame() {
        // Barcha farqlarni topish statusini nolga qaytarish
        foundDifferencesCount = 0;

        // Barcha rasmlarning ko'rinishini boshlang'ich holatga qaytarish
        // Masalan, shu kod orqali:
        // ans11.setImageResource(R.drawable.bosh_pictures_style);
        // ans12.setImageResource(R.drawable.bosh_pictures_style);
        // ...

        // (sizning boshqa loyihalar)
    }
}