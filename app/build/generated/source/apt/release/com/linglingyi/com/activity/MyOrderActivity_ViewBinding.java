// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyOrderActivity_ViewBinding implements Unbinder {
  private MyOrderActivity target;

  private View view2131296633;

  @UiThread
  public MyOrderActivity_ViewBinding(MyOrderActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyOrderActivity_ViewBinding(final MyOrderActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'ivBack' and method 'onViewClicked'");
    target.ivBack = Utils.castView(view, R.id.iv_back, "field 'ivBack'", ImageView.class);
    view2131296633 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'tvRight'", TextView.class);
    target.ivRight = Utils.findRequiredViewAsType(source, R.id.iv_right, "field 'ivRight'", ImageView.class);
    target.rvList = Utils.findRequiredViewAsType(source, R.id.rv_list, "field 'rvList'", RecyclerView.class);
    target.idImg = Utils.findRequiredViewAsType(source, R.id.id_img, "field 'idImg'", ImageView.class);
    target.relativeDefaultx = Utils.findRequiredViewAsType(source, R.id.relative_defaultx, "field 'relativeDefaultx'", RelativeLayout.class);
    target.srlRefresh = Utils.findRequiredViewAsType(source, R.id.srl_refresh, "field 'srlRefresh'", SmartRefreshLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyOrderActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.rvList = null;
    target.idImg = null;
    target.relativeDefaultx = null;
    target.srlRefresh = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
  }
}
