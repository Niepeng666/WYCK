package com.linglingyi.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wuyouchuangke.com.R;
import com.bumptech.glide.Glide;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.event.AddressEvent;
import com.linglingyi.com.event.AuthEvent;
import com.linglingyi.com.model.AddressModel;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.ItemModel;
import com.linglingyi.com.model.OrderModel;
import com.linglingyi.com.utils.GlideUtils;
import com.linglingyi.com.utils.IntentConstant;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/8/23
 */
public class OrderConfirmActivity extends BaseActivity {
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
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.cl_address)
    ConstraintLayout clAddress;
    @BindView(R.id.rl_address_add)
    RelativeLayout rlAddressAdd;
    @BindView(R.id.rl_address_manager)
    RelativeLayout rlAddressManager;
    @BindView(R.id.iv_product)
    ImageView ivProduct;
    @BindView(R.id.tv_product_name)
    TextView tvProductName;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_money_2)
    TextView tvMoney2;
    @BindView(R.id.tv_deliver_fee)
    TextView tvDeliverFee;
    @BindView(R.id.btn_purchase)
    Button btnPurchase;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.tv_specification)
    TextView tvSpecification;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    @BindView(R.id.tv_stock)
    TextView tvStock;
    @BindView(R.id.tv_item_num)
    TextView tvItemNum;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    private ItemModel mItemModel;
    private AddressModel mAddressModel;
    private String addressId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public int initLayout() {
        return R.layout.act_order_confirm;
    }

    @Override
    public void initData() {
        tvTitle.setText("订单支付");
        EventBus.getDefault().register(this);
        mItemModel = (ItemModel) getIntent().getSerializableExtra("goods");
        GlideUtils.loadImage(context, mItemModel.getImage(), ivProduct);
        tvProductName.setText(mItemModel.getName());
        tvPrice.setText("￥" + mItemModel.getPrice() + "");
        tvMoney2.setText("￥" + (mItemModel.getPrice() * mItemModel.getGoodsCount()));
        tvSpecification.setText("规格：" + mItemModel.getSize());
        tvStock.setText("x" + mItemModel.getGoodsCount());
        tvItemNum.setText("共" + mItemModel.getGoodsCount() + "件商品");
        tvTotalPrice.setText("合计：" + (mItemModel.getPrice() * mItemModel.getGoodsCount()));
//        }

        requestData();
    }

    /**
     * 收货地址列表 获取默认地址
     */
    private void requestData() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "590012");
        httpParams.put("9", getMerId());
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
                    clAddress.setVisibility(View.VISIBLE);
                    rlAddressAdd.setVisibility(View.GONE);
                    String addressListStr = model.getStr40();
                    List<AddressModel> modelList = com.alibaba.fastjson.JSONArray.parseArray(addressListStr, AddressModel.class);
                    for (AddressModel address : modelList) {
                        if ("1".equals(address.getStatus())) {
                            mAddressModel = address;
                            updateData();
                        }
                    }
                    if (mAddressModel == null && modelList.size() > 0) {
                        mAddressModel = modelList.get(0);
                        updateData();
                    }
                } else if (!StringUtil.isEmpty(model.getStr39()) && model.getStr39().contains("暂无地址")) {
                    clAddress.setVisibility(View.GONE);
                    rlAddressAdd.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void updateData() {
        if (mAddressModel == null) {
            return;
        }
        tvName.setText(mAddressModel.getName());
        tvPhone.setText(mAddressModel.getPhone());
        tvAddress.setText(mAddressModel.getAddress());
        addressId = mAddressModel.getId();
    }

    @Subscribe
    public void onEvent(AddressEvent addressEvent) {
        requestData();
    }

    @OnClick({R.id.iv_back, R.id.cl_address, R.id.rl_address_add, R.id.btn_purchase})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.cl_address:
                startActivityForResult(new Intent(context, AddressListActivity.class).putExtra("formOrder",true), 1);
                break;
            case R.id.rl_address_add:
                startActivity(new Intent(context, AddressAddActivity.class));
                break;
            case R.id.btn_purchase:
                if (StringUtil.isEmpty(addressId)) {
                    ViewUtils.makeToast(context, "请选择收货地址", 1000);
                    return;
                }
                generateOrders();
                break;
        }
    }

    /**
     * 生成订单
     */
    private void generateOrders() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "790103");
        httpParams.put("6", getMerId());
        httpParams.put("7", mItemModel.getId());
        httpParams.put("9", addressId);
        httpParams.put("10", mItemModel.getSize());
        httpParams.put("11", mItemModel.getGoodsCount());
        httpParams.put("12", tvRemark.getText().toString());
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    OrderModel orderModel = JSONObject.parseObject(model.getStr57(), OrderModel.class);
                    Intent intent = new Intent(context, OrderPayDetailActivity.class);
                    intent.putExtra(IntentConstant.ORDER, orderModel);
                    startActivity(intent);
                    if (ItemDetailActivity.activity != null) {
                        ItemDetailActivity.activity.finish();
                        finish();
                    }
                }
            }

            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            mAddressModel = (AddressModel) data.getSerializableExtra("data");
            updateData();
        }
    }
}
