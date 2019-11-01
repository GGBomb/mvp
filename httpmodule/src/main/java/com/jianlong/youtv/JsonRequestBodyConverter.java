package com.jianlong.youtv;

import com.android.httpmodule.encrypt.Encryption;
import com.android.httpmodule.utils.ZipUtils;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * 
 * @author 刘栋良
 * 自定义请求RequestBody
 * @param <T>
 */
public class JsonRequestBodyConverter <T> implements Converter<T, RequestBody> {

	private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    /***构造函数*/
    public  JsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }
    
	@Override
	public RequestBody convert(T value) throws IOException {
        //加密
		byte[] j = ZipUtils.compress(value.toString());
		String json = Encryption.Encrypt(j)+"=";
		return RequestBody.create(MEDIA_TYPE, gson.toJson(json));
		//###这里需要，特别注意的是，request是将T转换成json数据。你要在T转换成json之后再做加密。再将数据post给服务器
	}
}
