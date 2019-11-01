package com.android.httpmodule.interceptor;

import android.text.TextUtils;
import android.util.Log;

import com.android.base.application.BaseApplication;
import com.android.httpmodule.utils.NetWorkUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();//拦截Request对象
        Log.d("OkHttpUtils", "request.url():" + request.url());
        Response response = chain.proceed(request);//获取服务器响应
        if (TextUtils.isEmpty(response.cacheControl().toString())) {
            int time = 0;
            //判断是否有网络 如果有网络就加载网络数据 没有网络就加载缓存数据
            if (NetWorkUtil.isNetworkAvailable(BaseApplication.getInstance().getApplication().getBaseContext())) {
                time = 0;
            } else {
                time = 60 * 60 * 24 * 7;//30;//30天 7天
            }
            //生成新的response对象 往新的对象中手动添加Cache-Control 让其支持缓存
            Response newReponse = response.newBuilder().addHeader("Cache-Control", "max-age=" + time).build();
            return newReponse;
        }
        return response;
    }
}
