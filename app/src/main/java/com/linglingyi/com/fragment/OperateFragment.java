package com.linglingyi.com.fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.activity.OperateDetailActivity;
import com.linglingyi.com.adapter.OperateAdapter;
import com.linglingyi.com.base.BaseFragment;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.OperateModel;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/9/2
 */
public class OperateFragment extends BaseFragment {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tl_tab)
    TabLayout tlTab;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private OperateAdapter operateAdapter = null;
    private List<OperateModel> list = new ArrayList<>();

    public static OperateFragment newInstance() {
        return new OperateFragment();
    }

    @Override
    public int initLayout() {
        return R.layout.act_new_guide;
    }

    @Override
    public void initData(View v) {
        ivBack.setVisibility(View.GONE);
        tvTitle.setText("新手指引");

        operateAdapter = new OperateAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        operateAdapter.bindToRecyclerView(recyclerView);
        initListener();
        getData(0);
    }

    private void initListener() {
        operateAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                OperateModel model = (OperateModel) view.getTag();
                startActivity(new Intent(context, OperateDetailActivity.class).putExtra("model", model));
            }
        });

        tlTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                getData(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void getData(int position) {
        list.clear();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "792001");
        httpParams.put("21", position == 0 ? "10A" : "10B");
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
                    list = JSONArray.parseArray(model.getStr57(), OperateModel.class);
                    operateAdapter.setNewData(list);
                }
            }
        });
    }
}
