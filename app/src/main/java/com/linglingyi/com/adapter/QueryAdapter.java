package com.linglingyi.com.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.linglingyi.com.model.QueryModel;
import com.linglingyi.com.utils.LogUtils;
import com.wuyouchuangke.com.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/5/10
 */
public class QueryAdapter extends BaseQuickAdapter<QueryModel, QueryAdapter.MyViewHolder> {
    private List<QueryModel> mList;

    public QueryAdapter(@Nullable List<QueryModel> data) {
        super(R.layout.running_water_item, data);
        mList = data;
    }

    @Override
    protected void convert(MyViewHolder helper, QueryModel item) {
//        String tixianStatus = "";
//        int position = helper.getAdapterPosition();
//        LogUtils.i("position=" + position);
//        helper.tvTradeTime.setText(item.getCreateTime().substring(0, 10));
//        String tradeType1 = item.getTradeType();
////        if ("消费撤销".equals(tradeTypeDes)) {
////            helper.tvTradeType.setTextColor(ContextCompat.getColor(mContext, R.color.red));
////        } else {
////            helper.tvTradeType.setTextColor(ContextCompat.getColor(mContext, R.color.black));
////        }
////
//        String tradeType;
//        if ("WK".equals(tradeType1)) {
//            tradeType = "银联快捷";
//        } else if ("HK".equals(tradeType1)) {
//            tradeType = "代还还款";
//        } else if ("WXPAY".equals(tradeType1)) {
//            tradeType = "微信";
//        } else if ("QHK".equals(tradeType1)) {
//            tradeType = "全额还款";
//        } else if ("QYK".equals(tradeType1)) {
//            tradeType = "全额代付";
//        } else if ("QYKFEE".equals(tradeType1)) {
//            tradeType = "全额还手续费";
//        } else if ("YK".equals(tradeType1)) {
//            tradeType = "代还消费";
//        } else if ("ZFB".equals(tradeType1)) {
//            tradeType = "支付宝";
//        } else {
//            tradeType = "未知交易";
//        }
//        helper.tvTradeType.setText(tradeType);
//        String payStatus = item.getStatus();
//        if ("70A".equals(payStatus)) {
//            tixianStatus = "交易失败";
//            helper.tradestatus.setTextColor(Color.BLACK);
//        } else if ("10B".equals(payStatus)) {
//            tixianStatus = "交易成功";
//            helper.tradestatus.setTextColor(Color.GREEN);
//        } else if ("10C".equals(payStatus)) {
//            tixianStatus = "交易成功";
//            helper.tradestatus.setTextColor(ContextCompat.getColor(mContext, R.color.green));
//        } else if ("10D".equals(payStatus)) {
//            tixianStatus = "交易成功";
//            helper.tradestatus.setTextColor(Color.BLACK);
//
//        } else {
//            helper.tradestatus.setTextColor(Color.BLACK);
//        }
//
//        if (TextUtils.isEmpty(payStatus)) {
//            helper.tradestatus.setText(item.getStatus());
//        } else {
//            helper.tradestatus.setText(tixianStatus);
//        }
//
////        String tradeMoney = item.getTrxAmt();
////        if (tradeMoney.contains("-")) {
////            tradeMoney = tradeMoney.replace("-", "");
////        }
//        helper.tvMoney.setText(String.valueOf(item.getTrxAmt()) + "元");
//        if (position == 0) {
//            helper.llMonth.setVisibility(View.VISIBLE);
//            helper.tvMonth.setText(item.getCreateTime().substring(5, 7).replace("-", "") + "月");
//        } else if (position > 0) {
//            if (!mList.get(position).getCreateTime().substring(5, 7).equals(mList.get(position - 1).getCreateTime().substring(5, 7))) {
//                helper.llMonth.setVisibility(View.VISIBLE);
//                helper.tvMonth.setText(item.getCreateTime().substring(5, 7).replace("-", "") + "月");
//            } else {
//                helper.llMonth.setVisibility(View.GONE);
//            }
//        }
    }

    class MyViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_month)
        TextView tvMonth;
        @BindView(R.id.ll_month)
        LinearLayout llMonth;
        @BindView(R.id.tv_trade_time)
        TextView tvTradeTime;
        @BindView(R.id.tv_trade_type)
        TextView tvTradeType;
        @BindView(R.id.tradestatus)
        TextView tradestatus;
        @BindView(R.id.tv_money)
        TextView tvMoney;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
