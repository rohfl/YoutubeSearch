package com.rohit.youtubesearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class YtAdapter extends RecyclerView.Adapter<YtAdapter.ViewHolder>{
    Context isContext ;
    ArrayList<VideoObject> videoObjects ;
    @NonNull
    @Override
    public YtAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View isView = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_layout, parent , false) ;
        return new ViewHolder(isView) ;
    }

    @Override
    public void onBindViewHolder(@NonNull YtAdapter.ViewHolder holder, int position) {
        VideoObject obj = this.videoObjects.get(position) ;
        String desc , chan, ti, thumb ;
        desc = obj.getDescription() ;
        chan = obj.getChannel() ;
        ti = obj.getTitle() ;
        thumb = obj.getThumbnail() ;

        holder.description.setText(desc);
        holder.channel.setText(chan);
        holder.title.setText(ti);
        Glide.with(isContext).load(thumb).into(holder.thumbnail) ;
    }

    @Override
    public int getItemCount() {
        return videoObjects.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView thumbnail ;
        private TextView title, description, channel ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.thumbnail) ;
            title = itemView.findViewById(R.id.title) ;
            description = itemView.findViewById(R.id.description) ;
            channel = itemView.findViewById(R.id.channel) ;


        }
    }

}
