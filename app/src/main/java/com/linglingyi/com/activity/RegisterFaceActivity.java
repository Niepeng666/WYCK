package com.linglingyi.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.utils.CheckOutMessage;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.Constant;
import com.linglingyi.com.utils.KeyBoardUtils;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/8/15
 */
public class RegisterFaceActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_repassword)
    EditText etRepassword;
    @BindView(R.id.bt_determine)
    Button btDetermine;
    private String phone;

    private Timer timer;
    private int time = 60;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (time == 0) {
                    timer.cancel();
                    tvGetCode.setText("发送验证码");
                } else {
                    tvGetCode.setText(time + "秒后可重发");
                    time--;
                }
            }
        }
    };

    @Override
    public int initLayout() {
        return R.layout.act_register_face;
    }

    @Override
    public void initData() {
        tvTitle.setText("面对面注册");
        phone = StorageCustomerInfo02Util.getInfo("phone", context);
    }

    @OnClick({R.id.iv_back, R.id.tv_get_code, R.id.bt_determine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
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
            case R.id.bt_determine:
                KeyBoardUtils.hideKeyboard(view);
                register();
                break;
        }
    }

    private void register() {
        String pwdValue = etPassword.getText().toString();
        String confirmPwdValue = etRepassword.getText().toString();
        final String phoneNum = etPhone.getText().toString();
        String check_codeValue = etCode.getText().toString();
        if (CheckOutMessage.isEmpty(context, "手机号", phoneNum)) return;
        if (CheckOutMessage.isEmpty(context, "密码", pwdValue)) return;
        if (CheckOutMessage.isEmpty(context, "确认密码", confirmPwdValue)) return;
        if (CheckOutMessage.isEmpty(context, "验证码", check_codeValue)) return;

        if (pwdValue.length() < 6) {
            ViewUtils.makeToast(context, "密码位数不能小于6位数", 1000);
            return;
        }
        if (pwdValue.length() > 14) {
            ViewUtils.makeToast(context, "密码位数超限，请重新录入", 1000);
            return;
        }
        if (!pwdValue.equals(confirmPwdValue)) {
            ViewUtils.makeToast(context, "两次新密码输入不一致", 1000);
            return;
        }
        if (check_codeValue.length() != 6) {
            ViewUtils.makeToast(context, "请输入6位验证码", 1000);
            return;
        }

        loadingDialog.show();
        HttpParams map = OkClient.getParamsInstance().getParams();
        map.put("1", phoneNum);
        map.put("3", "190918");
        map.put("8", CommonUtils.Md5(pwdValue));
        map.put("44", Constant.AGENCY_CODE44);
        map.put("45", phone);
        map.put("52", check_codeValue);
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
                    ViewUtils.makeToast(context,
                            "注册成功", 500);
                    StorageCustomerInfo02Util.putInfo(context, "phoneNum", phoneNum);
                    Intent intent = new Intent();
                    intent.putExtra("phone", phoneNum);
                    setResult(RESULT_OK, intent);
                    finish();
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
        timer = new Timer();
        time = 60;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                timing();
            }
        };
        timer.schedule(task, 0, 1000);
    }

    void timing() {
        Message message = Message.obtain();
        message.what = 1;
        handler.sendMessage(message);
    }

}
