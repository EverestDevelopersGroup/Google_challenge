package com.example.samandar_dem.Articulation;

public class VideoItem {
    private int name;
    private String resourceId;

    public VideoItem(int name, String resourceId) {
        this.name = name;
        this.resourceId = resourceId;
    }

    public int getName() {
        return name;
    }

    public String getResourceId() {
        return resourceId;
    }
}
