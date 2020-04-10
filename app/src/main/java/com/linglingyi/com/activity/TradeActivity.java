package com.linglingyi.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.linglingyi.com.adapter.QueryAdapter;
import com.linglingyi.com.adapter.QueryNewAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.QueryModel;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.wuyouchuangke.com.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/23
 */
public class TradeActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tl_tab)
    TabLayout tlTab;
    @BindView(R.id.total_money)
    TextView totalMoney;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.tv_seven_title)
    TextView tvSevenTitle;
    @BindView(R.id.tv_seven_num)
    TextView tvSevenNum;
    @BindView(R.id.view_center_line)
    View viewCenterLine;
    @BindView(R.id.tv_seven_money_title)
    TextView tvSevenMoneyTitle;
    @BindView(R.id.tv_seven_money)
    TextView tvSevenMoney;
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    private int pageIndex = 1;
    private int count;
    private List<QueryModel> mList = new ArrayList<>();
    private QueryNewAdapter mQueryAdapter;
    private String type = "YK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.act_trade;
    }

    @Override
    public void initData() {
        tvTitle.setText("交易明细");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvList.setLayoutManager(linearLayoutManager);
        mQueryAdapter = new QueryNewAdapter(mList);
        mQueryAdapter.bindToRecyclerView(rvList);
        mQueryAdapter.setEmptyView(R.layout.layout_empty, rvList);
        setTabLine(50, 50);
        loadData();
        initListener();
    }

    private void initListener() {
        tlTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pageIndex = 1;
                mList.clear();
                type = tab.getPosition() == 0 ? "YK" : "WK";
                loadData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mQueryAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, rvList);
        mQueryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(context, TradeRecordDetailActivity.class).putExtra("detail", mList.get(position)));
            }
        });
    }


    private void loadMore() {
        LogUtils.i("pageIndex=" + pageIndex);
        if (count > 0 && pageIndex < count) {
            pageIndex++;
            loadData();
        } else {
            mQueryAdapter.loadMoreEnd();
        }
    }


    private void loadData() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "190978");
        httpParams.put("42", getMerNo());
        httpParams.put("45", pageIndex + "");
        httpParams.put("8", type);
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
                mQueryAdapter.loadMoreComplete();
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                mQueryAdapter.loadMoreComplete();
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }

                if ("00".equals(model.getStr39())) {
                    LogUtils.i("result=" + model.getStr23());
                    tvTotalMoney.setText(model.getStr11());
                    tvSevenMoney.setText(model.getStr12());
                    tvSevenNum.setText(model.getStr13());
                    List<QueryModel> list = JSONArray.parseArray(model.getStr57(), QueryModel.class);
                    count = Integer.parseInt(model.getStr10());
                    mList.addAll(list);
                    mQueryAdapter.setNewData(mList);

                }
            }
        });
    }

    public void setTabLine(int left, int right) {
        try {
            Class<?> tablayout = tlTab.getClass();
            Field tabStrip = tablayout.getDeclaredField("mTabStrip");
            tabStrip.setAccessible(true);
            LinearLayout ll_tab = (LinearLayout) tabStrip.get(tlTab);
            for (int i = 0; i < ll_tab.getChildCount(); i++) {
                View child = ll_tab.getChildAt(i);
                child.setPadding(0, 0, 0, 0);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                //修改两个tab的间距
                params.setMarginStart(CommonUtils.dp2px(context, left));
                params.setMarginEnd(CommonUtils.dp2px(context, right));
                child.setLayoutParams(params);
                child.invalidate();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        ViewUtils.overridePendingTransitionBack(context);
    }
}
