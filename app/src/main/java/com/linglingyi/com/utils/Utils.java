/*
 * Copyright 2011 ??辩?ヤ?????
 * Website:http://www.azsy.cn/
 * Email:info锛?azsy.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.linglingyi.com.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.wuyouchuangke.com.R;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Utils {
    /**
     * ?????扮?????
     *
     * @return
     */
    public static String encode(String s) {
        if (s == null) {
            return "";
        }
        try {
            return URLEncoder.encode(s, "UTF-8").replace("+", "%20")
                    .replace("*", "%2A").replace("%7E", "~")
                    .replace("#", "%23");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * ?????板??缂????
     *
     * @param s
     * @return
     */
    public static String decode(String s) {
        if (s == null) {
            return "";
        }
        try {
            return URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static String getCurrentTime() {
        return getCurrentTime(new Date(), "yyyy-MM-dd  HH:mm:ss");
    }

    public static String getCurrentTime(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        String currentTime = sdf.format(date);
        return currentTime;
    }

    public static String parseTime(long time) {
        return getCurrentTime(time, "yyyy-MM-dd  HH:mm:ss");
    }

    public static String getCurrentTime(long time, String format) {
        Date date = new Date(time);
        return getCurrentTime(date, format);
    }

    public static boolean isTime(String filterString) {
        if (StringUtil.isEmpty(filterString)) {
            return false;
        }
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(filterString);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
        return true;
    }

    /**
     * 检测String是否全是中文
     *
     * @param name
     * @return
     */
    public static boolean checkNameChinese(String name) {
        boolean res = true;
        char[] cTemp = name.toCharArray();
        for (int i = 0; i < name.length(); i++) {
            if (isChinese(cTemp[i])) {
                res = false;
                break;
            }
        }
        return res;
    }

    /**
     * 判定输入汉字
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    public static boolean checkCustomerIfSubmitPhoto(Activity context) {
        String infoImageUrl_10M = StorageCustomerInfo02Util.getInfo("infoImageUrl_10M", context);
        String infoImageUrl_10E = StorageCustomerInfo02Util.getInfo("infoImageUrl_10E", context);
        String infoImageUrl_10F = StorageCustomerInfo02Util.getInfo("infoImageUrl_10F", context);
        String infoImageUrl_10K = StorageCustomerInfo02Util.getInfo("infoImageUrl_10K", context);
        if (TextUtils.isEmpty(infoImageUrl_10M) ||
                TextUtils.isEmpty(infoImageUrl_10E) || TextUtils.isEmpty(infoImageUrl_10F) || TextUtils.isEmpty(infoImageUrl_10K)) {
            return false;
        }
        return true;
    }

    public static void getCertificationLogo(String name, ImageView iv, Context context) {
        if (StringUtil.isEmpty(name))
            return;
        switch (name) {
            case "SM":
                iv.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.smrz));
                break;
            case "LXR":
                iv.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.lxr));
                break;
            case "YYS":
                iv.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.yys));
                break;
            case "XJD":
                iv.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.xjd));
                break;
            case "JD":
                iv.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.jd));
                break;
            case "ZM":
                iv.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.zmxy));
                break;
            case "GJJ":
                iv.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.gjjrz));
                break;
            case "XJ":
                iv.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.xjrz));
                break;
            case "XYK":
                iv.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.xyk));
                break;
        }
    }

    public static void initBankCode(String bankCode, ImageView iv_bankIcon, Context context) {
        if (bankCode == null) {
            return;
        }
        if (bankCode.equals("304")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.huaxiayinhang));
        } else if (bankCode.equals("105")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.jiansheyinhang));
        } else if (bankCode.equals("301")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.jiaotongyinhang));
        } else if (bankCode.equals("305")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.minshenyinhang));
        } else if (bankCode.equals("103")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.nongyeyinhang));
        } else if (bankCode.equals("307")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.pinanyinhang));
        } else if (bankCode.equals("310")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.pufayinhang));
        } else if (bankCode.equals("308")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.zhaoshangyinhang));
        } else if (bankCode.equals("104")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.zhonguoyinhang));
        } else if (bankCode.equals("403")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.youzhenyinhang));
        } else if (bankCode.equals("102")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.gonshangyinhang));
        } else if (bankCode.equals("313003")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.beijinyinhang));
        } else if (bankCode.equals("303")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.guangdayinhang));
        } else if (bankCode.equals("306")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.guangfayinhang));
        } else if (bankCode.equals("302")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.zhonxinyinhang));
        } else if (bankCode.equals("313062")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.shanghaiyinhang));
        } else if (bankCode.equals("313027")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.hangzhouyinhang));
        } else if (bankCode.equals("402002")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.beijinnongshang));
        } else if (bankCode.equals("309")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.xinyeyinhang));
        } else if (bankCode.equals("303066")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.taizhouyinhang));
        } else if (bankCode.equals("313080")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.tailongyinhang));
        } else if (bankCode.equals("313079")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.mintaiyinhang));
        } else if (bankCode.equals("314")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.noncunxinyonsheyinhang));
        } else {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.yinlian_icon));
        }
    }

    public static void initBankBackgroundCode(String bankCode, View rl_back, Context context) {
        if (StringUtil.isEmpty(bankCode)) {
            return;
        }
        if ("304".equals(bankCode)) {
            rl_back.setBackgroundResource(R.drawable.huaxiayinhang_back);
        } else if ("105".equals(bankCode)) {
            rl_back.setBackgroundResource(R.drawable.jiansheyinhang_back);
        } else if ("301".equals(bankCode)) {
            rl_back.setBackgroundResource(R.drawable.jiaotongyinhang_back);
        } else if ("305".equals(bankCode)) {
            rl_back.setBackgroundResource(R.drawable.minshengyinhang_back);
        } else if ("103".equals(bankCode)) {
            rl_back.setBackgroundResource(R.drawable.nongyeyinhang_back);
        } else if ("307".equals(bankCode)) {
            rl_back.setBackgroundResource(R.drawable.pinganyinhang_back);
        } else if ("310".equals(bankCode)) {
            rl_back.setBackgroundResource(R.drawable.pufayinhang_back);
        } else if ("308".equals(bankCode)) {
            rl_back.setBackgroundResource(R.drawable.zhaoshangyinhang_back);
        } else if ("104".equals(bankCode)) {
            rl_back.setBackgroundResource(R.drawable.zhongguoyinhang_back);
        } else if ("403".equals(bankCode)) {
            rl_back.setBackgroundResource(R.drawable.youzhengchuxuyinhang_back);
        } else if ("102".equals(bankCode)) {
            rl_back.setBackgroundResource(R.drawable.gongshangyinhang_back);
        } else if ("303".equals(bankCode)) {
            rl_back.setBackgroundResource(R.drawable.guangdayinhang_back);
        } else if ("306".equals(bankCode)) {
            rl_back.setBackgroundResource(R.drawable.guangfayinhang_back);
        } else if ("302".equals(bankCode)) {
            rl_back.setBackgroundResource(R.drawable.zhongxinyinhang_back);
        } else if ("309".equals(bankCode)) {
            rl_back.setBackgroundResource(R.drawable.xingyeyinhang_back);
        } else {
            rl_back.setBackgroundResource(R.drawable.yinlian_back);
        }
    }

    /**
     * 获取彩色银行logo
     *
     * @param bankCode
     * @param iv_bankIcon
     * @param context
     */
    public static void initBankCodeColorIcon(String bankCode, ImageView iv_bankIcon, Context context) {
        if (bankCode == null) {
            return;
        }
        if (bankCode.equals("304")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_huaxia));
        } else if (bankCode.equals("105")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_jianshe));
        } else if (bankCode.equals("301")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_jiaotong));
        } else if (bankCode.equals("305")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_mingsheng));
        } else if (bankCode.equals("103")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_nongye));
        } else if (bankCode.equals("310")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_pudong));
        } else if (bankCode.equals("308")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_zhaoshang));
        } else if (bankCode.equals("104")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_zhongguo));
        } else if (bankCode.equals("403")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_youzheng));
        } else if (bankCode.equals("102")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_gongshang));
        } else if (bankCode.equals("303")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_guangda));
        } else if (bankCode.equals("313062")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_shanghai));
        } else if (bankCode.equals("309")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_xingye));
        } else if (bankCode.equals("302")) {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_zhongxin));
        } else {
            iv_bankIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_yinlian));
        }
    }

    /**
     * 根据银行编号 填充背景
     */
