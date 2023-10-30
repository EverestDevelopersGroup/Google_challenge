package com.example.samandar_demo;
import com.google.gson.annotations.SerializedName;

public class ResponseModel {
    @SerializedName("text")
    private String text;

    public String getText() {
        return text;
    }
}
