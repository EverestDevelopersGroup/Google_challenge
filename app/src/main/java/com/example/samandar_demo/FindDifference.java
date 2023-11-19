package com.example.samandar_demo;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.samandar_demo.Activities.MainActivity;
import com.example.samandar_demo.Activities.Picovoice;

public class FindDifference extends AppCompatActivity {
    private int foundDifferencesCount = 0;
    MediaPlayer tabrik;
    private static final int TOTAL_DIFFERENCES = 4; // Umumiy farqlar soni

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_difference);

        ImageView ans11 = findViewById(R.id.ans11);
        ImageView ans12 = findViewById(R.id.ans12);
        ImageView ans31 = findViewById(R.id.ans31);
        ImageView ans32 = findViewById(R.id.ans32);
        ImageView ans41 = findViewById(R.id.ans41);
        ImageView ans42 = findViewById(R.id.ans42);
        ImageView ans51 = findViewById(R.id.ans51);
        ImageView ans52 = findViewById(R.id.ans52);




        //region 1st
        setClickListenerForDifferences(ans11, ans12);
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
            Toast.makeText(this, foundDifferencesCount + R.string.ta_farq, Toast.LENGTH_SHORT).show();
            tabrik = MediaPlayer.create(getApplicationContext(), R.raw.yaxhi2); // O'zgarish kiritilsin
            tabrik.start();
        }
    }

    private void showCongratulationsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.tabriklaymiz);
        builder.setMessage(R.string.barcha_farqlar);
        builder.setPositiveButton(R.string.keyingi_bosqich, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(FindDifference.this , Picovoice.class);
                startActivity(intent);
                Toast.makeText(FindDifference.this, R.string.tafarq5, Toast.LENGTH_SHORT).show();
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
        builder.setMessage( foundDifferencesCount + R.string.ta_farq);
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
