package com.linglingyi.com.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.utils.CheckOutMessage;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.KeyBoardUtils;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PasswordChangeActivity extends BaseActivity {


    private final long COUNT_DOWN_TOTAL = 60000;
    private final long COUNT_DOWN_INTERVAL = 1000;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.et_old_pass)
    EditText etOldPass;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_repassword)
    EditText etRepassword;
    @BindView(R.id.bt_determine)
    Button btDetermine;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    private final CountDownTimer countDownTimer = new CountDownTimer(COUNT_DOWN_TOTAL, COUNT_DOWN_INTERVAL) {
        @Override
        public void onTick(long millisUntilFinished) {
            tvGetCode.setText(String.format("%ds", millisUntilFinished / COUNT_DOWN_INTERVAL));
            tvGetCode.setEnabled(false);
        }

        @Override
        public void onFinish() {
            tvGetCode.setText("发送验证码");
            tvGetCode.setEnabled(true);
        }
    };
    @BindView(R.id.ll_container)
    LinearLayout llContainer;
    private boolean islogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_password_change;
    }

    @Override
    public void initData() {
        tvTitle.setText("修改密码");
        islogin = getIntent().getBooleanExtra("isLogin", false);
        etOldPass.setHint(islogin ? R.string.pass_old_login_tip : R.string.pass_old_withdraw_tip);
    }

    @OnClick({R.id.iv_back, R.id.bt_determine, R.id.tv_get_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.bt_determine:
                KeyBoardUtils.hideKeyboard(llContainer);
                String phoneNum = etPhone.getText().toString();
                String check_codeValue = etCode.getText().toString();
                String old_pwd = etOldPass.getText().toString();
                String new_pwd = etPassword.getText().toString();
                String new_pwd_confirm = etRepassword.getText().toString();
                if (CheckOutMessage.isEmpty(context, "手机号", phoneNum)) return;
                if (CheckOutMessage.isEmpty(context, "验证码", check_codeValue)) return;
                if (CheckOutMessage.isEmpty(context, "旧密码", old_pwd)) return;
                if (CheckOutMessage.isEmpty(context, "新密码", new_pwd)) return;
                if (CheckOutMessage.isEmpty(context, "确认密码",
                        new_pwd_confirm)) return;
                if (check_codeValue.length() != 6) {
                    ViewUtils.makeToast(context, "请输入6位验证码", 1000);
                    return;
                }
                if (new_pwd.length() < 6 || new_pwd.length() > 14) {
                    ViewUtils.makeToast(context, "请输入6至14位密码", 1000);
                    return;
                }
                if (!new_pwd.equals(new_pwd_confirm)) {
                    ViewUtils.makeToast(context, "两次新密码输入不一致", 1000);
                    return;
                }
                if (CommonUtils.getConnectedType(context) == -1) {
                    ViewUtils.makeToast(context, getString(R.string.nonetwork), 1500);
                    return;
                }

                sendToModifyPwd(phoneNum, check_codeValue, old_pwd, new_pwd);

                break;
            case R.id.tv_get_code:
                if ("发送验证码".equals(tvGetCode.getText().toString())) {
                    String phoneValue = etPhone.getText().toString();
                    if (CheckOutMessage.isEmpty(context, "手机号", phoneValue)) return;
                    if (phoneValue.length() != 11) {
                        ViewUtils.makeToast(context, "请输入正确的手机号", 1000);
                        return;
                    }
                    etCode.setText("");
                    sendCheckCode(phoneValue);
                }
                break;
        }
    }

    private void sendToModifyPwd(String phoneNum, String check_codeValue, String old_pwd, String new_pwd) {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "190929");
        httpParams.put("1", phoneNum);
        httpParams.put("6", check_codeValue);
        httpParams.put("10", islogin ? "1" : "2");
        httpParams.put("8", CommonUtils.Md5(old_pwd));
        httpParams.put("9", CommonUtils.Md5(new_pwd));
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
                    StorageCustomerInfo02Util.removeKey("passwd", context);
                    if (islogin) {
                        ViewUtils.makeToast2(context,
                                "修改成功", 500, LoginNewActivity.class,
                                "MOD_PWD");
                    } else {
                        ViewUtils.makeToast(context, "修改成功", 500);
                        finish();
                    }
                }
            }
        });
    }

    /**
     * 发送验证码
     *
     * @param phoneValue
     */
    private void sendCheckCode(final String phoneValue) {
        loadingDialog.show();
        HttpParams map = OkClient.getParamsInstance().getParams();
        map.put("1", phoneValue);
        map.put("3", "190919");
        OkClient.getInstance().post(map, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                BaseEntity model = response.body();
                loadingDialog.dismiss();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    ViewUtils.makeToast(context, "验证码已发送，请注意查收", 500);
                    time();
                }
            }
        });
    }

    void time() {
        countDownTimer.start();
    }

}
