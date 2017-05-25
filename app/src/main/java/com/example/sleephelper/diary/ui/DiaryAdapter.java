package com.example.sleephelper.diary.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sleephelper.R;
import com.example.sleephelper.diary.bean.DiaryBean;
import com.example.sleephelper.diary.event.StartUpdateDiaryEvent;
import com.example.sleephelper.diary.utils.GetDate;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by 杨健 on 2017/5/25.
 * function: 日记适配器
 */

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<DiaryBean> diaryBeanList;
    private int editPosition = -1;

    public DiaryAdapter(Context context, List<DiaryBean> diaryBeanList) {
        this.context = context;
        this.diaryBeanList = diaryBeanList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public DiaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_diary, parent, false);
        return new DiaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DiaryViewHolder holder, final int position) {
        String date = GetDate.getDate().toString();
        if (diaryBeanList.get(position).getDate().equals(date)) {
            holder.ivCircle.setImageResource(R.drawable.circle_orange);
        }
        holder.tvDate.setText(diaryBeanList.get(position).getDate());
        holder.tvTitle.setText(diaryBeanList.get(position).getTitle());
        holder.tvContent.setText("       " + diaryBeanList.get(position).getContent());
        holder.ivEdit.setVisibility(View.INVISIBLE);

        if (editPosition == position) {
            holder.ivEdit.setVisibility(View.VISIBLE);
        } else {
            holder.ivEdit.setVisibility(View.GONE);
        }

        holder.layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.ivEdit.getVisibility() == View.VISIBLE) {
                    holder.ivEdit.setVisibility(View.GONE);
                } else {
                    holder.ivEdit.setVisibility(View.VISIBLE);
                }

                if (editPosition != position) {
                    notifyItemChanged(position);
                }
                editPosition = position;
            }
        });

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new StartUpdateDiaryEvent(diaryBeanList.get(position)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return diaryBeanList.size();
    }

    public static class DiaryViewHolder extends RecyclerView.ViewHolder {

        TextView tvDate;
        TextView tvTitle;
        TextView tvContent;
        ImageView ivEdit;
        LinearLayout layout1;
        LinearLayout layout2;
        ImageView ivCircle;
        LinearLayout layout3;
        RelativeLayout relativeLayout;

        public DiaryViewHolder(View itemView) {
            super(itemView);
            ivCircle = (ImageView) itemView.findViewById(R.id.iv_circle);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            ivEdit = (ImageView) itemView.findViewById(R.id.iv_edit);
            layout1 = (LinearLayout) itemView.findViewById(R.id.item_layout);
            layout2 = (LinearLayout) itemView.findViewById(R.id.main_title);
            layout3 = (LinearLayout) itemView.findViewById(R.id.main_layout);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.item_edit);
        }
    }
}
