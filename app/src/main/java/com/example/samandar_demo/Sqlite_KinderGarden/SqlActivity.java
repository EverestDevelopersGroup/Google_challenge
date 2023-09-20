package com.example.samandar_demo.Sqlite_KinderGarden;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.samandar_demo.R;
import com.example.samandar_demo.Sqlite_KinderGarden.activity.FoodListActivity;
import com.example.samandar_demo.Sqlite_KinderGarden.database.SQliteHelper;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SqlActivity extends AppCompatActivity {


    private EditText bogcha_name , viloyat , xizmatlar , kuntartib , tili , ratsion , bola_soni , tolov_oy , ustunlik_jihat , bonus , xavfsizlik  , raqam , qoshimca_malumot;
    private Button chooseImage, saveFood, showFood;
    private ImageView foodImage;
    public static SQliteHelper sQliteHelper;
    final int REQUEST_CODE_GALLERY = 999;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        setContentView(R.layout.activity_sql);


        bogcha_name = findViewById(R.id.editTextbogcha);
        viloyat = findViewById(R.id.editTextviloyat);
        xizmatlar = findViewById(R.id.editTextxizmat);
        kuntartib = findViewById(R.id.editTextkuntartib);
        tili = findViewById(R.id.editTexttil);
        ratsion = findViewById(R.id.editTextratsion);
        bola_soni = findViewById(R.id.editTextbolasoni);
        tolov_oy = findViewById(R.id.editTexttolovoy);
        ustunlik_jihat = findViewById(R.id.editTextustunlik);
        bonus = findViewById(R.id.editTextbonus);
        xavfsizlik = findViewById(R.id.editTextxavfsizlik);
        raqam = findViewById(R.id.editTextraqam);
        qoshimca_malumot = findViewById(R.id.editTextqoshimcha);

        chooseImage = findViewById(R.id.buttonchooseimage);
        saveFood = findViewById(R.id.buttonsavefood);
        showFood = findViewById(R.id.buttonviewfood);
        foodImage = findViewById(R.id.imageViewfood);


        // SQlite database initialization & database & table create fuctions
        sQliteHelper = new SQliteHelper(this,"FoodDB.sqlite",null,1);
        sQliteHelper.queryData("CREATE TABLE IF NOT EXISTS FOOD (ID INTEGER PRIMARY KEY AUTOINCREMENT, bogcha_name VARCHAR, viloyat VARCHAR, xizmatlar VARCHAR, kuntartib VARCHAR, tili VARCHAR, ratsion VARCHAR, bola_soni VARCHAR, tolov_oy VARCHAR, ustunlik_jihat VARCHAR, bonus VARCHAR, xavfsizlik VARCHAR, raqam VARCHAR, qoshimca_malumot VARCHAR, image BLOB)");


        // Imagecoose button action
        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Request Permission
                ActivityCompat.requestPermissions(SqlActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_GALLERY);

            }
        });

        // Add Food button Action
        // Add Food button Action
        saveFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bogcha_name.getText().toString().trim().isEmpty() ||
                        viloyat.getText().toString().trim().isEmpty() ||
                        xizmatlar.getText().toString().trim().isEmpty() ||
                        kuntartib.getText().toString().trim().isEmpty() ||
                        tili.getText().toString().trim().isEmpty() ||
                        ratsion.getText().toString().trim().isEmpty() ||
                        bola_soni.getText().toString().trim().isEmpty() ||
                        tolov_oy.getText().toString().trim().isEmpty() ||
                        ustunlik_jihat.getText().toString().trim().isEmpty() ||
                        bonus.getText().toString().trim().isEmpty() ||
                        xavfsizlik.getText().toString().trim().isEmpty() ||
                        raqam.getText().toString().trim().isEmpty() ||
                        qoshimca_malumot.getText().toString().trim().isEmpty() ||
                        foodImage.getDrawable() == null) {

                    // Agar ma'lumotlarning biror qismi yoki rasm to'ldirilmagan bo'lsa, xabar chiqar
                    Toast.makeText(SqlActivity.this, "Iltimos ma'lumotlarni to'ldiring va rasm tanlang", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        sQliteHelper.insertdata(
                                bogcha_name.getText().toString().trim(),
                                viloyat.getText().toString().trim(),
                                xizmatlar.getText().toString().trim(),
                                kuntartib.getText().toString().trim(),
                                tili.getText().toString().trim(),
                                ratsion.getText().toString().trim(),
                                bola_soni.getText().toString().trim(),
                                tolov_oy.getText().toString().trim(),
                                ustunlik_jihat.getText().toString().trim(),
                                bonus.getText().toString().trim(),
                                xavfsizlik.getText().toString().trim(),
                                raqam.getText().toString().trim(),
                                qoshimca_malumot.getText().toString().trim(),
                                imageViewToByte(foodImage)
                        );
                        // Ma'lumotlar muvaffaqiyatli qo'shildi, foydalanuvchi xabarni ko'rsatish
                        Toast.makeText(SqlActivity.this, "Ma'lumotlar ro`yxatga qo`shildi", Toast.LENGTH_SHORT).show();
                        // Ma'lumotlarni tozalash
                        bogcha_name.setText("");
                        viloyat.setText("");
                        xizmatlar.setText("");
                        kuntartib.setText("");
                        tili.setText("");
                        ratsion.setText("");
                        bola_soni.setText("");
                        tolov_oy.setText("");
                        ustunlik_jihat.setText("");
                        bonus.setText("");
                        xavfsizlik.setText("");
                        raqam.setText("");
                        qoshimca_malumot.setText("");
                        foodImage.setImageResource(R.drawable.placeholde);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        // Foodlist button Action to go food list activity
        showFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SqlActivity.this, FoodListActivity.class));

            }
        });

    }

    // caller method in save food insert image
    public static byte[] imageViewToByte(ImageView image) {

        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;

    }

    // on Request Permission overide method here for check storage permission added or not
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE_GALLERY)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);
            }
            else
            {
                Toast.makeText(SqlActivity.this, "Sizda fayllardan foydalanish uchun ruxsat yo`q", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    // on Activity Result override method
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null)
        {
            Uri uri = data.getData();
            try
            {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                // set our ui image view from storage choose image
                foodImage.setImageBitmap(bitmap);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}