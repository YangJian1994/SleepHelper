package com.example.sleephelper.meizi.api;

import com.example.sleephelper.common.utils.GetRandom;

/**
 * Created by 杨健 on 2017/5/27.
 * function: 妹子图片的api
 */

public class MeiziApi {

    public static String getMeiziApi(){
        StringBuilder meiziApi = new StringBuilder();
        meiziApi.append("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/").append("15").append("/" + GetRandom.getRandom());
        return String.valueOf(meiziApi);
    }
}
