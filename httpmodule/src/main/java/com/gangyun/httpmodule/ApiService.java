package com.gangyun.httpmodule;

import com.android.httpmodule.bean.BaseResponse;
import com.gangyun.httpmodule.MovieSubject;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    //https://api.douban.com/v2/movie/
    //https://api.douban.com/v2/movie/top250?start=0&count=10
    @GET("top250")
    Observable<MovieSubject> getTop250(@Query("start")int start, @Query("count")int count);

    /*@FormUrlEncoded
    @POST("/x3/weather")
    Call<String> getWeather(@Field("cityId")String cityId, @Field("key")int key);*/

    @FormUrlEncoded
    @POST("/x3/weather")
    Observable<String> getWeather(@Field("cityId")String cityId, @Field("key")int key);

}
