package com.example.samandar_demo.VoiceAnalyzer.network.response;

import java.util.ArrayList;

public class Result{
    public ArrayList<Integer> range;
    public String text;

    @Override
    public String toString() {
        return "Result{" +
                "range=" + range +
                ", text='" + text + '\'' +
                '}';
    }
}
