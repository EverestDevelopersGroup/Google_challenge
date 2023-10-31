package com.example.samandar_demo.VideoLesson;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.samandar_demo.R;

import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.VideoViewHolder> {

    private List<LessonItem> videoList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public LessonAdapter(List<LessonItem> videoList, OnItemClickListener listener) {
        this.videoList = videoList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.bind(videoList.get(position));
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public LessonItem getItem(int position) {
        return videoList.get(position);
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {

        private TextView videoNameTextView;

        VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoNameTextView = itemView.findViewById(R.id.text_mashq);
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

        void bind(LessonItem videoItem) {
            videoNameTextView.setText(videoItem.getName());
        }
    }
}
