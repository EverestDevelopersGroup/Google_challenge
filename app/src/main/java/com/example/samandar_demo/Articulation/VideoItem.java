package com.example.samandar_demo.Articulation;

public class VideoItem {
    private String title;
    private String videoUrl;

    public VideoItem(String title, String videoUrl) {
        this.title = title;
        this.videoUrl = videoUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }
}
