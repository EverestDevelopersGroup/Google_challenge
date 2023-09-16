package com.example.samandar_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.os.Bundle;

import com.google.common.util.concurrent.ListenableFuture;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.FileInputStream;
import java.io.IOException;

import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.common.FileUtil;
import java.io.IOException;
import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;

import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import org.tensorflow.lite.Interpreter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import android.view.TextureView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import org.tensorflow.lite.Interpreter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ModelActivity extends AppCompatActivity {

//    private static final int PERMISSION_REQUEST_CODE = 10;
//    private PreviewView previewView;
//    private ImageView resultImageView;
//    private TextView resultTextView;
//    private Interpreter interpreter;
//    private Handler handler;
//    private Executor executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        previewView = findViewById(R.id.previewView);
//        resultImageView = findViewById(R.id.resultImageView);
//        resultTextView = findViewById(R.id.resultTextView);
//
//        // Initialize the TensorFlow Lite interpreter with your model
//        try {
//            interpreter = new Interpreter(loadModelFile(), null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // Request camera permissions
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
//                    PERMISSION_REQUEST_CODE);
//        } else {
//            startCamera();
//        }
//    }
//
//    private MappedByteBuffer loadModelFile() throws Exception {
//        // Load your model here, for example:
//         AssetFileDescriptor fileDescriptor = getAssets().openFd("model.tflite");
//         FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
//         FileChannel fileChannel = inputStream.getChannel();
//         long startOffset = fileDescriptor.getStartOffset();
//         long declaredLength = fileDescriptor.getDeclaredLength();
//         return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
//
//        // Replace the code above with the appropriate code to load your model
//        // and return the MappedByteBuffer for the model.
//    }
//
//    private void startCamera() {
//        final ListenableFuture<ProcessCameraProvider> cameraProviderFuture =
//                ProcessCameraProvider.getInstance(this);
//
//        cameraProviderFuture.addListener(() -> {
//            try {
//                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
//
//                // Set up the preview
//                Preview preview = new Preview.Builder().build();
//                preview.setSurfaceProvider(previewView.getSurfaceProvider());
//
//                // Set up the image analysis
//                ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
//                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
//                        .build();
//
//                imageAnalysis.setAnalyzer(executor, new ImageAnalysis.Analyzer() {
//                    @Override
//                    public void analyze(@NonNull ImageProxy image) {
//                        Bitmap bitmap = previewView.getBitmap();
//                        // Preprocess the bitmap and run inference with the TensorFlow model
//                        float[] result = performInference(bitmap);
//                        // Update the UI with the result
//                        runOnUiThread(() -> updateUI(result));
//                        image.close();
//                    }
//                });
//
//                // Select the back camera
//                CameraSelector cameraSelector = new CameraSelector.Builder()
//                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
//                        .build();
//
//                // Bind the camera to the lifecycle
//                Camera camera = cameraProvider.bindToLifecycle((LifecycleOwner)this,
//                        cameraSelector, preview, imageAnalysis);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }, ContextCompat.getMainExecutor(this));
//    }
//
//    private float[] performInference(Bitmap bitmap) {
//        // Preprocess the input image (resize, normalize, etc.)
//        // Convert the Bitmap to a ByteBuffer
//        ByteBuffer inputBuffer = ByteBuffer.allocateDirect(4 * 224 * 224 * 3);
//        inputBuffer.order(ByteOrder.nativeOrder());
//        int[] intValues = new int[224 * 224];
//        bitmap.getPixels(intValues, 0, 224, 0, 0, 224, 224);
//        int pixel = 0;
//        for (int i = 0; i < 224; ++i) {
//            for (int j = 0; j < 224; ++j) {
//                final int val = intValues[pixel++];
//                inputBuffer.putFloat(((val >> 16) & 0xFF) / 255.0f);
//                inputBuffer.putFloat(((val >> 8) & 0xFF) / 255.0f);
//                inputBuffer.putFloat((val & 0xFF) / 255.0f);
//            }
//        }
//
//        // Run inference with the TensorFlow Lite model
//        float[] outputBuffer = new float[3]; // Replace 'numClasses' with the number of classes in your model
//        interpreter.run(inputBuffer, outputBuffer);
//
//        return outputBuffer;
//    }
//
//    private void updateUI(float[] result) {
//        // Update the UI with the inference result
//        // For example, you can display the result in a TextView or ImageView
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == PERMISSION_REQUEST_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                startCamera();
//            } else {
//                // Permission denied
//            }
//        }
    }
}
