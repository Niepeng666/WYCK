package com.linglingyi.com.adapter;

import android.support.annotation.Nullable;

import com.wuyouchuangke.com.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.linglingyi.com.model.Area;

import java.util.List;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/1
 */
public class AreaAdapter extends BaseQuickAdapter<Area, BaseViewHolder> {
    public AreaAdapter(@Nullable List<Area> data) {
        super(android.R.layout.simple_list_item_1, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Area item) {
        helper.setText(android.R.id.text1, item.getDivisionName());
    }
}
