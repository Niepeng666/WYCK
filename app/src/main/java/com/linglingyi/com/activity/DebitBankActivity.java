package com.linglingyi.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.linglingyi.com.MyApplication;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.Utils;
import com.linglingyi.com.utils.ViewUtils;
import com.wuyouchuangke.com.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/7/9
 */
public class DebitBankActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.iv_bank_icon)
    ImageView ivBankIcon;
    @BindView(R.id.tv_bankName)
    TextView tvBankName;
    @BindView(R.id.tv_debit_card_name)
    TextView tvDebitCardName;
    @BindView(R.id.tv_debit_card_account)
    TextView tvDebitCardAccount;
    @BindView(R.id.tv_update)
    TextView tvUpdate;
    @BindView(R.id.rl_debit_card)
    RelativeLayout rlDebitCard;



    @Override
    public int initLayout() {
        return R.layout.act_debit_bank;
    }

    @Override
    public void initData() {
        tvTitle.setText("我的卡包");
        String bankAccountName = StorageCustomerInfo02Util.getInfo("merchantCnName", context);
        tvDebitCardName.setText(bankAccountName);

    }

    @Override
    protected void onResume() {
        super.onResume();
        String bankAccount = StorageCustomerInfo02Util.getInfo("bankAccount",
                context);
        String bankCode = StorageCustomerInfo02Util.getInfo("bankCode",
                context);
        String bankNum1 = "", bankNum2 = "";
        if (bankAccount.length() > 4) {
            bankNum1 = bankAccount.substring(0, 4);
            bankNum2 = bankAccount.substring(bankAccount.length() - 4, bankAccount.length());
        }
        Utils.initBankBackgroundCode(bankCode,rlDebitCard,context);
        Utils.initBankCode(bankCode, ivBankIcon, context);
        tvBankName.setText(MyApplication.getBankName(bankCode));
        tvDebitCardAccount.setText(String.format("**** **** **** %s", bankNum2));
    }

    @OnClick({R.id.iv_back, R.id.tv_update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.tv_update:
                startActivity(new Intent(context, DebitBankChangeActivity.class));
                break;
        }
    }
}