//    public static void initBankBg(String bankCode, ViewGroup view) {
//        if (bankCode == null) {
//            return;
//        }
//        int resId;
//        switch (bankCode) {
//            case "304":
//                resId = R.drawable.bg_hx;
//                break;
//            case "105":
//                resId = R.drawable.bg_js;
//                break;
//            case "301":
//                resId = R.drawable.bg_jt;
//                break;
//            case "305":
//                resId = R.drawable.bg_ms;
//                break;
//            case "103":
//                resId = R.drawable.bg_ny;
//                break;
//            case "307":
//                resId = R.drawable.bg_pa;
//                break;
//            case "310":
//                resId = R.drawable.bg_pf;
//                break;
//            case "403":
//                resId = R.drawable.bg_yz;
//                break;
//            case "102":
//                resId = R.drawable.bg_gs;
//                break;
//            case "303":
//                resId = R.drawable.bg_gd;
//                break;
//            case "306":
//                resId = R.drawable.bg_gf;
//                break;
//            case "302":
//                resId = R.drawable.bg_zx;
//                break;
//            case "309":
//                resId = R.drawable.bg_xy;
//                break;
//            case "104":
//                resId = R.drawable.bg_zg;
//                break;
//            case "308":
//                resId = R.drawable.bg_zs;
//                break;
//            default:
//                resId = R.drawable.bg_yl;
//                break;
//        }
//        view.setBackgroundResource(resId);
//    }
    public static int substring_workingFund(String value) {
        String substring = value.substring(0, value.indexOf("."));
        int parseInt = Integer.parseInt(substring);
        return parseInt;
    }

}

