package com.linglingyi.com.activity;

import android.content.Context;
import android.media.AudioManager;
import android.net.http.SslError;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.linglingyi.com.base.BaseActivity;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.utils.Constant;
import com.linglingyi.com.utils.StringUtil;


public class ZhucewebActivity extends BaseActivity {
    private WebView webview;
    String url;

    @Override
    public int initLayout() {
        return R.layout.activity_zhuceweb;
    }

    @Override
    public void initData() {
        url = Constant.register_url;
        String title=getIntent().getStringExtra("title");
        ((TextView) findViewById(R.id.tv_title)).setText(StringUtil.isEmpty(title)?"注册协议":title);
        webview = (WebView) findViewById(R.id.webview);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CloseSpeaker();
                finish();
            }
        });


        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);
        webview.loadUrl(url);
        //设置Web视图
        webview.setWebViewClient(new HelloWebViewClient());
    }
//
//    @Override
//    protected void onPause() {
//        webview.reload();
//
//        super.onPause();
//    }

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

    //Web视图
    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }
    }
}
