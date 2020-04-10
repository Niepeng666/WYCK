package com.linglingyi.com.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.wuyouchuangke.com.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.linglingyi.com.model.CardHonorModel;
import com.linglingyi.com.model.ScoreHistoryModel;
import com.linglingyi.com.utils.CommonUtils;

import java.util.List;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/3
 */
public class CardHonorHistoyListAdapter extends BaseQuickAdapter<CardHonorModel, BaseViewHolder> {
    public CardHonorHistoyListAdapter(@Nullable List<CardHonorModel> data) {
        super(R.layout.item_honor_history, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CardHonorModel item) {
        helper.setText(R.id.tv_name, item.getNote())
                .setText(R.id.tv_status, "10B".equals(item.getStatus()) ? "受理成功" : "未受理");
        helper.setTextColor(R.id.tv_status, ContextCompat.getColor(mContext, "10B".equals(item.getStatus()) ? R.color.darkgreen : R.color.red));
        helper.setText(R.id.tv_phone, CommonUtils.translateShortNumber(item.getTrack2(), 3, 4));
        helper.setText(R.id.tv_time, item.getCreateTime());
    }
}
