package com.linglingyi.com.activity;

import android.content.Context;
import android.media.AudioManager;
import android.net.http.SslError;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.LogUtil;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.ViewUtils;
import com.wuyouchuangke.com.R;


public class WebViewActivity extends BaseActivity implements
        View.OnClickListener {
    private WebView webview;
    private ProgressBar pg1;
    private String url = "";
    private boolean canBack;

    @Override
    public int initLayout() {
        return R.layout.activity_apply_credit;
    }

    @Override
    public void initData() {
        findViewById(R.id.iv_back).setOnClickListener(WebViewActivity.this);
        String title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        canBack = getIntent().getBooleanExtra("canBack", true);


        ((TextView) findViewById(R.id.tv_title)).setText(title);
        webview = (WebView) findViewById(R.id.webview);
        pg1 = (ProgressBar) findViewById(R.id.progressBar1);
        //设置WebView属性，能够执行Javascript脚本
        //webview.getSettings().setJavaScriptEnabled(true);
        //加载需要显示的网页
        // webview.loadUrl("http://fans.lanqt.com/index.php?g=App&m=Index&a=xyk001");

        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);
       /* if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }*/

        webview.loadUrl(url);
        //设置Web视图
        webview.setWebViewClient(new HelloWebViewClient());
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    pg1.setVisibility(View.GONE);
                } else {
                    pg1.setVisibility(View.VISIBLE);
                    pg1.setProgress(newProgress);
                }
            }
        });
    }


    public void CloseSpeaker() {

        try {
            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            int currVolume = audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL);
            if (audioManager != null) {
                if (audioManager.isSpeakerphoneOn()) {
                    audioManager.setSpeakerphoneOn(false);
                    audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, currVolume,
                            AudioManager.STREAM_VOICE_CALL);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack() && canBack) {
            webview.goBack(); //goBack()表示返回WebView的上一页面
            LogUtil.syso("back1:" + webview.getUrl());
        } else {
            CloseSpeaker();
            return super.onKeyDown(keyCode, event);
        }
        return false;
    }

    public void onClick(View v) {
        if (CommonUtils.isFastDoubleClick()) {
            return;
        }
        switch (v.getId()) {
            case R.id.iv_back:
                CloseSpeaker();
                ViewUtils.overridePendingTransitionBack(this);
                break;
        }
    }


    //Web视图
    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            LogUtils.i("url="+url);
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }
    }


}
