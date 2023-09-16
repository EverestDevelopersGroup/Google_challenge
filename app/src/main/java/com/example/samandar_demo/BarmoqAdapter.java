package com.example.samandar_demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BarmoqAdapter  extends RecyclerView.Adapter<BarmoqAdapter.VideoViewHolder>{


    private List<String> videoUrls;
    private BarmoqAdapter.OnItemClickListener listener;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public BarmoqAdapter(List<String> videoUrls, BarmoqActvity listener) {
        this.videoUrls = videoUrls;
        this.listener = (BarmoqAdapter.OnItemClickListener) listener;
    }

    @NonNull
    @Override
    public BarmoqAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarmoqAdapter.VideoViewHolder holder, int position) {
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


