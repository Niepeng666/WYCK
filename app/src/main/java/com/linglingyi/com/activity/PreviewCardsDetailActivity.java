package com.linglingyi.com.activity;

import android.content.Intent;
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
import com.linglingyi.com.adapter.PreviewCardsPlanDetailAdapter;
import com.linglingyi.com.adapter.PreviewDetailPlanAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.event.BankCardEvent;
import com.linglingyi.com.event.CardsPlanCloseEvent;
import com.linglingyi.com.event.PlanCloseEvent;
import com.linglingyi.com.model.Area;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.CardPlanList;
import com.linglingyi.com.model.CardsPlanModel;
import com.linglingyi.com.model.CardsPreviewPlanAllModel;
import com.linglingyi.com.model.PreviewPlanModel;
import com.linglingyi.com.utils.DateUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.viewone.dialog.PlanTotalPriceDialog;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description: 一卡多还预览详情
 * @date: 2019/4/1
 */
public class PreviewCardsDetailActivity extends BaseActivity {


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
    @BindView(R.id.tv_money_2)
    TextView tvMoney2;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;

    private List<CardsPlanModel> mList = new ArrayList<>();
    private PreviewCardsPlanDetailAdapter mPreviewCardsPlanDetailAdapter;
    private CardsPreviewPlanAllModel mCardsPreviewPlanAllModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.act_preview_cards_detail;
    }

    @Override
    public void initData() {
        tvTitle.setText("预览计划");
        mCardsPreviewPlanAllModel = (CardsPreviewPlanAllModel) getIntent().getSerializableExtra("detail");
        mList = mCardsPreviewPlanAllModel.getList();
        for (int i = 0; i < mList.size(); i++) {
            CardsPlanModel model = mList.get(i);
            if (i == 0) {
                // TODO: 2019/10/29 主卡显示手续费
                model.setSaleFee(mCardsPreviewPlanAllModel.getSaleFee());
                model.setNumFee(mCardsPreviewPlanAllModel.getNumFee());
                model.setTotalFee(mCardsPreviewPlanAllModel.getTotalFee());
                model.setCityId(mCardsPreviewPlanAllModel.getCityId());
                model.setTotalPrice(mCardsPreviewPlanAllModel.getTotalPrice());
                model.setExpend(true);
            }
            model.setAcqCode(mCardsPreviewPlanAllModel.getAcqCode());
            model.setChannelName(mCardsPreviewPlanAllModel.getChannelName());
            model.setPayTime(DateUtil.formatHMD(mCardsPreviewPlanAllModel.getStartDate()) + "至" + DateUtil.formatHMD(mCardsPreviewPlanAllModel.getEndDate()));
        }
        rvList.setLayoutManager(new LinearLayoutManager(context));
        mPreviewCardsPlanDetailAdapter = new PreviewCardsPlanDetailAdapter(mList, context);
        mPreviewCardsPlanDetailAdapter.bindToRecyclerView(rvList);

        tvTotalPrice.setText(mCardsPreviewPlanAllModel.getTotalPrice());
        initListener();
    }

    private void initListener() {
        mPreviewCardsPlanDetailAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_total_service_fee:
                        PreviewPlanModel previewPlanModel = new PreviewPlanModel();
                        previewPlanModel.setTotalServiceFee(mCardsPreviewPlanAllModel.getTotalFee());
                        previewPlanModel.setWorkingFund("");
                        previewPlanModel.setFee(mCardsPreviewPlanAllModel.getSaleFee());
                        previewPlanModel.setTimesFee(mCardsPreviewPlanAllModel.getNumFee());

                        PlanTotalPriceDialog planTotalPriceDialog = PlanTotalPriceDialog.getInstance(previewPlanModel, false);
                        planTotalPriceDialog.show(getSupportFragmentManager(), "totalfee");
                        break;
                    case R.id.ll_trangle_plan:
                        CardsPlanModel cardsPlanModel = (CardsPlanModel) adapter.getData().get(position);
                        cardsPlanModel.setExpend(!cardsPlanModel.isExpend());
                        adapter.notifyDataSetChanged();
                        break;
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
                // TODO: 2019/10/29 提交计划
                submitPlan();
                break;
        }
    }

    /**
     * 提交计划
     */
    private void submitPlan() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "393044");
        httpParams.put("8", mCardsPreviewPlanAllModel.getPayTotalPrice());
        httpParams.put("9", mCardsPreviewPlanAllModel.getTotalPrice());
        httpParams.put("10", mCardsPreviewPlanAllModel.getStartDate());
        httpParams.put("11", mCardsPreviewPlanAllModel.getEndDate());
        httpParams.put("12", mCardsPreviewPlanAllModel.getSaleFee());
        httpParams.put("13", mCardsPreviewPlanAllModel.getNumFee());
        httpParams.put("14", mCardsPreviewPlanAllModel.getDayNum());
        httpParams.put("42", getMerNo());
        httpParams.put("44", mCardsPreviewPlanAllModel.getChannelName());
        httpParams.put("43", mCardsPreviewPlanAllModel.getAcqCode());
        httpParams.put("57", JSONArray.parseArray(JSONObject.toJSONString(mCardsPreviewPlanAllModel.getList())).toJSONString());
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
                    // TODO: 2019/10/29  BACEB38D8E26427FB04F0622B44F2A24 大计划id
                    ViewUtils.makeToast(context, "提交成功", 500);
                    EventBus.getDefault().post(new CardsPlanCloseEvent());
                    EventBus.getDefault().post(new BankCardEvent());
                    goPlanDetail(model.getStr40());
                }

            }
        });
    }

    /**
     * 进入计划详情
     *
     * @param planId
     */
    private void goPlanDetail(String planId) {
        Intent intent = new Intent(context, CardsPlanDetailActivity.class);
        intent.putExtra("planId", planId);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 998 && resultCode == RESULT_OK) {
            String merchant = data.getStringExtra("merchant");
            int smallPlanPosition = data.getIntExtra("smallPlanPosition", 0);
            int bigPlanPosition = data.getIntExtra("bigPlanPosition", 0);
            List<CardsPlanModel> mData = mPreviewCardsPlanDetailAdapter.getData();
            mData.get(bigPlanPosition).getPlanItemList().get(smallPlanPosition).setIndustryName(merchant);
            mPreviewCardsPlanDetailAdapter.notifyItemChanged(bigPlanPosition);
        }

    }
}
