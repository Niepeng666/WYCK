package com.linglingyi.com.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.linglingyi.com.model.ApplyRecordEntity;
import com.wuyouchuangke.com.R;

import java.util.List;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/30
 */
public class ApplyRecordAdapter extends BaseQuickAdapter<ApplyRecordEntity,BaseViewHolder> {
    public ApplyRecordAdapter(@Nullable List<ApplyRecordEntity> data) {
        super(R.layout.item_apply_record, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, ApplyRecordEntity item) {
        helper.setText(R.id.tv_name, item.getProductName());
        helper.setText(R.id.tv_date, item.getCreateTime());
        String status="";
        switch (item.getStatus()) {
            case "10B":
                status="已通过";
                break;
            case "70A":
                status="审核拒绝";
                break;
            default:
                status="审核中";
                break;
        }
        helper.setText(R.id.tv_status,status);
    }
}
