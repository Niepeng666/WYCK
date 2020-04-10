package com.linglingyi.com.adapter;

import android.support.annotation.Nullable;

import com.wuyouchuangke.com.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.linglingyi.com.model.AddressModel;

import java.util.List;

public class AddressAdapter extends BaseQuickAdapter<AddressModel, BaseViewHolder> {

    public AddressAdapter(@Nullable List<AddressModel> data) {
        super(R.layout.item_address, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressModel item) {
        helper.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_phone, item.getPhone())
                .setText(R.id.tv_address, item.getAddress());
        if ("1".equals(item.getStatus())) {
            helper.setVisible(R.id.tv_default, true);
        } else {
            helper.setGone(R.id.tv_default, false);
        }
        helper.addOnClickListener(R.id.tv_edit);
        helper.addOnClickListener(R.id.tv_delete);
    }
}
