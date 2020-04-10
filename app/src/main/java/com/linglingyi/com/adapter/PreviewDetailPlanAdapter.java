package com.linglingyi.com.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linglingyi.com.utils.DateUtil;
import com.wuyouchuangke.com.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.linglingyi.com.model.CardPlanList;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/1
 */
public class PreviewDetailPlanAdapter extends BaseQuickAdapter<CardPlanList, BaseViewHolder> {
    boolean zhia;
    private boolean showCity = true;

    public PreviewDetailPlanAdapter(@Nullable List<CardPlanList> data, boolean zhia) {
        super(R.layout.item_dialog_preview_detail_plan, data);
        this.zhia = zhia;
    }


    public void setShowCity(boolean showCity) {
        this.showCity = showCity;
    }

    @Override
    protected void convert(BaseViewHolder helper, CardPlanList item) {
        LinearLayout ll_area = helper.getView(R.id.ll_area);
        LinearLayout ll_industry = helper.getView(R.id.ll_industry);
        TextView industry = helper.getView(R.id.industry);

        if (zhia) {
            if ("sale".equals(item.getType())) {
                helper.setText(R.id.type_tv, "手续费");
                helper.setBackgroundRes(R.id.type_tv, R.drawable.shape_solid_dark_green_corner_5);
                helper.setBackgroundColor(R.id.ll_content, Color.WHITE);
                ll_area.setVisibility(View.GONE);
                ll_industry.setVisibility(View.GONE);
            } else if ("payment".equals(item.getType())) {
                helper.setText(R.id.type_tv, "还款");
                helper.setBackgroundRes(R.id.type_tv, R.drawable.shape_solid_blue_corner_5);
                helper.setBackgroundColor(R.id.ll_content, Color.WHITE);
                ll_area.setVisibility(View.GONE);
                ll_industry.setVisibility(View.GONE);

            } else if ("pay".equals(item.getType())) {
                helper.setText(R.id.type_tv, "消费");
                helper.setBackgroundRes(R.id.type_tv, R.drawable.shape_solid_orange_corner_5);
                helper.setBackgroundColor(R.id.ll_content, Color.WHITE);
                if (item.getDiqu() != null && !"-1".equals(item.getDiqu().get("province").getId())) {
                    helper.setText(R.id.tv_area, item.getDiquString())
                            .addOnClickListener(R.id.ll_area)
                            .addOnClickListener(R.id.industry);
                    industry.setText(item.getMerString());
                    ll_area.setVisibility(View.VISIBLE);
                    ll_industry.setVisibility(View.VISIBLE);
                } else {
                    ll_area.setVisibility(View.GONE);
                    ll_industry.setVisibility(View.GONE);
                }

            }
        } else {
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
                if (item.getDiqu() != null && !"-1".equals(item.getDiqu().get("province").getId())) {
                    helper.setText(R.id.tv_area, item.getDiquString())
                            .addOnClickListener(R.id.ll_area)
                            .addOnClickListener(R.id.industry);
                    industry.setText(item.getMerString());
                    ll_area.setVisibility(View.VISIBLE);
                    ll_industry.setVisibility(View.VISIBLE);
                } else {
                    ll_area.setVisibility(View.GONE);
                    ll_industry.setVisibility(View.GONE);
                }
            }

        }
        if (!showCity) {
            ll_area.setVisibility(View.GONE);
        }
        helper.setBackgroundRes(R.id.tv_area, !zhia ? R.drawable.bottom_null : R.drawable.bottom_line);


        helper.setText(R.id.tv_date, zhia ? DateUtil.formateDateTOYMD(item.getPlanTime()) : DateUtil.formatDateToHMS(item.getPlanTime()))
                .setText(R.id.tv_money, item.getToMoney().toString());
    }

}
