package com.linglingyi.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.ItemModel;
import com.linglingyi.com.model.ShoppingMalModel;
import com.linglingyi.com.utils.GlideUtils;
import com.linglingyi.com.utils.StatusBarUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.viewone.dialog.BuyGoodsDialog;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/8/22
 */
public class ItemDetailActivity extends BaseActivity {

    public static ItemDetailActivity activity;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_stock)
    TextView tvStock;
    @BindView(R.id.tv_deliver_fee_1)
    TextView tvDeliverFee1;
    @BindView(R.id.introduce_shopping)
    WebView introduceShopping;
    @BindView(R.id.tv_money_2)
    TextView tvMoney2;
    @BindView(R.id.tv_deliver_fee)
    TextView tvDeliverFee;
    @BindView(R.id.btn_purchase)
    Button btnPurchase;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    private ShoppingMalModel mShoppingMalModel;
    private ItemModel itemModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.act_item_detail;
    }

    @Override
    public void initData() {
        tvTitle.setText("商品详情");
        activity = this;
        mShoppingMalModel = (ShoppingMalModel) getIntent().getSerializableExtra("item");

//        if (mShoppingMalModel != null) {
//            tvPrice.setText(mShoppingMalModel.getPrice() + "积分");
//            tvMoney2.setText(mShoppingMalModel.getPrice() + "积分");
//            tvName.setText(mShoppingMalModel.getName());
//            tvStock.setText("库存：" + mShoppingMalModel.getStock());
//            GlideUtils.loadImage(context, mShoppingMalModel.getImage(), image);
//        }
        WebSettings webSettings = introduceShopping.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        loadData();
    }

    private void loadData() {
        loadingDialog.show();
        HttpParams map = OkClient.getParamsInstance().getParams();
        map.put("3", "790102");
        map.put("22", mShoppingMalModel.getId());
        loadingDialog.show();
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
                BaseEntity model = response.body();
                String result = model.getStr39();
                if ("00".equals(result)) {
                    itemModel = JSONObject.parseObject(model.getStr40(), ItemModel.class);
                    updateData(itemModel);
                } else {
                    ViewUtils.makeToast(context, result, 500);
                }
            }
        });
    }

    private void updateData(ItemModel itemModel) {
        if (itemModel != null) {
            tvPrice.setText("￥" + itemModel.getPrice());
            tvMoney2.setText(itemModel.getPrice() + "元");
            tvName.setText(itemModel.getName());
            tvDeliverFee1.setText("邮费："+itemModel.getFreight() + "元");
//            tvStock.setText("库存：" + itemModel.getInventory());
            introduceShopping.loadUrl(itemModel.getDetailImage());
            GlideUtils.loadImage(context, mShoppingMalModel.getImage(), image);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        StatusBarUtil.setStatusBarTranslucent(context, true);
    }

    @OnClick({R.id.iv_back, R.id.btn_purchase})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.btn_purchase:
                if (itemModel == null) {
                    ViewUtils.makeToast(context, "系统异常", 1000);
                    return;
                }
                if (itemModel.getInventory() == 0) {
                    ViewUtils.makeToast(context, "库存为0，请选择其它商品", 1000);
                    return;
                }
                BuyGoodsDialog dialog = BuyGoodsDialog.getIntence(itemModel);
                dialog.setOnButtonClickListener(new BuyGoodsDialog.OnButtonClickListener() {
                    @Override
                    public void onButtonClick(ItemModel model) {
                        Intent intent = new Intent(context, OrderConfirmActivity.class);
                        intent.putExtra("goods", model);
                        startActivity(intent);
                        finish();
                    }
                });
                dialog.show(getSupportFragmentManager(), "");

                break;
        }
    }
}
