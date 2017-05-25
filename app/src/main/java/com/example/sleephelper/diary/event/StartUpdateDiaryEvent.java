package com.example.sleephelper.diary.event;

import com.example.sleephelper.diary.bean.DiaryBean;

/**
 * Created by 杨健 on 2017/5/25.
 * function: 日记修改
 */

public class StartUpdateDiaryEvent {

    private DiaryBean diaryBean;

    public StartUpdateDiaryEvent(DiaryBean diaryBean) {
        this.diaryBean = diaryBean;
    }

    public DiaryBean getDiaryBean() {
        return diaryBean;
    }
}
