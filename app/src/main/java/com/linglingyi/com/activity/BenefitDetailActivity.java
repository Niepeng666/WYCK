package com.linglingyi.com.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BenefitModel;
import com.linglingyi.com.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/8
 */
public class BenefitDetailActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_money_type)
    TextView tvMoneyType;
    @BindView(R.id.tv_money_from)
    TextView tvMoneyFrom;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_money_benefit)
    TextView tvMoneyBenefit;
    @BindView(R.id.tv_type_name)
    TextView tvTypeName;
    @BindView(R.id.tv_from_name)
    TextView tvFromName;
    @BindView(R.id.ll_money)
    LinearLayout llMoney;
    @BindView(R.id.tv_benefit_name)
    TextView tvBenefitName;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.tv_trade_date_title)
    TextView tvTradeDateTitle;

    private String title;
    private BenefitModel mBenefitModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.act_benefit_detail;
    }

    @Override
    public void initData() {
        title = getIntent().getStringExtra("title");
        tvTitle.setText("明细详情");
        String type = getIntent().getStringExtra("type");
        if ("3".equals(type)) {
            llMoney.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
            tvBenefitName.setText("签到奖励：");
            tvTradeDateTitle.setText("签到时间：");
        }
        mBenefitModel = (BenefitModel) getIntent().getSerializableExtra("detail");
        if (mBenefitModel == null) {
            return;
        }
        tvMoneyType.setText(mBenefitModel.getMoneyType());
        tvMoneyFrom.setText(mBenefitModel.getMerchantCnName());
        tvMoney.setText(mBenefitModel.getMoney());
        tvMoneyBenefit.setText(mBenefitModel.getTrxAmt());
        tvDate.setText(mBenefitModel.getCreateTime());
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        ViewUtils.overridePendingTransitionBack(context);
    }
}
