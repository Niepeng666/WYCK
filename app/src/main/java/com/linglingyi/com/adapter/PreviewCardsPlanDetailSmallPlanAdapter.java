package com.linglingyi.com.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.model.CardsPlanModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/10/28
 */
public class PreviewCardsPlanDetailSmallPlanAdapter extends BaseQuickAdapter<CardsPlanModel.PlanItemListBean, BaseViewHolder> {

    public PreviewCardsPlanDetailSmallPlanAdapter(@Nullable List<CardsPlanModel.PlanItemListBean> data) {
        super(R.layout.item_dialog_preview_detail_plan, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CardsPlanModel.PlanItemListBean item) {
        LinearLayout ll_area = helper.getView(R.id.ll_area);
        LinearLayout ll_industry = helper.getView(R.id.ll_industry);
        if (!"sale".equals(item.getType())) {
            helper.setText(R.id.type_tv, "还款");
            helper.setBackgroundRes(R.id.type_tv, R.drawable.shape_solid_blue_corner_5);
            helper.setBackgroundColor(R.id.ll_content, Color.WHITE);
            ll_area.setVisibility(View.GONE);
            ll_industry.setVisibility(View.GONE);
        } else {
            helper.setText(R.id.type_tv, "消费");
            helper.setBackgroundRes(R.id.type_tv, R.drawable.shape_solid_orange_corner_5);
            helper.setBackgroundColor(R.id.ll_content, Color.WHITE);
            helper.setText(R.id.industry, item.getIndustryName());
            helper.setText(R.id.tv_area,item.getCityName());
            ll_area.setVisibility(View.VISIBLE);
            ll_industry.setVisibility(View.VISIBLE);
            helper.addOnClickListener(R.id.ll_industry);
        }
        helper.setText(R.id.tv_date, item.getTime());
        helper.setText(R.id.tv_money, item.getMoney() + "");
        helper.setBackgroundRes(R.id.tv_area, R.drawable.bottom_null);
    }


}
