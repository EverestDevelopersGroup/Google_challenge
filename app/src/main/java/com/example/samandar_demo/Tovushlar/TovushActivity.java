package com.example.samandar_demo.Tovushlar;

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

public class TovushActivity extends AppCompatActivity implements TovushAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private TovushAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tovush);

        recyclerView = findViewById(R.id.recyclerView_tovush);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Ma'lumotlarni yaratish
        List<TovushItem> videoList = new ArrayList<>();

        videoList.add(new TovushItem(R.string.unlilar, "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Funlilar_bw5tNfxA.mp4?alt=media&token=9329e513-8868-4194-b1bf-d8fbf5d77deb"));
        videoList.add(new TovushItem(R.string.m_harf, "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Ftovush2_GFTERAy9.mp4?alt=media&token=3ca1c647-62d6-4ac1-8321-4e7e0daf0839"));
        videoList.add(new TovushItem(R.string.maxsus_sozlar , "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fsozlarr.mp4?alt=media&token=136f05a6-98c2-4ba4-b7af-3edbe5869e54"));
        // URL manzili
        // Boshqa URL manzili
//  unli tovushlar  i, e, a, o, u, o`
        // Boshqa video manzillarini qo'shish...

        adapter = new TovushAdapter(videoList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        // Itemga bosinganda VideoActivityga o'tish
        Intent intent = new Intent(this, TovushVideoActivity.class);
        TovushItem videoItem = adapter.getItem(position);
        intent.putExtra("videoName", videoItem.getName());
        intent.putExtra("videoUrl", videoItem.getResourceId());

        if (position == 0){
            intent.putExtra("Unlilar" , " Ushbu so`zlarni mikrofonni bosgan holda alohida 1martadan ayting:\n E`lon , Alo , Olim , Uzum , O`rdak  , Ilon ");
        }
       if (position == 1){
            intent.putExtra("M" , " Ushbu so`zlarni mikrofonni bosgan holda alohida 1martadan ayting:\n Malina , Muz , Mashina , Maymun");
        }
       if (position == 2){
            intent.putExtra("R" , "Ushbu so`zlarni mikrofonni bosgan holda alohida 1martadan ayting:\n Randa ,Ramda , Arra , Qor , Sariq , Rano , Kaptar , Xayr , Sayr, Burgut");
        }

        // URL manzili
        startActivity(intent);
    }
}
