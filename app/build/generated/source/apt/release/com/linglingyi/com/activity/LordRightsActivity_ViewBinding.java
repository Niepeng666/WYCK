// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LordRightsActivity_ViewBinding implements Unbinder {
  private LordRightsActivity target;

  private View view2131296633;

  private View view2131296734;

  private View view2131296831;

  private View view2131296760;

  private View view2131296789;

  @UiThread
  public LordRightsActivity_ViewBinding(LordRightsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LordRightsActivity_ViewBinding(final LordRightsActivity target, View source) {
    this.target = target;

    View view;
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
    target.smartRefreshLayout = Utils.findRequiredViewAsType(source, R.id.smartRefreshLayout, "field 'smartRefreshLayout'", SmartRefreshLayout.class);
    view = Utils.findRequiredView(source, R.id.iv_back, "method 'onViewClicked'");
    view2131296633 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_apply, "method 'onViewClicked'");
    view2131296734 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_winning, "method 'onViewClicked'");
    view2131296831 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_earnings, "method 'onViewClicked'");
    view2131296760 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my, "method 'onViewClicked'");
    view2131296789 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    LordRightsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitle = null;
    target.recyclerView = null;
    target.smartRefreshLayout = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296734.setOnClickListener(null);
    view2131296734 = null;
    view2131296831.setOnClickListener(null);
    view2131296831 = null;
    view2131296760.setOnClickListener(null);
    view2131296760 = null;
    view2131296789.setOnClickListener(null);
    view2131296789 = null;
  }
}
