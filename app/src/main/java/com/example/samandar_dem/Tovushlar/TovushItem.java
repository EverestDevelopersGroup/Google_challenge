package com.example.samandar_dem.Tovushlar;

public class TovushItem {
    private int name;
    private String resourceId;

    public TovushItem(int name, String resourceId) {
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
