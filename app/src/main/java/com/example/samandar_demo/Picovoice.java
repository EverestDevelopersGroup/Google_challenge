package com.example.samandar_demo;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.File;

public class Picovoice extends AppCompatActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private String folderName = "Samandar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picovoice);

        Button createFolderButton = findViewById(R.id.createFolderButton);
        createFolderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermissionsAndCreateFolder();
            }
        });
    }

    private void checkPermissionsAndCreateFolder() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_EXTERNAL_STORAGE);
        } else {
            createFolder();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                createFolder();
            } else {
                Toast.makeText(this, "Yozish ruxsatini bermadingiz.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void createFolder() {
        File folder = new File(getExternalFilesDir(null), folderName);

        if (!folder.exists()) {
            if (folder.mkdir()) {
                Toast.makeText(this, "Samandar nomli papka muvaffaqiyatli yaratildi.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Xatolik: Samandar nomli papka yaratilmadi.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Samandar nomli papka allaqachon mavjud.", Toast.LENGTH_SHORT).show();
        }
    }
}
