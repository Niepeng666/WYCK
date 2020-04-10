package com.linglingyi.com.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


/**
 * @author yuanjigong
 * @ClassName:
 * @Description: TODO   存储查询参数类
 * @date 2014-8-12 下午10:59:43
 */
public class StorageAppInfoUtil {

    public static void putInfo(Context context, String key, String value) {

        SharedPreferences sps = context.getSharedPreferences("appinfo", Context.MODE_PRIVATE);
        Editor editor = sps.edit();
        editor.putString(key, value);

        editor.commit();
    }

    public static void putInfo(Context context, String key, boolean value) {

        SharedPreferences sps = context.getSharedPreferences("appinfo", Context.MODE_PRIVATE);
        Editor editor = sps.edit();
        editor.putBoolean(key, value);

        editor.commit();
    }

    public static boolean getBooleanInfo(String key, Context context) {

        SharedPreferences sps = context.getSharedPreferences("appinfo", Context.MODE_PRIVATE);
        boolean value = sps.getBoolean(key, true);
        return value;
    }

    public static String getInfo(String key, Context context) {

        SharedPreferences sps = context.getSharedPreferences("appinfo", Context.MODE_PRIVATE);
        String value = sps.getString(key, "");

        return value;
    }

    public static String getInfo(String key, String v, Context context) {

        SharedPreferences sps = context.getSharedPreferences("appinfo", Context.MODE_PRIVATE);
        String value = sps.getString(key, v);
        return value;
    }

    /**
     * @param key
     * @param context
     * @return
     */
    public static boolean removeKey(String key, Context context) {

        SharedPreferences sp = context.getSharedPreferences("appinfo",
                Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
        return true;

    }

    public static boolean clearKey(Context context) {

        SharedPreferences sp = context.getSharedPreferences("appinfo",
                Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.clear();
        editor.commit();
        return true;

    }

}
