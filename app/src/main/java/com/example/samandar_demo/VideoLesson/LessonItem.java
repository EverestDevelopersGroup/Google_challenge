package com.example.samandar_demo.VideoLesson;

public class LessonItem {
    private int name;
    private String resourceId;

    public LessonItem(int name, String resourceId) {
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
