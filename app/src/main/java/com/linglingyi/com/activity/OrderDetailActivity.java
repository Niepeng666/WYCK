package com.linglingyi.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.OrderModel;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.DateUtil;
import com.linglingyi.com.utils.GlideUtils;
import com.linglingyi.com.utils.IntentConstant;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/10/18
 */
public class OrderDetailActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_order_status)
    TextView tvOrderStatus;
    @BindView(R.id.iv_order_status)
    ImageView ivOrderStatus;
    @BindView(R.id.iv_address_icon)
    ImageView ivAddressIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.iv_product)
    ImageView ivProduct;
    @BindView(R.id.tv_product_name)
    TextView tvProductName;
    @BindView(R.id.tv_specification)
    TextView tvSpecification;
    @BindView(R.id.tv_stock)
    TextView tvStock;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_delivery)
    TextView tvDelivery;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.tv_order_no)
    TextView tvOrderNo;
    @BindView(R.id.tv_order_time)
    TextView tvOrderTime;
    @BindView(R.id.tv_deliver_number)
    TextView tvDeliverNumber;
    @BindView(R.id.rl_deliver_number)
    RelativeLayout rlDeliverNumber;
    @BindView(R.id.tv_money_title)
    TextView tvMoneyTitle;
    @BindView(R.id.tv_money_2)
    TextView tvMoney2;
    @BindView(R.id.tv_deliver_fee)
    TextView tvDeliverFee;
    @BindView(R.id.btn_purchase)
    Button btnPurchase;
    @BindView(R.id.ll_bottom_pay)
    RelativeLayout llBottomPay;
    @BindView(R.id.tv_single_price)
    TextView tvSinglePrice;
    private OrderModel orderModel;
    private String orderId;

    @Override
    public int initLayout() {
        return R.layout.act_order_detail;
    }

    @Override
    public void initData() {
        tvTitle.setText("订单详情");
//        orderModel = (OrderModel) getIntent().getSerializableExtra(IntentConstant.ORDER);
        orderId=getIntent().getStringExtra("orderId");

        loadDetail();
    }

    private void loadDetail() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "790107");
        httpParams.put("35", orderId);
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
                    tvName.setText(model.getStr31());
                    tvPhone.setText(model.getStr32());
                    tvAddress.setText(model.getStr30());
                    if (!StringUtil.isEmpty(model.getStr57())) {
                        orderModel = JSONObject.parseObject(model.getStr57(), OrderModel.class);
                        fillData();
                    }
                }
            }
        });
    }

    private void fillData() {
        GlideUtils.loadImage(context, orderModel.getGoodsImage(), ivProduct);
        tvOrderStatus.setText(orderModel.getStatusString());
        tvProductName.setText(orderModel.getGoodsName());
        tvSpecification.setText("规格：" + orderModel.getGoodsSpecification());
        tvSinglePrice.setText("￥" + orderModel.getGoodsPrice());
        tvStock.setText("x" + orderModel.getGoodsCount());
        tvPrice.setText("￥" + orderModel.getPay());
        tvTotalPrice.setText(orderModel.getPay());
        if (StringUtil.isEqual(orderModel.getStatus(), "10A") || StringUtil.isEqual(orderModel.getStatus(), "10B") || StringUtil.isEqual(orderModel.getStatus(), "10C")) {
            rlDeliverNumber.setVisibility(View.GONE);
            ivOrderStatus.setImageResource(R.drawable.order_paying);
            llBottomPay.setVisibility(View.VISIBLE);
        } else {
            rlDeliverNumber.setVisibility(View.VISIBLE);
            tvDeliverNumber.setText(orderModel.getPostNumber());
            ivOrderStatus.setImageResource(R.drawable.order_payed);
            llBottomPay.setVisibility(View.GONE);
        }
        if (StringUtil.isEqual(orderModel.getStatus(), "10A") || StringUtil.isEqual(orderModel.getStatus(), "10B")) {
            llBottomPay.setVisibility(View.VISIBLE);
        } else {
            llBottomPay.setVisibility(View.GONE);
        }

        if (orderModel.getId().length() >= 16) {
            tvOrderNo.setText(orderModel.getId().substring(0, 16));
        } else {
            tvOrderNo.setText(orderModel.getId());
        }

        tvOrderTime.setText(DateUtil.formatDateToHMS(orderModel.getCreateTime().getTime()));
        tvMoney2.setText(orderModel.getPay() + "元");
    }

    @OnClick({R.id.iv_back, R.id.btn_purchase})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.btn_purchase:
                // TODO: 2019/10/18 支付
                if (!orderModel.isPay()) {
                    startActivity(new Intent(context, OrderPayDetailActivity.class).putExtra(IntentConstant.ORDER, orderModel));
                    finish();
                } else {
                    ViewUtils.makeToast(context, "订单已支付", 1500);
                    return;
                }
                break;
            default:
                break;
        }
    }
}
