package com.linglingyi.com.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.model.CardsSmallPlanModel;
import com.linglingyi.com.utils.DateUtil;
import com.linglingyi.com.utils.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/10/28
 */
public class CardsPlanDetailSmallPlanAdapter extends BaseQuickAdapter<CardsSmallPlanModel.ItemListBean, CardsPlanDetailSmallPlanAdapter.MyViewHolder> {
    private String bigPlanStatus;

    public CardsPlanDetailSmallPlanAdapter(@Nullable List<CardsSmallPlanModel.ItemListBean> data, String bigPlanStatus) {
        super(R.layout.item_cards_detail_small_plan, data);
        this.bigPlanStatus = bigPlanStatus;
    }


    @Override
    protected void convert(MyViewHolder helper, CardsSmallPlanModel.ItemListBean item) {
        if (!StringUtil.isEmpty(item.getIndustryName())) {
            helper.tvArea.setText("地区：" + item.getCustomizeCity());
            helper.tvArea.setVisibility(View.VISIBLE);
            helper.tvIndustry.setText("行业：" + item.getIndustryName());
            helper.tvIndustry.setVisibility(View.VISIBLE);
        } else {
            helper.tvArea.setVisibility(View.GONE);
            helper.tvIndustry.setVisibility(View.GONE);
        }
        String consumeOrRepay = String.valueOf(item.getMoney());
        String payStatus = "";

        if ("sale".equals(item.getType())) {
            helper.tvPayType.setBackgroundResource(R.drawable.shape_solid_orange_corner_5);
            helper.tvPayType.setText("消费");
            payStatus = "失败";
        } else {
            helper.tvPayType.setBackgroundResource(R.drawable.shape_solid_blue_corner_5);
            helper.tvPayType.setText("还款");
            payStatus = "失败";
        }

        String status = item.getStatus();
        int dealStatusDrawableId = R.drawable.icon_wait_deal;
        helper.tvFix.setVisibility(View.GONE);
        switch (status) {
            case "10A":
            case "10D":
                dealStatusDrawableId = R.drawable.icon_wait_deal;
                helper.tvPayStatus.setVisibility(View.INVISIBLE);
                helper.tvFailReason.setVisibility(View.GONE);
                break;
            case "10B":
                dealStatusDrawableId = R.drawable.icon_success_deal;
                helper.tvPayStatus.setVisibility(View.INVISIBLE);
                helper.tvFailReason.setVisibility(View.GONE);
                break;
            case "10C":
                dealStatusDrawableId = R.drawable.icon_plan_detail_fail;
                helper.tvPayStatus.setVisibility(View.VISIBLE);
                helper.tvPayStatus.setText(payStatus);
                helper.tvFailReason.setVisibility(View.VISIBLE);
                // TODO: 2019/10/30 计划失败原因 
//                helper.tvFailReason.setText(item.getMessage());
                break;
            default:
                break;
        }
        if ("10C".equals(bigPlanStatus) || "10D".equals(bigPlanStatus)) {
            if (!"10B".equals(status)) {
                dealStatusDrawableId = R.drawable.icon_plan_detail_fail;
                helper.tvPayStatus.setText(payStatus);
            }
        }
        helper.ivDealStatus.setBackgroundResource(dealStatusDrawableId);
        helper.tvMoney.setText(consumeOrRepay);

        helper.tvDate.setText(DateUtil.formatDateToHMS(item.getPlanTime().getTime()));
    }

    class MyViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_payType)
        TextView tvPayType;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_payStatus)
        TextView tvPayStatus;
        @BindView(R.id.iv_dealStatus)
        ImageView ivDealStatus;
        @BindView(R.id.tv_area)
        TextView tvArea;
        @BindView(R.id.tv_fail_reason)
        TextView tvFailReason;
        @BindView(R.id.tv_fix)
        TextView tvFix;
        @BindView(R.id.tv_industry)
        TextView tvIndustry;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
