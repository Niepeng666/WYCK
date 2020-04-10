package com.linglingyi.com.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.linglingyi.com.utils.CommonUtils;
import com.wuyouchuangke.com.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.linglingyi.com.model.WithdrawModel;
import com.linglingyi.com.utils.DateUtil;

import java.util.List;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/4
 */
public class WithdrawListAdapter extends BaseQuickAdapter<WithdrawModel, BaseViewHolder> {
    public WithdrawListAdapter(@Nullable List<WithdrawModel> data) {
        super(R.layout.item_withdraw_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WithdrawModel item) {
//        helper.setText(R.id.tv_name, "收益提现");
        if (item.getCreateTime() != null) {
            helper.setText(R.id.tv_date_start, DateUtil.formatDateToHMS(item.getCreateTime().getTime()));
        }
        helper.setText(R.id.tv_money, "￥" + item.getTrxAmt());
        String status = item.getStatus();
        String statusName = "";
        switch (status) {
            case "10A":
                statusName = "提现失败";
                helper.setTextColor(R.id.tv_status, ContextCompat.getColor(mContext, R.color.red));
                break;
            case "10B":
                statusName = "审核中";
                helper.setTextColor(R.id.tv_status, ContextCompat.getColor(mContext, R.color.gray_888));
                break;
            case "10C":
                statusName = "提现成功";
                helper.setTextColor(R.id.tv_status, ContextCompat.getColor(mContext, R.color.background));
                break;
            case "10D":
                statusName = "提现失败";
                helper.setTextColor(R.id.tv_status, ContextCompat.getColor(mContext, R.color.red));
                break;
        }
        helper.setText(R.id.tv_status, statusName);

    }
}
