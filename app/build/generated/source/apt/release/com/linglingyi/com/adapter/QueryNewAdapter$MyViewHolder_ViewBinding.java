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

public class QueryNewAdapter$MyViewHolder_ViewBinding implements Unbinder {
  private QueryNewAdapter.MyViewHolder target;

  @UiThread
  public QueryNewAdapter$MyViewHolder_ViewBinding(QueryNewAdapter.MyViewHolder target,
      View source) {
    this.target = target;

    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvDate = Utils.findRequiredViewAsType(source, R.id.tv_date, "field 'tvDate'", TextView.class);
    target.tvMoney = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tvMoney'", TextView.class);
    target.tvDetail = Utils.findRequiredViewAsType(source, R.id.tv_detail, "field 'tvDetail'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    QueryNewAdapter.MyViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvName = null;
    target.tvDate = null;
    target.tvMoney = null;
    target.tvDetail = null;
  }
}
