package com.linglingyi.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Group;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.event.BenefitEvent;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.wuyouchuangke.com.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyWalletActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_benefit)
    TextView tvBenefit;
    @BindView(R.id.rl_top)
    ConstraintLayout rlTop;
    @BindView(R.id.btn_withdraw)
    Button btnWithdraw;
    @BindView(R.id.tv_benefit_title)
    TextView tvBenefitTitle;
    @BindView(R.id.tv_commission_title)
    TextView tvCommissionTitle;
    @BindView(R.id.tv_today_benefit)
    TextView tvTodayBenefit;
    @BindView(R.id.cl_today_benefit)
    ConstraintLayout clTodayBenefit;
    @BindView(R.id.tv_profit_title)
    TextView tvProfitTitle;
    @BindView(R.id.tv_yesterday_benefit)
    TextView tvYesterdayBenefit;
    @BindView(R.id.cl_yesterday_benefit)
    ConstraintLayout clYesterdayBenefit;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.cl_total_price)
    ConstraintLayout clTotalPrice;
    @BindView(R.id.tv_withdrew_title)
    TextView tvWithdrewTitle;
    @BindView(R.id.tv_company_benefit)
    TextView tvCompanyBenefit;
    @BindView(R.id.cl_company_benefit)
    ConstraintLayout clCompanyBenefit;
    @BindView(R.id.group_benefit)
    Group groupBenefit;
    private String benefit;

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

    @Override
    public int initLayout() {
        return R.layout.activity_benefit_detail;
    }

    @Override
    public void initData() {
        tvTitle.setText("我的钱包");
        loadData();
    }

    private void loadData() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "190113");
        httpParams.put("42", getMerNo());
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
                    benefit = model.getStr43();
                    tvTodayBenefit.setText(model.getStr44());
                    tvYesterdayBenefit.setText(model.getStr45());
                    tvTotalPrice.setText(model.getStr46());
                    if (StringUtil.isEmpty(model.getStr47())) {
                        clCompanyBenefit.setVisibility(View.GONE);
                    } else {
                        clCompanyBenefit.setVisibility(View.VISIBLE);
                        tvCompanyBenefit.setText(model.getStr47());
                    }
                    tvBenefit.setText(CommonUtils.format00(model.getStr43()));
                }
            }
        });
    }

    /**
     * 绑卡成功后，自动刷新数据
     *
     * @param event
     */
    @Subscribe
    public void onEvent(BenefitEvent event) {
        loadData();
    }

    @OnClick({R.id.iv_back, R.id.cl_today_benefit, R.id.cl_yesterday_benefit,
            R.id.cl_total_price, R.id.cl_company_benefit, R.id.btn_withdraw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;

            case R.id.btn_withdraw:
                if (!checkCustomerInfoCompleteShowToast()) {
                    return;
                }
                startActivity(new Intent(context, WithdrawalActivity.class).putExtra("money", benefit));
                break;
            case R.id.cl_today_benefit:
                goBenefitList("1", "今日收入");
                break;
            case R.id.cl_yesterday_benefit:
                goBenefitList("2", "昨日收入");
                break;
            case R.id.cl_total_price:
                goBenefitList("3", "累计收入");
                break;
            case R.id.cl_company_benefit:
                goBenefitList("4", "公司奖励");
                break;
        }
    }

    private void goBenefitList(String type, String title) {
        Intent intent = new Intent(context, BenefitListActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("title", title);
        startActivity(intent);
    }
}
