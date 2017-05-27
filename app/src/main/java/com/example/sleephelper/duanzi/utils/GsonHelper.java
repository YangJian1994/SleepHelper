package com.example.sleephelper.duanzi.utils;

import com.example.sleephelper.duanzi.bean.DuanziBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 杨健 on 2017/5/25.
 * function: Json解析的帮助类
 */

public class GsonHelper {

    public static List<DuanziBean> getDuanziBeanList(String response) {
        List<DuanziBean> duanziBeanList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            String data = jsonObject.getJSONObject("data").getString("data");
            Type type = new TypeToken<List<DuanziBean>>(){}.getType();
            Gson gson = new Gson();
            duanziBeanList = gson.fromJson(data, type);
            return duanziBeanList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return duanziBeanList;
    }
}
