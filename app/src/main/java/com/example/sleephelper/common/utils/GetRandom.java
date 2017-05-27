package com.example.sleephelper.common.utils;

/**
 * Created by 杨健 on 2017/5/27.
 * function: 获取随机数
 */

public class GetRandom {

    public static int getRandom(){
        double random = Math.random();
        int result = (int)(random * 50 - 20);
        return Math.abs(result);
    }
}
