package com.linglingyi.com.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.linglingyi.com.MyApplication;
import com.linglingyi.com.model.BindCard;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.Utils;
import com.wuyouchuangke.com.R;

import java.util.List;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/5/9
 */
public class ManagerCreditAdapter extends BaseQuickAdapter<BindCard, BaseViewHolder> {
    boolean is2Pay;
    private boolean isVip;
    private boolean isCardManager;
    private boolean isCards;
    private String makeType;

    public ManagerCreditAdapter(@Nullable List<BindCard> data) {
        super(R.layout.item_manager_bank, data);
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public void setIs2Pay(boolean is2Pay) {
        this.is2Pay = is2Pay;
    }

    public void setMakeType(String makeType) {
        this.makeType = makeType;
    }

    public void setCards(boolean cards) {
        isCards = cards;
    }

    public void setCardManager(boolean cardManager) {
        isCardManager = cardManager;
    }

    @Override
    protected void convert(BaseViewHolder helper, BindCard item) {

        if (!is2Pay && !isCardManager) {
            helper.addOnClickListener(R.id.ll_make_design);
            helper.addOnClickListener(R.id.tv_look_plan);
            helper.addOnClickListener(R.id.tv_look_data);
            helper.addOnClickListener(R.id.tv_card_unbind);
            helper.addOnClickListener(R.id.tv_card_score);

        }
        helper.addOnClickListener(R.id.tv_unbind);
//        helper.setGone(R.id.tv_unbind, !is2Pay);
        helper.addOnClickListener(R.id.btn_make);
        helper.setGone(R.id.btn_make, !isVip);
        helper.setGone(R.id.ll_make, !isCards);
        helper.setText(R.id.btn_make, makeType);
        helper.setText(R.id.tv_plan_tip, item.getPlanCount() > 0 ? "正在执行中...." : "");
        if (item.getPlanCount() > 0 && !is2Pay) {
            helper.setText(R.id.btn_make, "计划进行中");
        }
        helper.setGone(R.id.ll_plan, item.isExpand());

        String banNum = item.getBANK_ACCOUNT();
        if (banNum.length() > 4) {
            String bankNum1 = banNum.substring(0, 4);
            String bankNum2 = banNum.substring(banNum.length() - 4, banNum.length());
            helper.setText(R.id.tv_bank_account, bankNum1 + " **** **** " + bankNum2);
        }
        helper.setText(R.id.tv_limit, item.getLIMIT_MONEY())
                .setText(R.id.tv_bill_day, item.getBILL_DAY())
                .setText(R.id.tv_pay_day, item.getREPAYMENT_DAY());
        String bankName = item.getBANK_NAME();
        Utils.initBankCodeColorIcon(bankName, (ImageView) helper.getView(R.id.iv_bank_icon), mContext);
        helper.setText(R.id.tv_bank_name, item.getShort_cn_name());
        if (helper.getAdapterPosition() % 3 == 0) {
            helper.setBackgroundRes(R.id.cl_card, R.drawable.shape_solid_red_top_left_right);
        } else if (helper.getAdapterPosition() % 3 == 1) {
            helper.setBackgroundRes(R.id.cl_card, R.drawable.shape_solid_blue_top_left_right);
        } else {
            helper.setBackgroundRes(R.id.cl_card, R.drawable.shape_solid_green_top_left_right);
        }

    }
}
