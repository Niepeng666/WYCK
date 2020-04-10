package com.linglingyi.com.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.linglingyi.com.adapter.ApplyRecordAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.ApplyRecordEntity;
import com.linglingyi.com.utils.ViewUtils;
import com.wuyouchuangke.com.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/30
 */
public class ApplyRecordActivity extends BaseActivity {
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
    private String type;
    private ApplyRecordAdapter mApplyRecordAdapter;
    private List<ApplyRecordEntity> mList = new ArrayList<>();
    @Override
    public int initLayout() {
        return R.layout.act_recycler_view;
    }

    @Override
    public void initData() {
        tvTitle.setText("申请记录");
        type = getIntent().getStringExtra("type");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rvList.setLayoutManager(linearLayoutManager);
        mApplyRecordAdapter = new ApplyRecordAdapter(mList);
        mApplyRecordAdapter.bindToRecyclerView(rvList);
        mApplyRecordAdapter.setEmptyView(R.layout.layout_empty,rvList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        ViewUtils.overridePendingTransitionBack(context);
    }
}
