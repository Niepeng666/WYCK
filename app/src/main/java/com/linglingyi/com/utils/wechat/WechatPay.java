package com.linglingyi.com.utils.wechat;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.linglingyi.com.MyApplication;
import com.linglingyi.com.utils.LogUtil;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.List;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/8
 */
public class WechatPay {
    private static WechatPay wechatPay;
    private IWXAPI mIWXAPI;
    private PayListener mPayListener;

    private WechatPay() {
    }

    public static WechatPay getInstance() {
        if (wechatPay == null) {
            synchronized (WechatPay.class) {
                if (wechatPay == null) {
                    wechatPay = new WechatPay();
                }
            }
        }
        return wechatPay;
    }

    /**
     * 初始化微信支付接口
     *
     * @param appId
     */
    public void init(String appId) {
        mIWXAPI = WXAPIFactory.createWXAPI(MyApplication.applicationContext, null);
        mIWXAPI.registerApp(appId);
    }

    /**
     * 获取微信接口
     *
     * @return
     */
    public IWXAPI getWXApi() {
        return mIWXAPI;
    }

    /**
     * 调起支付
     *
     * @param appId
     * @param partnerId
     * @param prepayId
     * @param nonceStr
     * @param timeStamp
     * @param sign
     */
    public void startWeChatPay(String appId, String partnerId, String prepayId,
                               String nonceStr, String timeStamp, String sign, PayListener listener) {
        mPayListener = listener;
        init(appId);
        if (!checkWx()) {
            if (listener != null) {
                listener.onPayError("未安装微信或者微信版本过低");
            }
            return;
        }
        PayReq request = new PayReq();
        request.appId = appId;
        request.partnerId = partnerId;
        request.prepayId = prepayId;
        request.packageValue = "Sign=WXPay";
        request.nonceStr = nonceStr;
        request.timeStamp = timeStamp;
        request.sign = sign;
        request.extData = "app data";
        mIWXAPI.sendReq(request);
    }

    /**
     * 响应支付回调
     *
     * @param error_code
     * @param message
     */
    public void onResp(int error_code, String message) {
        LogUtil.i("lll","code="+error_code+"message="+message);
        if (error_code == 0) {
            //支付成功
            mPayListener.onPaySuccess();
        } else if (error_code == -1) {
            //支付异常
            mPayListener.onPayError(message);
        } else if (error_code == -2) {
            //支付取消
            mPayListener.onPayCancel();
        }
        mPayListener = null;
    }


    //检测微信客户端是否支持微信支付
    private boolean checkWx() {
        return isWeixinAvilible() && mIWXAPI.isWXAppInstalled() && mIWXAPI.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
    }

    /**
     * 判断微信是否安装
     *
     * @return
     */
    private boolean isWeixinAvilible() {
        return appIsAvilible("com.tencent.mm");
    }

    /**
     * 判断app是否安装
     *
     * @param packageName
     * @return
     */
    private boolean appIsAvilible(String packageName) {
        final PackageManager packageManager = MyApplication.applicationContext.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
