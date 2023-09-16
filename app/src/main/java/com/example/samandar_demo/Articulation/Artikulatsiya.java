package com.example.samandar_demo.Articulation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import com.example.samandar_demo.R;

public class Artikulatsiya extends AppCompatActivity implements VideoAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private VideoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikulatsiya);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Ma'lumotlarni yaratish
        List<String> videoUrls = new ArrayList<>();
        videoUrls.add("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Ffilcha.mp4?alt=media&token=cc8b5703-a7a2-4bf7-98fc-272889670f47");
        videoUrls.add("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Ftil1.mp4?alt=media&token=fc34ed8d-8f81-4d3f-ac93-a13bb284c82c");
        videoUrls.add("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Ftil2.mp4?alt=media&token=7a9785b3-7dfc-421c-a49c-8664c3cbf323");
        videoUrls.add("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Ftil3.mp4?alt=media&token=d81f4f80-2ba2-435e-8544-14c5ad8f62b4");
        videoUrls.add("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Ftil4.mp4?alt=media&token=a7c5394d-dbfb-4133-bb51-fcc65a3bcc2a");
        videoUrls.add("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Ftil5.mp4?alt=media&token=dd45e74b-8bfd-4b7e-aee3-8f00ae6e1dba");
        videoUrls.add("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Ftil6.mp4?alt=media&token=481962cf-732b-4e85-b2c1-8dc06217886f");
        videoUrls.add("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Ftabassum.mp4?alt=media&token=f69e55bf-141b-421f-b913-2512fad14374");
        videoUrls.add("https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Filoncha.mp4?alt=media&token=96f8ce36-e487-4a0b-8ab8-a2b8d778a6cf");


        // Boshqa video manzillarini qo'shing...

        adapter = new VideoAdapter(videoUrls, this);
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
