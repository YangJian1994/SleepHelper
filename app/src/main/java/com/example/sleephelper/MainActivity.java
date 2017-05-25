package com.example.sleephelper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sleephelper.common.view.CommonPagerAdapter;
import com.example.sleephelper.common.view.CommonTabBean;
import com.example.sleephelper.diary.bean.DiaryBean;
import com.example.sleephelper.diary.event.StartUpdateDiaryEvent;
import com.example.sleephelper.diary.ui.DiaryFragment;
import com.example.sleephelper.diary.ui.UpdateDiaryActivity;
import com.example.sleephelper.duanzi.ui.DuanziFragment;
import com.example.sleephelper.meizi.ui.MeiziFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

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
    @Bind(R.id.main_viewPager)
    ViewPager mainViewPager;
    @Bind(R.id.main_tabLayout)
    CommonTabLayout mainTabLayout;
    @Bind(R.id.navigationView)
    NavigationView navigationView;
    @Bind(R.id.main_drawerLayout)
    DrawerLayout mainDrawerLayout;

    private static final int[] SELECTED_ICONS = new int[]{R.drawable.diary_selected, R.drawable.duanzi_selected, R.drawable.meizi_selected};
    private static final int[] UN_SELECTED_ICONS = new int[]{R.drawable.diary_unselected, R.drawable.duanzi_unselected, R.drawable.meizi_unselected};
    private static final String[] TITLES = new String[]{"日记", "段子", "妹子"};

    private List<Fragment> fragments;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initTabLayout();
        initViewPager();
        initToolbar();
    }

    private void initToolbar() {
        ivDraw.setVisibility(View.GONE);
        ivMenu.setVisibility(View.GONE);
        tvTitleCenter.setVisibility(View.VISIBLE);
        tvTitleNormal.setVisibility(View.GONE);
    }

    private void initViewPager() {
        fragments = new ArrayList<>();
        fragments.add(DiaryFragment.newInstance());
        fragments.add(DuanziFragment.newInstance());
        fragments.add(MeiziFragment.newInstance());
        CommonPagerAdapter adapter = new CommonPagerAdapter(getSupportFragmentManager(), fragments);
        mainViewPager.setAdapter(adapter);
    }

    private void initTabLayout() {
        ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
        for (int i = 0; i < TITLES.length; i++) {
            tabEntities.add(new CommonTabBean(TITLES[i], SELECTED_ICONS[i], UN_SELECTED_ICONS[i]));
        }
        mainTabLayout.setTabData(tabEntities);
        mainTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mainViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        mainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mainTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mainViewPager.setOffscreenPageLimit(4);
        mainViewPager.setCurrentItem(1);
    }

    @Subscribe
    public void startUpdateDiaryActivity(StartUpdateDiaryEvent event) {
        DiaryBean diaryBean = event.getDiaryBean();
        String title = diaryBean.getTitle();
        String content = diaryBean.getContent();
        String tag = diaryBean.getTag();
        UpdateDiaryActivity.startActivity(this, title, content, tag);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
