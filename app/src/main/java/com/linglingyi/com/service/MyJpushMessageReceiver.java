package com.linglingyi.com.service;

import android.content.Context;

import com.linglingyi.com.utils.LogUtils;

import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.service.JPushMessageReceiver;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/10/7
 */
public class MyJpushMessageReceiver extends JPushMessageReceiver {
    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onAliasOperatorResult(context, jPushMessage);
        LogUtils.i("alias="+jPushMessage.getAlias());
        LogUtils.i("getErrorCode="+jPushMessage.getErrorCode());

    }


}
