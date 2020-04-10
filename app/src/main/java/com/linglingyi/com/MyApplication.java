package com.linglingyi.com;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.util.Log;

import com.iflytek.cloud.SpeechUtility;
import com.linglingyi.com.utils.AppUtils;
import com.linglingyi.com.viewone.GlidePickImageLoader;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
//import com.squareup.leakcanary.LeakCanary;
//import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;

/**
 * @author yuanjigong 初始化数据库
 */
public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks {

    // voucherNo 流水号,batchNo 批次号,terminalNo 终端号
    public static HashMap<String, String> bankNameList;
    public static HashMap<String, String> bankCodeList;
    public static Context applicationContext;
    private static MyApplication instance;
//    private RefWatcher mRefWatcher;

    public static String getBankName(String bankCode) {
        String name = bankCodeList.get(bankCode);
        if (TextUtils.isEmpty(name) || "null".equals(name)) {
            name = "城市商业银行";
        }
        return name;
    }


    public static MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }

//    public static RefWatcher getRefWatcher(Context context) {
//        MyApplication application = (MyApplication) context.getApplicationContext();
//        return application.mRefWatcher;
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);
        applicationContext = getApplicationContext();
        bankNameList = new HashMap<>();
        bankCodeList = new HashMap<>();
        initBankNameList(bankNameList);
        initBankCodeList(bankCodeList);
        //JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        ShareSDK.initSDK(this);
        initOkgo();
//        获取相册
        initImagePicker();
        //内存检测df
        initLeak();
//        SpeechUtility.createUtility(MyApplication.this, "appid=" + "582beb29");
        initBugly();
        initX5web();
    }

    private void initX5web() {
        HashMap map = new HashMap();
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER, true);
        QbSdk.initTbsSettings(map);
    }

    /**
     * bugly初始化
     *
     */
    private void initBugly() {

        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = AppUtils.getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化Bugly
        CrashReport.initCrashReport(getApplicationContext(), "563357b0f1", false);

    }


    private void initLeak() {
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            this.mRefWMatcher = RefWatcher.DISABLED;
//            return;
//        }
//        this.mRefWatcher = LeakCanary.install(this);
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlidePickImageLoader());
        imagePicker.setShowCamera(true);
        imagePicker.setCrop(false);
        imagePicker.setSelectLimit(1);
//        imagePicker.setFocusWidth(D);
//        imagePicker.setFocusHeight(800);
    }

    private void initOkgo() {
        OkGo.getInstance().init(this)
                .setRetryCount(0)
                .setCacheMode(CacheMode.NO_CACHE);

    }

    /**
     * 北京银行	313003
     * 光大银行	303
     * 广发银行	306
     * 建设银行	105
     * 交通银行	301
     * 民生银行	305
     * 农业银行	103
     * 平安银行	307
     * 浦发银行	310
     * 邮政储蓄银行	403
     * 招商银行	308
     * 中国工商银行	102
     * 中国银行	104
     * 中信银行	302
     * 上海银行	313062
     * 杭州银行	313027
     *
     * @param list
     */
    private void initBankNameList(HashMap<String, String> list) {
        list.put("北京银行", "313003");
        list.put("光大银行", "303");
        list.put("广发银行", "306");
        list.put("建设银行", "105");
        list.put("交通银行", "301");
        list.put("民生银行", "305");
        list.put("农业银行", "103");
        list.put("平安银行", "307");
        list.put("浦发银行", "310");
        list.put("邮政储蓄银行", "403");
        list.put("招商银行", "308");
        list.put("中国工商银行", "102");
        list.put("工商银行", "102");
        list.put("中国银行", "104");
        list.put("中信银行", "302");
        list.put("上海银行", "313062");
        list.put("杭州银行", "313027");
        list.put("华夏银行", "304");
        list.put("北京农商行", "402002");
        list.put("兴业银行", "309");
        list.put("台州银行", "313066");
        list.put("泰隆银行", "313080");
        list.put("民泰银行", "313079");
        list.put("农村信用社", "314");
        list.put("浙商银行", "316");
    }

    private void initBankCodeList(HashMap<String, String> list) {
        list.put("313003", "北京银行");
        list.put("303", "光大银行");
        list.put("306", "广发银行");
        list.put("105", "建设银行");
        list.put("301", "交通银行");
        list.put("305", "民生银行");
        list.put("103", "农业银行");
        list.put("307", "平安银行");
        list.put("310", "浦发银行");
        list.put("403", "邮政储蓄银行");
        list.put("308", "招商银行");
        list.put("102", "中国工商银行");
        list.put("102", "工商银行");
        list.put("104", "中国银行");
        list.put("302", "中信银行");
        list.put("313062", "上海银行");
        list.put("313027", "杭州银行");
        list.put("304", "华夏银行");
        list.put("402002", "北京农商行");
        list.put("309", "兴业银行");
        list.put("303066", "台州银行");
        list.put("313080", "泰隆银行");
        list.put("313079", "民泰银行");
        list.put("314", "农村信用社");
        list.put("316", "浙商银行");
    }



    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.i("Activity_Show", "Activity = " + activity.getLocalClassName());
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
