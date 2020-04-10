package com.linglingyi.com.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.activity.ChoiceMerchantActivity;
import com.linglingyi.com.model.CardsPlanModel;
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
public class PreviewCardsPlanDetailAdapter extends BaseQuickAdapter<CardsPlanModel, PreviewCardsPlanDetailAdapter.MyViewHolder> {



    private Activity mActivity;

    public PreviewCardsPlanDetailAdapter(@Nullable List<CardsPlanModel> data, Activity activity) {
        super(R.layout.item_preview_cards_detail, data);
        mActivity = activity;
    }

    @Override
    protected void convert(final MyViewHolder helper, final CardsPlanModel item) {
        String bankAccount = item.getBankAccount();
        if (!StringUtil.isEmpty(bankAccount) && bankAccount.length() > 4) {
            String bankNum1 = bankAccount.substring(0, 4);
            String bankNum2 = bankAccount.substring(bankAccount.length() - 4, bankAccount.length());
            helper.tvBankAccount.setText(bankNum1 + " **** **** " + bankNum2);
        }
        helper.tvBankName.setText(item.getBankName());
        Utils.initBankCodeColorIcon(item.getBankCode(), helper.ivBankIcon, mContext);
        helper.tvRepayCycle.setText(item.getPayTime());
        helper.tvChannelName.setText(item.getChannelName());
        if (helper.getAdapterPosition() == 0) {
            helper.clPrice1.setVisibility(View.VISIBLE);
            helper.llPrice2.setVisibility(View.GONE);
            helper.tvLimit.setText(item.getDebtMoney() + "");
            helper.tvTotalPrice.setText(item.getTotalPrice());
            helper.tvTotalServiceFee.setText(item.getTotalFee());
            helper.addOnClickListener(R.id.tv_total_service_fee);
        } else {
            helper.tvLimit2.setText(item.getDebtMoney() + "");
            helper.clPrice1.setVisibility(View.GONE);
            helper.llPrice2.setVisibility(View.VISIBLE);
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

        PreviewCardsPlanDetailSmallPlanAdapter previewCardsPlanDetailSmallPlanAdapter = new PreviewCardsPlanDetailSmallPlanAdapter(item.getPlanItemList());
        helper.rvList.setLayoutManager(new LinearLayoutManager(mContext));
        previewCardsPlanDetailSmallPlanAdapter.bindToRecyclerView(helper.rvList);
        previewCardsPlanDetailSmallPlanAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.ll_industry) {
                    Intent intent = new Intent(mActivity, ChoiceMerchantActivity.class);
                    intent.putExtra("cityId", item.getCityId());
                    intent.putExtra("bankId", item.getBankId());
                    intent.putExtra("acqCode", item.getAcqCode());
                    intent.putExtra("smallPosition", position);
                    intent.putExtra("bigPosition", helper.getAdapterPosition());
                    mActivity.startActivityForResult(intent, 998);
                }
            }
        });
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
        @BindView(R.id.cl_price_1)
        ConstraintLayout clPrice1;
        @BindView(R.id.tv_limit_2)
        TextView tvLimit2;
        @BindView(R.id.ll_price_2)
        LinearLayout llPrice2;
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
