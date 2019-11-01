package com.android.httpmodule.interceptor;

import android.util.Log;

import com.android.httpmodule.encrypt.Encryption;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class RequestEncryptInterceptor implements Interceptor {
    public static final String TAG = RequestEncryptInterceptor.class.getSimpleName();
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        RequestBody body = request.body();
        Buffer buffer = new Buffer();
        body.writeTo(buffer);
        Charset charset = Charset.forName("UTF-8");
        MediaType contentType = body.contentType();
        if (contentType != null) {
            charset = contentType.charset(charset);
        }
        String paramsStr = buffer.readString(charset);
        Log.e(TAG, "[intercept]paramsStr=" + paramsStr);
        try {
            paramsStr = Encryption.Encrypt(paramsStr.getBytes("UTF-8"));//EncryptUtils.encryptParams(paramsStr);
            Log.e(TAG, "[intercept]paramsStr=" + paramsStr);
        } catch (Exception e) {
            e.printStackTrace();
            //LogUtils.e(e);
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain; charset=utf-8"), paramsStr);

        request = request.newBuilder()
                .post(requestBody)
                .build();
        return chain.proceed(request);
    }
}
