package com.linglingyi.com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.security.rp.RPSDK;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.event.AuthEvent;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.utils.PermissionUtil;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.wuyouchuangke.com.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/7/9
 */
public class AuthProtocolActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_admit)
    Button btnAdmit;
    @BindView(R.id.wv_protocol)
    WebView wvProtocol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.act_auth_protocol;
    }

    @Override
    public void initData() {
        tvTitle.setText("实名认证协议");
        WebSettings settings = wvProtocol.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);
        loadData();
    }

    private void loadData() {
        loadingDialog.show();
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "153264");
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
                    String content = model.getStr57();
                    wvProtocol.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
                }
            }


        });
    }

    @OnClick({R.id.iv_back, R.id.btn_cancel, R.id.btn_admit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
            case R.id.btn_cancel:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.btn_admit:
                // TODO: 2019/7/9 进入阿里认证
                if (!PermissionUtil.CAMERA(context)) {
                    return;
                }
                getToken();
                break;
        }
    }

    /**
     * 获取token
     */
    private void getToken() {
        loadingDialog.show();
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "190936");
        httpParams.put("21", getMerNo());
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
                    String token = model.getStr41();
                    String ticketId = model.getStr42();
                    if (StringUtil.isEmpty(token)) {
                        ViewUtils.makeToast(context, "系统异常，请联系客服", 1000);
                        return;
                    }
                    toRPSDK(context, token, ticketId);
                }
            }


        });

    }

    /**
     * 进行阿里云认证
     *
     * @param context
     * @param token
     * @param ticketId
     */
    private void toRPSDK(final Activity context, String token, final String ticketId) {
        RPSDK.start(token, context,
                new RPSDK.RPCompletedListener() {
                    @Override
                    public void onAuditResult(RPSDK.AUDIT audit, String s) {
                        if (audit == RPSDK.AUDIT.AUDIT_PASS) { //认证通过
                            getCertificationResults(context, ticketId);
                        } else if (audit == RPSDK.AUDIT.AUDIT_FAIL) { //认证不通过
                            ViewUtils.makeToast(context, "认证失败", 1000);
                        }  else if (audit == RPSDK.AUDIT.AUDIT_NOT) { //未认证，用户取消
                            ViewUtils.makeToast(context, "取消认证", 1000);
                        }
                    }
                });
    }

    private void getCertificationResults(final Activity context, String ticketId) {
        loadingDialog.show();
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "190937");
        httpParams.put("21", getMerNo());
        httpParams.put("22", ticketId);
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    StorageCustomerInfo02Util.putInfo(context, "freezeStatus", "10B");
                    StorageCustomerInfo02Util.putInfo(context, "idCardNumber", model.getStr36());
                    // : 2019/5/22 更新实名认证信息
                    startActivity(new Intent(context, DebitcardBindActivity.class));
                    EventBus.getDefault().post(new AuthEvent());
                    finish();
                }
            }

            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
            }
        });
    }
}
