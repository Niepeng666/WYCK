package com.linglingyi.com.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.ClientModel;
import com.linglingyi.com.model.User;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/8/23
 */
public class ClientSingleDetailActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_level_name)
    TextView tvLevelName;
    @BindView(R.id.tv_benefit)
    TextView tvBenefit;
    @BindView(R.id.tv_invite)
    TextView tvInvite;
    @BindView(R.id.tv_auth_name)
    TextView tvAuthName;
    @BindView(R.id.tv_register_time)
    TextView tvRegisterTime;
    @BindView(R.id.tv_month_benefit)
    TextView tvMonthBenefit;
    @BindView(R.id.tv_today_benefit)
    TextView tvTodayBenefit;
    @BindView(R.id.tv_yesterday_benefit)
    TextView tvYesterdayBenefit;
    @BindView(R.id.tv_client_total)
    TextView tvClientTotal;
    private ClientModel detail;

    @Override
    public int initLayout() {
        return R.layout.act_client_single_detail;
    }

    @Override
    public void initData() {
        tvTitle.setText("用户详情");
        detail = (ClientModel) getIntent().getSerializableExtra("detail");
        if (detail != null) {
            tvPhone.setText("手机号：" + detail.getLinkPhone());
            String levelName = "普通会员";
            switch (detail.getLevel()) {
                case "1":
                    levelName = "普通会员";
                    break;
                case "2":
                    levelName = "V1";
                    break;
                case "3":
                    levelName = "V2";
                    break;
                case "4":
                    levelName = "V3";
                    break;
                case "5":
                    levelName = "V+";
                    break;


            }
            tvLevelName.setText("身份：" + levelName);
            tvRegisterTime.setText("注册时间：" + detail.getCreateTime());
            tvInvite.setText("推荐人：" + detail.getLinkPhone());
            tvAuthName.setText("实名认证：" + detail.getMerchantCnName());
        }
        loadData();
    }

    private void loadData() {
        if (detail == null) {
            return;
        }
        loadingDialog.show();
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "390112");
//        httpParams.put("42", detail.getMerchantNo());
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
                    // : 2019/5/22 更新实名认证信息
                    tvTodayBenefit.setText("今日收益：" + model.getStr31());
                    tvYesterdayBenefit.setText("昨日收益：" + model.getStr32());
                    tvMonthBenefit.setText("当月收益：" + model.getStr33());
                    tvClientTotal.setText("下级商户总数：" + model.getStr35());
                    tvBenefit.setText("收益总额：" + model.getStr34());
                }
            }
        });
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        ViewUtils.overridePendingTransitionBack(context);
    }
}
