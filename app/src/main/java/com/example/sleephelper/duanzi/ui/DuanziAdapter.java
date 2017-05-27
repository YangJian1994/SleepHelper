package com.example.sleephelper.duanzi.ui;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sleephelper.R;
import com.example.sleephelper.duanzi.bean.DuanziBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 杨健 on 2017/5/27.
 * function: 段子界面的Adapter
 */

public class DuanziAdapter extends RecyclerView.Adapter<DuanziAdapter.DuanziViewHolder> {

    private Fragment fragment;
    private List<DuanziBean> duanziBeanList;

    public DuanziAdapter(Fragment fragment, List<DuanziBean> duanziBeanList) {
        this.fragment = fragment;
        this.duanziBeanList = duanziBeanList;
    }

    @Override
    public DuanziViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_duanzi, parent, false);
        return new DuanziViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DuanziViewHolder holder, int position) {
        try {
            DuanziBean duanziBean = duanziBeanList.get(position);
            Glide.with(fragment).load(duanziBean.getGroupBean().getUser().getAvatar_url()).into(holder.civAvatar);
            holder.tvAuthor.setText(duanziBean.getGroupBean().getUser().getName());
            holder.tvContent.setText(duanziBean.getGroupBean().getText());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return duanziBeanList.size();
    }

    public static class DuanziViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView civAvatar;
        private TextView tvAuthor;
        private TextView tvContent;

        public DuanziViewHolder(View itemView) {
            super(itemView);
            civAvatar = (CircleImageView) itemView.findViewById(R.id.civ_avatar);
            tvAuthor = (TextView) itemView.findViewById(R.id.tv_author);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
