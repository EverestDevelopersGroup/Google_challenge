package com.example.samandar_demo.VoiceAnalyzer.network.api;


import com.example.samandar_demo.VoiceAnalyzer.network.response.TextResponse;

public interface RepositoryService
{
    void loadResponseData(final TextResponse response);
    //void loadTreasures(final TreasureCallback callback);
}