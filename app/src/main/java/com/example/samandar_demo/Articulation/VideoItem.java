package com.example.samandar_demo.Articulation;

public class VideoItem {
    private String name;
    private String resourceId;

    public VideoItem(String name, String resourceId) {
        this.name = name;
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public String getResourceId() {
        return resourceId;
    }
}
