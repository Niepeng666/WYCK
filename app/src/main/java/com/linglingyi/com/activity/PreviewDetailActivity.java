package com.linglingyi.com.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.linglingyi.com.MyApplication;
import com.linglingyi.com.adapter.PreviewDetailPlanAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.event.BankCardEvent;
import com.linglingyi.com.event.PlanCloseEvent;
import com.linglingyi.com.model.Area;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.BindCard;
import com.linglingyi.com.model.CardPlanList;
import com.linglingyi.com.model.PreviewPlanModel;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.DateUtil;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.Utils;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.viewone.dialog.PlanTotalPriceDialog;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.wuyouchuangke.com.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/1
 */
public class PreviewDetailActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.rl_header)
    RelativeLayout rlHeader;
    @BindView(R.id.iv_bank_icon)
    ImageView ivBankIcon;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.ll_bank_name)
    LinearLayout llBankName;
    @BindView(R.id.tv_pay_title)
    TextView tvPayTitle;
    @BindView(R.id.tv_bank_account)
    TextView tvBankAccount;
    @BindView(R.id.tv_repayCycle)
    TextView tvRepayCycle;
    @BindView(R.id.bind_item)
    LinearLayout bindItem;
    @BindView(R.id.tv_channel_name)
    TextView tvChannelName;
    @BindView(R.id.tv_limit)
    TextView tvLimit;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.ll_total_price)
    LinearLayout llTotalPrice;
    @BindView(R.id.tv_total_service_fee)
    TextView tvTotalServiceFee;
    @BindView(R.id.ll_total_service_fee)
    LinearLayout llTotalServiceFee;
    @BindView(R.id.lv_plan_detail)
    RecyclerView lvPlanDetail;
    @BindView(R.id.bt_submit_plan)
    Button btSubmitPlan;
    private PreviewPlanModel mPreviewPlanModel;
    private List<CardPlanList> mList = new ArrayList<>();
    private PreviewDetailPlanAdapter adapter;
    private BindCard mBindCard;
    private int mPosition;
    /**
     * true 全额还 false养卡
     */
    private boolean zhia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.act_preview_detail;
    }

    @Override
    public void initData() {
        tvTitle.setText("预览计划");
        mPreviewPlanModel = (PreviewPlanModel) getIntent().getSerializableExtra("previewModel");
        String cardPlanLists = getIntent().getStringExtra("cardPlanLists");
        mList = JSONArray.parseArray(cardPlanLists, CardPlanList.class);
        mBindCard = (BindCard) getIntent().getSerializableExtra("bindCard");

        zhia = mPreviewPlanModel.isZhia();

        lvPlanDetail.setLayoutManager(new LinearLayoutManager(context));
        adapter = new PreviewDetailPlanAdapter(mList, zhia);
        adapter.bindToRecyclerView(lvPlanDetail);

        tvLimit.setText(mPreviewPlanModel.getRepayAmount());
        if (!zhia) {
            tvTotalPrice.setText(mPreviewPlanModel.getTotalFee());
            tvTotalServiceFee.setText(mPreviewPlanModel.getTotalServiceFee());
        } else {
            llTotalPrice.setVisibility(View.INVISIBLE);
            tvTotalServiceFee.setText(mPreviewPlanModel.getTotalServiceFee());
        }

        String bankAccount = mBindCard.getBANK_ACCOUNT();
        if (!StringUtil.isEmpty(bankAccount) && bankAccount.length() > 4) {
            String bankNum1 = bankAccount.substring(0, 4);
            String bankNum2 = bankAccount.substring(bankAccount.length() - 4, bankAccount.length());
            tvBankAccount.setText(bankNum1 + " **** **** " + bankNum2);
        }
        Utils.initBankCodeColorIcon(mBindCard.getBANK_NAME(), ivBankIcon, this);
        tvBankName.setText(MyApplication.bankCodeList.get(mBindCard.getBANK_NAME()));
        tvChannelName.setText(mPreviewPlanModel.getChannelName());
        tvRepayCycle.setText(mPreviewPlanModel.getStartDate() + "至" + mPreviewPlanModel.getEndDate());

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                CardPlanList plan = mList.get(position);
                switch (view.getId()) {
                    case R.id.ll_area:
                        if (zhia) {
                            Intent areaIntent = new Intent(context, ChoiceAreaActivity.class);
                            areaIntent.putExtra("bindid", mBindCard.getID());
                            areaIntent.putExtra("acqCode", mPreviewPlanModel.getAcqcode());
                            startActivityForResult(areaIntent, 997);
                        }
                        break;
                    case R.id.industry:
                        Intent intent = new Intent(context, ChoiceAreaActivity.class);
                        intent.putExtra("onlyMer", true);
                        intent.putExtra("area", plan.getDiqu());
                        intent.putExtra("bindid", mBindCard.getID());
                        intent.putExtra("acqCode", zhia ? mPreviewPlanModel.getAcqcode() : plan.getAcqCode());
                        startActivityForResult(intent, 998);
                        break;
                    default:
                        break;
                }
                mPosition = position;
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 998 && resultCode == RESULT_OK) {
            HashMap<String, Area> newMer = (HashMap<String, Area>) data.getSerializableExtra("data");
            List<CardPlanList> mData = adapter.getData();
            mData.get(mPosition).setDiqu(newMer);
            TextView tv = (TextView) adapter.getViewByPosition(mPosition, R.id.industry);
            tv.setText(newMer.get("mer").getDivisionName());
            adapter.notifyItemChanged(mPosition);
        }
        if (requestCode == 997 && resultCode == RESULT_OK) {
            HashMap<String, Area> newArea = (HashMap<String, Area>) data.getSerializableExtra("data");
            List<CardPlanList> mData = adapter.getData();
            mData.get(mPosition).setDiqu(newArea);
            TextView area = (TextView) adapter.getViewByPosition(mPosition, R.id.tv_area);
            TextView industry = (TextView) adapter.getViewByPosition(mPosition, R.id.industry);
            industry.setText(newArea.get("mer").getDivisionName());
            area.setText(newArea.get("province").getDivisionName() + "-" + newArea.get("city").getDivisionName());
            adapter.notifyItemChanged(mPosition);
        }
    }

    @OnClick({R.id.iv_back, R.id.bt_submit_plan, R.id.tv_total_service_fee})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.showChoseDialog(context, true, "返回将不保存修改信息", View.VISIBLE, new ViewUtils.OnChoseDialogClickCallback() {
                    @Override
                    public void clickOk() {
                        finish();
                    }

                    @Override
                    public void clickCancel() {

                    }
                });
                break;
            case R.id.bt_submit_plan:
