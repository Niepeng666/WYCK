package com.linglingyi.com.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.wuyouchuangke.com.R;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.linglingyi.com.model.ShoppingMalModel;
import com.linglingyi.com.utils.LogUtil;

import java.util.List;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/8/20
 */
public class ShoppingMalAdapter extends BaseQuickAdapter<ShoppingMalModel, BaseViewHolder> {
    private static final String TAG="ShoppingMalAdapter";
    public ShoppingMalAdapter(@Nullable List<ShoppingMalModel> data) {
        super(R.layout.item_shopping_mall,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShoppingMalModel item) {
        helper.setText(R.id.name,item.getName())
                .setText(R.id.price,item.getPrice());

        Glide.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.image));


    }

}
