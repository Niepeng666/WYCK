package com.wuyouchuangke.com.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.utils.LogUtil;
import com.linglingyi.com.utils.wechat.WechatPay;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

public class WXPayEntryActivity extends FragmentActivity implements IWXAPIEventHandler {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i("lll", "oncreate");
        if (WechatPay.getInstance() != null) {
            if ((WechatPay.getInstance().getWXApi() != null)) {
                WechatPay.getInstance().getWXApi().handleIntent(getIntent(), this);
            }
        } else {
            finish();
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.i("lll", "intent=" + intent);
        setIntent(intent);
        if (WechatPay.getInstance() != null) {
            WechatPay.getInstance().getWXApi().handleIntent(intent, this);
        }
    }

    @Override
    public void onReq(BaseReq baseReq) {
        LogUtil.i("lll ", "进入支付回调页onReq " + baseReq);

    }

    @Override
    public void onResp(BaseResp baseResp) {

        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (WechatPay.getInstance() != null) {
                WechatPay.getInstance().onResp(baseResp.errCode, baseResp.errStr);
                finish();
            }
        } else if (baseResp.getType() == ConstantsAPI.COMMAND_LAUNCH_WX_MINIPROGRAM) {
            WXLaunchMiniProgram.Resp launchMiniProResp = (WXLaunchMiniProgram.Resp) baseResp;
            String extraData = launchMiniProResp.extMsg; // 对应JsApi navigateBackApplication中的extraData字段数据
            LogUtil.i("lll", "content=" + extraData + launchMiniProResp.errStr + launchMiniProResp.errCode);
        }
    }
}
