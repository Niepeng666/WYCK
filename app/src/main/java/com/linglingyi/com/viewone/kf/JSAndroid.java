package com.linglingyi.com.viewone.kf;

import android.app.Activity;
import android.webkit.JavascriptInterface;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/7/11
 */
public class JSAndroid extends Object {
    private Activity context;
    private ConfigerManagner configerManagner;

    public JSAndroid(Activity context) {
        this.context = context;
    }

    @JavascriptInterface
    public void openAndroid(String msg) {
        context.finish();
    }

    @JavascriptInterface
    public void writeData(String msg) {
        configerManagner = ConfigerManagner.getInstance(context);
        configerManagner.setString("js", msg);
    }

    @JavascriptInterface
    public String giveInformation(String msg) {
        configerManagner = ConfigerManagner.getInstance(context);
        String msg1 = configerManagner.getString("js");
        return msg1;
    }
}
