package com.linglingyi.com.activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
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
import com.linglingyi.com.utils.Constant;
import com.linglingyi.com.utils.KeyBoardUtils;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.viewone.FontIconView;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @作者 chenlanxin
 * @创建日期 2019/2/10 17:20
 * @功能 注册
 **/
public class RegisterNewActivity extends BaseActivity {


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
    @BindView(R.id.tv_registration_protocol)
    TextView tvRegistrationProtocol;
    @BindView(R.id.ly_check)
    LinearLayout lyCheck;
    @BindView(R.id.bt_register)
    Button btRegister;
    @BindView(R.id.et_invite_phone)
    EditText etInvitePhone;
    @BindView(R.id.checkbox)
    FontIconView checkbox;
    @BindView(R.id.tv_secret)
    TextView tvSecret;
    @BindView(R.id.tv_descrition)
    TextView tvDescrition;
    private boolean isChecked = true;
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

    @OnClick({R.id.iv_back, R.id.tv_get_code, R.id.tv_registration_protocol, R.id.tv_secret, R.id.tv_descrition, R.id.checkbox, R.id.bt_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
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
            case R.id.tv_registration_protocol:
//                startActivity(new Intent(context, ZhucewebActivity.class));
                Intent intent3 = new Intent(context, X5WebViewActivity.class);
                intent3.putExtra("title", "用户注册协议");
                intent3.putExtra("url", Constant.register_url);
                startActivity(intent3);
                break;
            case R.id.tv_secret:
                Intent intent = new Intent(context, X5WebViewActivity.class);
                intent.putExtra("url", Constant.Secret);
                intent.putExtra("title", "隐私政策");
                startActivity(intent);
                break;
            case R.id.tv_descrition:
                Intent intent1 = new Intent(context, X5WebViewActivity.class);
                intent1.putExtra("url", Constant.descrition);
                intent1.putExtra("title", "申请人资格说明");
                startActivity(intent1);
                break;
            case R.id.checkbox:
                isChecked = !isChecked;
                int[] attrArray1 = {R.attr.theme_bg_color};
                TypedArray mTypedArray1 = context.obtainStyledAttributes(attrArray1);
                checkbox.setTextColor(isChecked ? mTypedArray1.getColor(0, 0xFF000000) : ContextCompat.getColor(context, R.color.gray));
                break;
            case R.id.bt_register:
                KeyBoardUtils.hideKeyboard(view);
                if (!isChecked) {
                    ViewUtils.makeToast(context, "请勾选用户协议", 1000);
                    return;
                }

                register();
                break;
        }
    }

    private void register() {
        String pwdValue = etPassword.getText().toString();
        String confirmPwdValue = etRepassword.getText().toString();
        final String phoneNum = etPhone.getText().toString();
        String check_codeValue = etCode.getText().toString();
        String invitePhone = etInvitePhone.getText().toString();
        if (CheckOutMessage.isEmpty(context, "手机号", phoneNum)) return;
        if (CheckOutMessage.isEmpty(context, "密码", pwdValue)) return;
        if (CheckOutMessage.isEmpty(context, "确认密码", confirmPwdValue)) return;
        if (CheckOutMessage.isEmpty(context, "验证码", check_codeValue)) return;
//        if (CheckOutMessage.isEmpty(context, "推荐人手机号", invitePhone)) return;

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
        if (!StringUtil.isEmpty(invitePhone)) {
            map.put("45", invitePhone);
        }
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_new_register;
    }

    @Override
    public void initData() {
        tvTitle.setText("注  册");
    }

}
