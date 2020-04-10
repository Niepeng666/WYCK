package com.linglingyi.com.viewone.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linglingyi.com.callback.StringCallback;
import com.linglingyi.com.utils.ViewUtils;
import com.wuyouchuangke.com.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class VipBottomDialog extends DialogFragment {


    public Dialog loadingDialog;
    Unbinder unbinder;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.rb_wechat)
    ImageView rbWechat;
    @BindView(R.id.rl_wechat)
    LinearLayout rlWechat;
    @BindView(R.id.rb_alipay)
    ImageView rbAlipay;
    @BindView(R.id.rl_alipay)
    LinearLayout rlAlipay;
    @BindView(R.id.rg_pay)
    LinearLayout rgPay;
    @BindView(R.id.btn_pay)
    TextView btnPay;


    private Activity mContext;
    private StringCallback mStringCallback;
    private String type = "w";


    public void setStringCallback(StringCallback stringCallback) {
        mStringCallback = stringCallback;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.dialog_vip);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = window.getAttributes();
        ((WindowManager.LayoutParams) layoutParams).gravity = Gravity.BOTTOM;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes((WindowManager.LayoutParams) layoutParams);
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
        View view = inflater.inflate(R.layout.dialog_vip_pay_bottom, container);

        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        getDialog().setCanceledOnTouchOutside(false);

        loadingDialog = ViewUtils.createLoadingDialog(getActivity(), getString(R.string.loading_wait), false);
    }


    @OnClick({R.id.btn_pay, R.id.iv_close, R.id.rl_alipay, R.id.rl_wechat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_wechat:
                type = "w";
                rbWechat.setImageResource(R.drawable.check_circle_sel);
                rbAlipay.setImageResource(R.drawable.check_circle);
                break;
            case R.id.rl_alipay:
                type = "z";
                rbAlipay.setImageResource(R.drawable.check_circle_sel);
                rbWechat.setImageResource(R.drawable.check_circle);
                break;
            case R.id.btn_pay:
                if (mStringCallback != null) {
                    mStringCallback.callback(type);
                }
                dismiss();
                break;
            case R.id.iv_close:
                dismiss();
                break;
        }
    }


}
