package com.linglingyi.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.ImageTypeModel;
import com.linglingyi.com.model.UserInfoModel;
import com.linglingyi.com.utils.AppUtils;
import com.linglingyi.com.utils.CheckOutMessage;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.Constant;
import com.linglingyi.com.utils.KeyBoardUtils;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.viewone.dialog.UpdateDialog;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/10/17
 */
public class LoginBindPhoneActivity extends BaseActivity {
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
    @BindView(R.id.bt_bind)
    Button btBind;
    private String customerNo;

    private final long COUNT_DOWN_TOTAL = 60000;
    private final long COUNT_DOWN_INTERVAL = 1000;
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

    @Override
    public int initLayout() {
        return R.layout.act_login_bind_phone;
    }

    @Override
    public void initData() {
        tvTitle.setText("绑定账号");
        customerNo = getIntent().getStringExtra("customerNum");
    }

    @OnClick({R.id.iv_back, R.id.bt_bind, R.id.tv_get_code})
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
            case R.id.bt_bind:
                KeyBoardUtils.hideKeyboard(view);
                String phoneNum = etPhone.getText().toString();
                String check_codeValue = etCode.getText().toString();
                String password = etPassword.getText().toString();
                if (CheckOutMessage.isEmpty(context, "手机号", phoneNum)) return;
                if (CheckOutMessage.isEmpty(context, "密码", password)) return;
                if (CheckOutMessage.isEmpty(context, "验证码", check_codeValue)) return;
                if (password.length() < 6) {
                    ViewUtils.makeToast(context, "密码位数不能小于6位数", 1000);
                    return;
                }
                if (password.length() > 14) {
                    ViewUtils.makeToast(context, "密码位数超限，请重新录入", 1000);
                    return;
                }
                if (check_codeValue.length() != 6) {
                    ViewUtils.makeToast(context, "请输入6位验证码", 1000);
                    return;
                }
                bindPhone(phoneNum, check_codeValue, password);
                break;
            default:
                break;
        }
    }

    private void bindPhone(final String phoneNum, String code, final String password) {
        loadingDialog.show();
        HttpParams map = OkClient.getParamsInstance().getParams();
        map.put("1", phoneNum);
        map.put("3", "191929");
        map.put("5", phoneNum);
        map.put("6", code);
        map.put("8", StringUtil.isEmpty(password) ? "" : CommonUtils.Md5(password));
        map.put("42", customerNo);

        OkClient.getInstance().post(map, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                BaseEntity baseEntity = response.body();
                if (baseEntity == null) {
                    return;
                }
                String result = baseEntity.getStr39();
                if ("00".equals(result) || "W8".equals(result)) {
                    List<UserInfoModel> list = JSONArray.parseArray(baseEntity.getStr42(), UserInfoModel.class);
                    if (list == null || list.size() == 0) {
                        return;
                    }
                    UserInfoModel userInfoModel = list.get(0);

                    saveUserData(userInfoModel, result);

                    StorageCustomerInfo02Util.putInfo(context, "phoneNum", phoneNum);
                    StorageCustomerInfo02Util.putInfo(context, "passwd", password);
                    goMainActivity();

                }
            }
        });
    }




    /**
     * 保存商户数据
     *
     * @param item
     * @param result
     */
    private void saveUserData(UserInfoModel item, String result) {
        StorageCustomerInfo02Util.putInfo(context, "merchantId", item.getId());
        StorageCustomerInfo02Util.putInfo(context, "customerNum", item.getMerchantNo());

        StorageCustomerInfo02Util.putInfo(context, "level", StringUtil.stringToInt(item.getLevel()));
        StorageCustomerInfo02Util.putInfo(context, "merchantCnName", item.getMerchantCnName());
        StorageCustomerInfo02Util.putInfo(context, "bankAccount", item.getBankAccount());
        StorageCustomerInfo02Util.putInfo(context, "bankAccountName", item.getBankAccountName());
        StorageCustomerInfo02Util.putInfo(context, "idCardNumber", item.getIdCardNumber());
        StorageCustomerInfo02Util.putInfo(context, "bankDetail", item.getBankDetail());
        StorageCustomerInfo02Util.putInfo(context, "bankCode", item.getBankCode());
        StorageCustomerInfo02Util.putInfo(context, "phone", item.getPhone());
        StorageCustomerInfo02Util.putInfo(context, "source", item.getMerchantSource());
        StorageCustomerInfo02Util.putInfo(context, "useStatus", item.getUseStatus());
        StorageCustomerInfo02Util.putInfo(context, "parentPhone", item.getParentPhone());
        JPushInterface.setAlias(context, 1, item.getMerchantNo());

//10A 未审核，10B 审核通过，10C 审核拒绝，10D 重新审核
        String freezeStatus = item.getFreezeStatus();
        StorageCustomerInfo02Util.putInfo(context, "freezeStatus", freezeStatus);
        String examineResult = item.getRcexamineResult();
        if ("W8".equals(result)) {
            StorageCustomerInfo02Util.putInfo(context, "examineResult", examineResult);
            //审核状态
            StorageCustomerInfo02Util.putInfo(context, "examineState", "W8");
        }

    }

    /**
     * 进入主页面
     */
    private void goMainActivity() {
        Intent intent_start = new Intent();
        intent_start.setClass(context, HomeNewActivity.class);
        intent_start.putExtra("fromLogin", true);
        startActivity(intent_start);
        ViewUtils.overridePendingTransitionComeFinish(context);
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
