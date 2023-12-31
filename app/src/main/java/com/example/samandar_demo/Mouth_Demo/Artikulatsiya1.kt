package com.example.samandar_demo.Mouth_Demo


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.liveness.LivenessActivity
import com.example.samandar_demo.R
import com.example.samandar_demo.Activities.VideoFrame
import com.example.samandar_demo.databinding.ActivityArtikulatsiyaBinding
import com.example.samandar_demo.databinding.ViewholderVideoBinding
import java.util.ArrayList

class Artikulatsiya1 : AppCompatActivity(), VideoAdapter1.OnItemClickListener {

    private lateinit var binding: ViewholderVideoBinding
    private lateinit var adapter: VideoAdapter1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ViewholderVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvRecycler1.layoutManager = LinearLayoutManager(this)

        val videoList = ArrayList<VideoItem1>()
        videoList.add(VideoItem1(R.string.otcha, "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Ftil2_7m6GMB1R.mp4?alt=media&token=e51efdf9-6f5a-48c1-b963-e96f7ed9a9e3"))
        videoList.add(VideoItem1(R.string.murabbo, "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Ftil4_tUwd4LR3.mp4?alt=media&token=83eb74cd-097a-4b32-9c8e-1b65542fd80c"))
        videoList.add(VideoItem1(R.string.quymoq, "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Ftil1_i268WC3x.mp4?alt=media&token=5f419f08-ca4c-4a44-b957-1b0a8b549d01"))
        videoList.add(VideoItem1(R.string.futbol, "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Ftil3_lj8hFQx2.mp4?alt=media&token=5fd5de72-a107-46a9-9fc9-42952ac5c5a1"))
        videoList.add(VideoItem1(R.string.soatcha, "https://firebasestorage.googleapis.com/v0/b/yangiliklar-ee745.appspot.com/o/Video%20articulation%2Fargimchoq_i9GWlYf7.mp4?alt=media&token=7edc00e0-03ee-4ce2-99b3-a8eb5d3a5fbc"))

        // URL manzili
        // Boshqa URL manzili

        // Boshqa video manzillarini qo'shish...

        adapter = VideoAdapter1(videoList, this)
        binding.rvRecycler1.adapter = adapter
    }

    override fun onItemClick(position: Int) {
        // Itemga bosinganda VideoActivityga o'tish
        val intent = Intent(this, LivenessActivity::class.java)
        val videoItem = adapter.getItem(position)
        intent.putExtra("videoName", videoItem.name)
        intent.putExtra("videoUrl", videoItem.resourceId) // URL manzili
        startActivity(intent)
    }
}
