// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CardsPlanDetailAdapter$MyViewHolder_ViewBinding implements Unbinder {
  private CardsPlanDetailAdapter.MyViewHolder target;

  @UiThread
  public CardsPlanDetailAdapter$MyViewHolder_ViewBinding(CardsPlanDetailAdapter.MyViewHolder target,
      View source) {
    this.target = target;

    target.ivBankIcon = Utils.findRequiredViewAsType(source, R.id.iv_bank_icon, "field 'ivBankIcon'", ImageView.class);
    target.tvBankName = Utils.findRequiredViewAsType(source, R.id.tv_bank_name, "field 'tvBankName'", TextView.class);
    target.llBankName = Utils.findRequiredViewAsType(source, R.id.ll_bank_name, "field 'llBankName'", LinearLayout.class);
    target.tvPayTitle = Utils.findRequiredViewAsType(source, R.id.tv_pay_title, "field 'tvPayTitle'", TextView.class);
    target.tvBankAccount = Utils.findRequiredViewAsType(source, R.id.tv_bank_account, "field 'tvBankAccount'", TextView.class);
    target.tvRepayCycle = Utils.findRequiredViewAsType(source, R.id.tv_repayCycle, "field 'tvRepayCycle'", TextView.class);
    target.tvPreRepayAmount = Utils.findRequiredViewAsType(source, R.id.tv_preRepayAmount, "field 'tvPreRepayAmount'", TextView.class);
    target.tvPayedAmount = Utils.findRequiredViewAsType(source, R.id.tv_payed_amount, "field 'tvPayedAmount'", TextView.class);
    target.tvOrderStatus = Utils.findRequiredViewAsType(source, R.id.tv_orderStatus, "field 'tvOrderStatus'", TextView.class);
    target.tvChannelName = Utils.findRequiredViewAsType(source, R.id.tv_channel_name, "field 'tvChannelName'", TextView.class);
    target.tvPayType = Utils.findRequiredViewAsType(source, R.id.tv_payType, "field 'tvPayType'", TextView.class);
    target.tvTotalPrice = Utils.findRequiredViewAsType(source, R.id.tv_total_price, "field 'tvTotalPrice'", TextView.class);
    target.tvPayFee = Utils.findRequiredViewAsType(source, R.id.tv_pay_fee, "field 'tvPayFee'", TextView.class);
    target.tvTimesFee = Utils.findRequiredViewAsType(source, R.id.tv_times_fee, "field 'tvTimesFee'", TextView.class);
    target.tvDecreaseMoney = Utils.findRequiredViewAsType(source, R.id.tv_decrease_money, "field 'tvDecreaseMoney'", TextView.class);
    target.tvLevelFee = Utils.findRequiredViewAsType(source, R.id.tv_level_fee, "field 'tvLevelFee'", TextView.class);
    target.llMainInfo = Utils.findRequiredViewAsType(source, R.id.ll_main_info, "field 'llMainInfo'", LinearLayout.class);
    target.tvTranglePlan = Utils.findRequiredViewAsType(source, R.id.tv_trangle_plan, "field 'tvTranglePlan'", TextView.class);
    target.ivTranglePlan = Utils.findRequiredViewAsType(source, R.id.iv_trangle_plan, "field 'ivTranglePlan'", ImageView.class);
    target.llTranglePlan = Utils.findRequiredViewAsType(source, R.id.ll_trangle_plan, "field 'llTranglePlan'", LinearLayout.class);
    target.rvList = Utils.findRequiredViewAsType(source, R.id.rv_list, "field 'rvList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CardsPlanDetailAdapter.MyViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBankIcon = null;
    target.tvBankName = null;
    target.llBankName = null;
    target.tvPayTitle = null;
    target.tvBankAccount = null;
    target.tvRepayCycle = null;
    target.tvPreRepayAmount = null;
    target.tvPayedAmount = null;
    target.tvOrderStatus = null;
    target.tvChannelName = null;
    target.tvPayType = null;
    target.tvTotalPrice = null;
    target.tvPayFee = null;
    target.tvTimesFee = null;
    target.tvDecreaseMoney = null;
    target.tvLevelFee = null;
    target.llMainInfo = null;
    target.tvTranglePlan = null;
    target.ivTranglePlan = null;
    target.llTranglePlan = null;
    target.rvList = null;
  }
}
