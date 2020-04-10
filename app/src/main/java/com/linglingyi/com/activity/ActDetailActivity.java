package com.linglingyi.com.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.ActModel;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.utils.Constant;
import com.linglingyi.com.utils.DateUtil;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.viewone.dialog.ShareUrlBottomDialog;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.wuyouchuangke.com.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/5/14
 */
public class ActDetailActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.wv_content)
    WebView wvContent;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    private ActModel mActModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.act_act_detail;
    }

    @Override
    public void initData() {
        tvTitle.setText("资讯详情");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("分享");
        mActModel = (ActModel) getIntent().getSerializableExtra("actDetail");
        if (mActModel == null) {
            return;
        }
        tvTitleName.setText(mActModel.getTitle());
        tvDate.setText(DateUtil.formatDateToHMS(mActModel.getCreateTime().getTime()));
        WebSettings settings = wvContent.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        wvContent.loadDataWithBaseURL(null, mActModel.getNote(), "text/html", "UTF-8", null);
    }

    @OnClick({R.id.iv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.tv_right:
                // TODO: 2019/5/14 分享
                goShare();
                break;
        }
    }


    private void goShare() {
        ShareUrlBottomDialog.getInstance(mActModel.getTitle(), mActModel.getShareUrl(), Constant.SHARE_LOGO, "福腾壹家，每天给你最新资讯")
        .show(getSupportFragmentManager(),"share");
    }
}
