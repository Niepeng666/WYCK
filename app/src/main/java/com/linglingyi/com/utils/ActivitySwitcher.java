package com.linglingyi.com.utils;

import android.app.Activity;
import android.content.Intent;

import com.linglingyi.com.activity.AuthBankActivity;
import com.linglingyi.com.activity.X5WebViewActivity;


import java.util.ArrayList;
import java.util.List;

/**
 * @author dyx
 * @date on 2019/3/30
 * @describe
 */
public class ActivitySwitcher {
    public static final String AUTH_UNEXMAIN = "10A";
    public static final String AUTH_PASS = "10B";
    public static final String AUTH_REFUSE = "10C";
    public static final String AUTH_RE_EXMAIN = "10D";

    public static void startActivity(Activity from, final Class<? extends Activity> to) {
        //判断是否需要实名认证
        if (!filterAuthorizing(from, to)) {
            return;
        }
        if (CommonUtils.isFastDoubleClick()) {
            return;
        }
        from.startActivity(new Intent(from, to));
    }

    public static void startActivity(Activity from, final Class<? extends Activity> to, Intent intent) {
        //判断是否需要实名认证
        if (!filterAuthorizing(from, to)) {
            return;
        }
        intent.setClass(from, to);
        if (CommonUtils.isFastDoubleClick()) {
            return;
        }
        from.startActivity(intent);
    }

    public static void startActivityForResult(Activity from, final Class<? extends Activity> to, int requsetCode) {
        //判断是否需要实名认证
        if (!filterAuthorizing(from, to)) {
            return;
        }
        if (CommonUtils.isFastDoubleClick()) {
            return;
        }
        from.startActivityForResult(new Intent(from, to), requsetCode);
    }

    /**
     * 判断跳转页是否需要通过实名认证
     * 判断是否实名认证
     */
    private static boolean filterAuthorizing(Activity from, Class<? extends Activity> to) {
        for (String str : classList) {
            if (StringUtil.isEqual(to.getName(), str)) {
                return judgeIfIsAuthorizing(from);
            }
        }
        return true;

    }

    private static boolean judgeIfIsAuthorizing(Activity from) {
        return false;
    }

    //跳转需要实名认证
    private static List<String> classList = new ArrayList() {{
        add(AuthBankActivity.class.getName());
    }};

    public static void jumpWebView(Activity activity, String title, String url) {
        activity.startActivity(new Intent(activity, X5WebViewActivity.class).putExtra("url", url).putExtra("title", title));
    }
}
