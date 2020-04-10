package com.linglingyi.com.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.linglingyi.com.model.MyOrderModel;
import com.linglingyi.com.model.OrderModel;
import com.linglingyi.com.utils.DateUtil;
import com.linglingyi.com.utils.GlideUtils;
import com.linglingyi.com.utils.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ${tags}
 *
 * @Title: ${enclosing_method}
 * @author:wujun
 */
public class MyOrderAdapter extends BaseQuickAdapter<OrderModel, BaseViewHolder> {
    public MyOrderAdapter(@Nullable List<OrderModel> data) {
        super(R.layout.item_order, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, OrderModel item) {
        switch (item.getStatus()) {//10j是什么状态
            case "10A"://待支付 显示前往支付
                helper.setText(R.id.tv_status, "待支付");
                helper.setText(R.id.btnSubmit, "付款");
                helper.setVisible(R.id.btnSubmit, true);
                break;
            case "10B"://支付中
                helper.setText(R.id.tv_status, "支付中");
                helper.setGone(R.id.btnSubmit, false);
                break;
            case "10C"://待发货
                helper.setText(R.id.tv_status, "待发货");
                helper.setGone(R.id.btnSubmit, false);
//                helper.setText(R.id.btnSubmit, "取消订单");
                break;
            case "10D"://待签收 显示确认收货按钮
                helper.setText(R.id.tv_status, "待签收");
                helper.setText(R.id.btnSubmit, "确认收货");
                helper.setVisible(R.id.btnSubmit, true);
                break;
            case "10E"://已签收
                helper.setText(R.id.tv_status, "已签收");
                helper.setGone(R.id.btnSubmit, false);
                break;
            case "10F"://已取消
                helper.setText(R.id.tv_status, "已取消");
                helper.setGone(R.id.btnSubmit, false);
                break;
            case "70A"://支付失败
                helper.setText(R.id.tv_status, "支付失败");
                helper.setGone(R.id.btnSubmit, false);
                break;
            default:
                helper.setText(R.id.tv_status, "未知状态");
                helper.setGone(R.id.btnSubmit, false);
                break;
        }

        helper.setText(R.id.tv_time, DateUtil.formatDateToHMS(item.getCreateTime().getTime()))
                .setText(R.id.tv_product_name, item.getGoodsName())
                .setText(R.id.tv_price, item.getGoodsPrice() + "")
                .setText(R.id.tv_price_total, "合计:￥" + item.getPay() + "")
                .setText(R.id.tv_amount, "共" + item.getGoodsCount() + "件商品")
                .setText(R.id.tv_amount_top, "x" + item.getGoodsCount())
                .setText(R.id.tv_unit_price, item.getGoodsPrice())
                .setText(R.id.tv_size, "规格:" + item.getGoodsSpecification())
        ;


        GlideUtils.loadImage(mContext, item.getGoodsImage(), (ImageView) helper.getView(R.id.iv_product));
        helper.addOnClickListener(R.id.btnSubmit);
        helper.addOnClickListener(R.id.my_service);

    }


}
