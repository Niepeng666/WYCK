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

public class BusinessClassActivity_ViewBinding implements Unbinder {
  private BusinessClassActivity target;

  private View view2131296633;

  @UiThread
  public BusinessClassActivity_ViewBinding(BusinessClassActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BusinessClassActivity_ViewBinding(final BusinessClassActivity target, View source) {
    this.target = target;

    View view;
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.ivTop = Utils.findRequiredViewAsType(source, R.id.iv_top, "field 'ivTop'", ImageView.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
    target.smartRefreshLayout = Utils.findRequiredViewAsType(source, R.id.smartRefreshLayout, "field 'smartRefreshLayout'", SmartRefreshLayout.class);
    view = Utils.findRequiredView(source, R.id.iv_back, "method 'onViewClicked'");
    view2131296633 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    BusinessClassActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitle = null;
    target.ivTop = null;
    target.recyclerView = null;
    target.smartRefreshLayout = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
  }
}
