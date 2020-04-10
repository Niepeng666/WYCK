package com.linglingyi.com.fragment;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.linglingyi.com.activity.ActDetailActivity;
import com.linglingyi.com.adapter.ActAdapter;
import com.linglingyi.com.base.BaseFragment;
import com.linglingyi.com.model.ActModel;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wuyouchuangke.com.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/3/28
 */
public class ActFragment extends BaseFragment {


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
    @BindView(R.id.srl_refresh)
    SmartRefreshLayout srlRefresh;
    Unbinder unbinder;
    private ActAdapter mActAdapter;
    private List<ActModel> mList = new ArrayList<>();
    private int pageIndex = 1, count;

    public static ActFragment newInstance() {
        return new ActFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public int initLayout() {
        return R.layout.frag_act;
    }

    @Override
    public void initData(View v) {
        tvTitle.setText("资讯");
        ivBack.setVisibility(View.GONE);
        mActAdapter = new ActAdapter(mList);
        rvList.setLayoutManager(new LinearLayoutManager(context));
        mActAdapter.bindToRecyclerView(rvList);
        srlRefresh.setRefreshHeader(new ClassicsHeader(context));

        loadData();
        initListener();
    }

    /**
     * 分页，点击事件，刷新事件
     */
    private void initListener() {
        mActAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (count > 0 && count > pageIndex) {
                    pageIndex++;
                    loadData();
                } else {
                    mActAdapter.loadMoreEnd();
                }
            }
        }, rvList);
        mActAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                getShareUrl(position);

            }
        });
        srlRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshData();
            }
        });
    }

    /**
     * 获取资讯分享链接
     *
     * @param position
     */
    private void getShareUrl(final int position) {
        loadingDialog.show();
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "390009");
        httpParams.put("42", getMerNo());
        httpParams.put("43", mList.get(position).getId());
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
                    ActModel actModel = mList.get(position);
                    actModel.setShareUrl(model.getStr38());
                    goActDetail(actModel);
                }
            }
        });
    }

    /**
     * 进入资讯详情
     */
    private void goActDetail(ActModel actModel) {
        Intent intent = new Intent(context, ActDetailActivity.class);
        intent.putExtra("actDetail", actModel);
        startActivity(intent);
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
     * 获取资讯列表
     */
    private void loadData() {
        loadingDialog.show();
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "390008");
        httpParams.put("10", pageIndex);
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
                mActAdapter.loadMoreComplete();
                srlRefresh.finishRefresh();
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                mActAdapter.loadMoreComplete();
                srlRefresh.finishRefresh();
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    count = StringUtil.stringToInt(model.getStr12());
                    List<ActModel> list = JSONArray.parseArray(model.getStr57(), ActModel.class);
                    mList.addAll(list);
                    if (pageIndex == 1 && mList.size() == 0) {
                        mActAdapter.setEmptyView(R.layout.layout_empty, rvList);
                    }
                    mActAdapter.setNewData(mList);
                }
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
//        if (!hidden) {
//            refreshData();
//        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
