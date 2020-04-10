// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LordRightsMyActivity_ViewBinding implements Unbinder {
  private LordRightsMyActivity target;

  private View view2131296633;

  private View view2131297443;

  @UiThread
  public LordRightsMyActivity_ViewBinding(LordRightsMyActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LordRightsMyActivity_ViewBinding(final LordRightsMyActivity target, View source) {
    this.target = target;

    View view;
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.ivHead = Utils.findRequiredViewAsType(source, R.id.iv_head, "field 'ivHead'", ImageView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvLevel = Utils.findRequiredViewAsType(source, R.id.tv_level, "field 'tvLevel'", TextView.class);
    target.tvBalance = Utils.findRequiredViewAsType(source, R.id.tv_balance, "field 'tvBalance'", TextView.class);
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
    view = Utils.findRequiredView(source, R.id.tv_withdrawal, "method 'onViewClicked'");
    view2131297443 = view;
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
    LordRightsMyActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitle = null;
    target.ivHead = null;
    target.tvName = null;
    target.tvLevel = null;
    target.tvBalance = null;
    target.recyclerView = null;
    target.smartRefreshLayout = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131297443.setOnClickListener(null);
    view2131297443 = null;
  }
}
