package com.linglingyi.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.event.PlanCloseEvent;
import com.linglingyi.com.model.BindCard;
import com.linglingyi.com.model.PreviewPlanModel;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.ViewUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WorkingFundActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_pro_fee)
    TextView tvProFee;
    @BindView(R.id.tv_service_fee)
    TextView tvServiceFee;
    @BindView(R.id.tv_working_fund)
    TextView tvWorkingFund;
    @BindView(R.id.btn_admit)
    Button btnAdmit;
    private PreviewPlanModel mPreviewPlanModel;
    private BindCard mBindCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 提交计划后，自动关闭页面
     *
     * @param event
     */
    @Subscribe
    public void onEvent(PlanCloseEvent event) {
        ViewUtils.overridePendingTransitionBack(context);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_working_fund;
    }

    @Override
    public void initData() {
        tvTitle.setText("周转金确认");
        mPreviewPlanModel = (PreviewPlanModel) getIntent().getSerializableExtra("previewModel");
        mBindCard = (BindCard) getIntent().getSerializableExtra("bindCard");
        tvProFee.setText(mPreviewPlanModel.getWorkingFund());
        BigDecimal serviceFee = CommonUtils.formatNewWithScale(mPreviewPlanModel.getFee(), 2).add(CommonUtils.formatNewWithScale(mPreviewPlanModel.getTimesFee(), 2));
        tvServiceFee.setText(serviceFee.toString());
        tvWorkingFund.setText(mPreviewPlanModel.getTotalFee());
    }

    @OnClick({R.id.iv_back, R.id.btn_admit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.btn_admit:
                Intent intent = new Intent(context, PreviewPlanActivity.class);
                intent.putExtra("previewModel", mPreviewPlanModel);
                intent.putExtra("bindCard", mBindCard);
                startActivity(intent);
                break;
        }
    }
}
