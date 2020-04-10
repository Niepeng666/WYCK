package com.linglingyi.com.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.model.WinningRecordModel;
import com.linglingyi.com.utils.DateUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @作者 chenlanxin
 * @创建日期 2019/10/17 16:16
 * @功能
 **/
public class WinningRecordAdapter extends BaseQuickAdapter<WinningRecordModel, WinningRecordAdapter.ViewHolder> {
    private List<WinningRecordModel> mList;

    public WinningRecordAdapter(List<WinningRecordModel> list) {
        super(R.layout.item_winning_record, list);
        mList = list;
    }

    @Override
    protected void convert(ViewHolder helper, WinningRecordModel item) {
        helper.tvCity.setSelected(true);
        helper.tvArea.setSelected(true);
        helper.tvName.setText("中标领主：" + item.getMerchantName());
        helper.tvOffer.setText("报价：" + item.getTotalMoney());
        helper.tvTime.setText("有效期：" + item.getStartTime() + "至" + item.getEndTime());
        helper.tvCity.setText(item.getCity());
        helper.tvArea.setText(item.getArea());
        String date = DateUtil.formatHM2(item.getStartTime());

        if (helper.getAdapterPosition() == 0) {
            helper.tvMonth.setVisibility(View.VISIBLE);
            helper.tvMonth.setText(date);
        } else if (helper.getAdapterPosition() > 0) {
            int position = helper.getAdapterPosition() - 1;
            String yesterDate = DateUtil.formatHM2(mList.get(position).getStartTime());
            if (!date.equals(yesterDate)) {
                helper.tvMonth.setVisibility(View.VISIBLE);
                helper.tvMonth.setText(date);
            } else {
                helper.tvMonth.setVisibility(View.GONE);
            }
        }
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_month)
        TextView tvMonth;
        @BindView(R.id.tv_city)
        TextView tvCity;
        @BindView(R.id.tv_area)
        TextView tvArea;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_offer)
        TextView tvOffer;
        @BindView(R.id.tv_time)
        TextView tvTime;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
