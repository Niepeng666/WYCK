package com.linglingyi.com.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.adapter.CardsPlanDetailAdapter;
import com.linglingyi.com.adapter.PreviewCardsPlanDetailAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.event.BankCardEvent;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.CardsBigPlanModel;
import com.linglingyi.com.model.CardsPlanModel;
import com.linglingyi.com.model.CardsSmallPlanModel;
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
 * @date: 2019/10/29
 */
public class CardsPlanDetailActivity extends BaseActivity {
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
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;

    private List<CardsSmallPlanModel> mList = new ArrayList<>();
    private CardsPlanDetailAdapter mCardsPlanDetailAdapter;
    private String planId;
    private CardsBigPlanModel mCardsBigPlanModel = new CardsBigPlanModel();
    private String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    public int initLayout() {
        return R.layout.act_cards_plan_detail;
    }

    @Override
    public void initData() {
        tvTitle.setText("计划详情");

        planId = getIntent().getStringExtra("planId");
        status = getIntent().getStringExtra("status");

        if ("10C".equals(status) || "10D".equals(status)) {
//            取消/暂停状态
            btnSubmit.setText("启动计划");
            btnSubmit.setBackgroundResource(R.drawable.shape_solid_background_corner_5);
        } else {
            btnSubmit.setText("取消计划");
            btnSubmit.setBackgroundResource(R.drawable.shape_solid_gray_light_corner_5);
        }
        rvList.setLayoutManager(new LinearLayoutManager(context));
        mCardsPlanDetailAdapter = new CardsPlanDetailAdapter(mList);
        mCardsPlanDetailAdapter.bindToRecyclerView(rvList);
        loadPlanDetail();
        initListener();
    }

    private void initListener() {
        mCardsPlanDetailAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_trangle_plan:
                        CardsSmallPlanModel cardsPlanModel = (CardsSmallPlanModel) adapter.getData().get(position);
                        cardsPlanModel.setExpend(!cardsPlanModel.isExpend());
                        adapter.notifyDataSetChanged();
                        break;
                }
            }
        });
    }

    /**
     * 获取计划详情
     */
    private void loadPlanDetail() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "393045");
        httpParams.put("41", planId);
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
                    // TODO: 2019/10/29
                    mCardsBigPlanModel = JSONObject.parseObject(model.getStr45(), CardsBigPlanModel.class);
                    List<CardsSmallPlanModel> list = JSONArray.parseArray(model.getStr57(), CardsSmallPlanModel.class);
                    mList.addAll(list);
                    if (mList.size() > 0) {
                        mList.get(0).setExpend(true);
                    }

                    mCardsPlanDetailAdapter.setCardsBigPlanModel(mCardsBigPlanModel);
                    mCardsPlanDetailAdapter.setNewData(mList);
                }

            }
        });
    }

    @OnClick({R.id.iv_back, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.btn_submit:
                // TODO: 2019/10/29
                cancelPlanRequest();
                break;
        }
    }

    /**
     * 启动/取消计划
     */
    private void cancelPlanRequest() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "190214");
        httpParams.put("7", "10C".equals(status)||"10D".equals(status) ? "1" : "0");
        httpParams.put("8", planId);
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    if ("10C".equals(status)||"10D".equals(status)) {
                        ViewUtils.makeToast(context, "启用成功", 500);
                        btnSubmit.setText("取消计划");
                        btnSubmit.setBackgroundResource(R.drawable.shape_solid_gray_light_corner_5);
                        btnSubmit.setTextColor(Color.WHITE);
                        status = "10B";
                    } else {
                        ViewUtils.makeToast(context, "取消成功", 500);
                        btnSubmit.setText("启动计划");
                        btnSubmit.setBackgroundResource(R.drawable.shape_solid_background_corner_5);
                        btnSubmit.setTextColor(Color.WHITE);
                        status = "10D";
                    }
                    EventBus.getDefault().post(new BankCardEvent());
                    refreshData();
                }
            }

            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
            }
        });
    }

    private void refreshData() {
        mList.clear();
        loadPlanDetail();
    }
}
