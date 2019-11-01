package com.jianlong.youtv;

import com.android.httpmodule.encrypt.Encryption;
import com.android.httpmodule.utils.ZipUtils;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author 刘栋良
 *         created at 2016/11/3 8:55
 */
public class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    public static final String TAG = JsonResponseBodyConverter.class.getSimpleName();

    private final Gson mGson;//gson对象
    private final TypeAdapter<T> adapter;

    /**
     * 构造器
     */
    public JsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.mGson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody responseBody) throws IOException {
        //解密
        byte[] jsonByte = Encryption.Decrypt(responseBody.string());// 返回为空
        String responseData = ZipUtils.unCompress(jsonByte);
        return  (T)responseData;
        //需要注意的是，response是将string转换成T，string需要先解密成json再转换成T，同样要注意你的T指代的谁
    }
}
