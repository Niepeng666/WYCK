package com.linglingyi.com.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.MyApplication;
import com.linglingyi.com.base.BaseActivity;
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
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.Utils;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/1
 */
public class PreviewPlanActivity extends BaseActivity {
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
    @BindView(R.id.tv_limit2)
    TextView tvLimit2;
    @BindView(R.id.tv_repay_cyc)
    TextView tvRepayCyc;
    @BindView(R.id.tv_day_times)
    TextView tvDayTimes;
    @BindView(R.id.tv_money_cyc)
    TextView tvMoneyCyc;
    @BindView(R.id.tv_fee)
    TextView tvFee;
    @BindView(R.id.tv_fee2)
    TextView tvFee2;
    @BindView(R.id.tv_zhouzhuanjin)
    TextView tvZhouzhuanjin;
    @BindView(R.id.diqu)
    TextView diqu;
    @BindView(R.id.tv_feeLossAmount)
    TextView tvFeeLossAmount;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.bt_plan_detail)
    Button btPlanDetail;
    @BindView(R.id.bt_changePlan)
    Button btChangePlan;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.ll_fee2)
    LinearLayout llFee2;
    @BindView(R.id.tv_workingFund_name)
    TextView tvWorkingFundName;
    @BindView(R.id.ll_workfound)
    LinearLayout llWorkfound;
    @BindView(R.id.ll_day_times)
    LinearLayout llDayTimes;
    @BindView(R.id.ll_money_cyc)
    LinearLayout llMoneyCyc;
    @BindView(R.id.ll_diqu)
    LinearLayout llDiqu;
    @BindView(R.id.rl_header)
    RelativeLayout rlHeader;
    @BindView(R.id.tv_channel_name)
    TextView tvChannelName;
    private String repayAmount, workingFund, fee, timesFee, dayTimes, acqcode;
    private boolean isGround;
    private Long startTimes, endTimes;
    private List<CardPlanList> mCardPlanLists = new ArrayList<>();
    private PreviewPlanModel mPreviewPlanModel;
    /**
     * 自选商家
     */
    private int randomMax = 0;
    /**
     * 银行卡信息
     */
    private BindCard mBindCard;

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
        return R.layout.act_preview_plan;
    }

    @Override
    public void initData() {
        tvTitle.setText("提交计划");
        mPreviewPlanModel = (PreviewPlanModel) getIntent().getSerializableExtra("previewModel");
        mBindCard = (BindCard) getIntent().getSerializableExtra("bindCard");
        List<PlanModel> list = JSONArray.parseArray(mPreviewPlanModel.getF57(), PlanModel.class);

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
            cardPlanList.setAcqCode(model.getAcqCode());

            //需要有落地商户
            if ("1".equals(mPreviewPlanModel.getIsLuodi())) {

                if (mPreviewPlanModel.isZhia() && !TextUtils.isEmpty(mPreviewPlanModel.getIndustryJson())) {
                    List<IndustryModel> industyList = JSONArray.parseArray(mPreviewPlanModel.getIndustryJson(), IndustryModel.class);
                    randomMax = industyList.size();
                    if (randomMax >= 1) {
                        Random random = new Random();
                        int randomInt = random.nextInt(randomMax);
                        IndustryModel industy = industyList.get(randomInt);
                        Area industyArea = new Area();
                        if (industy != null) {
                            industyArea.setId(industy.getAcqMerchantNo());
                            industyArea.setDivisionName(industy.getAcqMerchantName());
                            HashMap<String, Area> diquRandom = new HashMap<>();
                            diquRandom.put("province", mPreviewPlanModel.getArea().get("province"));
                            diquRandom.put("city", mPreviewPlanModel.getArea().get("city"));
                            diquRandom.put("mer", industyArea);
                            if ("pay".equals(cardPlanList.getType()) && !"-1".equals(diquRandom.get("province").getId())) {
                                cardPlanList.setDiqu(diquRandom);
                            }
                        }
                    }
                } else {
                    Area industyArea = new Area();
                    industyArea.setId(model.getCityIndustry());
                    industyArea.setDivisionName(model.getIndustryName());
                    HashMap<String, Area> diquRandom = new HashMap<>();
                    diquRandom.put("province", mPreviewPlanModel.getArea().get("province"));
                    diquRandom.put("city", mPreviewPlanModel.getArea().get("city"));
                    diquRandom.put("mer", industyArea);

                    if ("sale".equals(cardPlanList.getType()) && !"-1".equals(diquRandom.get("province").getId())) {
                        cardPlanList.setDiqu(diquRandom);
                    }
                }
            }
            mCardPlanLists.add(cardPlanList);
        }
        updateData();
    }

    /**
     * 刷新数据
     */
    private void updateData() {
        if (mPreviewPlanModel == null) {
            return;
        }
        repayAmount = mPreviewPlanModel.getRepayAmount();
        String startDate = mPreviewPlanModel.getStartDate();
        String endDate = mPreviewPlanModel.getEndDate();
        dayTimes = mPreviewPlanModel.getDayTimes();
        workingFund = mPreviewPlanModel.getWorkingFund();
        String totalFee = mPreviewPlanModel.getTotalFee();

        startTimes = DateUtil.parseYMDToLong(mPreviewPlanModel.getStartDate());
        endTimes = DateUtil.parseYMDToLong(mPreviewPlanModel.getEndDate());
        fee = mPreviewPlanModel.getFee();
        timesFee = mPreviewPlanModel.getTimesFee();
        dayTimes = mPreviewPlanModel.getDayTimes();
        acqcode = mPreviewPlanModel.getAcqcode();
        isGround = mPreviewPlanModel.isGround();

        tvLimit2.setText(repayAmount);
        tvRepayCyc.setText(startDate + "至" + endDate);
        tvChannelName.setText(mPreviewPlanModel.getChannelName());
        if (!mPreviewPlanModel.isZhia() && checkCustomIndustry()) {
            tvDayTimes.setText(dayTimes + "笔");
            tvMoneyCyc.setText(workingFund);
            if (mCardPlanLists.get(0).getDiqu() == null) {
                diqu.setText("无");
                llDiqu.setVisibility(View.GONE);
            } else {
                llDiqu.setVisibility(View.VISIBLE);
                String str = "";
                str += mCardPlanLists.get(0).getDiqu().get("province").getDivisionName() + "-" + mCardPlanLists.get(0).getDiqu().get("city").getDivisionName();
                diqu.setText(str);
            }
            tvWorkingFundName.setText("周转金总额:");
            tvZhouzhuanjin.setText("" + totalFee);
        } else {
            llDayTimes.setVisibility(View.GONE);
            llMoneyCyc.setVisibility(View.GONE);
//            llDiqu.setVisibility(View.GONE);
//            diqu.setText("无");
            if (mCardPlanLists.get(2).getDiqu() == null) {
                diqu.setText("无");
                llDiqu.setVisibility(View.GONE);
            } else {
                llDiqu.setVisibility(View.VISIBLE);
                String str = "";
                str += mCardPlanLists.get(2).getDiqu().get("province").getDivisionName() + "-" + mCardPlanLists.get(2).getDiqu().get("city").getDivisionName();
                diqu.setText(str);
            }
            tvWorkingFundName.setText("手续费小计:");
            tvZhouzhuanjin.setText(mPreviewPlanModel.getTotalServiceFee());

        }


        tvFee2.setText(mPreviewPlanModel.getTimesFee());
        llFee2.setVisibility(View.VISIBLE);
        tvFee.setText(mPreviewPlanModel.getFee());
        llWorkfound.setVisibility(View.VISIBLE);

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

    }

    /**
     * 判断是否需要自选商户
     *
     * @return
     */
    private boolean checkCustomIndustry() {
        return TextUtils.equals(mPreviewPlanModel.getIsLuodi(), "1") && TextUtils.equals(mPreviewPlanModel.getIsZiXuan(), "1");
    }

    /**
     * 提交计划后，自动关闭页面
     *
     * @param event
     */
    @Subscribe
    public void onEvent(PlanCloseEvent event) {
        ViewUtils.overridePendingTransitionBack(context);
    }

    @OnClick({R.id.iv_back, R.id.bt_plan_detail, R.id.bt_changePlan, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.bt_plan_detail:
                // : 2019/4/1 预览详情
                Intent intent = new Intent(context, PreviewDetailActivity.class);
                intent.putExtra("previewModel", mPreviewPlanModel);
                intent.putExtra("bindCard", mBindCard);
                intent.putExtra("cardPlanLists", JSON.toJSONString(mCardPlanLists));
                startActivity(intent);
                break;
            case R.id.bt_changePlan:
                // : 2019/4/1 修改计划
                finish();
                break;
            case R.id.btn_submit:
                // : 2019/4/1 提交计划
                if (mPreviewPlanModel.isZhia()) {
                    new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
                            .setTitle("提示")
                            .setMessage("我公司接入人民银行征信系统，网络小额贷款黑名单，支付宝黑名单等多种风控体系。公司将定期将不守信的用户上送各大征信平台。")
                            .setPositiveButton("我会守信", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    submitQYKPlan();
                                }
                            })
                            .setNegativeButton("我不会守信", null).show();
                } else {
                    submitYKPlan();
                }


                break;
        }
    }

    /**
     * 提交全额还计划
     */
    private void submitQYKPlan() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "390049");
        httpParams.put("8", repayAmount);
        httpParams.put("9", workingFund);
        httpParams.put("10", startTimes.toString());
        httpParams.put("11", endTimes.toString());
        httpParams.put("12", fee);
        httpParams.put("13", timesFee);
        httpParams.put("42", getMerNo());
        httpParams.put("44", mPreviewPlanModel.getChannelName());
        httpParams.put("43", acqcode);
        httpParams.put("57", mCardPlanLists.toString());
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
                    ViewUtils.makeToast(context, "提交成功", 500);
                    Intent intent = new Intent(context, LookPlanActivity.class);
                    intent.putExtra("bindCard", mBindCard);
                    startActivity(intent);
                    EventBus.getDefault().post(new BankCardEvent());
                    EventBus.getDefault().post(new PlanCloseEvent());
                }

            }
        });
    }

    /**
     * 提交养卡计划
     */
    private void submitYKPlan() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "190210");
        httpParams.put("8", repayAmount);
        httpParams.put("9", workingFund);
        httpParams.put("10", startTimes.toString());
        httpParams.put("11", endTimes.toString());
        httpParams.put("12", fee);
        httpParams.put("13", timesFee);
        httpParams.put("14", dayTimes);
        httpParams.put("16", isGround ? "2" : "1");
        httpParams.put("42", getMerNo());
        httpParams.put("44", mPreviewPlanModel.getChannelName());
        httpParams.put("43", acqcode);
        httpParams.put("57", mCardPlanLists.toString());
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
                    ViewUtils.makeToast(context, "提交成功", 500);
                    Intent intent = new Intent(context, LookPlanActivity.class);
                    intent.putExtra("bindCard", mBindCard);
                    startActivity(intent);
                    EventBus.getDefault().post(new BankCardEvent());
                    EventBus.getDefault().post(new PlanCloseEvent());
                }

            }
        });
    }
}
