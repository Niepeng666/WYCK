package com.linglingyi.com.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.model.OperateModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @作者 chenlanxin
 * @创建日期 2019/10/14 15:34
 * @功能
 **/
public class OperateAdapter extends BaseQuickAdapter<OperateModel, OperateAdapter.ViewHolder> {


    public OperateAdapter() {
        super(R.layout.item_operate);
    }

    @Override
    protected void convert(ViewHolder helper, OperateModel item) {
        helper.tvTitle.setText(item.getTitle());
        helper.itemView.setTag(item);
    }

    public class ViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
