package com.example.sleephelper.common.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 杨健 on 2017/5/25.
 * function: ViewPager和Fragment通用的Adapter
 */

public class CommonPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public CommonPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
