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
public class QueryNewAdapter extends BaseQuickAdapter<QueryModel, QueryNewAdapter.MyViewHolder> {
    private List<QueryModel> mList;

    public QueryNewAdapter(@Nullable List<QueryModel> data) {
        super(R.layout.item_trade_list, data);
        mList = data;
    }


    @Override
    protected void convert(MyViewHolder helper, QueryModel item) {
        int position = helper.getAdapterPosition();
        LogUtils.i("position=" + position);
        helper.tvDate.setText(item.getCompleteTimeString());
        helper.tvName.setText(item.getTradeTypeName());
        helper.tvMoney.setText(item.getTrxAmt() + "");
    }

    class MyViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_detail)
        TextView tvDetail;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
