package com.example.samandar_demo.repo;



import android.content.Context;
import android.util.Log;

import com.example.samandar_demo.api.FileApi;
import com.example.samandar_demo.api.RepositoryService;
import com.example.samandar_demo.client.APIClient;
import com.example.samandar_demo.response.FileResponse;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FileRepository {
    FileApi apiInterface;
    RepositoryService listener;





    public void requestFileToApi(File file, Context context) {

        MediaType mediaType = MediaType.parse("multipart/form-data");

        RequestBody fileRequestBody = RequestBody.create(mediaType, file);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("frame", file.getName(), fileRequestBody);
        MultipartBody.Part level = MultipartBody.Part.createFormData("level", "ogiz_ochiq");
        apiInterface = APIClient.getClient(context).create(FileApi.class);
        Call<FileResponse> call = apiInterface.requestFileToApi(level, filePart);
        try {
            call.enqueue(new Callback<FileResponse>() {
                @Override
                public void onResponse(Call<FileResponse> call, Response<FileResponse> response) {
                    if (response.isSuccessful()) {
                        listener.loadTroops(response.body());
                        Log.d("TTT", "onResponse: "+response.body());
                        System.out.println("DATA : "+response.body().toString());

                    }else  {
                       listener.loadError("Please check your internet connection and try again." );
                    }
                }

                @Override
                public void onFailure(Call<FileResponse> call, Throwable t) {
                    Log.d("TTT", "onFailure: "+t.getMessage());

                    t. printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void  setDataListener(RepositoryService dataListener){
        listener =dataListener;
    }
}
