package com.example.sleephelper.meizi.ui;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.sleephelper.R;
import com.example.sleephelper.meizi.bean.MeiziBean;

import java.util.List;

/**
 * Created by 杨健 on 2017/5/27.
 * function: MeiziFragment界面的Adapter
 */

public class MeiziAdapter extends RecyclerView.Adapter<MeiziAdapter.MeiziViewHolder> {

    private Fragment fragment;
    private List<MeiziBean> meiziBeanList;

    public MeiziAdapter(Fragment fragment, List<MeiziBean> meiziBeanList) {
        this.fragment = fragment;
        this.meiziBeanList = meiziBeanList;
    }

    @Override
    public MeiziViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meizi, parent, false);
        return new MeiziViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MeiziViewHolder holder, int position) {
        Glide.with(fragment)
                .load(meiziBeanList.get(position).getImageUrl())
                .fitCenter()
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.iv_Meizi);

        holder.iv_Meizi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }

    @Override
    public int getItemCount() {
        return meiziBeanList != null ? meiziBeanList.size() : 0;
    }

    public static class MeiziViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_Meizi;

        public MeiziViewHolder(View itemView) {
            super(itemView);
            iv_Meizi = (ImageView) itemView.findViewById(R.id.iv_meizi);
        }
    }
}
