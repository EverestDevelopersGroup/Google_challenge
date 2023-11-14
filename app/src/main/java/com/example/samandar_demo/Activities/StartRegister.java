package com.example.samandar_demo.Activities;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import com.ahmedteleb.buttons3d.Button3d;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.samandar_demo.R;

public class StartRegister extends AppCompatActivity {

    Button3d boshlash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if the app was launched for the first time
        SharedPreferences preferences = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        boolean firstTime = preferences.getBoolean("first_time", true);

        if (!firstTime) {
            launchRegister();
            return;
        }

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("first_time", false);
        editor.apply();

        setContentView(R.layout.activity_start_register);

        boshlash = findViewById(R.id.boshlash);

        boshlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchRegister();
            }
        });
    }

    private void launchRegister() {
        Intent intent = new Intent(StartRegister.this, Register.class);
        startActivity(intent);
        Animatoo.INSTANCE.animateSlideDown(StartRegister.this);
        finish(); // Close the StartRegister activity after navigating to Register
    }
}
