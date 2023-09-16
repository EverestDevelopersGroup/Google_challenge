package com.example.samandar_demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samandar_demo.Tovushlar.TovushActivity;

import java.util.List;

public class MimikaAdapter  extends RecyclerView.Adapter<MimikaAdapter.VideoViewHolder>{


    private List<String> videoUrls;
    private MimikaAdapter.OnItemClickListener listener;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public MimikaAdapter(List<String> videoUrls, MimikaActivity listener) {
        this.videoUrls = videoUrls;
        this.listener = (MimikaAdapter.OnItemClickListener) listener;
    }

    @NonNull
    @Override
    public MimikaAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new MimikaAdapter.VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MimikaAdapter.VideoViewHolder holder, int position) {
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


