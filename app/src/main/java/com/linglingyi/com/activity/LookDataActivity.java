package com.linglingyi.com.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.MyApplication;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BindCard;
import com.linglingyi.com.utils.Utils;
import com.linglingyi.com.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 查看资料页
 */
public class LookDataActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.tv_bank_account_2)
    TextView tvBankAccount2;
    @BindView(R.id.tv_name_2)
    TextView tvName2;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_id_card)
    TextView tvIdCard;
    @BindView(R.id.tv_limit)
    TextView tvLimit;
    @BindView(R.id.tv_bill_day)
    TextView tvBillDay;
    @BindView(R.id.tv_pay_day)
    TextView tvPayDay;
    @BindView(R.id.tv_dead_line)
    TextView tvDeadLine;
    @BindView(R.id.tv_cvv)
    TextView tvCvv;
    @BindView(R.id.ll_bank_info)
    LinearLayout llBankInfo;
    @BindView(R.id.iv_bank_icon)
    ImageView ivBankIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_bank_account)
    TextView tvBankAccount;
    @BindView(R.id.cl_bank_head)
    ConstraintLayout clBankHead;
    private BindCard mBindCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_bank_credit_detail;
    }

    @Override
    public void initData() {
        tvTitle.setText("银行卡详情");
        mBindCard = (BindCard) getIntent().getSerializableExtra("bindCard");

        Utils.initBankCodeColorIcon(mBindCard.getBANK_NAME(), ivBankIcon, context);
        tvName.setText(MyApplication.getBankName(mBindCard.getBANK_NAME()));
        String banNum = mBindCard.getBANK_ACCOUNT();
        if (banNum.length() > 4) {
            String bankNum2 = banNum.substring(banNum.length() - 4, banNum.length());
            tvBankAccount.setText("尾号 " + bankNum2);
        }

        tvBankName.setText(MyApplication.getBankName(mBindCard.getBANK_NAME()));
        tvBankAccount2.setText(mBindCard.getBANK_ACCOUNT());
        tvName2.setText(mBindCard.getBANK_ACCOUNT_NAME());
        tvPhone.setText(mBindCard.getBANK_PHONE());
        tvIdCard.setText(mBindCard.getID_CARD_NUMBER());
        tvLimit.setText(mBindCard.getLIMIT_MONEY());
        tvBillDay.setText(mBindCard.getBILL_DAY());
        tvPayDay.setText(mBindCard.getREPAYMENT_DAY() + "");
        tvDeadLine.setText(mBindCard.getExpdate());
        tvCvv.setText(mBindCard.getCvn());
    }

    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;

        }
    }


}
