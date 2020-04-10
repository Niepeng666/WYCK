package com.linglingyi.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Patterns;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.OperateModel;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.viewone.X5WebView;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.URLUtil;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @作者 chenlanxin
 * @创建日期 2019/10/14 16:16
 * @功能
 **/
public class OperateDetailActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_small_title)
    TextView tvSmallTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.webview)
    X5WebView webview;

    private OperateModel model = null;

    @Override
    public int initLayout() {
        return R.layout.activity_operate_detail;
    }

    @Override
    public void initData() {
        model = (OperateModel) getIntent().getSerializableExtra("model");


        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);
        settings.setSupportZoom(false);
        settings.setUseWideViewPort(false);

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(com.tencent.smtt.sdk.WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }

            @Override
            public boolean shouldOverrideUrlLoading(com.tencent.smtt.sdk.WebView view, String url) {
                webview.loadUrl(url);
                return true;
            }
        });
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onCreateWindow(com.tencent.smtt.sdk.WebView webView, boolean b, boolean b1, Message message) {
                com.tencent.smtt.sdk.WebView newWebView = new com.tencent.smtt.sdk.WebView(context);
                newWebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(com.tencent.smtt.sdk.WebView view, String url) {
                        // 在此处进行跳转URL的处理, 一般情况下_black需要重新打开一个页面,
                        startActivity(new Intent(context, WebViewActivity.class).putExtra("url", url));
                        return true;
                    }

                });
                com.tencent.smtt.sdk.WebView.WebViewTransport transport = (WebView.WebViewTransport) message.obj;
                transport.setWebView(newWebView);
                message.sendToTarget();
                return true;
            }
        });

        getData();
    }

    private void getData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "792002");
        httpParams.put("21", model.getId());
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    OperateModel operateModel = JSONArray.parseArray(model.getStr57(), OperateModel.class).get(0);
                    tvTitle.setText(operateModel.getTitle());
                    webview.loadDataWithBaseURL(null, operateModel.getContent().replace("_self", "_blank"), "text/html", "UTF-8", null);
                    tvSmallTitle.setText(operateModel.getTitle());
                }
            }
        });
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }


}
