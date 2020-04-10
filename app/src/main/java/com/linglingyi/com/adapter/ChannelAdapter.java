package com.linglingyi.com.adapter;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/2
 */

import android.support.annotation.Nullable;

import com.wuyouchuangke.com.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.linglingyi.com.model.ChannelBean;

import java.util.List;

/**
 * recycleview 适配器
 */
public class ChannelAdapter extends BaseQuickAdapter<ChannelBean.Channel, BaseViewHolder> {

    public ChannelAdapter(@Nullable List<ChannelBean.Channel> data) {
        super(R.layout.item_channel, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChannelBean.Channel item) {
        helper.setText(R.id.tv_name, item.getChannelName());
    }
}