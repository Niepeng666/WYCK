package com.linglingyi.com.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.adapter.LordRightsAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.LordRightsModel;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @作者 chenlanxin
 * @创建日期 2019/10/17 15:13
 * @功能 收益排行
 **/
public class IncomeRankedActivity extends BaseActivity implements OnRefreshListener, OnLoadmoreListener {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    private LordRightsAdapter lordRightsAdapter;
    private List<LordRightsModel> list = new ArrayList<>();
    private int page = 1, allPage = 1;

    @Override
    public int initLayout() {
        return R.layout.activity_income_ranked;
    }

    @Override
    public void initData() {
        tvTitle.setText("收益排行");

        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(context));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(context));
        smartRefreshLayout.setOnRefreshListener(this);
        smartRefreshLayout.setOnLoadmoreListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        lordRightsAdapter = new LordRightsAdapter(true);
        lordRightsAdapter.bindToRecyclerView(recyclerView);
        lordRightsAdapter.setNewData(list);
        lordRightsAdapter.setEmptyView(R.layout.layout_empty, recyclerView);

        smartRefreshLayout.autoRefresh();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        if (page > 0 && page < allPage) {
            page++;
            requestData(false);
        } else {
            refreshlayout.finishLoadmoreWithNoMoreData();
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        list.clear();
        page = 1;
        refreshlayout.resetNoMoreData();
        requestData(true);
    }

    private void requestData(boolean isRefresh) {
        if (isRefresh) {
            smartRefreshLayout.finishRefresh();
        } else {
            smartRefreshLayout.finishLoadmore();
        }
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "393001");
        httpParams.put("35",getMerId());
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
                    List<LordRightsModel> lordRightsModels = JSONArray.parseArray(model.getStr57(), LordRightsModel.class);
                    list.addAll(lordRightsModels);
                    lordRightsAdapter.setNewData(list);
                }
            }
        });
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
