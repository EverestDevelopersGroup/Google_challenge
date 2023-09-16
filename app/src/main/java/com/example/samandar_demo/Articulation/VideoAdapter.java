package com.example.samandar_demo.Articulation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import com.example.samandar_demo.R;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private List<String> videoUrls;
    private OnItemClickListener listener;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public VideoAdapter(List<String> videoUrls, OnItemClickListener listener) {
        this.videoUrls = videoUrls;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return videoUrls.size();
    }

    public String getItem(int position) {
        return videoUrls.get(position);
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {

        VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        void bind(int position) {
            // Har bir elementni o'rnating, masalan, boshqa ma'lumotlarni
            // TextView orqali o'rnating (agar kerak bo'lsa).
        }
    }
}
