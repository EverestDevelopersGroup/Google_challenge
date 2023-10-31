package com.example.samandar_demo.VideoLesson;

public class LessonItem {
    private String name;
    private String resourceId;

    public LessonItem(String name, String resourceId) {
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
