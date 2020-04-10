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

public class LordRightsAdapter$ViewHolder_ViewBinding implements Unbinder {
  private LordRightsAdapter.ViewHolder target;

  @UiThread
  public LordRightsAdapter$ViewHolder_ViewBinding(LordRightsAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.ivRanking = Utils.findRequiredViewAsType(source, R.id.iv_ranking, "field 'ivRanking'", ImageView.class);
    target.tvRanking = Utils.findRequiredViewAsType(source, R.id.tv_ranking, "field 'tvRanking'", TextView.class);
    target.tvCity = Utils.findRequiredViewAsType(source, R.id.tv_city, "field 'tvCity'", TextView.class);
    target.tvArea = Utils.findRequiredViewAsType(source, R.id.tv_area, "field 'tvArea'", TextView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvEarnings = Utils.findRequiredViewAsType(source, R.id.tv_earnings, "field 'tvEarnings'", TextView.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
    target.ivRob = Utils.findRequiredViewAsType(source, R.id.iv_rob, "field 'ivRob'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LordRightsAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivRanking = null;
    target.tvRanking = null;
    target.tvCity = null;
    target.tvArea = null;
    target.tvName = null;
    target.tvEarnings = null;
    target.tvTime = null;
    target.ivRob = null;
  }
}
