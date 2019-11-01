package com.android.httpmodule.interceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpCommonInterceptor implements Interceptor {
    private Map<String, String> mHeaderParamsMap = new HashMap<String, String>();
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        //新的请求
        Request.Builder requestBuilder = oldRequest.newBuilder();
        requestBuilder.method(oldRequest.method(), oldRequest.body());
        requestBuilder.header("Content-Type", "text/plain; charset=utf-8")
                .header("Accept", "*/*")
                //.header("Accept-Encoding", "gzip")
        ;
        //添加公共参数,添加到header中
        if(mHeaderParamsMap.size() > 0) {
            for(Map.Entry<String, String> param: mHeaderParamsMap.entrySet()) {
                requestBuilder.header(param.getKey(), param.getValue());
            }
        }
        Request newRequest = requestBuilder.build();
        return chain.proceed(newRequest);
    }

    public static class Builder {
        HttpCommonInterceptor mHttpCommonInterceptor;
        public Builder() {
            mHttpCommonInterceptor = new HttpCommonInterceptor();
        }

        public Builder addHeaderParams(String key, String value) {
            mHttpCommonInterceptor.mHeaderParamsMap.put(key, value);
            return this;
        }

        public Builder addHeaderParams(String key, int value) {
            mHttpCommonInterceptor.mHeaderParamsMap.put(key, value + "");
            return this;
        }

        public Builder addHeaderParams(String key, float value) {
            mHttpCommonInterceptor.mHeaderParamsMap.put(key, value + "");
            return this;
        }

        public Builder addHeaderParams(String key, double value) {
            mHttpCommonInterceptor.mHeaderParamsMap.put(key, value + "");
            return this;
        }

        public Builder addHeaderParams(Map<String, String> headerParams) {
            if (null == headerParams) {
                return this;
            }
            for (Map.Entry<String, String> entry : headerParams.entrySet()) {
                mHttpCommonInterceptor.mHeaderParamsMap.put(entry.getKey(), entry.getValue());
            }
            return this;
        }

        public HttpCommonInterceptor build() {
            return mHttpCommonInterceptor;
        }
    }
}
