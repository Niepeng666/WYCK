package com.linglingyi.com.viewone.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.linglingyi.com.callback.CancelCallback;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
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
 * @作者 chenlanxin
 * @创建日期 2019/2/1 15:37
 * @功能 贷款 操作流程
 **/
public class RedBagTipDialog extends DialogFragment {


    public Dialog loadingDialog;

    Unbinder unbinder;
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.tv_guafen)
    TextView tvGuafen;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_money_label)
    TextView tvMoneyLabel;
    @BindView(R.id.rl_red)
    RelativeLayout rlRed;
    @BindView(R.id.iv_close)
    ImageView ivClose;

    private boolean isHave;
    private CancelCallback mCancelCallback;
    private Activity mActivity;

    public static RedBagTipDialog getInstance() {
        RedBagTipDialog dialog = new RedBagTipDialog();
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.MyProgressDialog);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    public void setCancelCallback(CancelCallback cancelCallback) {
        mCancelCallback = cancelCallback;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_red_bag, container);
        unbinder = ButterKnife.bind(this, view);
        initData();

        return view;
    }

    private void initData() {
        getDialog().setCanceledOnTouchOutside(false);
        loadingDialog = ViewUtils.createLoadingDialog(getActivity(), getString(R.string.loading_wait), false);
        tvMoney.setText(StorageCustomerInfo02Util.getInfo("redMoney", mActivity));
    }

    @OnClick({R.id.tv_guafen, R.id.iv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_guafen:
                loadMoney();
                break;
            case R.id.iv_close:
                dismiss();
                break;
        }
    }

    /**
     * 领取红包
     */
    private void loadMoney() {
        loadingDialog.show();
        String merchantNo = StorageCustomerInfo02Util.getInfo("customerNum", mActivity);
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "641619");
        httpParams.put("42", merchantNo);
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(getActivity(), BaseEntity.class) {
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
                    ViewUtils.makeToast(mActivity, "领取成功", 500);
                }
            }
        });
    }
}
