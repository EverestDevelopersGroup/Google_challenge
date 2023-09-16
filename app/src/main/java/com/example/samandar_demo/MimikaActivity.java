package com.example.samandar_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.samandar_demo.Articulation.VideoActivity;
import com.example.samandar_demo.Articulation.VideoAdapter;
import com.example.samandar_demo.Tovushlar.TovushAdapter;

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
        List<String> videoUrls = new ArrayList<>();
        videoUrls.add("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Femotsiyalar.mp4?alt=media&token=fc2b5b3e-7121-4983-91b7-f8cbf82c402e");
        videoUrls.add("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Ftabassum.mp4?alt=media&token=f69e55bf-141b-421f-b913-2512fad14374");


        // Boshqa video manzillarini qo'shing...

        adapter = new MimikaAdapter(videoUrls, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        // Itemga bosinganda VideoActivityga o'tish
        Intent intent = new Intent(this, VideoActivity.class);
        intent.putExtra("videoUrl", adapter.getItem(position));
        startActivity(intent);
    }
}
