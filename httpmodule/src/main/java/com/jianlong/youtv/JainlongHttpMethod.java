package com.jianlong.youtv;

import android.util.Log;

import com.android.httpmodule.ObjectLoader;
import com.android.httpmodule.RetrofitServiceManager;
import com.android.httpmodule.bean.BaseResponse;
import com.jianlong.youtv.bean.Token;
import com.jianlong.youtv.params.LoginInfo;
import com.jianlong.youtv.params.LoginMethod;
import com.jianlong.youtv.params.Subject;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class JainlongHttpMethod extends ObjectLoader{
    public static final String TAG = JainlongHttpMethod.class.getSimpleName();
    private JianlongApiService jianlongApiService;

    public JainlongHttpMethod(Map<String, String> headerparams) {
        jianlongApiService = RetrofitServiceManager.getInstance(headerparams).create(JianlongApiService.class);
    }

    //http://192.168.2.180:9999/auth/mobile/token?mobile=13542101235/1&randomStr=95751550477390709&code=963258&grant_type=mobile&scope=server
    public Observable<Token> onToken(LoginInfo loginInfo) {
        return jianlongApiService.token(loginInfo.getMobile(), loginInfo.getRandomStr(), loginInfo.getCode(), loginInfo.getGrant_type(), loginInfo.getScope()).map(new Function<BaseResponse<Token>, Token>() {
            @Override
            public Token apply(BaseResponse<Token> tokenBaseResponse) throws Exception {
                Log.e(TAG, "[onToken]" + tokenBaseResponse.message);
                return tokenBaseResponse.data;
            }
        });
    }

    public Observable<BaseResponse> getListDepartment(Subject subject) {
        return jianlongApiService.getListDeparts(subject.getDepartname(), subject.getDepartid());
    }
}
