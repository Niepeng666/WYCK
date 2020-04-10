package com.linglingyi.com.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.model.PlanCardModel;
import com.linglingyi.com.utils.DateUtil;

import java.util.Date;
import java.util.List;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/10/25
 */
public class PlanCardsAdapter extends BaseQuickAdapter<PlanCardModel, BaseViewHolder> {
    public PlanCardsAdapter(@Nullable List<PlanCardModel> data) {
        super(R.layout.item_plan_cards, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PlanCardModel item) {
        helper.setText(R.id.tv_bank, item.getBank())
                .setText(R.id.tv_no, "批次号：" + item.getID().substring(0, 10))
                .setText(R.id.tv_date, DateUtil.formatDateToHMS(item.getCREATE_TIME().getTime()));
        switch (item.getSTATUS()) {
            case "10A":
                helper.setText(R.id.tv_status, "待执行");
                helper.setTextColor(R.id.fcv_status,ContextCompat.getColor(mContext,R.color.orange_fb8d1c));
                break;
            case "10B":
                helper.setText(R.id.tv_status, "执行中");
                helper.setTextColor(R.id.fcv_status,ContextCompat.getColor(mContext,R.color.darkgreen));
                break;
            case "10D":
                helper.setText(R.id.tv_status, "已暂停");
                helper.setTextColor(R.id.fcv_status,ContextCompat.getColor(mContext,R.color.red));
                break;
            case "10C":
                helper.setText(R.id.tv_status, "已失败");
                helper.setTextColor(R.id.fcv_status,ContextCompat.getColor(mContext,R.color.red));
                break;
            case "10E":
                helper.setText(R.id.tv_status, "已完成");
                helper.setTextColor(R.id.fcv_status,ContextCompat.getColor(mContext,R.color.background));
                break;
        }
        helper.addOnClickListener(R.id.tv_delete);
        helper.addOnClickListener(R.id.ll_plan);
    }
}
