package com.example.samandar_demo;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;

import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ResponseModel {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        String inputVideoPath = "yangilar.mp4"; // Input video file path
        String outputTextFile = "base64_frames.txt"; // Output text file path

        List<String> frames = videoToBase64(inputVideoPath);

        try {
            FileWriter writer = new FileWriter(outputTextFile);
            for (String frame : frames) {
                writer.write(frame + "\n");
            }
            writer.close();
            System.out.println("Video frames converted and saved to " + outputTextFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static List<String> videoToBase64(String videoPath) {
        VideoCapture cap = new VideoCapture(videoPath);
        Mat frame = new Mat();
        MatOfByte buffer = new MatOfByte();
        List<String> frames = new ArrayList<>();

        while (cap.isOpened()) {
            cap.read(frame);
            if (frame.empty()) {
                break;
            }

            Imgcodecs.imencode(".jpg", frame, buffer);
            byte[] imageData = buffer.toArray();
            String base64Frame = Base64.getEncoder().encodeToString(imageData);
            frames.add(base64Frame);
        }

        cap.release();
        return frames;
    }
}
