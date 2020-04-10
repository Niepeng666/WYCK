package com.linglingyi.com.viewone.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.callback.CancelCallback;
import com.linglingyi.com.callback.SuccessCallback;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @作者 chenlanxin
 * @创建日期 2019/2/27 10:47
 * @功能 公告
 **/
public class BindParentPhoneDialog extends DialogFragment {


    Unbinder unbinder;
    @BindView(R.id.et_invite_phone)
    EditText etInvitePhone;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    private CancelCallback mCancelCallback;
    private SuccessCallback<String> mStringSuccessCallback;

    public static BindParentPhoneDialog getInstance() {
        BindParentPhoneDialog dialog = new BindParentPhoneDialog();
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.custom_Dialog);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        String parentPhone = StorageCustomerInfo02Util.getInfo("parentPhone", getActivity());
        if (StringUtil.isEmpty(parentPhone) || "123".equals(parentPhone)) {
            if (mCancelCallback != null) {
                mCancelCallback.cancel();
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.75), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setCancelCallback(CancelCallback cancelCallback) {
        mCancelCallback = cancelCallback;
    }

    public void setStringSuccessCallback(SuccessCallback<String> stringSuccessCallback) {
        mStringSuccessCallback = stringSuccessCallback;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_bind_parent_phone, container);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {


    }

    @OnClick(R.id.tv_submit)
    public void onViewClicked() {
        String parentPhone = etInvitePhone.getText().toString().trim();
        if (StringUtil.isEmpty(parentPhone)) {
            ViewUtils.makeToast(getActivity(), "请输入推荐人手机号", 500);
            return;
        }
        bindParentPhone(parentPhone);
    }


    /**
     * 绑定上级手机号
     *
     * @param parentPhone
     */
    private void bindParentPhone(final String parentPhone) {
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "190931");
        httpParams.put("43", parentPhone);
        httpParams.put("42", StorageCustomerInfo02Util.getInfo("customerNum", getActivity()));
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(getActivity(), BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);

            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);

                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    ViewUtils.makeToast(getActivity(), "绑定成功", 1500);
                    StorageCustomerInfo02Util.putInfo(getActivity(), "parentPhone", parentPhone);
                    dismiss();
                }
            }
        });
    }
}
