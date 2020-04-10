package com.linglingyi.com.viewone.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.linglingyi.com.activity.LookPlanActivity;
import com.linglingyi.com.adapter.PreviewDetailPlanAdapter;
import com.linglingyi.com.callback.CancelCallback;
import com.linglingyi.com.event.BankCardEvent;
import com.linglingyi.com.event.PlanCloseEvent;
import com.linglingyi.com.model.Area;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.BindCard;
import com.linglingyi.com.model.CardPlanList;
import com.linglingyi.com.model.IndustryModel;
import com.linglingyi.com.model.PlanModel;
import com.linglingyi.com.model.PreviewPlanModel;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.DateUtil;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.wuyouchuangke.com.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author dyx
 * @date on 2019/5/10
 * @describe
 */
public class PreviewPlanDialog extends DialogFragment {
    public Dialog loadingDialog;
    @BindView(R.id.lv_plan_detail)
    RecyclerView lvPlanDetail;
    Unbinder unbinder;
    private Context mContext;
    private PreviewPlanModel previewPlanModel;
    private List<CardPlanList> mCardPlanLists = new ArrayList<>();
    private PreviewDetailPlanAdapter adapter;
    private String merchantNo;
    private BindCard mBindCard;
    /**
     * 自选商家
     */
    private int randomMax = 0;
    private CancelCallback mCancelCallback;

    public PreviewPlanDialog() {
    }

    public void setCancelCallback(CancelCallback cancelCallback) {
        mCancelCallback = cancelCallback;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.dialog);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        unbinder.unbind();
    }

    public PreviewPlanDialog setData(PreviewPlanModel previewPlanModel) {
        this.previewPlanModel = previewPlanModel;
        return this;
    }

    public PreviewPlanDialog setBindCard(BindCard bindCard) {
        mBindCard = bindCard;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dlg_preview_plan, container);
        unbinder = ButterKnife.bind(this, view);

        initData();
        initView();
        return view;
    }

    private void initView() {
        lvPlanDetail.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new PreviewDetailPlanAdapter(mCardPlanLists, false);
        adapter.setShowCity(false);
        adapter.bindToRecyclerView(lvPlanDetail);
        merchantNo = StorageCustomerInfo02Util.getInfo("customerNum", mContext);
        loadingDialog = ViewUtils.createLoadingDialog(getActivity(), getString(R.string.loading_wait), false);
    }

    private void initData() {
        List<PlanModel> list = JSONArray.parseArray(previewPlanModel.getF57(), PlanModel.class);

        for (PlanModel model : list) {
            CardPlanList cardPlanList = new CardPlanList();
            cardPlanList.setPlanTime(DateUtil.parseDateToLong(model.getTime()));
            cardPlanList.setFromCard(String.valueOf(model.getCardNo()));
            cardPlanList.setToCard(model.getCardNo());
            cardPlanList.setFromBindId(model.getBindID());
            cardPlanList.setToBindId(model.getBindID());
            cardPlanList.setToMoney(CommonUtils.formatNewWithScale(String.valueOf(model.getMoney()), 2));
            cardPlanList.setFromMoney(CommonUtils.formatNewWithScale(String.valueOf(model.getMoney()), 2));
            cardPlanList.setType(model.getType());
            cardPlanList.setStatus(model.getStatus());
            cardPlanList.setGroupNum(String.valueOf(model.getGroupNum()));

            if (!previewPlanModel.isZhia()) {
                List<IndustryModel> industyList = JSONArray.parseArray(previewPlanModel.getIndustryJson(), IndustryModel.class);
                randomMax = industyList.size();
                Random random = new Random();
                //需要有落地商户
                if ("1".equals(previewPlanModel.getIsLuodi()) && !TextUtils.isEmpty(previewPlanModel.getIndustryJson()) && randomMax >= 1) {
                    int randomInt = random.nextInt(randomMax);
                    IndustryModel industy = industyList.get(randomInt);
                    Area industyArea = new Area();
                    if (industy != null) {
                        industyArea.setId(industy.getAcqMerchantNo());
                        industyArea.setDivisionName(industy.getAcqMerchantName());
                        HashMap<String, Area> diquRandom = new HashMap<>();
                        diquRandom.put("province", previewPlanModel.getArea().get("province"));
                        diquRandom.put("city", previewPlanModel.getArea().get("city"));
                        diquRandom.put("mer", industyArea);
                        if ("sale".equals(cardPlanList.getType()) && !"-1".equals(diquRandom.get("province").getId())) {
                            cardPlanList.setDiqu(diquRandom);
                        }
                    }
                }
            }
            mCardPlanLists.add(cardPlanList);
        }
    }

    @OnClick(R.id.bt_cancel_plan)
    void cancel() {
        // TODO: 2019/5/10 取消计划
        if (mCancelCallback != null) {
            mCancelCallback.cancel();
        }
        dismiss();
    }

    @OnClick(R.id.bt_submit_plan)
    void submit() {
        // TODO: 2019/5/10 提交计划
        submitYKPlan();
    }


    /**
     * 提交养卡计划
     */
    private void submitYKPlan() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "190210");
        httpParams.put("8", previewPlanModel.getRepayAmount());
        httpParams.put("9", previewPlanModel.getWorkingFund());
        httpParams.put("10", DateUtil.parseYMDToLong(previewPlanModel.getStartDate()));
        httpParams.put("11", DateUtil.parseYMDToLong(previewPlanModel.getEndDate()));
        httpParams.put("12", previewPlanModel.getFee());
        httpParams.put("13", previewPlanModel.getTimesFee());
        httpParams.put("14", previewPlanModel.getDayTimes());
        httpParams.put("16", previewPlanModel.isGround() ? "2" : "1");
        httpParams.put("42", merchantNo);
        httpParams.put("43", previewPlanModel.getAcqcode());
        httpParams.put("57", mCardPlanLists.toString());
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(mContext, BaseEntity.class) {
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
                    ViewUtils.makeToast(mContext, "提交成功", 500);
                    Intent intent = new Intent(mContext, LookPlanActivity.class);
                    intent.putExtra("bindCard", mBindCard);
                    startActivity(intent);
                    EventBus.getDefault().post(new PlanCloseEvent());
                    EventBus.getDefault().post(new BankCardEvent());
                    dismiss();
                }

            }
        });
    }
}