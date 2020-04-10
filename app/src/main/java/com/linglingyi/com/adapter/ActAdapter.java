package com.linglingyi.com.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.linglingyi.com.model.ActModel;
import com.linglingyi.com.model.BindCard;
import com.linglingyi.com.utils.DateUtil;
import com.linglingyi.com.utils.GlideUtils;
import com.wuyouchuangke.com.R;

import java.util.Date;
import java.util.List;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/25
 */
public class ActAdapter extends BaseQuickAdapter<ActModel, BaseViewHolder> {
    public ActAdapter(@Nullable List<ActModel> data) {
        super(R.layout.item_act, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ActModel item) {
        GlideUtils.loadImage(mContext, item.getImageUrl(), (ImageView) helper.getView(R.id.iv_pic));
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_limit, item.getLevelName())
                .setText(R.id.tv_date, DateUtil.formatDateToHMS(item.getCreateTime().getTime()));
    }
}
