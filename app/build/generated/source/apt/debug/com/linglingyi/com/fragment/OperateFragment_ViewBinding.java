// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OperateFragment_ViewBinding implements Unbinder {
  private OperateFragment target;

  @UiThread
  public OperateFragment_ViewBinding(OperateFragment target, View source) {
    this.target = target;

    target.ivBack = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'ivBack'", ImageView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tlTab = Utils.findRequiredViewAsType(source, R.id.tl_tab, "field 'tlTab'", TabLayout.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OperateFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tlTab = null;
    target.recyclerView = null;
  }
}
