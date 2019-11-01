package com.android.httpmodule.converterfactory;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class EncryptGsonConverterFactory extends Converter.Factory {
    public static final String TAG = EncryptGsonConverterFactory.class.getSimpleName();
    private final Gson gson;

    public static EncryptGsonConverterFactory create() {
        return create(new Gson());
    }

    @SuppressWarnings("ConstantConditions")
    public static EncryptGsonConverterFactory create(Gson gson) {
        if (null == gson) {
            throw new NullPointerException("[EncryptGsonConverterFactory]gson == null");
        }
        return new EncryptGsonConverterFactory(gson);
    }

    private EncryptGsonConverterFactory(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        //return super.responseBodyConverter(type, annotations, retrofit);
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new EncryptGsonResponseConverter<>(gson, adapter);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        //return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new EncryptGsonRequestBodyConverter<>(gson, adapter);
    }

}
