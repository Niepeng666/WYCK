package com.linglingyi.com.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.linglingyi.com.MyApplication;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.event.BankChangeEvent;
import com.linglingyi.com.model.BankNameModel;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.wuyouchuangke.com.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/5/13
 */
public class ChangeAuthBankCardActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_id_card)
    TextView tvIdCard;
    @BindView(R.id.et_bank_account)
    EditText etBankAccount;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.ll_card_change)
    LinearLayout llCardChange;
    @BindView(R.id.btn_admit)
    Button btnAdmit;
    private String bankCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.act_change_bank_card;
    }

    @Override
    public void initData() {
        tvTitle.setText("更换储蓄卡");
        String name = StorageCustomerInfo02Util.getInfo("merchantCnName", context);
        tvName.setText(name);
        String idCard = StorageCustomerInfo02Util.getInfo("idCardNumber", context);
        tvIdCard.setText(CommonUtils.translateShortNumber(idCard, 4, 4));
        initListener();
    }


    private void initListener() {
        etBankAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etBankAccount.getText().toString().length() >= 16) {
                    getBankName(etBankAccount.getText().toString());
                }
            }
        });
    }

    /**
     * 获取银行卡名称
     *
     * @param bankNum 卡号
     */
    private void getBankName(String bankNum) {
        HttpParams map = OkClient.getParamsInstance().getParams();
        map.put("15", bankNum);
        map.put("3", "690013");
        OkClient.getInstance().post(map, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    BankNameModel bankNameModel = JSONObject.parseObject(model.getStr16(), BankNameModel.class);
                    bankCode = bankNameModel.getBankCode();
                    tvBankName.setText(bankNameModel.getShortCnName());
                }
            }


        });
    }


    @OnClick({R.id.iv_back, R.id.btn_admit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.btn_admit:
                String bankAccount = etBankAccount.getText().toString();
                String bankPhone = etPhone.getText().toString();
                String bankName = tvBankName.getText().toString();
                if (!CommonUtils.checkBankCard(bankAccount)) {
                    ViewUtils.makeToast(context, "请输入正确的银行卡号", 1000);
                    return;
                }
                if (StringUtil.isEmpty(bankPhone) || StringUtil.isEmpty(bankName)) {
                    ViewUtils.makeToast(context, "请输入预留手机号", 1000);
                    return;
                }
                submit(bankAccount, bankPhone);
                break;
        }
    }

    /**
     * 修改储蓄卡
     *
     * @param bankAccount
     * @param bankPhone
     */
    private void submit(final String bankAccount, String bankPhone) {
        loadingDialog.show();
        HttpParams map = OkClient.getParamsInstance().getParams();
        map.put("42", getMerNo());
        map.put("5", bankAccount);
        map.put("6", bankPhone);
        map.put("3", "390001");
        OkClient.getInstance().post(map, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    ViewUtils.makeToast(context, "修改成功", 500);
                    StorageCustomerInfo02Util.putInfo(context, "bankAccount", bankAccount);
                    StorageCustomerInfo02Util.putInfo(context, "bankDetail", tvBankName.getText().toString());
                    StorageCustomerInfo02Util.putInfo(context, "bankCode", bankCode);
                    EventBus.getDefault().post(new BankChangeEvent());
                    finish();
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
