package com.linglingyi.com.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.linglingyi.com.model.SchoolBusinessModel;
import com.linglingyi.com.utils.GlideUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/8/20
 */
public class SchoolBusinessAdapter extends BaseQuickAdapter<SchoolBusinessModel, SchoolBusinessAdapter.ViewHolder> {

    private Context context;

    public SchoolBusinessAdapter(Context context) {
        super(R.layout.item_school_business);
        this.context=context;
    }

    @Override
    protected void convert(ViewHolder helper, SchoolBusinessModel item) {
        helper.tvTitle.setText(item.getTitle());
        GlideUtils.loadImage(context,item.getImage(),helper.ivImg);
        helper.lyItem.setTag(item);
        helper.addOnClickListener(R.id.ly_item);
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv_img)
        ImageView ivImg;
        @BindView(R.id.ly_item)
        LinearLayout lyItem;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
