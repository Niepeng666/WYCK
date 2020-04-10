// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.constraint.ConstraintLayout;
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

public class PreviewCardsPlanDetailAdapter$MyViewHolder_ViewBinding implements Unbinder {
  private PreviewCardsPlanDetailAdapter.MyViewHolder target;

  @UiThread
  public PreviewCardsPlanDetailAdapter$MyViewHolder_ViewBinding(PreviewCardsPlanDetailAdapter.MyViewHolder target,
      View source) {
    this.target = target;

    target.ivBankIcon = Utils.findRequiredViewAsType(source, R.id.iv_bank_icon, "field 'ivBankIcon'", ImageView.class);
    target.tvBankName = Utils.findRequiredViewAsType(source, R.id.tv_bank_name, "field 'tvBankName'", TextView.class);
    target.llBankName = Utils.findRequiredViewAsType(source, R.id.ll_bank_name, "field 'llBankName'", LinearLayout.class);
    target.tvPayTitle = Utils.findRequiredViewAsType(source, R.id.tv_pay_title, "field 'tvPayTitle'", TextView.class);
    target.tvBankAccount = Utils.findRequiredViewAsType(source, R.id.tv_bank_account, "field 'tvBankAccount'", TextView.class);
    target.tvRepayCycle = Utils.findRequiredViewAsType(source, R.id.tv_repayCycle, "field 'tvRepayCycle'", TextView.class);
    target.tvChannelName = Utils.findRequiredViewAsType(source, R.id.tv_channel_name, "field 'tvChannelName'", TextView.class);
    target.tvLimit = Utils.findRequiredViewAsType(source, R.id.tv_limit, "field 'tvLimit'", TextView.class);
    target.tvTotalPrice = Utils.findRequiredViewAsType(source, R.id.tv_total_price, "field 'tvTotalPrice'", TextView.class);
    target.llTotalPrice = Utils.findRequiredViewAsType(source, R.id.ll_total_price, "field 'llTotalPrice'", LinearLayout.class);
    target.tvTotalServiceFee = Utils.findRequiredViewAsType(source, R.id.tv_total_service_fee, "field 'tvTotalServiceFee'", TextView.class);
    target.llTotalServiceFee = Utils.findRequiredViewAsType(source, R.id.ll_total_service_fee, "field 'llTotalServiceFee'", LinearLayout.class);
    target.clPrice1 = Utils.findRequiredViewAsType(source, R.id.cl_price_1, "field 'clPrice1'", ConstraintLayout.class);
    target.tvLimit2 = Utils.findRequiredViewAsType(source, R.id.tv_limit_2, "field 'tvLimit2'", TextView.class);
    target.llPrice2 = Utils.findRequiredViewAsType(source, R.id.ll_price_2, "field 'llPrice2'", LinearLayout.class);
    target.tvTranglePlan = Utils.findRequiredViewAsType(source, R.id.tv_trangle_plan, "field 'tvTranglePlan'", TextView.class);
    target.ivTranglePlan = Utils.findRequiredViewAsType(source, R.id.iv_trangle_plan, "field 'ivTranglePlan'", ImageView.class);
    target.llTranglePlan = Utils.findRequiredViewAsType(source, R.id.ll_trangle_plan, "field 'llTranglePlan'", LinearLayout.class);
    target.rvList = Utils.findRequiredViewAsType(source, R.id.rv_list, "field 'rvList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PreviewCardsPlanDetailAdapter.MyViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBankIcon = null;
    target.tvBankName = null;
    target.llBankName = null;
    target.tvPayTitle = null;
    target.tvBankAccount = null;
    target.tvRepayCycle = null;
    target.tvChannelName = null;
    target.tvLimit = null;
    target.tvTotalPrice = null;
    target.llTotalPrice = null;
    target.tvTotalServiceFee = null;
    target.llTotalServiceFee = null;
    target.clPrice1 = null;
    target.tvLimit2 = null;
    target.llPrice2 = null;
    target.tvTranglePlan = null;
    target.ivTranglePlan = null;
    target.llTranglePlan = null;
    target.rvList = null;
  }
}
