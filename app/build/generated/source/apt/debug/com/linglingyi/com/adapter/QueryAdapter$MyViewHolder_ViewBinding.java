// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class QueryAdapter$MyViewHolder_ViewBinding implements Unbinder {
  private QueryAdapter.MyViewHolder target;

  @UiThread
  public QueryAdapter$MyViewHolder_ViewBinding(QueryAdapter.MyViewHolder target, View source) {
    this.target = target;

    target.tvMonth = Utils.findRequiredViewAsType(source, R.id.tv_month, "field 'tvMonth'", TextView.class);
    target.llMonth = Utils.findRequiredViewAsType(source, R.id.ll_month, "field 'llMonth'", LinearLayout.class);
    target.tvTradeTime = Utils.findRequiredViewAsType(source, R.id.tv_trade_time, "field 'tvTradeTime'", TextView.class);
    target.tvTradeType = Utils.findRequiredViewAsType(source, R.id.tv_trade_type, "field 'tvTradeType'", TextView.class);
    target.tradestatus = Utils.findRequiredViewAsType(source, R.id.tradestatus, "field 'tradestatus'", TextView.class);
    target.tvMoney = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tvMoney'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    QueryAdapter.MyViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvMonth = null;
    target.llMonth = null;
    target.tvTradeTime = null;
    target.tvTradeType = null;
    target.tradestatus = null;
    target.tvMoney = null;
  }
}
