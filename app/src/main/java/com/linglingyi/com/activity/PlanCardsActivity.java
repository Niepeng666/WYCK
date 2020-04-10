package com.linglingyi.com.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.adapter.ManagerCreditAdapter;
import com.linglingyi.com.adapter.PlanCardsAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.event.BankCardEvent;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.BindCard;
import com.linglingyi.com.model.PlanCardModel;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/10/25
 */
public class PlanCardsActivity extends BaseActivity {
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
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    private PlanCardsAdapter mPlanCardsAdapter;
    private List<PlanCardModel> mList = new ArrayList<>();
    private String type;

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
        return R.layout.act_plan_cards;
    }

    @Override
    public void initData() {
        tvTitle.setText("一卡多还");
        tvRight.setText("添加计划");
        tvRight.setVisibility(View.VISIBLE);
        EventBus.getDefault().register(this);
        mPlanCardsAdapter = new PlanCardsAdapter(mList);
        rvList.setLayoutManager(new LinearLayoutManager(context));

        mPlanCardsAdapter.bindToRecyclerView(rvList);
        mPlanCardsAdapter.addFooterView(getFooterView());
        requestData();
        initListener();
    }

    private void initListener() {
        tlTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        type = "";
                        break;
                    case 1:
                        type = "10A";
                        break;
                    case 2:
                        type = "10B";
                        break;
                    case 3:
                        type = "10E";
                        break;
                }
                mList.clear();
                requestData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mPlanCardsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_delete:
                        showDeleteDialog(mList.get(position));
                        break;
                    case R.id.ll_plan:
                        goPlanDetail(position);
                        break;
                }
            }
        });
    }

    private void showDeleteDialog(final PlanCardModel planCardModel) {
        new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage("是否删除计划，计划删除将无法恢复")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!"10C".equals(planCardModel.getSTATUS())) {
                            ViewUtils.makeToast(context, "请先取消计划再删除！", 1000);
                            return;
                        }
                        deletePlan(planCardModel);
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    /**
     * 删除计划
     *
     * @param planCardModel
     */
    private void deletePlan(PlanCardModel planCardModel) {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "390002");
        httpParams.put("8", planCardModel.getID());
        httpParams.put("42", getMerNo());
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
                    ViewUtils.makeToast(context, "删除成功", 1500);
                    refreshData();
                }
            }


        });
    }

    private void refreshData() {
        mList.clear();
        requestData();
    }

    /**
     * 进入计划详情页
     *
     * @param position
     */
    private void goPlanDetail(int position) {
        Intent intent = new Intent(context, CardsPlanDetailActivity.class);
        intent.putExtra("planId", mList.get(position).getID());
        intent.putExtra("status", mList.get(position).getSTATUS());
        startActivity(intent);
    }

    private View getFooterView() {
        TextView tvAdd = new TextView(context);
        tvAdd.setText("添加计划");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 120);
        params.setMargins(50, 50, 50, 0);
        tvAdd.setGravity(Gravity.CENTER);
        tvAdd.setBackgroundColor(Color.WHITE);
        tvAdd.setLayoutParams(params);
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// TODO: 2019/10/25 添加计划
                startActivity(new Intent(context, MakeCardsActivity.class));
            }
        });
        return tvAdd;
    }

    /**
     * 获取信用卡列表
     */
    private void requestData() {
        loadingDialog.show();
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "393046");
        if (!StringUtil.isEmpty(type)) {
            httpParams.put("43", type);
        }
        httpParams.put("42", getMerNo());
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
                    mList = JSONArray.parseArray(model.getStr57(), PlanCardModel.class);
                    mPlanCardsAdapter.setNewData(mList);
                }
            }
        });
    }

    /**
     * @param event
     */
    @Subscribe
    public void onEvent(BankCardEvent event) {
        type = "";
        refreshData();
    }

    @OnClick({R.id.iv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.tv_right:
                // TODO: 2019/10/25 添加计划
                startActivity(new Intent(context, MakeCardsActivity.class));
                break;
        }
    }
}
