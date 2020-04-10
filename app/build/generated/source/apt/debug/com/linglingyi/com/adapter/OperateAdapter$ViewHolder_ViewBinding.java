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

public class OperateAdapter$ViewHolder_ViewBinding implements Unbinder {
  private OperateAdapter.ViewHolder target;

  @UiThread
  public OperateAdapter$ViewHolder_ViewBinding(OperateAdapter.ViewHolder target, View source) {
    this.target = target;

    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OperateAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitle = null;
  }
}
