package com.linglingyi.com.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.wuyouchuangke.com.R;

/**
 * @作者 chenlanxin
 * @创建日期 2019/1/24 10:32
 * @功能
 **/
public class BankLogoUtils {

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
        }
    }
}
