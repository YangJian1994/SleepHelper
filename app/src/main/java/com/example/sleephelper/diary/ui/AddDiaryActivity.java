package com.example.sleephelper.diary.ui;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sleephelper.R;
import com.example.sleephelper.diary.db.DiaryDatabaseHelper;
import com.example.sleephelper.diary.utils.GetDate;
import com.example.sleephelper.diary.widget.LinedEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.trity.floatingactionbutton.FloatingActionButton;
import cc.trity.floatingactionbutton.FloatingActionsMenu;

public class AddDiaryActivity extends AppCompatActivity {

    @Bind(R.id.iv_draw)
    ImageView ivDraw;
    @Bind(R.id.tv_title_normal)
    TextView tvTitleNormal;
    @Bind(R.id.iv_menu)
    ImageView ivMenu;
    @Bind(R.id.toolbar)
    LinearLayout toolbar;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.et_title)
    EditText etTitle;
    @Bind(R.id.et_content)
    LinedEditText etContent;
    @Bind(R.id.fab_back)
    FloatingActionButton fabBack;
    @Bind(R.id.fab_add)
    FloatingActionButton fabAdd;
    @Bind(R.id.fab_menu)
    FloatingActionsMenu fabMenu;

    private DiaryDatabaseHelper helper;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AddDiaryActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        initToolbar();
        initView(intent);
        helper = new DiaryDatabaseHelper(this, "Diary.db", null, 1);
    }

    private void initView(Intent intent) {
        etTitle.setText(intent.getStringExtra("title"));
        tvDate.setText("今天，" + GetDate.getDate());
        etContent.setText(intent.getStringExtra("content"));
    }

    private void initToolbar() {
        ivDraw.setImageResource(R.drawable.app_back);
        tvTitleNormal.setText("添加日记");
        ivMenu.setVisibility(View.GONE);
    }

    @OnClick({R.id.iv_draw, R.id.et_title, R.id.et_content, R.id.fab_back, R.id.fab_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_draw:
                backToDiaryFragment();
                break;
            case R.id.et_title:
                break;
            case R.id.et_content:
                break;
            case R.id.fab_back:
                String date = GetDate.getDate().toString();
                String tag = String.valueOf(System.currentTimeMillis());
                String title = etTitle.getText().toString();
                String content = etContent.getText().toString();
                if (!title.equals("") || !content.equals("")) {
                    SQLiteDatabase db = helper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("date", date);
                    values.put("title", title);
                    values.put("content", content);
                    values.put("tag", tag);
                    db.insert("Diary", null, values);
                    values.clear();
                }
                finish();
                break;
            case R.id.fab_add:
                backToDiaryFragment();
                break;
        }
    }

    private void backToDiaryFragment() {
        final String dateBack = GetDate.getDate().toString();
        final String titleBack = etTitle.getText().toString();
        final String contentBack = etContent.getText().toString();
        if (!titleBack.isEmpty() || !contentBack.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("是否保存日记内容？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SQLiteDatabase db = helper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("data", dateBack);
                    values.put("title", titleBack);
                    values.put("content", contentBack);
                    db.insert("Diary", null, values);
                    values.clear();
                    finish();
                }
            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).show();
        } else {
            finish();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
