// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CardsPlanDetailSmallPlanAdapter$MyViewHolder_ViewBinding implements Unbinder {
  private CardsPlanDetailSmallPlanAdapter.MyViewHolder target;

  @UiThread
  public CardsPlanDetailSmallPlanAdapter$MyViewHolder_ViewBinding(CardsPlanDetailSmallPlanAdapter.MyViewHolder target,
      View source) {
    this.target = target;

    target.tvPayType = Utils.findRequiredViewAsType(source, R.id.tv_payType, "field 'tvPayType'", TextView.class);
    target.tvDate = Utils.findRequiredViewAsType(source, R.id.tv_date, "field 'tvDate'", TextView.class);
    target.tvMoney = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tvMoney'", TextView.class);
    target.tvPayStatus = Utils.findRequiredViewAsType(source, R.id.tv_payStatus, "field 'tvPayStatus'", TextView.class);
    target.ivDealStatus = Utils.findRequiredViewAsType(source, R.id.iv_dealStatus, "field 'ivDealStatus'", ImageView.class);
    target.tvArea = Utils.findRequiredViewAsType(source, R.id.tv_area, "field 'tvArea'", TextView.class);
    target.tvFailReason = Utils.findRequiredViewAsType(source, R.id.tv_fail_reason, "field 'tvFailReason'", TextView.class);
    target.tvFix = Utils.findRequiredViewAsType(source, R.id.tv_fix, "field 'tvFix'", TextView.class);
    target.tvIndustry = Utils.findRequiredViewAsType(source, R.id.tv_industry, "field 'tvIndustry'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CardsPlanDetailSmallPlanAdapter.MyViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvPayType = null;
    target.tvDate = null;
    target.tvMoney = null;
    target.tvPayStatus = null;
    target.ivDealStatus = null;
    target.tvArea = null;
    target.tvFailReason = null;
    target.tvFix = null;
    target.tvIndustry = null;
  }
}
