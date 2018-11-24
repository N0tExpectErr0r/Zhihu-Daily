package com.n0texpecterr0r.base.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.Reader;
import java.util.List;

/**
 * 解析Gson需要用此类, 认为此处与Gson解耦
 *
 * @author N0tExpectErr0r
 * @time 2018/11/23
 */
public class Gsoner {
    private static Gson mGson;

    private static void checkState() {
        if (mGson == null) {
            mGson = new Gson();
        }
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        checkState();
        return mGson.fromJson(json, classOfT);
    }

    public static <T> List<T> fromJson(String json, TypeToken<List<T>> typeOfT) {
        checkState();
        return mGson.fromJson(json, typeOfT.getType());
    }

    public static <T> T fromJson(Reader json, Class<T> classOfT) {
        checkState();
        return mGson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(Reader json, TypeToken<List<T>> typeOfT) {
        checkState();
        return mGson.fromJson(json, typeOfT.getType());
    }

    public static <T> T fromJson(JsonReader jsonReader, TypeToken<List<T>> typeOfT) {
        checkState();
        return mGson.fromJson(jsonReader, typeOfT.getType());
    }

    public static <T> T fromJson(JsonElement jsonElement, Class<T> classOfT) {
        checkState();
        return mGson.fromJson(jsonElement, classOfT);
    }

    public static <T> T fromJson(JsonElement jsonElement, TypeToken<List<T>> typeOfT) {
        checkState();
        return mGson.fromJson(jsonElement, typeOfT.getType());
    }
}
