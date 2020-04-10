package com.linglingyi.com.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.wuyouchuangke.com.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.linglingyi.com.model.RecordListModel;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.GlideUtils;
import com.linglingyi.com.utils.StringUtil;

import java.util.List;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/8/20
 */
public class RecordListAdapter extends BaseQuickAdapter<RecordListModel, BaseViewHolder> {
    private String type;

    public RecordListAdapter(@Nullable List<RecordListModel> data) {
        super(R.layout.item_record_list, data);

    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, RecordListModel item) {
        int position = helper.getAdapterPosition();
        if (position == 0) {
            helper.setGone(R.id.cl_no_1, true);
            helper.setGone(R.id.cl_no, false);
            Log.e("zzz",item.getHEAD_URL());
                GlideUtils.loadAvatar(mContext, item.getHEAD_URL(), (ImageView) helper.getView(R.id.iv_pic_1));
            helper.setText(R.id.tv_phone_1, CommonUtils.translateShortNumber(item.getPHONE(), 3, 4));
            helper.setText(R.id.tv_number_1, "10A".equals(type) ?  CommonUtils.formatNewWithScale(item.getCount().toString(),0) + "" : item.getCount() + "");
        } else {
            helper.setGone(R.id.cl_no_1, false);
            helper.setGone(R.id.cl_no, true);
            if (position == 1 || position == 2) {
                helper.setGone(R.id.tv_no, false);
                helper.setGone(R.id.iv_no, true);
                helper.setGone(R.id.iv_no_4, false);
                helper.setGone(R.id.cl_no_2, true);
                helper.setImageResource(R.id.iv_huangguan_2, position == 1 ? R.drawable.record_2_icon : R.drawable.record_3_icon);
                helper.setImageResource(R.id.iv_number_2, position == 1 ? R.drawable.record_no_2 : R.drawable.record_no_3);
                helper.setImageResource(R.id.iv_no, position == 1 ? R.drawable.record_2 : R.drawable.record_3);
                helper.setBackgroundRes(R.id.iv_no_small, position == 1 ? R.drawable.shape_strike_ring_2_circle : R.drawable.shape_strike_tong_2_circle);
            } else {
                helper.setGone(R.id.iv_no_4, true);
                helper.setGone(R.id.cl_no_2, false);
                helper.setGone(R.id.tv_no, true);
                helper.setGone(R.id.iv_no, false);
                helper.setText(R.id.tv_no, "NO." + (position + 1));
//                helper.setImageResource(R.id.iv_no_small,R.drawable.record_pic);
            }

            GlideUtils.loadAvatar(mContext, item.getHEAD_URL(), (ImageView) helper.getView(R.id.iv_no_small));
            GlideUtils.loadAvatar(mContext, item.getHEAD_URL(), (ImageView) helper.getView(R.id.iv_no_4));
//            Glide.with(mContext).load(item.getImage_url()).into((ImageView) helper.getView(R.id.iv_pic_1));
//            Glide.with(mContext).load(item.getImage_url()).into((ImageView) helper.getView(R.id.iv_no_small));
//            Glide.with(mContext).load(item.getImage_url()).into((ImageView) helper.getView(R.id.iv_no_4));
            if (!StringUtil.isEmpty(item.getPHONE())) {
                helper.setText(R.id.tv_phone, CommonUtils.translateShortNumber(item.getPHONE(), 3, 4));
            }
            helper.setText(R.id.tv_number, "10A".equals(type) ?  CommonUtils.formatNewWithScale(item.getCount().toString(),0) + "" : item.getCount() + "");

        }
        if ("10A".equals(type)) {
            helper.setText(R.id.tv_level_1_type_title, "团队VIP");
            helper.setText(R.id.tv_level_1_type_title_2, "人");
            helper.setText(R.id.tv_type_title, "团队VIP");
            helper.setText(R.id.tv_type_title_2, "人");
        } else {
            helper.setText(R.id.tv_level_1_type_title, "刷卡金额");
            helper.setText(R.id.tv_level_1_type_title_2, "元");
            helper.setText(R.id.tv_type_title, "刷卡金额");
            helper.setText(R.id.tv_type_title_2, "元");
        }
//        switch (item.getLevel()){
//            case "1":
////                helper.setGone(R.id.iv_level_1,true);
////                helper.setGone(R.id.iv_level_icon,true);
//                helper.setImageResource(R.id.iv_level_1,R.drawable.record_level_1);
//                helper.setImageResource(R.id.iv_level_icon,R.drawable.record_level_1);
//                break;
//            case "2":
////                helper.setGone(R.id.iv_level_1,true);
////                helper.setGone(R.id.iv_level_icon,true);
//                helper.setImageResource(R.id.iv_level_1,R.drawable.record_level_2);
//                helper.setImageResource(R.id.iv_level_icon,R.drawable.record_level_2);
//                break;
//            case "3":
////                helper.setGone(R.id.iv_level_1,true);
////                helper.setGone(R.id.iv_level_icon,true);
//                helper.setImageResource(R.id.iv_level_1,R.drawable.record_level_3);
//                helper.setImageResource(R.id.iv_level_icon,R.drawable.record_level_3);
//                break;
//            case "4":
//                helper.setImageResource(R.id.iv_level_1,R.drawable.record_level_4);
//                helper.setImageResource(R.id.iv_level_icon,R.drawable.record_level_4);
////                helper.setGone(R.id.iv_level_1,false);
////                helper.setGone(R.id.iv_level_icon,false);
//                break;
//            case "5":
//                helper.setImageResource(R.id.iv_level_1,R.drawable.record_level_5);
//                helper.setImageResource(R.id.iv_level_icon,R.drawable.record_level_5);
//
//                break;
//            case "6":
////                helper.setGone(R.id.iv_level_1,false);
////                helper.setGone(R.id.iv_level_icon,false);
//                helper.setImageResource(R.id.iv_level_1,R.drawable.record_level_6);
//                helper.setImageResource(R.id.iv_level_icon,R.drawable.record_level_6);
//                break;
//
//        }
    }
}