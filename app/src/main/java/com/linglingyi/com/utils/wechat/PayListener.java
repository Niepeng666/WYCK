package com.linglingyi.com.utils.wechat;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/8
 */
public interface PayListener {
    //支付成功
    void onPaySuccess();

    //支付失败
    void onPayError(String resultStatus);

    //支付取消
    void onPayCancel();
}
