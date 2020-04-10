package com.linglingyi.com.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.widget.Toast;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/12
 */
public class ToastUtils {
//    private static final int BLUE = Color.parseColor("#0FAAE2");
//    private static final int RED = Color.parseColor("#FA7277");
//    private static final int ORANGE = Color.parseColor("#FF7200");
//    private static final int WHITE = Color.parseColor("#FFFFFF");
//    private static int duration = Toast.LENGTH_SHORT;
//    private static Toast toast = null;
//    @SuppressLint("StaticFieldLeak")
//    private static Context mContext;
//
//    private ToastUtils() {
//        throw new UnsupportedOperationException("cannot be instantiated");
//    }
//
//    public static void init(Application application) {
//        mContext = application.getApplicationContext();
//        Toasty.Config.getInstance()
//                .setSuccessColor(BLUE).setErrorColor(RED).setWarningColor(ORANGE).setInfoColor(BLUE)
//                .setTextColor(WHITE).setTextSize(14)
//                .apply();
//    }
//
//    /**
//     * 用户保证页面只有一个toast
//     */
//    public static void cancelToast() {
//        if (toast != null) {
//            toast.cancel();
//        }
//        toast = null;
//    }
//
//    private static boolean checkSDK() {
//        return Build.VERSION_CODES.N_MR1 == Build.VERSION.SDK_INT;
//    }
//
//    /**
//     * 普通吐司
//     */
//    public static void nomal(String message) {
//        cancelToast();
//        if (checkSDK())
//            toast = ToastCompat.makeText(mContext, message, duration);
//        else
//            toast = Toasty.normal(mContext, message, duration);
//        toast.show();
//    }
//
//    /**
//     * 普通吐司
//     */
//
//    public static void tip(String message) {
//        cancelToast();
//        if (checkSDK())
//            toast = ToastCompat.makeText(mContext, message, duration);
//        else
//            toast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
//        toast.show();
//    }
//
//    /**
//     * 屏幕中间的普通吐司
//     */
//    public static void CenterTip(String message) {
//        cancelToast();
//        if (checkSDK())
//            toast = ToastCompat.makeText(mContext, message, duration);
//        else
//            toast = Toasty.normal(mContext, message, duration);
//        toast.setGravity(Gravity.CENTER, 0, 0);
//        toast.show();
//    }
//
//
//    /**
//     * 成功吐司
//     */
//    public static void success(String message) {
//        cancelToast();
//        if (checkSDK())
//            toast = ToastCompat.makeText(mContext, message, duration);
//        else
//            toast = Toasty.success(mContext, message, duration);
//        toast.show();
//    }
//
//    /**
//     * 失败吐司
//     */
//    public static void error(String message) {
//        cancelToast();
//        if (checkSDK())
//            toast = ToastCompat.makeText(mContext, message, duration);
//        else
//            toast = Toasty.error(mContext, message, duration);
//        toast.show();
//    }
//
//    /**
//     * 警告吐司
//     */
//    public static void warning(String message) {
//        cancelToast();
//        if (checkSDK())
//            toast = ToastCompat.makeText(mContext, message, duration);
//        else
//            toast = Toasty.warning(mContext, message, duration);
//        toast.show();
//    }
//
//    /**
//     * 提醒吐司
//     */
//    public static void notify(String message) {
//        cancelToast();
//        if (checkSDK())
//            toast = ToastCompat.makeText(mContext, message, duration);
//        else
//            toast = Toasty.info(mContext, message, duration);
//        toast.show();
//    }
}
