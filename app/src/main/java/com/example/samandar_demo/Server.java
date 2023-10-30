package com.example.samandar_demo;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Server {
    @Multipart
    @POST("upload")
    Call<ResponseBody> uploadData(
            @Part("level") RequestBody level,
            @Part("frame") MultipartBody.Part frame
            
    );
}



