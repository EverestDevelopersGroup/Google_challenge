package com.example.samandar_demo;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.Tensor;
import org.tensorflow.lite.DataType;


import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MyTensorflowModel {
    private Interpreter interpreter;

    public MyTensorflowModel(AssetManager assetManager, String modelPath) throws IOException {
        MappedByteBuffer modelBuffer = loadModelFile(assetManager, modelPath);
        Interpreter.Options options = new Interpreter.Options();
        interpreter = new Interpreter(modelBuffer, options);
    }

    private MappedByteBuffer loadModelFile(AssetManager assetManager, String modelPath) throws IOException {
        AssetFileDescriptor fileDescriptor = assetManager.openFd(modelPath);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    public float[] runInference(float[] inputData) {
        float[] outputData = new float[11]; // Eslatma: outputTensorSize'ni o'zgartiring

        interpreter.run(inputData, outputData);

        return outputData;
    }

    // Qo'shimcha metodlar va o'zgaruvchilar (kerak bo'lsa)
}
