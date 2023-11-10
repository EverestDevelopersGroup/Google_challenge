package com.example.samandar_demo.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samandar_demo.Articulation.VideoActivity;
import com.example.samandar_demo.Articulation.VideoItem;
import com.example.samandar_demo.MimikaAdapter;
import com.example.samandar_demo.R;

import java.util.ArrayList;
import java.util.List;

public class MimikaActivity extends AppCompatActivity implements MimikaAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private MimikaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mimika);

        recyclerView = findViewById(R.id.recyclerView_mimika);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Ma'lumotlarni yaratish
        List<VideoItem> videoList = new ArrayList<>();

        videoList.add(new VideoItem("Yuz mimikasi", "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Femotsiyalar_RkdGkuj8.mp4?alt=media&token=0289faa1-6355-4c22-9b9d-cd5151032dbe"));
        videoList.add(new VideoItem("Tabassum", "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Ftabassum_INiHCfbk.mp4?alt=media&token=93b4cc1f-cd71-43b9-babf-fabc81953c3a"));
        videoList.add(new VideoItem("Lablar" , "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Ffilcha_BxZTqqm6.mp4?alt=media&token=bbd408f0-1344-4496-b518-ecf3a46e6768"));
        // URL manzili
        // Boshqa URL manzili

        // Boshqa video manzillarini qo'shish...

        adapter = new MimikaAdapter(videoList, this);
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
