package com.example.samandar_dem.api;

import com.example.samandar_dem.response.FileResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface FileApi {
    @Multipart

    @POST("user_action")
    Call<FileResponse> requestFileToApi(@Part MultipartBody.Part level, @Part MultipartBody.Part frame);

}
