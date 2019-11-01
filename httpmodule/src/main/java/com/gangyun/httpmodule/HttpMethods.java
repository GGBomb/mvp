package com.gangyun.httpmodule;

import com.android.httpmodule.ObjectLoader;
import com.android.httpmodule.RetrofitServiceManager;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class HttpMethods extends ObjectLoader {
    public static final String TAG = HttpMethods.class.getSimpleName();
    private final ApiService apiService;
    private static HttpMethods httpMethods;

    private HttpMethods() {
        apiService = RetrofitServiceManager.getInstance(null).create(ApiService.class);
    }

    public static synchronized HttpMethods getInstance() {
        if (null == httpMethods) {
            httpMethods = new HttpMethods();
        }
        return httpMethods;
    }

   /* public Observable<List<Movie>> getTop250(int start, int count) {
        return observe(apiService.getTop250(start, count);
    }*/

    public Observable<String> getWeather(String cityId, int key) {
        return observe(apiService.getWeather(cityId, key));
    }
}
