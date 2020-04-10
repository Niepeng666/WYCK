// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LordRightsMyAdapter$ViewHolder_ViewBinding implements Unbinder {
  private LordRightsMyAdapter.ViewHolder target;

  @UiThread
  public LordRightsMyAdapter$ViewHolder_ViewBinding(LordRightsMyAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.tvDay = Utils.findRequiredViewAsType(source, R.id.tv_day, "field 'tvDay'", TextView.class);
    target.tvType = Utils.findRequiredViewAsType(source, R.id.tv_type, "field 'tvType'", TextView.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
    target.tvMoney = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tvMoney'", TextView.class);
    target.clItem = Utils.findRequiredViewAsType(source, R.id.cl_item, "field 'clItem'", ConstraintLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LordRightsMyAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvDay = null;
    target.tvType = null;
    target.tvTime = null;
    target.tvMoney = null;
    target.clItem = null;
  }
}
