package com.linglingyi.com.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.adapter.WithdrawListAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.WithdrawModel;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WithdrawListActivity extends BaseActivity {


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
    private WithdrawListAdapter mWithdrawListAdapter;
    private List<WithdrawModel> mList = new ArrayList<>();
    private int pageIndex = 1;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.act_recycler_view;
    }

    @Override
    public void initData() {
        tvTitle.setText("提现记录");
        type = getIntent().getStringExtra("type");
        mWithdrawListAdapter = new WithdrawListAdapter(mList);
        rvList.setLayoutManager(new LinearLayoutManager(context));
        rvList.addItemDecoration(new DividerItemDecoration(context, OrientationHelper.VERTICAL));
        mWithdrawListAdapter.bindToRecyclerView(rvList);
        mWithdrawListAdapter.setEmptyView(R.layout.layout_empty, rvList);
        loadData();
    }

    private void loadData() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "190125");
        httpParams.put("42", getMerNo());
        if ("2".equals(type)) {
            httpParams.put("43", "10L");
        }
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
                    List<WithdrawModel> list = JSONArray.parseArray(model.getStr57(), WithdrawModel.class);
                    mList.addAll(list);
                    mWithdrawListAdapter.setNewData(mList);
                }
            }
        });
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        ViewUtils.overridePendingTransitionBack(context);
    }
}