// : 2019/4/1 提交计划
                if (CommonUtils.isFastDoubleClick2())
                    return;
                if (zhia) {
                    new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
                            .setTitle("提示")
                            .setMessage("我公司接入人民银行征信系统，网络小额贷款黑名单，支付宝黑名单等多种风控体系。公司将定期将不守信的用户上送各大征信平台。")
                            .setPositiveButton("我会守信", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // : 2019/4/1 全额还
                                    submitQYKPlan();
                                }
                            })
                            .setNegativeButton("我不会守信", null).show();
                } else {
                    // : 2019/4/1 养卡
                    submitYKPlan();
                }
                break;
            case R.id.tv_total_service_fee:
                PlanTotalPriceDialog planTotalPriceDialog = PlanTotalPriceDialog.getInstance(mPreviewPlanModel, zhia ? false : true);
                planTotalPriceDialog.show(getSupportFragmentManager(), "totalfee");
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
        httpParams.put("8", mPreviewPlanModel.getRepayAmount());
        httpParams.put("10", DateUtil.parseYMDToLong(mPreviewPlanModel.getStartDate()));
        httpParams.put("11", DateUtil.parseYMDToLong(mPreviewPlanModel.getEndDate()));
        httpParams.put("12", mPreviewPlanModel.getFee());
        httpParams.put("13", mPreviewPlanModel.getTimesFee());
        httpParams.put("42", getMerNo());
        httpParams.put("44", mPreviewPlanModel.getChannelName());
        httpParams.put("43", mPreviewPlanModel.getAcqcode());
        httpParams.put("57", mList.toString());
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
                    EventBus.getDefault().post(new PlanCloseEvent());
                    EventBus.getDefault().post(new BankCardEvent());
                    finish();
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
        httpParams.put("8", mPreviewPlanModel.getRepayAmount());
        httpParams.put("9", mPreviewPlanModel.getWorkingFund());
        httpParams.put("10", DateUtil.parseYMDToLong(mPreviewPlanModel.getStartDate()));
        httpParams.put("11", DateUtil.parseYMDToLong(mPreviewPlanModel.getEndDate()));
        httpParams.put("12", mPreviewPlanModel.getFee());
        httpParams.put("13", mPreviewPlanModel.getTimesFee());
        httpParams.put("14", mPreviewPlanModel.getDayTimes());
        httpParams.put("16", mPreviewPlanModel.isGround() ? "2" : "1");
        httpParams.put("42", getMerNo());
        httpParams.put("44", mPreviewPlanModel.getChannelName());
        httpParams.put("43", mPreviewPlanModel.getAcqcode());
        httpParams.put("57", mList.toString());
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
                    EventBus.getDefault().post(new PlanCloseEvent());
                    EventBus.getDefault().post(new BankCardEvent());
                    finish();
                }

            }
        });
    }
}
