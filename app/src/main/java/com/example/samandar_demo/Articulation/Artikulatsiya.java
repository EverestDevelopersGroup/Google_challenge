package com.example.samandar_demo.Articulation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samandar_demo.R;

import java.util.ArrayList;
import java.util.List;

public class Artikulatsiya extends AppCompatActivity implements VideoAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private VideoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikulatsiya);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        List<VideoItem> videoList = new ArrayList<>();
        videoList.add(new VideoItem("Otcha", "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Ftil2_7m6GMB1R.mp4?alt=media&token=e51efdf9-6f5a-48c1-b963-e96f7ed9a9e3"));
        videoList.add(new VideoItem("Murabbo", "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Ftil4_tUwd4LR3.mp4?alt=media&token=83eb74cd-097a-4b32-9c8e-1b65542fd80c"));
        videoList.add(new VideoItem("Quymoq", "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Ftil1_i268WC3x.mp4?alt=media&token=5f419f08-ca4c-4a44-b957-1b0a8b549d01"));
        videoList.add(new VideoItem("Futbol", "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Ftil3_lj8hFQx2.mp4?alt=media&token=5fd5de72-a107-46a9-9fc9-42952ac5c5a1"));
        videoList.add(new VideoItem("Soatcha", "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fargimchoq_i9GWlYf7.mp4?alt=media&token=7edc00e0-03ee-4ce2-99b3-a8eb5d3a5fbc"));


       // URL manzili
         // Boshqa URL manzili

        // Boshqa video manzillarini qo'shish...

        adapter = new VideoAdapter(videoList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        // Itemga bosinganda VideoActivityga o'tish
        Intent intent = new Intent(this, VideoActivity.class);
        VideoItem videoItem = adapter.getItem(position);
        intent.putExtra("videoName", videoItem.getName());
        intent.putExtra("videoUrl", videoItem.getResourceId()); // URL manzili
        startActivity(intent);
    }
}
