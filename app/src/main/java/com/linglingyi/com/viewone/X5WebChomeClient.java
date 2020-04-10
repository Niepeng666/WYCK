package com.linglingyi.com.viewone;

import android.net.Uri;

import com.tencent.smtt.sdk.ValueCallback;

/**
 * Created by 53kf on 2017/11/7.
 */

public class X5WebChomeClient extends com.tencent.smtt.sdk.WebChromeClient {

    private OpenFileChooserCallBack mOpenFileChooserCallBack;

    public X5WebChomeClient(OpenFileChooserCallBack openFileChooserCallBack) {
        mOpenFileChooserCallBack = openFileChooserCallBack;
    }

    // For Android  >= 3.0
    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
        openFileChooser(uploadMsg, "");
    }

    /**
     * For Android < 3.0
     *
     * @param uploadMsg
     * @param acceptType
     */
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
        mOpenFileChooserCallBack.openFileChooserCallBack(uploadMsg, acceptType);
    }

    /**
     * For Android  >= 4.1
     *
     * @param uploadMsg
     * @param acceptType
     * @param capture
     */
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        openFileChooser(uploadMsg, acceptType);
    }

    /**
     * For Android  >5.0
     *
     * @param webView
     * @param filePathCallback
     * @param fileChooserParams
     * @return
     */
    @Override
    public boolean onShowFileChooser(com.tencent.smtt.sdk.WebView webView, com.tencent.smtt.sdk.ValueCallback<Uri[]> filePathCallback,
                                     FileChooserParams fileChooserParams) {

        return mOpenFileChooserCallBack.openFileChooserCallBackAndroid5(webView, filePathCallback, fileChooserParams);
    }


    public interface OpenFileChooserCallBack {
        /**
         * for API - Version below 5.0.
         *
         * @param uploadMsg
         * @param acceptType
         */
        void openFileChooserCallBack(ValueCallback<Uri> uploadMsg, String acceptType);

        /**
         * for API - Version above 5.0 (contais 5.0).
         *
         * @param webView
         * @param filePathCallback
         * @param fileChooserParams
         * @return
         */

        boolean openFileChooserCallBackAndroid5(com.tencent.smtt.sdk.WebView webView, ValueCallback<Uri[]> filePathCallback,
                                                FileChooserParams fileChooserParams);
    }


}
