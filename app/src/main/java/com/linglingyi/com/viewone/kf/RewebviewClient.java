package com.linglingyi.com.viewone.kf;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by 53kf on 2017/10/25.
 */

public class RewebviewClient extends WebViewClient {


    @Override

    public void onPageStarted(WebView view, String url, Bitmap favicon) {

        super.onPageStarted(view, url, favicon);

    }



    @Override

    public void onPageFinished(WebView view, String url) {

        super.onPageFinished(view, url);

    }

}
