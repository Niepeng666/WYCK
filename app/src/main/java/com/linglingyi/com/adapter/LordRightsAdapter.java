package com.linglingyi.com.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.model.LordRightsModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @作者 chenlanxin
 * @创建日期 2019/10/17 11:03
 * @功能
 **/
public class LordRightsAdapter extends BaseQuickAdapter<LordRightsModel, LordRightsAdapter.ViewHolder> {

    private boolean isRank;//是否是收益排行榜

    public LordRightsAdapter(boolean isRank) {
        super(R.layout.item_lord_rights);
        this.isRank = isRank;
    }

    @Override
    protected void convert(ViewHolder helper, LordRightsModel item) {
        if (isRank) {
            helper.ivRob.setVisibility(View.GONE);

            int position = helper.getAdapterPosition();
            if (position <= 2) {
                switch (position) {
                    case 0:
                        helper.ivRanking.setImageResource(R.drawable.iv_earnings_first);
                        break;
                    case 1:
                        helper.ivRanking.setImageResource(R.drawable.iv_earnings_second);
                        break;
                    case 2:
                        helper.ivRanking.setImageResource(R.drawable.iv_earnings_third);
                        break;
                }
                helper.ivRanking.setVisibility(View.VISIBLE);
                helper.tvRanking.setVisibility(View.GONE);
            } else {
                helper.tvRanking.setText(String.valueOf(position + 1));
                helper.ivRanking.setVisibility(View.GONE);
                helper.tvRanking.setVisibility(View.VISIBLE);
            }
        } else {
            helper.ivRanking.setVisibility(View.GONE);
            helper.tvRanking.setVisibility(View.GONE);
            helper.ivRob.setVisibility(View.VISIBLE);
        }
        helper.tvCity.setText(item.getCity());
        helper.tvArea.setText(item.getArea());
        helper.tvName.setText("领主：" + item.getMerchantName());
        helper.tvEarnings.setText("收益：" + item.getTotalMoney());
        helper.tvTime.setText("到期时间\n" + item.getEndTime());
        helper.ivRob.setImageResource("1".equals(item.getStatus()) ? R.drawable.iv_lord_rights_unrob : R.drawable.iv_lord_rights_rob);
        helper.tvCity.setSelected(true);
        helper.tvArea.setSelected(true);
        helper.ivRob.setTag(item);
//        helper.addOnClickListener(R.id.iv_rob);
    }

    public class ViewHolder extends BaseViewHolder {
        @BindView(R.id.iv_ranking)
        ImageView ivRanking;
        @BindView(R.id.tv_ranking)
        TextView tvRanking;
        @BindView(R.id.tv_city)
        TextView tvCity;
        @BindView(R.id.tv_area)
        TextView tvArea;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_earnings)
        TextView tvEarnings;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.iv_rob)
        ImageView ivRob;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
