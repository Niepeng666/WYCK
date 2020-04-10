// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WinningRecordAdapter$ViewHolder_ViewBinding implements Unbinder {
  private WinningRecordAdapter.ViewHolder target;

  @UiThread
  public WinningRecordAdapter$ViewHolder_ViewBinding(WinningRecordAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.tvMonth = Utils.findRequiredViewAsType(source, R.id.tv_month, "field 'tvMonth'", TextView.class);
    target.tvCity = Utils.findRequiredViewAsType(source, R.id.tv_city, "field 'tvCity'", TextView.class);
    target.tvArea = Utils.findRequiredViewAsType(source, R.id.tv_area, "field 'tvArea'", TextView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvOffer = Utils.findRequiredViewAsType(source, R.id.tv_offer, "field 'tvOffer'", TextView.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WinningRecordAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvMonth = null;
    target.tvCity = null;
    target.tvArea = null;
    target.tvName = null;
    target.tvOffer = null;
    target.tvTime = null;
  }
}
