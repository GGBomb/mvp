package com.android.httpmodule.converterfactory;

import android.util.Log;

import com.android.httpmodule.encrypt.Encryption;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;
import retrofit2.Converter;

public class EncryptGsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    public static final String TAG = EncryptGsonRequestBodyConverter.class.getSimpleName();
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    public EncryptGsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        Buffer buffer = new Buffer();
        Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
        JsonWriter jsonWriter = gson.newJsonWriter(writer);
        adapter.write(jsonWriter, value);
        Log.e(TAG, "[convert]params value=" + value.toString());
        //String encrptparams = Encryption.Encrypt(buffer.readByteArray());//加密
        jsonWriter.close();
        //return RequestBody.create(MEDIA_TYPE, encrptparams);
        return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
    }
}
