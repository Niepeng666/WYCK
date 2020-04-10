package com.linglingyi.com.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.linglingyi.com.model.ApplyCreditEntity;
import com.linglingyi.com.utils.GlideUtils;
import com.wuyouchuangke.com.R;

import java.util.List;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/30
 */
public class CreditAdapter extends BaseQuickAdapter<ApplyCreditEntity, BaseViewHolder> {

    public CreditAdapter(@Nullable List<ApplyCreditEntity> data) {
        super(R.layout.item_loan, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ApplyCreditEntity item) {
        GlideUtils.loadImage(mContext, item.getIconPath(),
                (ImageView) helper.getView(R.id.iv_pic));
        helper.setText(R.id.tv_name, item.getName());
    }

}