package com.example.samandar_demo.VoiceAnalyzer.recorder;

import android.media.MediaRecorder;

import java.io.File;
import java.io.IOException;

public class AudioRecorder {

    private MediaRecorder mediaRecorder;
    private String outputFile;

    public AudioRecorder(String outputFile) {
        this.outputFile = outputFile;
    }









    public void startRecording() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(outputFile);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaRecorder.start();
    }

    public void deleteRecording() {
        if (outputFile != null) {
            // Faylni o'chirib tashlash
            new File(outputFile).delete();
        }
    }
    public String getOutputFile() {
        return outputFile;
    }

    public void stopRecording() {
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
    }
}
