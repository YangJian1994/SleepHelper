package com.example.sleephelper.common.net;

import com.android.volley.VolleyError;

/**
 * Created by 杨健 on 2017/5/24.
 * function: 用于网络请求的回调
 */

public interface VolleyResponseCallback {

    void onSuccess(String response);

    void onError(VolleyError error);

}
