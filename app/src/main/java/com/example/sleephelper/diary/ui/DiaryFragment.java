package com.example.sleephelper.diary.ui;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sleephelper.R;
import com.example.sleephelper.diary.bean.DiaryBean;
import com.example.sleephelper.diary.db.DiaryDatabaseHelper;
import com.example.sleephelper.diary.utils.GetDate;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiaryFragment extends Fragment {


    private static final String TAG = DiaryFragment.class.getSimpleName();
    @Bind(R.id.iv_circle)
    ImageView ivCircle;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.item_layout)
    LinearLayout itemLayout;
    @Bind(R.id.item_first)
    LinearLayout itemFirst;
    @Bind(R.id.main_recyclerView)
    RecyclerView mainRecyclerView;
    @Bind(R.id.main_linearLayout)
    LinearLayout mainLinearLayout;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.main_relativeLayout)
    RelativeLayout mainRelativeLayout;

    private List<DiaryBean> diaryBeanList;
    private DiaryDatabaseHelper databaseHelper;

    private static final String DB_DIARY_NAME = "Diary.db";

    public DiaryFragment() {
        // Required empty public constructor
    }

    public static DiaryFragment newInstance() {

        Bundle args = new Bundle();

        DiaryFragment fragment = new DiaryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diary, container, false);
        ButterKnife.bind(this, view);
        databaseHelper = new DiaryDatabaseHelper(getActivity(), DB_DIARY_NAME, null, 1);
        getDiaryBeanList();
        initView();
        return view;
    }

    private void initView() {
        tvDate.setText(GetDate.getDate().toString());
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mainRecyclerView.setAdapter(new DiaryAdapter(getActivity(), diaryBeanList));
    }

    private void getDiaryBeanList() {
        diaryBeanList = new ArrayList<>();
        List<DiaryBean> diaryList = new ArrayList<>();
        SQLiteDatabase sqliteDatabase = databaseHelper.getWritableDatabase();
        Cursor cursor = sqliteDatabase.query("Diary", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String dateSystem = GetDate.getDate().toString();
                if (date.equals(dateSystem)) {
                    mainRecyclerView.removeView(itemFirst);
                }
                break;
            } while (cursor.moveToNext());
        }

        if (cursor.moveToFirst()) {
            do {
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String tag = cursor.getString(cursor.getColumnIndex("tag"));
                diaryBeanList.add(new DiaryBean(date, title, content, tag));
            } while (cursor.moveToNext());
        }
        cursor.close();

        for (int i = diaryBeanList.size() - 1; i >= 0 ; i--) {
            diaryList.add(diaryBeanList.get(i));
        }

        diaryBeanList = diaryList;
        Log.e(TAG, diaryBeanList.size() + "");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getDiaryBeanList();
        initView();
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        AddDiaryActivity.startActivity(getActivity());
    }
}
