package com.example.samandar_dem.VoiceAnalyzer.repository;

import android.content.Context;

import androidx.annotation.NonNull;


import com.example.samandar_dem.VoiceAnalyzer.client.APIClient;
import com.example.samandar_dem.VoiceAnalyzer.network.api.AudioToTextApi;
import com.example.samandar_dem.VoiceAnalyzer.network.api.RepositoryService;
import com.example.samandar_dem.VoiceAnalyzer.network.response.TextResponse;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AudioRepository {
    AudioToTextApi apiInterface;
    RepositoryService listener;

    public void requestApiToAudio(@NonNull File audio, Context context) throws IOException {
        MediaType mediaType = MediaType.parse("multipart/form-data");

        RequestBody fileRequestBody = RequestBody.create(mediaType, audio);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", audio.getName(), fileRequestBody);

        apiInterface = APIClient.getClient(context).create(AudioToTextApi.class);

        Call<TextResponse> call = apiInterface.sendAudioToText(filePart);
        try {
            call.enqueue(new Callback<TextResponse>() {
                @Override
                public void onResponse(Call<TextResponse> call, Response<TextResponse> response) {
                    if (response.isSuccessful()) {
                        listener.loadResponseData(response.body());
                        System.out.println(response.body());
                    }
                }

                @Override
                public void onFailure(Call<TextResponse> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void setDataListener(RepositoryService dataListener) {
        listener = dataListener;
    }
}
