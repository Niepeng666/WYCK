package com.linglingyi.com.adapter;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.model.CardsBigPlanModel;
import com.linglingyi.com.model.CardsSmallPlanModel;
import com.linglingyi.com.utils.DateUtil;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/10/28
 */
public class CardsPlanDetailAdapter extends BaseQuickAdapter<CardsSmallPlanModel, CardsPlanDetailAdapter.MyViewHolder> {

    private CardsBigPlanModel mCardsBigPlanModel;

    public CardsPlanDetailAdapter(@Nullable List<CardsSmallPlanModel> data) {
        super(R.layout.item_cards_plan_detail, data);
    }

    public void setCardsBigPlanModel(CardsBigPlanModel cardsBigPlanModel) {
        mCardsBigPlanModel = cardsBigPlanModel;
    }

    @Override
    protected void convert(final MyViewHolder helper, final CardsSmallPlanModel item) {
        String bankAccount = item.getBankAccount();
        if (!StringUtil.isEmpty(bankAccount) && bankAccount.length() > 4) {
            String bankNum1 = bankAccount.substring(0, 4);
            String bankNum2 = bankAccount.substring(bankAccount.length() - 4, bankAccount.length());
            helper.tvBankAccount.setText(bankNum1 + " **** **** " + bankNum2);
        }
        helper.tvBankName.setText(item.getBankName());
        Utils.initBankCodeColorIcon(item.getBankCode(), helper.ivBankIcon, mContext);
        helper.tvRepayCycle.setText(DateUtil.formateDateTOYMD(item.getStartTime().getTime()) + "至" + DateUtil.formateDateTOYMD(item.getEndTime().getTime()));
//
        if (helper.getAdapterPosition() == 0) {
            if (mCardsBigPlanModel != null) {
                helper.tvChannelName.setText("通道名称：" + mCardsBigPlanModel.getACQ_NAME());
                helper.tvPayType.setText("还款形式：" + mCardsBigPlanModel.getEVERY_NUM() + "笔/日");
                helper.tvPayFee.setText("手续费：" + mCardsBigPlanModel.getSALE_FREE() + "");
                helper.tvTimesFee.setText("笔数费：" + mCardsBigPlanModel.getPAY_FREE() + "");
                helper.tvDecreaseMoney.setText("自用减免：" + mCardsBigPlanModel.getDISCOUNTS_MONEY() + "");
                helper.tvTotalPrice.setText("预留金总金额：" + mCardsBigPlanModel.getCB_AMT() + "");
                String content = "<html><font color=\"#808080\">您当前为" +
                        "</font><font color=\"#FF0000\">" + mCardsBigPlanModel.getLevel() + "</font><font color=\"#808080\">, </font>" +
                        "<font color=\"#808080\">自用</font></font><font color=\"#FF0000\">" + "可省" + "</font>" +
                        "<font color=\"#808080\">手续费￥" + mCardsBigPlanModel.getDISCOUNTS_MONEY() + "</font></html>";
                helper.tvLevelFee.setText(fromHtml(content));
            }
        }
        helper.tvPreRepayAmount.setText("还款金额：" + item.getPaymentMoney() + "");
        helper.tvPayedAmount.setText("已还金额：" + item.getSuccessMoney() + "");
        switch (item.getStatus()) {
            case "10A":
                helper.tvOrderStatus.setText("待执行");

                break;
            case "10B":
                helper.tvOrderStatus.setText("执行中");
                break;
            case "10D":
                helper.tvOrderStatus.setText("已暂停");
                break;
            case "10C":
                helper.tvOrderStatus.setText("已失败");
                break;
            case "10E":
                helper.tvOrderStatus.setText("已完成");
                break;
        }
        if (helper.getAdapterPosition() != 0) {
            helper.llMainInfo.setVisibility(View.GONE);
        }

        if (item.isExpend()) {
            helper.tvTranglePlan.setText("收起计划");
            helper.ivTranglePlan.setImageResource(R.drawable.arrow_up);
            helper.rvList.setVisibility(View.VISIBLE);
        } else {
            helper.tvTranglePlan.setText("查看计划");
            helper.ivTranglePlan.setImageResource(R.drawable.arrow_down);
            helper.rvList.setVisibility(View.GONE);
        }
        helper.addOnClickListener(R.id.ll_trangle_plan);


        CardsPlanDetailSmallPlanAdapter previewCardsPlanDetailSmallPlanAdapter = new CardsPlanDetailSmallPlanAdapter(item.getItemList(), item.getStatus());
        helper.rvList.setLayoutManager(new LinearLayoutManager(mContext));
        previewCardsPlanDetailSmallPlanAdapter.bindToRecyclerView(helper.rvList);
    }

    public Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }

    class MyViewHolder extends BaseViewHolder {
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
        @BindView(R.id.tv_preRepayAmount)
        TextView tvPreRepayAmount;
        @BindView(R.id.tv_payed_amount)
        TextView tvPayedAmount;
        @BindView(R.id.tv_orderStatus)
        TextView tvOrderStatus;
        @BindView(R.id.tv_channel_name)
        TextView tvChannelName;
        @BindView(R.id.tv_payType)
        TextView tvPayType;
        @BindView(R.id.tv_total_price)
        TextView tvTotalPrice;
        @BindView(R.id.tv_pay_fee)
        TextView tvPayFee;
        @BindView(R.id.tv_times_fee)
        TextView tvTimesFee;
        @BindView(R.id.tv_decrease_money)
        TextView tvDecreaseMoney;
        @BindView(R.id.tv_level_fee)
        TextView tvLevelFee;
        @BindView(R.id.ll_main_info)
        LinearLayout llMainInfo;
        @BindView(R.id.tv_trangle_plan)
        TextView tvTranglePlan;
        @BindView(R.id.iv_trangle_plan)
        ImageView ivTranglePlan;
        @BindView(R.id.ll_trangle_plan)
        LinearLayout llTranglePlan;
        @BindView(R.id.rv_list)
        RecyclerView rvList;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
