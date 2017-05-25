package com.example.sleephelper.common.view;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * Created by 杨健 on 2017/5/24.
 * function: Tab类
 */

public class CommonTabBean implements CustomTabEntity {

    private int selectedIcon;
    private int unselectedIcon;
    private String title;

    public CommonTabBean(String title, int selectedIcon, int unselectedIcon) {
        this.selectedIcon = selectedIcon;
        this.unselectedIcon = unselectedIcon;
        this.title = title;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unselectedIcon;
    }
}
