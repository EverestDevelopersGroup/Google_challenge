package com.example.samandar_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.samandar_demo.Articulation.VideoActivity;
import com.example.samandar_demo.Tovushlar.TovushAdapter;

import java.util.ArrayList;
import java.util.List;

public class BarmoqActvity extends AppCompatActivity   implements BarmoqAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private BarmoqAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barmoq_actvity);

        recyclerView = findViewById(R.id.recyclerView_barmoq);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Ma'lumotlarni yaratish
        List<String> videoUrls = new ArrayList<>();
        videoUrls.add("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fbarmoq1.mp4?alt=media&token=c1f23d3a-2d94-4bf4-9e81-191c5a728ac4");
        videoUrls.add("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fbarmoq2.mp4?alt=media&token=eab888ff-35b5-4301-9c1f-725fc38c9164");
        videoUrls.add("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fbarmoq3.mp4?alt=media&token=926252ec-6fe0-4f0f-a507-44f4620c641c");
        videoUrls.add("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fbarmoq4.mp4?alt=media&token=82fe58d0-cb1e-4808-8096-c40ee3e3b0e5");





        // Boshqa video manzillarini qo'shing...

        adapter = new BarmoqAdapter(videoUrls, this);
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