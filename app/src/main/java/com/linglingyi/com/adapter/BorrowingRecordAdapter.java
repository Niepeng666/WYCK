package com.linglingyi.com.adapter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.linglingyi.com.model.BorrowingRecordModel;
import com.linglingyi.com.utils.StringUtil;
import com.wuyouchuangke.com.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @作者 chenlanxin
 * @创建日期 2019/2/21 17:14
 * @功能
 **/
public class BorrowingRecordAdapter extends BaseQuickAdapter<BorrowingRecordModel, BorrowingRecordAdapter.ViewHolder> {

    public BorrowingRecordAdapter() {
        super(R.layout.item_borrowing_record);
    }

    @Override
    protected void convert(ViewHolder helper, BorrowingRecordModel item) {
        helper.tvTime.setText(item.getCreateTime());
        helper.tvMoney.setText("借款"+item.getLoanAmt()+" "+item.getDays()+"天");
        helper.tvWhy.setVisibility(View.GONE);

        switch (item.getStatus()) {
            case "10A"://申请中
                helper.tvState.setText("申请中");
                break;
            case "10B"://审核通过
                helper.tvState.setText("审核通过");
                break;
            case "10C"://已放款
                helper.tvState.setText("已放款");
                break;
            case "10D"://已逾期
                helper.tvState.setText("已逾期");
                break;
            case "10F"://已还清
                helper.tvState.setText("已还清");
                break;
            case "10H"://交易关闭
                helper.tvState.setText("交易关闭");
                break;
            case "10E"://拒绝
                helper.tvState.setText("审核拒绝");
                if (!StringUtil.isEmpty(item.getRemarks())){
                    helper.tvWhy.setText(item.getRemarks());
                }
                helper.tvWhy.setVisibility(View.VISIBLE);
                break;
            case "10G"://放款失败
                helper.tvState.setText("放款失败");
                break;
            case "10J":
                helper.tvState.setText("打款中");
                break;
            case "10K":
                helper.tvState.setText("还款中");
                break;
        }
        helper.lyItem.setTag(item.getId());
        helper.addOnClickListener(R.id.ly_item);
    }

    public class ViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_why)
        TextView tvWhy;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.ly_item)
        LinearLayout lyItem;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
