package com.linglingyi.com.viewone.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.linglingyi.com.callback.SuccessCallback;
import com.linglingyi.com.utils.DeviceUtils;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.viewone.CustomInputView;
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
public class withdrawPassDialog extends DialogFragment {


    public Dialog loadingDialog;

    Unbinder unbinder;
    @BindView(R.id.input_jihuo)
    CustomInputView inputJihuo;
    @BindView(R.id.btn_admit)
    Button btnAdmit;
    @BindView(R.id.btn_cancel)
    Button btnCancel;


    private SuccessCallback mSuccessCallback;
    private Activity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.MyProgressDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null && dialog.getWindow() != null) {
            dialog.getWindow().setLayout((int) (DeviceUtils.getScreenWidth(getActivity()) * 0.9), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    public void setSuccessCallback(SuccessCallback successCallback) {
        mSuccessCallback = successCallback;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_jihuoma, container);
        unbinder = ButterKnife.bind(this, view);
        initData();

        return view;
    }

    private void initData() {
        getDialog().setCanceledOnTouchOutside(true);
        loadingDialog = ViewUtils.createLoadingDialog(getActivity(), getString(R.string.loading_wait), false);
        btnAdmit.setClickable(false);
        btnAdmit.setEnabled(false);
        initListener();
    }

    private void initListener() {
        inputJihuo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = inputJihuo.getPasswordString();
                if (text.length() < 6) {
                    btnAdmit.setEnabled(false);
                    btnAdmit.setClickable(false);
                    btnAdmit.setBackgroundResource(R.drawable.shape_solid_gray_corner_5);
                } else {
                    btnAdmit.setEnabled(true);
                    btnAdmit.setClickable(true);
                    btnAdmit.setBackgroundResource(R.drawable.shape_solid_background_corner_5);
                }
            }
        });
    }

    @OnClick({R.id.btn_admit, R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_admit:
                String text = inputJihuo.getPasswordString();
                if (mSuccessCallback != null) {
                    mSuccessCallback.success(text);
                }
//                goSubmit(text);
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
        }
    }




}
