package com.linglingyi.com.adapter;

import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.model.BenefitModel;
import com.linglingyi.com.utils.DateUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @作者 chenlanxin
 * @创建日期 2019/10/17 17:45
 * @功能
 **/
public class LordRightsMyAdapter extends BaseQuickAdapter<BenefitModel, LordRightsMyAdapter.ViewHolder> {
    private List<BenefitModel> mList;

    public LordRightsMyAdapter(@Nullable List<BenefitModel> data) {
        super(R.layout.item_lord_rights_my, data);
        mList = data;
    }

    @Override
    protected void convert(ViewHolder helper, BenefitModel item) {
        helper.clItem.setTag(item);
        helper.addOnClickListener(R.id.cl_item);

        helper.tvType.setText(item.getMoneyType());
        helper.tvTime.setText(item.getCreateTime());
        helper.tvMoney.setText(item.getTrxAmt());

        String date = DateUtil.formatHM3(item.getCreateTime());
        if (helper.getAdapterPosition() == 0) {
            helper.tvDay.setVisibility(View.VISIBLE);
            helper.tvDay.setText(date);
        } else if (helper.getAdapterPosition() > 0) {
            int position = helper.getAdapterPosition() - 1;
            String yesterDate = DateUtil.formatHM3(mList.get(position).getCreateTime());
            if (!date.equals(yesterDate)) {
                helper.tvDay.setVisibility(View.VISIBLE);
                helper.tvDay.setText(date);
            } else {
                helper.tvDay.setVisibility(View.GONE);
            }
        }
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_day)
        TextView tvDay;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.cl_item)
        ConstraintLayout clItem;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
