package com.linglingyi.com.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.wuyouchuangke.com.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.linglingyi.com.model.ChannelBean;
import com.linglingyi.com.utils.StringUtil;

import java.util.List;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/3/31
 */
public class ChooseAccountAdapter extends BaseQuickAdapter<ChannelBean.Channel, BaseViewHolder> {
    private boolean isPay;

    public ChooseAccountAdapter(@Nullable List<ChannelBean.Channel> data, boolean isPay) {
        super(R.layout.item_choose_account, data);
        this.isPay = isPay;
    }

    @Override
    protected void convert(BaseViewHolder helper, final ChannelBean.Channel item) {
        helper.setText(R.id.tv_name, item.getChannelName())
                .setText(R.id.tv_rate, "费率：" + item.getRate() + "%")
                .setText(R.id.tv_fee_single, "单笔限额：" + item.getLimit());
        String[] strings = item.getRemark().split(",");
        if (isPay) {
            if (strings.length >= 1) {
                helper.setText(R.id.tv_limit_single, strings[0]);
            }
            if (strings.length >= 2) {
                helper.setText(R.id.tv_tip_second, "结算：" + strings[1]);
            }

        } else {
            if (strings.length >= 2) {
                helper.setText(R.id.tv_limit_single, strings[0]);
                helper.setText(R.id.tv_tip_second, strings[1]);
            } else {
                helper.setText(R.id.tv_limit_single, strings[0]);
            }
            helper.setGone(R.id.tv_tip_second, false);
        }
        if (StringUtil.isEmpty(item.getQuota())) {
            helper.setGone(R.id.tv_tip, false);
        } else {
            helper.setGone(R.id.tv_tip, true);
            helper.setText(R.id.tv_tip, item.getQuota());
        }
        helper.setText(R.id.tv_trade_time, "交易时间：" + item.getT0date());

    }
}
