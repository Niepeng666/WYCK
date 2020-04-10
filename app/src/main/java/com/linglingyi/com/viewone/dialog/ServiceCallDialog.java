package com.linglingyi.com.viewone.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.ViewUtils;
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
public class ServiceCallDialog extends DialogFragment {


    public Dialog loadingDialog;

    TextView tvShare;
    Unbinder unbinder;
    @BindView(R.id.tv_dialogTitle)
    TextView tvDialogTitle;
    @BindView(R.id.phoneNum)
    TextView phoneNum;
    @BindView(R.id.bt_suspendCancel)
    Button btSuspendCancel;
    @BindView(R.id.bt_cancelPlan)
    Button btCancelPlan;

    private Activity mActivity;
    private String phone;

    public ServiceCallDialog() {
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
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        unbinder.unbind();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.callserver_dialog, container);
        unbinder = ButterKnife.bind(this, view);
        initData();

        return view;
    }

    private void initData() {
        getDialog().setCanceledOnTouchOutside(false);
        loadingDialog = ViewUtils.createLoadingDialog(getActivity(), getString(R.string.loading_wait), false);
        phone = StorageCustomerInfo02Util.getInfo("serviceNumber", mActivity);
        phoneNum.setText(phone);
    }

    @OnClick({R.id.bt_suspendCancel, R.id.bt_cancelPlan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_cancelPlan:
                if (CommonUtils.isFastDoubleClick2()) {
                    return;
                }
                dismiss();
                String serviceNumber = phone.replace("-", "");
                Intent phoneIntent = new Intent(
                        "android.intent.action.CALL", Uri.parse("tel:"
                        + serviceNumber));
                startActivity(phoneIntent);

                break;
            case R.id.bt_suspendCancel:
                dismiss();
                break;
        }
    }


}
