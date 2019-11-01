package com.android.httpmodule;

import android.os.Environment;

import com.android.base.constance.ConfigConstant;
import com.android.httpmodule.api.ApiConfig;
import com.android.httpmodule.converterfactory.EncryptGsonConverterFactory;
import com.android.httpmodule.encrypt.CustomTrust;
import com.android.httpmodule.interceptor.CacheInterceptor;
import com.android.httpmodule.interceptor.GzipRequestInterceptor;
import com.android.httpmodule.interceptor.HttpCommonInterceptor;
import com.android.httpmodule.interceptor.RequestEncryptInterceptor;
import com.gangyun.httpmodule.GangyunHttpMethod;

import java.io.File;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceManager {
    private static RetrofitServiceManager mRetrofitServiceManager;
    private static final int DEFAULT_TIME_OUT = 5;//连接超时时间
    private static final int DEFAULT_READ_TIME_OUT = 10;//
    private Retrofit mRetrofit;
    private static Map<String, String> headerParams = new HashMap<>();

    private RetrofitServiceManager() {
        this(null);
    }

    public static synchronized RetrofitServiceManager getInstance(Map<String, String> params) {
        if (null == mRetrofitServiceManager || (null != params && headerParams.equals(params))) {
            mRetrofitServiceManager = new RetrofitServiceManager(params);
        }
        return mRetrofitServiceManager;
    }

    /**
     * 获取对应的Service
     */
    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }

    public RetrofitServiceManager(Map<String, String> headerParams) {
        if (null != headerParams) {
            this.headerParams = headerParams;
        }
        //设置缓存策略
        File directory = new File(Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_DOCUMENTS);
        long maxSize = 50 * 1024  * 1024;//50M * 1024 KB * 1024 Byte
        Cache cache = new Cache(directory, maxSize);
        CacheInterceptor cacheInterceptor = new CacheInterceptor();
        //创建OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);
        builder.cache(cache).addNetworkInterceptor(cacheInterceptor);//设置缓存
        //添加公共参数拦截器//add to Header
        HttpCommonInterceptor commonInterceptor = new HttpCommonInterceptor.Builder()
                //.addHeaderParams("os", "android" + android.os.Build.VERSION.RELEASE)
                //.addHeaderParams("model", android.os.Build.MODEL)
                //.addHeaderParams("Content-Type", "application/json")
                //.addHeaderParams("Accept", "application/json")
                .addHeaderParams(headerParams)
                .build();
        builder.addInterceptor(commonInterceptor);
        //builder.addInterceptor(new RequestEncryptInterceptor());
        //builder.addInterceptor(new GzipRequestInterceptor());
        /**
         * Log信息拦截器
         */
        if (ConfigConstant.DEBUG) {
            // Log信息拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //设置 Debug Log 模式
            builder.addInterceptor(loggingInterceptor);
        }
        /**
         * 设置cookie
         */
        //CookieManager cookieManager = new CookieManager();
        //cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        //cookieManager.put();
        //builder.cookieJar(new JavaNetCookieJar(cookieManager));
        /**
         * 设置证书
         */
        //CustomTrust ct = new CustomTrust();
        //builder.sslSocketFactory(ct.sslSocketFactory, ct.trustManager);
        //创建Retrofit
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(EncryptGsonConverterFactory.create())
                //.baseUrl(ApiConfig.BASE_URL)
                .baseUrl(GangyunHttpMethod.BASE_URL)
                .build();
    }
}
