package com.linglingyi.com.adapter;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.wuyouchuangke.com.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.linglingyi.com.MyApplication;
import com.linglingyi.com.model.BindCard;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.DateUtil;
import com.linglingyi.com.utils.Utils;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/3/30
 */
public class BankCardListAdapter extends BaseQuickAdapter<BindCard, BankCardListAdapter.ViewHolder> {
    boolean is2Pay;
    private boolean isCardManager;

    public BankCardListAdapter(List<BindCard> list, boolean is2Pay,boolean isCardManager) {
        super(R.layout.item_bank_card, list);
        this.is2Pay = is2Pay;
        this.isCardManager=isCardManager;
    }


    @Override
    protected void convert(ViewHolder helper, BindCard item) {
        String bankName = item.getBANK_NAME();
        Utils.initBankBackgroundCode(bankName, helper.lyItem, mContext);
        Utils.initBankCode(bankName, helper.ivBankLogo, mContext);

//        if (helper.getAdapterPosition() % 3 == 0) {
//            helper.setBackgroundRes(R.id.ly_item, R.drawable.bg_1);
//        } else if (helper.getAdapterPosition() % 3 == 1) {
//            helper.setBackgroundRes(R.id.ly_item, R.drawable.bg_2);
//        } else {
//            helper.setBackgroundRes(R.id.ly_item, R.drawable.bg_3);
//        }
        helper.tvBankName.setText(item.getShort_cn_name());
        String banNum = item.getBANK_ACCOUNT();
        if (banNum.length() > 4) {
            String bankNum1 = banNum.substring(banNum.length() - 4, banNum.length());
            helper.tvBankNum.setText(bankNum1);
        }
        helper.tvZdr.setText(item.getBILL_DAY());
        helper.tvHkr.setText(item.getREPAYMENT_DAY() + "");
        helper.tvEd.setText(item.getLIMIT_MONEY());
        helper.tvDate.setText(item.getDay());
        if (is2Pay) {
//            helper.lyItem.setTag(item);
//            helper.addOnClickListener(R.id.ly_item);
            helper.ivDelete.setVisibility(View.INVISIBLE);

            helper.tvMakePlan.setVisibility(View.GONE);
            helper.tvLookPlan.setVisibility(View.GONE);
        } else {
            helper.lyUpdateCard.setTag(item);
            helper.addOnClickListener(R.id.ly_update_card);
            helper.ivDelete.setVisibility(View.VISIBLE);
            helper.addOnClickListener(R.id.iv_delete);
            helper.ivDelete.setTag(item);
        }

        if (isCardManager) {
            helper.tvMakePlan.setVisibility(View.GONE);
            helper.tvLookPlan.setVisibility(View.GONE);
            helper.tvChange.setVisibility(View.VISIBLE);
            helper.tvChange.setTag(item);
            helper.addOnClickListener(R.id.tv_change);

        }

        helper.tvMakePlan.setTag(item);
        helper.addOnClickListener(R.id.tv_make_plan);
        helper.tvLookPlan.setTag(item);
        helper.addOnClickListener(R.id.tv_look_plan);
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.iv_bank_logo)
        ImageView ivBankLogo;
        @BindView(R.id.tv_bank_name)
        TextView tvBankName;
        @BindView(R.id.tv_bank_num)
        TextView tvBankNum;
        @BindView(R.id.iv_delete)
        ImageView ivDelete;
        @BindView(R.id.tv_zdr)
        TextView tvZdr;
        @BindView(R.id.tv_hkr)
        TextView tvHkr;
        @BindView(R.id.tv_ed)
        TextView tvEd;
        @BindView(R.id.ly_update_card)
        LinearLayout lyUpdateCard;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_make_plan)
        TextView tvMakePlan;
        @BindView(R.id.tv_look_plan)
        TextView tvLookPlan;
        @BindView(R.id.ly_item)
        LinearLayout lyItem;
        @BindView(R.id.tv_change)
        TextView tvChange;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
