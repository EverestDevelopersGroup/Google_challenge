package com.example.samandar_demo.Mouth_Demo




import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.samandar_demo.databinding.ItemVideoBinding

class VideoAdapter1(private val videoList: List<VideoItem1>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<VideoAdapter1.VideoViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(videoList[position])
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    fun getItem(position: Int): VideoItem1 {
        return videoList[position]
    }

    inner class VideoViewHolder(private val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position)
                }
            }
        }

        fun bind(videoItem: VideoItem1) {
            binding.textMashq.text = videoItem.name.toString()
        }
    }
}
