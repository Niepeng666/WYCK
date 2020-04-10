package com.linglingyi.com.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.linglingyi.com.utils.DateUtil;
import com.wuyouchuangke.com.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.linglingyi.com.model.NoticeModel;

import java.util.List;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/9
 */
public class NoticeListAdapter extends BaseQuickAdapter<NoticeModel, BaseViewHolder> {
    public NoticeListAdapter(@Nullable List<NoticeModel> data) {
        super(R.layout.item_notice_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoticeModel item) {
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_content, item.getContent());
        if (item.getEffectiveFromTime() != null) {
            helper.setText(R.id.tv_date, DateUtil.formatDateToHM(item.getEffectiveFromTime().getTime()));
        }
        int isRead = item.getHasRead();
        if (isRead == 0) {
            helper.setVisible(R.id.no_read, true);
        } else {
            helper.setVisible(R.id.no_read, false);
        }
    }
}
