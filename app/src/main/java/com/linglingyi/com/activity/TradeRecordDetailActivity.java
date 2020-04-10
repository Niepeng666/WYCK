package com.linglingyi.com.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.QueryModel;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.ViewUtils;
import com.wuyouchuangke.com.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/5/20
 */
public class TradeRecordDetailActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_merchant_name)
    TextView tvMerchantName;
    @BindView(R.id.tv_trade_type)
    TextView tvTradeType;
//    @BindView(R.id.tv_trade_rate)
//    TextView tvTradeRate;
    @BindView(R.id.tv_trade_time)
    TextView tvTradeTime;
    @BindView(R.id.tv_trade_account)
    TextView tvTradeAccount;
    @BindView(R.id.tv_trade_no)
    TextView tvTradeNo;
    @BindView(R.id.tv_trade_status)
    TextView tvTradeStatus;
    private QueryModel mQueryModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.act_trade_detail;
    }

    @Override
    public void initData() {
        tvTitle.setText("交易明细");
        mQueryModel = (QueryModel) getIntent().getSerializableExtra("detail");
        updateData();
    }

    private void updateData() {
        if (mQueryModel == null) {
            return;
        }
        tvMoney.setText("￥ " + CommonUtils.format00(Double.toString(mQueryModel.getTrxAmt())));
        tvMerchantName.setText(mQueryModel.getName());
        tvTradeType.setText(mQueryModel.getTradeTypeName());
//        tvTradeRate.setText(mQueryModel.getRate()+"+"+mQueryModel);
        tvTradeTime.setText(mQueryModel.getCompleteTimeString());
        tvTradeAccount.setText(mQueryModel.getCardNo());
        tvTradeNo.setText(mQueryModel.getOrderNo());
        tvTradeStatus.setText(mQueryModel.getStatusName());
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        ViewUtils.overridePendingTransitionBack(context);
    }
}
