package com.example.samandar_demo.VoiceAnalyzer.network.response;


public class TextResponse {
    public String id;
    public double progress;
    public Result result;
    public String status;

    @Override
    public String toString() {
        return "TextResponse{" +
                "id='" + id + '\'' +
                ", progress=" + progress +
                ", result=" + result +
                ", status='" + status + '\'' +
                '}';
    }
}

