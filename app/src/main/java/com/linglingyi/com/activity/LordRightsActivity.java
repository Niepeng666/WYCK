package com.linglingyi.com.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.adapter.LordRightsAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.Area;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.LordRightsModel;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.viewone.dialog.AdvertisingDialog;
import com.linglingyi.com.viewone.viewpager_layoutmanager.CenterSnapHelper;
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
 * @创建日期 2019/10/17 9:38
 * @功能 领主权益
 **/
public class LordRightsActivity extends BaseActivity implements OnRefreshListener, OnLoadmoreListener,
        BaseQuickAdapter.OnItemChildClickListener {
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
        return R.layout.activity_lord_rights;
    }

    @Override
    public void initData() {
        tvTitle.setText("领主");

        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(context));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(context));
        smartRefreshLayout.setOnRefreshListener(this);
        smartRefreshLayout.setOnLoadmoreListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        lordRightsAdapter = new LordRightsAdapter(false);
        lordRightsAdapter.bindToRecyclerView(recyclerView);
        lordRightsAdapter.setOnItemChildClickListener(this);
        lordRightsAdapter.setNewData(list);
        smartRefreshLayout.autoRefresh();
        lordRightsAdapter.setEmptyView(R.layout.layout_empty, recyclerView);
       loadPicData();
        initListener();
    }

    private void initListener() {
        lordRightsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(context, ApplyLordActivity.class);
                intent.putExtra("LordRightsModel", list.get(position));
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.ll_apply, R.id.ll_winning, R.id.ll_earnings, R.id.ll_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_apply:
                startActivity(new Intent(context, ApplyLordActivity.class));
                break;
            case R.id.ll_winning:
                startActivity(new Intent(context, WinningRecordActivity.class));
                break;
            case R.id.ll_earnings:
                startActivity(new Intent(context, IncomeRankedActivity.class));
                break;
            case R.id.ll_my:
                startActivity(new Intent(context, LordRightsMyActivity.class));
                break;
        }
    }

    private void loadPicData() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "390006");
        httpParams.put("42", getMerNo());
        httpParams.put("43", "10A");
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
                    List<String> list = JSONArray.parseArray(model.getStr57(), String.class);
//                    if (!StringUtil.isEmpty(url)) {
//                        AdvertisingDialog.getIntence(url).show(getSupportFragmentManager(), "");
//                    }

                }
            }
        });
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

    private void requestData(boolean isRefresh) {
        if (isRefresh) {
            smartRefreshLayout.finishRefresh();
        } else {
            smartRefreshLayout.finishLoadmore();
        }
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "393001");
        httpParams.put("35", getMerId());
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

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        list.clear();
        page = 1;
        refreshlayout.resetNoMoreData();
        requestData(true);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//        switch (view.getId()) {
//            case R.id.iv_rob:
////                LordRightsModel model = (LordRightsModel) view.getTag();
////                ViewUtils.makeToast(context, position + "", 1000);
//                break;
//        }
    }
}
