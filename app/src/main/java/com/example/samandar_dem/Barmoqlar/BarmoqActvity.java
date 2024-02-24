package com.example.samandar_dem.Barmoqlar;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samandar_dem.Articulation.VideoActivity;
import com.example.samandar_dem.Articulation.VideoItem;
import com.example.samandar_dem.R;


import java.util.ArrayList;
import java.util.List;

public class BarmoqActvity extends AppCompatActivity implements BarmoqAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private BarmoqAdapter adapter;
//    AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barmoq_actvity);

        recyclerView = findViewById(R.id.recyclerView_barmoq);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        // Ma'lumotlarni yaratish
        List<VideoItem> videoList = new ArrayList<>();

        videoList.add(new VideoItem(R.string.kozaynak, "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fbarmoq2_okXdKdKN.mp4?alt=media&token=be53ff4c-f6f7-4f87-a720-45328832e233"));
        videoList.add(new VideoItem(R.string.kapalak, "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fbarmoq1_PdSHk7ac.mp4?alt=media&token=4d975474-ba5a-4647-830f-5e06f9f44cd8"));
        videoList.add(new VideoItem(R.string.baliqcha, "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fbarmoq3_43sdzsAx.mp4?alt=media&token=2797f011-b6ed-4c04-a050-10fa7aaae784"));
        videoList.add(new VideoItem(R.string.panjara, "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fbarmoq4_Gf62fn9F.mp4?alt=media&token=eea58dd9-c06f-48e3-ae8b-54edd0e50a1d"));
        videoList.add(new VideoItem(R.string.argimchoq, "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fbarmoq1_PdSHk7ac.mp4?alt=media&token=4d975474-ba5a-4647-830f-5e06f9f44cd8"));

        videoList.add(new VideoItem(R.string.bargcha, "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fbarmoq3_43sdzsAx.mp4?alt=media&token=2797f011-b6ed-4c04-a050-10fa7aaae784"));
        videoList.add(new VideoItem(R.string.kaktus, "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fbarmoq4_Gf62fn9F.mp4?alt=media&token=eea58dd9-c06f-48e3-ae8b-54edd0e50a1d"));
        videoList.add(new VideoItem(R.string.maymuncha, "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fbarmoq1_PdSHk7ac.mp4?alt=media&token=4d975474-ba5a-4647-830f-5e06f9f44cd8"));

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
//DialogOption();
//alertDialog.show();


    }




//private void DialogOption(){
//
//
//    alertDialog = new AlertDialog.Builder(BarmoqActvity.this);
//    alertDialog.setTitle(R.string.diqqat);
//    alertDialog.setMessage(R.string.mashqlar_dialog);
//    // follow Step number 3 for adding image...
//    alertDialog.setIcon(R.drawable.img_15);
//
//    alertDialog.setPositiveButton(R.string.ha, new DialogInterface.OnClickListener(){
//        @Override
//        public void onClick(DialogInterface dialog, int which) {
//            Intent intent = new Intent(BarmoqActvity.this , MainActivity.class);
//            startActivity(intent);
//            Toast.makeText(BarmoqActvity.this, R.string.kerakli_bolim, Toast.LENGTH_SHORT).show();
//        }
//    });
//    alertDialog.setNegativeButton(R.string.yoq, new DialogInterface.OnClickListener(){
//        @Override
//        public void onClick(DialogInterface dialog, int which) {
//            Toast.makeText(BarmoqActvity.this, R.string.uzr, Toast.LENGTH_SHORT).show();
//        }
//    });
//
//
//}
//
//
//





}