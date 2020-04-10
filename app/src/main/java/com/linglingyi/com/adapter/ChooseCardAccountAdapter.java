package com.linglingyi.com.adapter;

import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.model.ChannelBean;
import com.linglingyi.com.utils.StringUtil;

import java.util.List;

import butterknife.BindView;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/3/31
 */
public class ChooseCardAccountAdapter extends BaseQuickAdapter<ChannelBean.Channel, BaseViewHolder> {
    private boolean multiCards = false;

    public ChooseCardAccountAdapter(@Nullable List<ChannelBean.Channel> data) {
        super(R.layout.item_card_choose_account, data);
    }

    public void setMultiCards(boolean multiCards){
        this.multiCards = multiCards;
    }

    @Override
    protected void convert(BaseViewHolder helper, final ChannelBean.Channel item) {
        helper.setText(R.id.tv_name, item.getChannelName() + "(" + item.getAcqcode() + "）")
                .setText(R.id.tv_rate, item.getRate() + "")
                .setText(R.id.tv_single_limit, "单笔限额：" + item.getLimit())
                .setText(R.id.tv_bank_support, item.getNoBank())
                .setText(R.id.tv_trade_time, "交易时间：" + item.getT0date() + "," + item.getRemark())
        ;
        if (StringUtil.isEmpty(item.getQuota())) {
            helper.setGone(R.id.rl_tip, false);
        } else {
            helper.setGone(R.id.rl_tip, true);
            helper.setText(R.id.tv_tip, item.getQuota());
        }

        if ("未开通".equals(item.getStatus())) {
            helper.setGone(R.id.chb_select, false);
            helper.setVisible(R.id.btn_report, true);
        } else {
            int[] attrArray = {R.attr.theme_bg_color};
            TypedArray mTypedArray = mContext.obtainStyledAttributes(attrArray);
            helper.setTextColor(R.id.chb_select, item.isCheck() ? mTypedArray.getColor(0, 0xFF000000) : ContextCompat.getColor(mContext, R.color.gray));

            helper.setVisible(R.id.chb_select, true);
            helper.setGone(R.id.btn_report, false);
        }
        helper.addOnClickListener(R.id.chb_select);
        helper.addOnClickListener(R.id.btn_report);
        helper.addOnClickListener(R.id.tv_limit_des);

        if (multiCards){
            int[] attrArray = {R.attr.theme_bg_color};
            TypedArray mTypedArray = mContext.obtainStyledAttributes(attrArray);
            helper.setTextColor(R.id.chb_select, item.isCheck() ? mTypedArray.getColor(0, 0xFF000000) : ContextCompat.getColor(mContext, R.color.gray));

            helper.setVisible(R.id.chb_select, true);
            helper.setGone(R.id.btn_report, false);
        }
    }
}
