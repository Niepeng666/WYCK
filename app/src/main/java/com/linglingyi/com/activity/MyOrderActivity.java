package com.linglingyi.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.wuyouchuangke.com.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.linglingyi.com.adapter.MyOrderAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.event.AddressEvent;
import com.linglingyi.com.event.OrderPayEvent;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.OrderModel;
import com.linglingyi.com.utils.IntentConstant;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ${tags}
 *
 * @Title: 我的订单
 * @author:wujun
 */
public class MyOrderActivity extends BaseActivity {

    private static final String TAG = "MyOrderActivity";
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.id_img)
    ImageView idImg;
    @BindView(R.id.relative_defaultx)
    RelativeLayout relativeDefaultx;
    @BindView(R.id.srl_refresh)
    SmartRefreshLayout srlRefresh;

    private List<OrderModel> mList = new ArrayList<>();
    private MyOrderAdapter orderAdapter;
    private Integer pageIndex = 1, count;

    @OnClick(R.id.iv_back)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }

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
        return R.layout.act_my_order;
    }

    @Override
    public void initData() {
        tvTitle.setText("我的订单");
        EventBus.getDefault().register(this);
        rvList.setLayoutManager(new LinearLayoutManager(context));
        orderAdapter = new MyOrderAdapter(mList);
        orderAdapter.bindToRecyclerView(rvList);
        srlRefresh.setRefreshHeader(new ClassicsHeader(context));
        loadData();
        initListener();
    }

    private void initListener() {
        orderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.my_service:
                        startActivity(new Intent(context, ServiceCenterActivity.class));
                        break;
                    case R.id.btnSubmit:
                        OrderModel orderModel = mList.get(position);

                        if (orderModel.getStatus().equals("10A")) {//待支付
// : 2019/8/26 去支付
                            startActivity(new Intent(context, OrderPayDetailActivity.class).putExtra(IntentConstant.ORDER, orderModel));
//                            goOrderDetail(position);
//                            goPay(mList.get(position).getId());
                        } else if (orderModel.getStatus().equals("10D")) {//确认收货
                            requestConfirm(orderModel.getId());
                        } else if (orderModel.getStatus().equals("10C")) {//取消订单
                            OrderCancel(orderModel.getId());
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        orderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                goOrderDetail(position);
            }
        });
        orderAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (count > 0 && count > pageIndex) {
                    pageIndex++;
                    loadData();
                } else {
                    orderAdapter.loadMoreEnd();
                }
            }
        }, rvList);
        srlRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshData();
            }
        });
    }

    /**
     * 进入订单详情页
     *
     * @param position
     */
    private void goOrderDetail(int position) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra("orderId", mList.get(position).getId());
        startActivity(intent);
    }

    /**
     * 取消订单
     *
     * @param id
     */
    private void OrderCancel(String id) {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "790106");
        httpParams.put("41", id);//订单Id
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
                    ViewUtils.makeToast(context, "取消成功", 1500);
                    srlRefresh.autoRefresh();
                }
            }


        });
    }

    /**
     * 请求确认收货
     *
     * @param id
     */
    private void requestConfirm(String id) {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "590019");
        httpParams.put("9", id);//订单Id
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
                    ViewUtils.makeToast(context, "签收成功", 1500);
                    srlRefresh.autoRefresh();
                }
            }


        });
    }

    /**
     * 刷新数据
     */
    private void refreshData() {
        mList.clear();
        pageIndex = 1;
        loadData();
    }

    /**
     * 获取订单
     */
    private void loadData() {
        final HttpParams map = OkClient.getParamsInstance().getParams();
        map.put("3", "790105");
        map.put("21", getMerId());
        map.put("22", pageIndex + "");
        loadingDialog.show();
        OkClient.getInstance().post(map, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                Log.i(TAG, "onError" + response.toString());
                loadingDialog.dismiss();
                orderAdapter.loadMoreComplete();
                srlRefresh.finishRefresh();
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                orderAdapter.loadMoreComplete();
                srlRefresh.finishRefresh();
                BaseEntity model = response.body();
                Log.i(TAG, "onSuccess" + model.toString());
                String result = model.getStr39();
                if ("00".equals(result)) {
                    count = Integer.parseInt(model.getStr41());
                    List<OrderModel> list = JSONArray.parseArray(model.getStr40(), OrderModel.class);
                    if (list != null) {
                        mList.addAll(list);
                        orderAdapter.setNewData(mList);
                    }

                }
            }
        });
    }

    @Subscribe
    public void onEvent(OrderPayEvent orderPayEvent) {
        refreshData();
    }

    /**
     * 进行积分支付
     *
     * @param orderId
     */
    private void goPay(String orderId) {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "790104");
        httpParams.put("21", orderId);
        httpParams.put("22", getMerId());
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
                    ViewUtils.makeToast(context, "支付成功", 500);
                    srlRefresh.autoRefresh();
                }
            }


        });
    }
}
