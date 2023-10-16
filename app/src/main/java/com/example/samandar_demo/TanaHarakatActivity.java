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

public class TanaHarakatActivity extends AppCompatActivity implements HarakatAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private HarakatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tana_harakat);

        recyclerView = findViewById(R.id.recyclerView_harakat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Ma'lumotlarni yaratish
        List<VideoItem> videoList = new ArrayList<>();

        videoList.add(new VideoItem("Badantarbiya1", "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fmashq-1_DX2EaY4c.mp4?alt=media&token=cac3e69a-5004-4229-b4bd-88a7b1d51183"));
        videoList.add(new VideoItem("Badantarbiya2", "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fmashq-2_hZQ1sYJ5.mp4?alt=media&token=400f5c31-f186-4543-99e2-d91d26b4c433"));
        videoList.add(new VideoItem("Badantarbiya3", "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fmashq-3_DTIRP6C6.mp4?alt=media&token=8ec843b6-25f2-4546-befe-0da7ddf2a7aa"));
        // URL manzili
        // Boshqa URL manzili

        // Boshqa video manzillarini qo'shish...

        adapter = new HarakatAdapter(videoList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        // Itemga bosinganda VideoActivityga o'tish
        Intent intent = new Intent(this, HarakatVideoActivity.class);
        VideoItem videoItem = adapter.getItem(position);
        intent.putExtra("videoName", videoItem.getName());
        intent.putExtra("videoUrl", videoItem.getResourceId()); // URL manzili
        startActivity(intent);
    }
}
