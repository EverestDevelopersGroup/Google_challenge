package com.example.samandar_demo.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.ahmedteleb.buttons3d.Button3d;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.samandar_demo.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class Register extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String USER_REGISTERED_KEY = "userRegistered";
//   AlertDialog.Builder alertDialog = new AlertDialog.Builder(this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    private static final int PERMISSIONS_REQUEST_CAMERA_AND_MICROPHONE_AND_NOTIFICATION = 1001;
    private ShimmerFrameLayout shimmerContainer2;
    SharedPreferences preferences;
    Button3d button;
    ImageView view;
    EditText ism, number, password;
    TextView privacy;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean userRegistered = settings.getBoolean(USER_REGISTERED_KEY, false);

        if (userRegistered) {
            startMainActivity(); // If the user is already registered, start the main activity
        } else {
            supportRequestWindowFeature(1);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            setContentView(R.layout.activity_register);

            button = findViewById(R.id.register);
            view = findViewById(R.id.image_register);
            shimmerContainer2 = findViewById(R.id.shimmer_register);

            ism = findViewById(R.id.name);
            number = findViewById(R.id.number);
            password = findViewById(R.id.password);
            privacy = findViewById(R.id.privacy);


            CheckBox checkBox = findViewById(R.id.agree);


            privacy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = "https://telegra.ph/Privacy-Policy-11-04-31"; // O'zgartiring, kerakli manzilni yozing

                    // Manzilga yo'naltirish uchun Intent yaratish
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
            });


            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        privacy.setText(R.string.agree);
                        button.setEnabled(true);
                    } else {
                        // CheckBox belgilanmagan bo'lsa, o'chirish uchun amal
                        privacy.setText(R.string.not_agree);
                        button.setEnabled(false);
                    }
                }
            });


            // Permission checks

            // Retrieve the user registration status

            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO)
                            != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{
                                android.Manifest.permission.CAMERA,
                                android.Manifest.permission.RECORD_AUDIO,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        },
                        PERMISSIONS_REQUEST_CAMERA_AND_MICROPHONE_AND_NOTIFICATION);
            } else {
                // Ruxsatlar olinishi mumkin
                Toast.makeText(this, "Barcha ruxsatlar olingan", Toast.LENGTH_SHORT).show();
            }

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = ism.getText().toString();
                    String nomer = number.getText().toString();
                    String pasword = password.getText().toString();

                    if (!name.isEmpty() && !nomer.isEmpty() && !pasword.isEmpty()) {
                        // Intent orqali MainActivityga o'tish
                        Intent intent = new Intent(Register.this, MainActivity.class);
                        startActivity(intent);
                        Animatoo.INSTANCE.animateSwipeLeft(Register.this);
                        finish();

                        // SharedPreferences orqali ma'lumotlarni saqlash
                        saveData(name, nomer, pasword);
                    } else {
                        saveUserRegistrationStatus(true); // Set the user as registered
                        startMainActivity();
                        Toast.makeText(Register.this, "Iltimos ma'lumotlarni to'liq kiriting", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            startShimmer(); // Shimmer effect initiation
            Picasso.get()
                    .load("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/img_15.png?alt=media&token=24413f6c-f125-4048-9fd9-f693bcd7e085")
                    .placeholder(R.drawable.loading)
                    .into(view, new Callback() {
                        @Override
                        public void onSuccess() {
                            shimmerContainer2.stopShimmer();
                            shimmerContainer2.setShimmer(null);
                        }

                        @Override
                        public void onError(Exception e) {
                            Toast.makeText(Register.this, "Xatolik", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        // Picasso code block
    }


    private void saveData(String name, String nomer, String pasword) {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ism", name);
        editor.putString("raqam", nomer);
        editor.putString("parol", pasword);
        editor.apply();
    }

    private void saveUserRegistrationStatus(boolean status) {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(USER_REGISTERED_KEY, status);
        editor.apply();
    }


    private void startMainActivity() {
        Intent intent = new Intent(Register.this, MainActivity.class);
        startActivity(intent);
        Animatoo.INSTANCE.animateSwipeLeft(Register.this);
        finish();
    }

    private void startShimmer() {
        shimmerContainer2.startShimmer();
    }
}
