package com.example.sleephelper.meizi.utils;

import com.example.sleephelper.meizi.bean.MeiziBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 杨健 on 2017/5/27.
 * function: 妹子图片json解析类
 */

public class GsonHelper {

    public static List<MeiziBean> getMeiziBean(String response){
        List<MeiziBean> meiziBeanList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            String meiziArrayStr = jsonObject.getString("results");
            Type meiziListType = new TypeToken<List<MeiziBean>>(){}.getType();
            Gson gson = new Gson();
            meiziBeanList = gson.fromJson(meiziArrayStr, meiziListType);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return meiziBeanList;
    }
}
