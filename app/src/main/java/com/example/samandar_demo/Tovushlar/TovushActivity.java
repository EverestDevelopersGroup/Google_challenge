package com.example.samandar_demo.Tovushlar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.samandar_demo.Articulation.VideoActivity;
import com.example.samandar_demo.Articulation.VideoAdapter;
import com.example.samandar_demo.R;

import java.util.ArrayList;
import java.util.List;

public class TovushActivity extends AppCompatActivity   implements TovushAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private TovushAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tovush);

        recyclerView = findViewById(R.id.recyclerView_tovush);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Ma'lumotlarni yaratish
        List<String> videoUrls = new ArrayList<>();
        videoUrls.add("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Funlilar.mp4?alt=media&token=61942b52-0662-4eef-b436-adeadf0c637f");
        videoUrls.add("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fmharfi.mp4?alt=media&token=fccf61b3-d583-4e17-b218-c34492bba02f");




        // Boshqa video manzillarini qo'shing...

        adapter = new TovushAdapter(videoUrls, this);
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