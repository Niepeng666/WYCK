package com.linglingyi.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.linglingyi.com.utils.LogUtils;
import com.wuyouchuangke.com.R;
import com.chad.library.adapter.base.BaseQuickAdapter;

import com.linglingyi.com.MyApplication;
import com.linglingyi.com.adapter.LookPlanAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.event.BankCardEvent;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.BindCard;
import com.linglingyi.com.model.PlanAllModel;
import com.linglingyi.com.model.PlanItem;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.DateUtil;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.Utils;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LookPlanActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.iv_bank_icon)
    ImageView ivBankIcon;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.ll_bank_name)
    LinearLayout llBankName;
    @BindView(R.id.tv_bank_account)
    TextView tvBankAccount;
    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.tv_limit)
    TextView tvLimit;
    @BindView(R.id.tv_billDay)
    TextView tvBillDay;
    @BindView(R.id.tv_payDay)
    TextView tvPayDay;
    @BindView(R.id.bind_item)
    LinearLayout bindItem;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.srl_refresh)
    SmartRefreshLayout srlRefresh;
    private BindCard mBindCard;
    private LookPlanAdapter mLookPlanAdapter;
    private List<PlanItem> mList = new ArrayList<>();
    private String bankAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_look_plan;
    }

    @Override
    public void initData() {
        tvTitle.setText("查看计划");
        mBindCard = (BindCard) getIntent().getSerializableExtra("bindCard");
        bankAccount = mBindCard.getBANK_ACCOUNT();
        String bankAccount = mBindCard.getBANK_ACCOUNT();
        if (!StringUtil.isEmpty(bankAccount) && bankAccount.length() > 4) {
            String bankNum1 = bankAccount.substring(0, 4);
            String bankNum2 = bankAccount.substring(bankAccount.length() - 4, bankAccount.length());
            tvBankAccount.setText(bankNum1 + " **** **** " + bankNum2);
        }
        tvBankName.setText(MyApplication.bankCodeList.get(mBindCard.getBANK_NAME()));
        Utils.initBankCodeColorIcon(mBindCard.getBANK_NAME(), ivBankIcon, this);
        tvLimit.setText(mBindCard.getLIMIT_MONEY());
        tvBillDay.setText(mBindCard.getBILL_DAY());
        tvPayDay.setText(mBindCard.getREPAYMENT_DAY() + "");
        String name = StorageCustomerInfo02Util.getInfo("bankAccountName", this);
        tvUserName.setText(name);

        rvList.setLayoutManager(new LinearLayoutManager(context));
        mLookPlanAdapter = new LookPlanAdapter(mList);
        mLookPlanAdapter.bindToRecyclerView(rvList);
        mLookPlanAdapter.setEmptyView(R.layout.layout_empty, srlRefresh);
        srlRefresh.setRefreshHeader(new ClassicsHeader(context));

        initListener();
//        loadData();
    }

    private void initListener() {
        srlRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mList.clear();
                loadData();
            }
        });
        mLookPlanAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(context, PlanDetailActivity.class);
                intent.putExtra("planItem", mList.get(position));
                intent.putExtra("bindCard", mBindCard);
                startActivity(intent);
            }
        });

        mLookPlanAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.btn_detail:
                        Intent intent = new Intent(context, PlanDetailActivity.class);
                        intent.putExtra("planItem", mList.get(position));
                        intent.putExtra("bindCard", mBindCard);
                        startActivity(intent);
                        break;
                    case R.id.btn_reset:
                        resetPlan(position);
                        break;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mList.clear();
        loadData();
    }

    /**
     * 重置计划
     *
     * @param position
     */
    private void resetPlan(int position) {
        loadingDialog.show();
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "390005");
        httpParams.put("43", mList.get(position).getPlanId());
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
                    srlRefresh.autoRefresh();
                }
            }


        });
    }

    /**
     * 获取大计划接口
     */
    private void loadData() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "190212");
        httpParams.put("42", getMerNo());
        httpParams.put("43", bankAccount);
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
                srlRefresh.finishRefresh();
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                srlRefresh.finishRefresh();
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    LogUtils.i("57=" + model.getStr57());
                    if (StringUtil.isEmpty(model.getStr57())) {
                        mLookPlanAdapter.setNewData(mList);
                        return;
                    }
                    // : 2019/4/2 数据返回太乱，需要做处理
                    List<PlanAllModel> list = JSONArray.parseArray(model.getStr57(), PlanAllModel.class);
                    for (PlanAllModel allmodel :
                            list) {
                        if (bankAccount.equals(allmodel.getBANK_ACCOUNT())) {
                            PlanItem planItem = new PlanItem();
                            planItem.setPrePayFee(CommonUtils.formatNewWithScale(String.valueOf(allmodel.getSALE_FREE()), 2).toString());
                            planItem.setFrozenAmount(CommonUtils.formatNewWithScale(String.valueOf(allmodel.getTHAW_TRX()), 2).toString());
                            planItem.setWorkingFund(String.valueOf(allmodel.getCB_AMT()));
                            planItem.setPayType("还款形式：" + allmodel.getEVERY_NUM() + "笔/日");
                            if (allmodel.getCREATE_TIME() != null) {
                                planItem.setCreateTime(DateUtil.formatDateToHMS(allmodel.getCREATE_TIME().getTime()));

                            }
                            if (allmodel.getSTART_TIME() != null && allmodel.getEND_TIME() != null) {
                                planItem.setPlanCycle(DateUtil.formateDateTOYMD(allmodel.getSTART_TIME().getTime()) + "至" + DateUtil.formateDateTOYMD(allmodel.getEND_TIME().getTime()));
                            }
                            planItem.setDeductFee(CommonUtils.formatNewWithScale(String.valueOf(allmodel.getFred()), 2).toString());
                            planItem.setDeductTimesFee(CommonUtils.formatNewWithScale(String.valueOf(allmodel.getNumed()), 2).toString());
                            planItem.setPaidAmountNow(allmodel.getPayed());
                            planItem.setShouldPayNow(String.valueOf(allmodel.getPLAN_AMT()));
                            planItem.setPlanId(allmodel.getID());
                            planItem.setPlanStatus(allmodel.getSTATUS());
                            planItem.setConsumed(CommonUtils.formatNewWithScale(String.valueOf(allmodel.getSaled()), 2).toString());
                            planItem.setType(allmodel.getTYPE());
                            int progress = CommonUtils.formatNewWithScale(allmodel.getPayed(), 0).intValue() * 100 /
                                    CommonUtils.formatNewWithScale(String.valueOf(allmodel.getPLAN_AMT()), 0).intValue();
//                            planItem.setPlanProgress((progress > 100 ? 100 : progress) + "%");
                            planItem.setACQ_NAME(allmodel.getACQ_NAME());
                            planItem.setDISCOUNTS_MONEY(allmodel.getDISCOUNTS_MONEY());
                            planItem.setPlanProgress(allmodel.getProgress());
                            planItem.setPreTimesAmount(CommonUtils.formatNewWithScale(String.valueOf(allmodel.getPAY_FREE()), 2).toString());
                            planItem.setLevel(allmodel.getLevel());
                            if (!mList.contains(planItem)) {
                                mList.add(planItem);
                            }
                        }
                    }
                    Collections.sort(mList);
                    mLookPlanAdapter.setNewData(mList);
                }
            }
        });
    }

    /**
     * @param event
     */
    @Subscribe
    public void onEvent(BankCardEvent event) {
        srlRefresh.autoRefresh();
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        ViewUtils.overridePendingTransitionBack(context);
    }
}
