package com.n0texpecterr0r.base.util;

import org.json.JSONObject;

import java.util.Iterator;

/**
 * 多层嵌套根据路径获取指定json字符串
 *
 * @author N0tExpectErr0r
 * @time 2018/9/8
 */
public class JsonUtil {
    /**
     * 在json多层嵌套情况下，获取指定路径的json字符串，
     * 如：a.b.c.d.e.f，获取到f层级的json字符串
     *
     * @param json  未加处理的json字符串
     * @param route json的指定路径
     * @return 处理过后的json字符串
     */
    public static String getNodeString(String json, String route) {
        if (json.isEmpty()) {
            return null;
        }
        // 获取到每一级json字符串的key
        String[] keys = route.split("\\.");
        String newJson = json;
        for (int i = 0; i < keys.length; i++) {
            newJson = splitJsonByKey(newJson, keys[i]);
            if (i == keys.length - 1) {
                return newJson;
            }
        }
        return null;
    }

    /**
     * 根据json字符串的路径裁截子json字符串
     *
     * @param json 原json字符串
     * @param key  json字符串中的键
     * @return 裁截处理后的子json字符串
     */
    private static String splitJsonByKey(String json, String key) {
        try {
            JSONObject obj = new JSONObject(json);
            for (Iterator<String> iter = obj.keys(); iter.hasNext(); ) {
                String s = iter.next();
                if (s == null) {
                    return null;
                }
                if (s.equals(key)) {
                    return obj.getString(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
