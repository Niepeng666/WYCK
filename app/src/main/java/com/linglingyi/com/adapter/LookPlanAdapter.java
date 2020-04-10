package com.linglingyi.com.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.wuyouchuangke.com.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.linglingyi.com.model.PlanItem;
import com.linglingyi.com.utils.DateUtil;

import java.util.Date;
import java.util.List;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/2
 */
public class LookPlanAdapter extends BaseQuickAdapter<PlanItem, BaseViewHolder> {
    public LookPlanAdapter(@Nullable List<PlanItem> data) {
        super(R.layout.item_look_plan, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PlanItem item) {
        String status = item.getPlanStatus();
        if ("10A".equals(status)) {
            status = "未执行";
            helper.setTextColor(R.id.tv_dealStatus, Color.parseColor("#4b4b4b"));
        } else if ("10B".equals(status)) {
            status = "执行中";
            helper.setTextColor(R.id.tv_dealStatus, Color.parseColor("#4b4b4b"));

        } else if ("10D".equals(status)) {
            helper.setTextColor(R.id.tv_dealStatus, Color.RED);
            status = "暂停";
        } else if ("10C".equals(status)) {
            helper.setTextColor(R.id.tv_dealStatus, Color.RED);
            status = "失败";
        } else if ("10E".equals(status)) {
            status = "完成";
            helper.setTextColor(R.id.tv_dealStatus, Color.parseColor("#00af43"));
        }
        helper.setText(R.id.tv_channel_name, "通道名称：" + item.getACQ_NAME());
        helper.setText(R.id.tv_dealStatus, status);
        helper.setText(R.id.tv_shouldPayNow, "本期应还：￥" + item.getShouldPayNow());
        helper.setText(R.id.tv_paidAmountNow, "本期已还：￥" + item.getPaidAmountNow());
        helper.setText(R.id.tv_progress, "进度：" + item.getPlanProgress());
        helper.setText(R.id.tv_planCycle, "计划周期：" + item.getPlanCycle());
        helper.setText(R.id.tv_planCreateTime, "创建时间：" + DateUtil.formatHM(item.getCreateTime()));
        helper.addOnClickListener(R.id.btn_detail);
        helper.addOnClickListener(R.id.btn_reset);
    }
}
