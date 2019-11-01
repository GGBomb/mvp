package com.jianlong.youtv;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
*
*@author ldl
*created at 2016/11/3 8:55
*/
public class MyFactory extends Converter.Factory {

	public static MyFactory create() {
        return create(new Gson());
    }

    public static MyFactory create(Gson gson) {
        return new MyFactory(gson);

    }

    private final Gson gson;

    public MyFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }
    
//	@Override
//	public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
//		TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
//		return new JsonResponseBodyConverter<>(gson, adapter);  //响应
//	}
//
//	@Override
//	public Converter<?, RequestBody> toRequestBody(Type type, Annotation[] annotations) {
//		TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
//		return new JsonRequestBodyConverter<>(gson, adapter); //请求
//	}

	@Override
	public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
		TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
		return new JsonResponseBodyConverter<>(gson, adapter);  //响应
	}

	@Override
	public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations,
                                                          Retrofit retrofit) {
		TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
		return new JsonRequestBodyConverter<>(gson, adapter); //请求
	}

}
