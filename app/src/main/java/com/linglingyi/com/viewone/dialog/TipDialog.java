package com.linglingyi.com.viewone.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.security.rp.RPSDK;
import com.linglingyi.com.activity.AuthBankActivity;
import com.linglingyi.com.activity.AuthProtocolActivity;
import com.linglingyi.com.activity.DebitBankActivity;
import com.linglingyi.com.activity.DebitcardBindActivity;
import com.linglingyi.com.activity.RealNameFirstActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.utils.DeviceUtils;
import com.linglingyi.com.utils.PermissionUtil;
import com.linglingyi.com.utils.StorageAppInfoUtil;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.wuyouchuangke.com.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/7/10
 */
public class TipDialog extends DialogFragment {
    public Dialog loadingDialog;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_admit)
    Button btnAdmit;
    Unbinder unbinder;

    private String type, content;
    private Activity mActivity;
    private String merchanNo;
    private boolean aliAuth;


    public static TipDialog getInstance(String content, String type) {
        TipDialog dialog = new TipDialog();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putString("content", content);
        dialog.setArguments(bundle);
        return dialog;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.custom_Dialog);
        mActivity = getActivity();
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getString("type");
            content = bundle.getString("content");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null && dialog.getWindow() != null) {
            dialog.getWindow().setLayout((int) (DeviceUtils.getScreenWidth(mActivity) * 0.8), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        unbinder.unbind();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_auth, container);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        getDialog().setCanceledOnTouchOutside(false);
        loadingDialog = ViewUtils.createLoadingDialog(getActivity(), getString(R.string.loading_wait), false);
        merchanNo = StorageCustomerInfo02Util.getInfo("customerNum", mActivity);
        tvContent.setText(content);
        aliAuth = StorageAppInfoUtil.getBooleanInfo("aliAuth", mActivity);
    }

    @OnClick({R.id.btn_cancel, R.id.btn_admit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                dismiss();
                break;
            case R.id.btn_admit:
                if ("auth".equals(type)) {
                    if (!aliAuth) {
                        startActivity(new Intent(mActivity, RealNameFirstActivity.class));
                    } else {
                        if (!PermissionUtil.CAMERA(mActivity)) {
                            return;
                        }
                        getToken();
                    }
                } else if ("bind".equals(type)) {
                    startActivity(new Intent(mActivity, DebitcardBindActivity.class));
                    dismiss();
                } else if ("recertification".equals(type)) {
                    if (!aliAuth) {
                        startActivity(new Intent(mActivity, RealNameFirstActivity.class).putExtra("isInfoComplete", true));
                    } else {
                        if (!PermissionUtil.CAMERA(mActivity)) {
                            return;
                        }
                        getToken();
                    }
                }
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
        httpParams.put("21", merchanNo);
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(mActivity, BaseEntity.class) {
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
                        ViewUtils.makeToast(mActivity, "系统异常，请联系客服", 1000);
                        return;
                    }
                    toRPSDK(mActivity, token, ticketId);
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
        RPSDK.initialize(context);
        RPSDK.start(token, context,
                new RPSDK.RPCompletedListener() {
                    @Override
                    public void onAuditResult(RPSDK.AUDIT audit, String s) {
                        if (audit == RPSDK.AUDIT.AUDIT_PASS) { //认证通过
                            getCertificationResults(context, ticketId);
                        } else if (audit == RPSDK.AUDIT.AUDIT_FAIL) { //认证不通过
                            ViewUtils.makeToast(context, "认证失败", 1000);
                        } else if (audit == RPSDK.AUDIT.AUDIT_NOT) { //未认证，用户取消
                            ViewUtils.makeToast(context, "取消认证", 1000);
                        }
                    }
                });
    }

    private void getCertificationResults(final Activity context, String ticketId) {
        loadingDialog.show();
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "190937");
        httpParams.put("21", merchanNo);
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
                    StorageCustomerInfo02Util.putInfo(context, "merchantCnName", model.getStr35());

                    startActivity(new Intent(context, DebitcardBindActivity.class));
                    dismiss();
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
