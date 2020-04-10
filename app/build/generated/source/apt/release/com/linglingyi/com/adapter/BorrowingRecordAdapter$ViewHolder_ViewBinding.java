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

public class BorrowingRecordAdapter$ViewHolder_ViewBinding implements Unbinder {
  private BorrowingRecordAdapter.ViewHolder target;

  @UiThread
  public BorrowingRecordAdapter$ViewHolder_ViewBinding(BorrowingRecordAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
    target.tvMoney = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tvMoney'", TextView.class);
    target.tvWhy = Utils.findRequiredViewAsType(source, R.id.tv_why, "field 'tvWhy'", TextView.class);
    target.tvState = Utils.findRequiredViewAsType(source, R.id.tv_state, "field 'tvState'", TextView.class);
    target.lyItem = Utils.findRequiredViewAsType(source, R.id.ly_item, "field 'lyItem'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BorrowingRecordAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTime = null;
    target.tvMoney = null;
    target.tvWhy = null;
    target.tvState = null;
    target.lyItem = null;
  }
}
