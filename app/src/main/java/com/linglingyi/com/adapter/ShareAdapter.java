package com.linglingyi.com.adapter;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.linglingyi.com.model.ShareModel;
import com.linglingyi.com.utils.BitmapManage;
import com.linglingyi.com.utils.GlideUtils;
import com.wuyouchuangke.com.R;

import java.util.List;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/17
 */
public class ShareAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private Bitmap bitmap;
    public ShareAdapter(@Nullable List<String> data, Bitmap bitmap) {
        super(R.layout.item_share, data);
        this.bitmap=bitmap;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        GlideUtils.loadImage(mContext, item, (ImageView) helper.getView(R.id.iv_pic));
        helper.setImageBitmap(R.id.iv_qr_code,bitmap);
    }
}
