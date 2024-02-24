package com.example.samandar_dem.VoiceAnalyzer.network.api;



import com.example.samandar_dem.VoiceAnalyzer.network.response.TextResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AudioToTextApi {
    @Multipart
    @POST("/api/v1/stt")
    @Headers({"Authorization:29c9b786-3b20-4d2a-a1b0-e5df8a51fadd:1ac6a5cb-2a24-4052-a2a1-05913898a757"})
    Call<TextResponse> sendAudioToText(@Part MultipartBody.Part file);
}
