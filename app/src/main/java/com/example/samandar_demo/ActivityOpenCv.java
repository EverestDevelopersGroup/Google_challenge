package com.example.samandar_demo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.android.Utils;

import java.io.IOException;



import android.Manifest;
import android.content.Intent;

import androidx.annotation.Nullable;

import androidx.documentfile.provider.DocumentFile;


import android.net.Uri;

import java.io.OutputStream;

public class ActivityOpenCv extends AppCompatActivity {

    static {
        if (!OpenCVLoader.initDebug()) {
            // OpenCV initialization failed.
        }
    }

    private static final int REQUEST_PERMISSION = 100;
    private static final int PICK_DESTINATION_REQUEST_CODE = 101;
    private String userDefinedPath = Environment.getExternalStorageDirectory().getPath(); // Default to external storage
    private StringBuilder numpyArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_cv);

        // Check and request runtime permissions for external storage
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
            } else {
                processImage();
            }
        } else {
            processImage();
        }
    }

    private void processImage() {
        int drawableResourceId = R.drawable.kazak;
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), drawableResourceId);
        Mat imageMat = new Mat(imageBitmap.getHeight(), imageBitmap.getWidth(), CvType.CV_8UC4);
        Utils.bitmapToMat(imageBitmap, imageMat);

        numpyArray = new StringBuilder("");

        for (int i = 0; i < imageMat.rows(); i++) {
            numpyArray.append("");
            for (int j = 0; j < imageMat.cols(); j++) {
                numpyArray.append("");
                for (int k = 0; k < imageMat.channels(); k++) {
                    numpyArray.append(imageMat.get(i, j)[0]);
                    if (k < imageMat.channels() - 1) {
                        numpyArray.append(", ");
                    }
                }
                numpyArray.append("");
                if (j < imageMat.cols() - 1) {
                    numpyArray.append("\n ");
                }
            }
            numpyArray.append("");
            if (i < imageMat.rows() - 1) {
                numpyArray.append("\n");
            } else {
                numpyArray.append("\n");
            }
        }

        // Request user-defined destination path
        openDocumentTree();
    }


    private void openDocumentTree() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        startActivityForResult(intent, PICK_DESTINATION_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_DESTINATION_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri treeUri = data.getData();
            DocumentFile pickedDir = DocumentFile.fromTreeUri(this, treeUri);

            if (pickedDir != null) {
                String fileName = "matrix.txt";
                saveMatrixToTxtFile(pickedDir, fileName, numpyArray.toString());
            } else {
                showToast("Failed to access the selected directory");
            }
        }
    }



    private void saveMatrixToTxtFile(DocumentFile pickedDir, String fileName, String data) {
        DocumentFile file = pickedDir.createFile("text/plain", fileName);
        if (file != null) {
            try {
                OutputStream outputStream = getContentResolver().openOutputStream(file.getUri());
                assert outputStream != null;
                outputStream.write(data.getBytes());
                outputStream.close();
                showToast("File saved in the specified location");
            } catch (IOException e) {
                e.printStackTrace();
                showToast("Failed to save file in the specified location");
            }
        } else {
            showToast("Failed to create the file in the specified location");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                processImage();
            } else {
                showToast("Permission denied. Cannot proceed.");

//                Toast.makeText(this, "This is macbook pro 13", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

