package com.gangyun.httpmodule;

import com.android.httpmodule.ObjectLoader;
import com.android.httpmodule.RetrofitServiceManager;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class GangyunHttpMethod extends ObjectLoader {
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";
    public static final String TAG = GangyunHttpMethod.class.getSimpleName();

    private ApiService apiService;

    public GangyunHttpMethod() {
        apiService = RetrofitServiceManager.getInstance(null).create(ApiService.class);
    }

    public Observable<Movie> getTop250(int start, int count) {
        return apiService.getTop250(start, count).map(new Function<MovieSubject, Movie>() {
            @Override
            public Movie apply(MovieSubject movieSubject) throws Exception {
                Movie movie = movieSubject.getSubjects().get(0);
                return movie;
            }
        });

    }
}
