package com.linglingyi.com.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.utils.Constant;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.viewone.kf.ImageUtil;
import com.linglingyi.com.viewone.kf.JSAndroid;
import com.linglingyi.com.viewone.kf.ReWebChomeClient;
import com.linglingyi.com.viewone.kf.RewebviewClient;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.wuyouchuangke.com.R;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/7/11
 */
public class ContactServiceActivity extends BaseActivity implements ReWebChomeClient.OpenFileChooserCallBack {
    public ValueCallback<Uri[]> mUploadMsgForAndroid5;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.progressBar1)
    ProgressBar pg1;
    @BindView(R.id.webview)
    WebView webview;

    private Intent mSourceIntent;
    private ValueCallback<Uri> mUploadMsg;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {

            if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
                if (data != null && requestCode == Constant.IMAGE_PICKER) {
                    ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                        mUploadMsg.onReceiveValue(Uri.fromFile(new File(images.get(0).path)));
                    } else {
                        mUploadMsgForAndroid5.onReceiveValue(new Uri[]{Uri.fromFile(new File(images.get(0).path))});
                    }
                } else {
                    ViewUtils.makeToast(context, "没有数据", 500);
                }
            }
        }
    }


    private void restoreUploadMsg() {
        if (mUploadMsg != null) {
            mUploadMsg.onReceiveValue(null);
            mUploadMsg = null;

        } else if (mUploadMsgForAndroid5 != null) {
            mUploadMsgForAndroid5.onReceiveValue(null);
            mUploadMsgForAndroid5 = null;
        }
    }

    @Override
    public void openFileChooserCallBack(ValueCallback<Uri> uploadMsg, String acceptType) {
        mUploadMsg = uploadMsg;

        if (!com.linglingyi.com.utils.PermissionUtil.CAMERA(context)) {
            return;
        }
        Intent imgIntent = new Intent(context, ImageGridActivity.class);
        startActivityForResult(imgIntent, Constant.IMAGE_PICKER);
    }


    @Override
    public boolean openFileChooserCallBackAndroid5(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        mUploadMsgForAndroid5 = filePathCallback;

        if (!com.linglingyi.com.utils.PermissionUtil.CAMERA(context)) {
            return false;
        }
        Intent imgIntent = new Intent(context, ImageGridActivity.class);
        startActivityForResult(imgIntent, Constant.IMAGE_PICKER);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_normal_web;
    }

    @Override
    public void initData() {
        tvTitle.setText("在线客服");
        webview.loadUrl(Constant.online_service);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        fixDirPath();
        webview.addJavascriptInterface(new JSAndroid(this), "Android");
        webview.setWebViewClient(new RewebviewClient());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebChromeClient(new ReWebChomeClient(this) {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    pg1.setVisibility(View.GONE);
                } else {
                    pg1.setVisibility(View.VISIBLE);
                    pg1.setProgress(newProgress);
                }
            }
        });
    }

    private void fixDirPath() {
        String path = ImageUtil.getDirPath();
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        ViewUtils.overridePendingTransitionBack(context);
    }

    private class DialogOnCancelListener implements DialogInterface.OnCancelListener {
        @Override
        public void onCancel(DialogInterface dialogInterface) {
            restoreUploadMsg();
        }
    }

}
