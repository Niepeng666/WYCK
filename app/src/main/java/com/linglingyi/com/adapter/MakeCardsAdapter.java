package com.linglingyi.com.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.model.MakeCardModel;
import com.linglingyi.com.model.PlanCardModel;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.Utils;

import java.util.List;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/10/25
 */
public class MakeCardsAdapter extends BaseQuickAdapter<MakeCardModel, BaseViewHolder> {
    public MakeCardsAdapter(@Nullable List<MakeCardModel> data) {
        super(R.layout.item_make_cards, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MakeCardModel item) {
        LogUtils.i("bind=" + item.getBindCard());
        switch (helper.getAdapterPosition()) {
            case 0:
                helper.setText(R.id.tv_card_1, "主卡");
                break;
            case 1:
                helper.setText(R.id.tv_card_1, "副卡");
                break;
            case 2:
                helper.setText(R.id.tv_card_1, "副卡");
                break;
            default:
                break;
        }
        if (item.getBindCard() != null) {
            String banNum = item.getBindCard().getBANK_ACCOUNT();
            if (banNum.length() > 4) {
                String bankNum1 = banNum.substring(0, 4);
                String bankNum2 = banNum.substring(banNum.length() - 4, banNum.length());
                helper.setText(R.id.tv_bank_account, bankNum1 + " **** **** " + bankNum2);
            }
            helper.setText(R.id.tv_limit, item.getBindCard().getLIMIT_MONEY())
                    .setText(R.id.tv_bill_day, item.getBindCard().getBILL_DAY())
                    .setText(R.id.tv_pay_day, item.getBindCard().getREPAYMENT_DAY());
            String bankName = item.getBindCard().getBANK_NAME();
            Utils.initBankCodeColorIcon(bankName, (ImageView) helper.getView(R.id.iv_bank_icon), mContext);
            helper.setText(R.id.tv_bank_name, item.getBindCard().getShort_cn_name());
            if (helper.getAdapterPosition() % 3 == 0) {
                helper.setBackgroundRes(R.id.cl_card, R.drawable.shape_solid_red_top_left_right);
            } else if (helper.getAdapterPosition() % 3 == 1) {
                helper.setBackgroundRes(R.id.cl_card, R.drawable.shape_solid_blue_top_left_right);
            } else {
                helper.setBackgroundRes(R.id.cl_card, R.drawable.shape_solid_green_top_left_right);
            }
        }

        helper.setText(R.id.btn_make, StringUtil.isEmpty(item.getDebtMoney()) ? "制定计划" : "修改计划");
        helper.setVisible(R.id.ll_card, item.getBindCard() != null);
        helper.addOnClickListener(R.id.btn_make);
        helper.addOnClickListener(R.id.cl_add_card);
    }
}
