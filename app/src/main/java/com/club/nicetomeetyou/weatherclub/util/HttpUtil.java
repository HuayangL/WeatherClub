package com.club.nicetomeetyou.weatherclub.util;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

/**
 * Created by nice to meet you on 2017/3/3.
 */

public class HttpUtil {
    public static void sendOkHttpRequest(String address,Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
