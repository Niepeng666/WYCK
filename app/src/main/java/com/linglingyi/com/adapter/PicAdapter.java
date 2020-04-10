package com.linglingyi.com.adapter;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.Property;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.linglingyi.com.event.OperateModel;
import com.linglingyi.com.utils.BitmapManage;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.Constant;
import com.linglingyi.com.utils.GlideUtils;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.viewone.ForeGroundImageview;
import com.wuyouchuangke.com.R;

import java.util.List;

import zxing.encoding.EncodingUtils;


public class PicAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private int checkedPosi = 0;

    public PicAdapter(@Nullable List<String> list) {
        super(R.layout.item_image, list);
    }


    public void setCheckedPosi(int checkedPosi) {
        this.checkedPosi = checkedPosi;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(final BaseViewHolder helper, String item) {
        LogUtils.i("item" + item);

//        GlideUtils.loadImage(mContext, item, (ImageView) helper.getView(R.id.img));
//        String shareUrl = Constant.BASE_URL + "/lly-posp-proxy/toAPPRegister.app?phone=" + phone + "&product="+Constant.product_name;
//        final Bitmap qrBit = EncodingUtils.createQRCode(shareUrl, CommonUtils.dp2px(mContext, 50), CommonUtils.dp2px(mContext, 50), null);
//        Glide.with(mContext).load(item).asBitmap().into(new SimpleTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                helper.setImageBitmap(R.id.img,resource);
//                helper.setImageBitmap(R.id.iv_qr_code, qrBit);
//            }
//        });
//        Glide.with(mContext).load(item).asBitmap().dontAnimate().override(720, 1280).into(new SimpleTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//
//                helper.setImageBitmap(R.id.img, resource);
//
//            }
//        });
        helper.addOnClickListener(R.id.img);
        GlideUtils.loadImage(mContext, item, (ImageView) helper.getView(R.id.img));
//        Glide.with(mContext).load(item).diskCacheStrategy(DiskCacheStrategy.NONE).dontAnimate().into((ImageView) helper.getView(R.id.img));
    }
}
