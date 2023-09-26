package com.example.samandar_demo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samandar_demo.Articulation.VideoActivity;
import com.example.samandar_demo.Articulation.VideoItem;
import com.example.samandar_demo.R;

import java.util.ArrayList;
import java.util.List;

public class BarmoqActvity extends AppCompatActivity implements BarmoqAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private BarmoqAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barmoq_actvity);

        recyclerView = findViewById(R.id.recyclerView_barmoq);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Ma'lumotlarni yaratish
        List<VideoItem> videoList = new ArrayList<>();

        videoList.add(new VideoItem("Ko'zoynakcha", "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fbarmoq2_okXdKdKN.mp4?alt=media&token=be53ff4c-f6f7-4f87-a720-45328832e233"));
        videoList.add(new VideoItem("Kapalakcha", "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fbarmoq2_okXdKdKN.mp4?alt=media&token=be53ff4c-f6f7-4f87-a720-45328832e233"));
        videoList.add(new VideoItem("Baliqcha", "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fbarmoq2_okXdKdKN.mp4?alt=media&token=be53ff4c-f6f7-4f87-a720-45328832e233"));
        videoList.add(new VideoItem("Panjara", "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fbarmoq2_okXdKdKN.mp4?alt=media&token=be53ff4c-f6f7-4f87-a720-45328832e233"));
        videoList.add(new VideoItem("Arg'imchoq", "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fbarmoq2_okXdKdKN.mp4?alt=media&token=be53ff4c-f6f7-4f87-a720-45328832e233"));

        // URL manzili
        // Boshqa URL manzili

        // Boshqa video manzillarini qo'shish...

        adapter = new BarmoqAdapter(videoList, this);
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
