package com.linglingyi.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.adapter.LordRightsMyAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.BenefitModel;
import com.linglingyi.com.utils.FastJsonUtil;
import com.linglingyi.com.utils.GlideUtils;
import com.linglingyi.com.utils.LogUtil;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
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
 * @创建日期 2019/10/17 16:49
 * @功能 领主-我的
 **/
public class LordRightsMyActivity extends BaseActivity implements OnRefreshListener, OnLoadmoreListener,
        BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    private LordRightsMyAdapter lordRightsMyAdapter;
    private List<BenefitModel> list = new ArrayList<>();
    private int page = 1, allPage = 1;
    private String withdrawMoney;

    @Override
    public int initLayout() {
        return R.layout.activity_lord_rights_my;
    }

    @Override
    public void initData() {
        tvTitle.setText("我的");
//        String head = StorageCustomerInfo02Util.getInfo("headImage", context);
//        LogUtils.i("head"+head);
//        GlideUtils.loadAvatar(context, head, ivHead);

        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(context));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(context));
        smartRefreshLayout.setOnRefreshListener(this);
        smartRefreshLayout.setOnLoadmoreListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        lordRightsMyAdapter = new LordRightsMyAdapter(list);
        lordRightsMyAdapter.setOnItemChildClickListener(this);
        lordRightsMyAdapter.bindToRecyclerView(recyclerView);
        lordRightsMyAdapter.setEmptyView(R.layout.layout_empty, recyclerView);

        smartRefreshLayout.autoRefresh();
    }

    @OnClick({R.id.iv_back, R.id.tv_withdrawal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_withdrawal:
                if (loadingDialog.isShowing()) {
                    return;
                }

                startActivity(new Intent(context, WithdrawalActivity.class).putExtra("money", withdrawMoney)
                        .putExtra("type", "2"));
                break;
        }
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
//        for (int i = 0; i < 2; i++) {
//            list.add(new LordRightsMyModel());
//        }
//        lordRightsMyAdapter.notifyDataSetChanged();
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "393006");
        httpParams.put("42", getMerNo());
        httpParams.put("43", page + "");
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
                    List<BenefitModel> lordRightsModels = FastJsonUtil.toList(model.getStr57(), BenefitModel.class);
                    list.addAll(lordRightsModels);
                    lordRightsMyAdapter.setNewData(list);
                    GlideUtils.loadAvatar(context, model.getStr33(), ivHead);
                    withdrawMoney = model.getStr37();
                    tvName.setText(model.getStr36());
                    tvBalance.setText("余额：" + model.getStr37());
                    tvLevel.setText(model.getStr35());

                }
            }
        });
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        list.clear();
        refreshlayout.resetNoMoreData();
        requestData(true);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.cl_item:
                BenefitModel myModel = (BenefitModel) view.getTag();
                Intent intent = new Intent(context, BenefitDetailActivity.class);
                intent.putExtra("detail", myModel);
                startActivity(intent);
                break;
        }
    }
}
