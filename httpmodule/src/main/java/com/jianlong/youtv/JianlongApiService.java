package com.jianlong.youtv;

import com.android.httpmodule.bean.BaseResponse;
import com.jianlong.youtv.bean.Token;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JianlongApiService {
    //http://192.168.2.180:9999/auth/mobile/token?mobile=13542101235/1&randomStr=95751550477390709&code=963258&grant_type=mobile&scope=server
    @GET("/auth/mobile/token/")
    public Observable<BaseResponse<Token>> token(@Query("mobile") String mobile, @Query("randomStr") String randomStr, @Query("code") String code, @Query("grant_type") String grant_type, @Query("scope") String scope);

    //http://192.168.2.180:9999/user/clientorg/listdeparts
    @FormUrlEncoded
    @POST("/user/clientorg/listdeparts")
    public Observable<BaseResponse> getListDeparts(@Field("departname")String departname, @Field("departid") long departid);
}
