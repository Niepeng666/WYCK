package com.linglingyi.com.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.linglingyi.com.adapter.ShoppingMalAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.ShoppingMalModel;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.FastJsonUtils;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.viewone.GridDivideItemDecoration;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/8/20
 */
public class ShopMallActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.tv_address_manager)
    TextView tvAddressManager;
    @BindView(R.id.linear_title)
    LinearLayout linearTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_order)
    TextView tvOrder;
    @BindView(R.id.srl_refresh)
    SmartRefreshLayout srlRefresh;
    private List<ShoppingMalModel> mList = new ArrayList<>();
    private ShoppingMalAdapter adapter;
    private Integer pageIndex = 1, count;

    @OnClick({R.id.iv_back, R.id.tv_order, R.id.tv_address_manager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.tv_order:
                startActivity(new Intent(context, MyOrderActivity.class));
                break;
            case R.id.tv_address_manager:
                startActivity(new Intent(context, AddressListActivity.class));
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
    public int initLayout() {
        return R.layout.act_shop_mall;
    }

    @Override
    public void initData() {
        tvTitle.setText("在线商城");
        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        recyclerView.addItemDecoration(new GridDivideItemDecoration(context, CommonUtils.dp2px(context, 10), Color.WHITE));
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ShoppingMalAdapter(mList);
        adapter.bindToRecyclerView(recyclerView);
        srlRefresh.setRefreshHeader(new ClassicsHeader(context));
//        showTip();
        loadData();
        initListener();
    }

    private void showTip() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_jifen_tip, null);

        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .show();
        dialog.getWindow().setLayout(CommonUtils.dp2px(context, 250), CommonUtils.dp2px(context, 250));
        view.findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void initListener() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                goItemDetail(position);
            }
        });
        srlRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshData();
            }
        });
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (count > 0 && count > pageIndex) {
                    pageIndex++;
                    loadData();
                } else {
                    adapter.loadMoreEnd();
                }
            }
        }, recyclerView);
    }

    private void refreshData() {
        pageIndex = 1;
        mList.clear();
        loadData();
    }

    private void goItemDetail(int position) {
        Intent intent = new Intent(context, ItemDetailActivity.class);
        intent.putExtra("item", mList.get(position));
        startActivity(intent);
    }

    private void loadData() {
        loadingDialog.show();
        HttpParams map = OkClient.getParamsInstance().getParams();
        map.put("3", "790101");
        map.put("22", pageIndex + "");
        loadingDialog.show();
        OkClient.getInstance().post(map, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
                srlRefresh.finishRefresh();
                adapter.loadMoreComplete();
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                srlRefresh.finishRefresh();
                adapter.loadMoreComplete();
                BaseEntity model = response.body();
                String result = model.getStr39();
                if ("00".equals(result)) {
                    count = Integer.parseInt(model.getStr40());
                    List<ShoppingMalModel> list = FastJsonUtils.toList(model.getStr41(), ShoppingMalModel.class);
                    mList.addAll(list);
                    adapter.setNewData(mList);
                } else {
                    ViewUtils.makeToast(context, result, 500);
                }
            }
        });
    }
}
