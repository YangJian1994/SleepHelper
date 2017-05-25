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
import com.example.sleephelper.diary.event.RefreshViewEvent;
import com.example.sleephelper.diary.utils.GetDate;
import com.example.sleephelper.diary.widget.LinedEditText;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.trity.floatingactionbutton.FloatingActionButton;
import cc.trity.floatingactionbutton.FloatingActionsMenu;

public class UpdateDiaryActivity extends AppCompatActivity {


    @Bind(R.id.iv_draw)
    ImageView ivDraw;
    @Bind(R.id.tv_title_center)
    TextView tvTitleCenter;
    @Bind(R.id.tv_title_normal)
    TextView tvTitleNormal;
    @Bind(R.id.iv_menu)
    ImageView ivMenu;
    @Bind(R.id.toolbar)
    LinearLayout toolbar;
    @Bind(R.id.tv_tag)
    TextView tvTag;
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
    @Bind(R.id.fab_delete)
    FloatingActionButton fabDelete;
    @Bind(R.id.fab_menu)
    FloatingActionsMenu fabMenu;

    private DiaryDatabaseHelper helper;

    public static void startActivity(Context context, String title, String content, String tag) {
        Intent intent = new Intent(context, UpdateDiaryActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        intent.putExtra("tag", tag);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_diary);
        ButterKnife.bind(this);
        helper = new DiaryDatabaseHelper(this, "Diary.db", null, 1);
        Intent intent = getIntent();
        initToolbar();
        initView(intent);
    }

    private void initToolbar() {
        ivDraw.setImageResource(R.drawable.app_back);
        ivDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitleNormal.setText("修改日记");
        ivMenu.setVisibility(View.GONE);
    }

    private void initView(Intent intent) {
        tvDate.setText("今天，" + GetDate.getDate());
        etTitle.setText(intent.getStringExtra("title"));
        etContent.setText(intent.getStringExtra("content"));
        tvTag.setText(intent.getStringExtra("tag"));
    }

    @OnClick({R.id.iv_draw, R.id.fab_back, R.id.fab_delete, R.id.fab_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_draw:
                finish();
                break;
            case R.id.fab_back:
                showTips();
                break;
            case R.id.fab_add:
                SQLiteDatabase dbUpdate = helper.getWritableDatabase();
                ContentValues valuesUpdate = new ContentValues();
                String title = etTitle.getText().toString();
                String content = etContent.getText().toString();
                valuesUpdate.put("title", title);
                valuesUpdate.put("content", content);
                dbUpdate.update("Diary", valuesUpdate, "title = ?", new String[]{title});
                dbUpdate.update("Diary", valuesUpdate, "content = ?", new String[]{content});
                finish();
                break;
            case R.id.fab_delete:
                finish();
                break;
        }
    }

    private void showTips() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定要删除该日记吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tag = tvTag.getText().toString();
                SQLiteDatabase dbDelete = helper.getWritableDatabase();
                dbDelete.delete("Diary", "tag = ?", new String[]{tag});
                finish();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
        EventBus.getDefault().post(new RefreshViewEvent());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
