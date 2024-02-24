package com.example.samandar_dem.Tovushlar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.samandar_dem.R;

import java.util.List;

public class TovushAdapter extends RecyclerView.Adapter<TovushAdapter.VideoViewHolder> {

    private List<TovushItem> videoList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public TovushAdapter(List<TovushItem> videoList, OnItemClickListener listener) {
        this.videoList = videoList;
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
        holder.bind(videoList.get(position));
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public TovushItem getItem(int position) {
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

        void bind(TovushItem videoItem) {
            videoNameTextView.setText(videoItem.getName());
        }
    }
}