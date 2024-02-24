package com.example.samandar_dem.VideoLesson;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samandar_dem.R;

import java.util.ArrayList;
import java.util.List;

public class Lessons extends AppCompatActivity implements LessonAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private LessonAdapter adapter;
//    private TextView allcourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);

        recyclerView = findViewById(R.id.recyclerView_lesson);
//        allcourse = findViewById(R.id.allcourses);


//        allcourse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(Lessons.this, "Sizda premium obuna mavjud emas", Toast.LENGTH_SHORT).show();
//
//            }
//        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        List<LessonItem> videoList = new ArrayList<>();
        videoList.add(new LessonItem(R.string.lessons1, "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fopa1.mp4?alt=media&token=12db9d57-ac25-4803-aa71-10101e83bb12&_gl=1*n5b3c6*_ga*MTU1ODY0MDAzNS4xNjk4NzM3MDYz*_ga_CW55HF8NVT*MTY5ODc0NzcxNS4zLjEuMTY5ODc0ODY0Mi42MC4wLjA."));
        videoList.add(new LessonItem(R.string.lessons2, "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fopa2.mp4?alt=media&token=eef31ba2-fff7-41da-b4e8-27d9b623ff65&_gl=1*1k6iffu*_ga*MTU1ODY0MDAzNS4xNjk4NzM3MDYz*_ga_CW55HF8NVT*MTY5ODc0NzcxNS4zLjEuMTY5ODc0ODcxNi42MC4wLjA."));
        videoList.add(new LessonItem(R.string.lessons3, "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fopa3.mp4?alt=media&token=65e61ccd-0e4e-4554-b155-7715b99124e8&_gl=1*iz1fgm*_ga*MTU1ODY0MDAzNS4xNjk4NzM3MDYz*_ga_CW55HF8NVT*MTY5ODc0NzcxNS4zLjEuMTY5ODc0ODczNy4zOS4wLjA."));
        videoList.add(new LessonItem(R.string.lessons4, "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fopa4.mp4?alt=media&token=997f6b0a-f934-438a-ba95-988e6d5af579&_gl=1*s60n9b*_ga*MTU1ODY0MDAzNS4xNjk4NzM3MDYz*_ga_CW55HF8NVT*MTY5ODc0NzcxNS4zLjEuMTY5ODc0ODc1NS4yMS4wLjA."));
        videoList.add(new LessonItem(R.string.lessons5, "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fopa5.mp4?alt=media&token=9b1a84b1-eb8d-42ca-9321-9da698c0660b&_gl=1*d4alae*_ga*MTU1ODY0MDAzNS4xNjk4NzM3MDYz*_ga_CW55HF8NVT*MTY5ODc1MTk4My40LjEuMTY5ODc1MjAwOC4zNS4wLjA."));
//        videoList.add(new LessonItem("6-dars" , ""));
//        videoList.add(new LessonItem("7-dars" , ""));
//        videoList.add(new LessonItem("8-dars" , ""));
//        videoList.add(new LessonItem("9-dars" , ""));
        // URL manzili
        // Boshqa URL manzili

        // Boshqa video manzillarini qo'shish...

        adapter = new LessonAdapter(videoList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        // Itemga bosinganda VideoActivityga o'tish
        Intent intent = new Intent(this, VideoLessonActivity.class);
        LessonItem videoItem = adapter.getItem(position);
        intent.putExtra("videoName", videoItem.getName());
        intent.putExtra("videoUrl", videoItem.getResourceId()); // URL manzili
        startActivity(intent);


    }



}
