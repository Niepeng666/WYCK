// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShopMallActivity_ViewBinding implements Unbinder {
  private ShopMallActivity target;

  private View view2131296633;

  private View view2131297128;

  private View view2131297318;

  @UiThread
  public ShopMallActivity_ViewBinding(ShopMallActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ShopMallActivity_ViewBinding(final ShopMallActivity target, View source) {
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
    target.ivHeader = Utils.findRequiredViewAsType(source, R.id.iv_header, "field 'ivHeader'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.tv_address_manager, "field 'tvAddressManager' and method 'onViewClicked'");
    target.tvAddressManager = Utils.castView(view, R.id.tv_address_manager, "field 'tvAddressManager'", TextView.class);
    view2131297128 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.linearTitle = Utils.findRequiredViewAsType(source, R.id.linear_title, "field 'linearTitle'", LinearLayout.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.tv_order, "field 'tvOrder' and method 'onViewClicked'");
    target.tvOrder = Utils.castView(view, R.id.tv_order, "field 'tvOrder'", TextView.class);
    view2131297318 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.srlRefresh = Utils.findRequiredViewAsType(source, R.id.srl_refresh, "field 'srlRefresh'", SmartRefreshLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ShopMallActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.ivHeader = null;
    target.tvAddressManager = null;
    target.linearTitle = null;
    target.recyclerView = null;
    target.tvOrder = null;
    target.srlRefresh = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131297128.setOnClickListener(null);
    view2131297128 = null;
    view2131297318.setOnClickListener(null);
    view2131297318 = null;
  }
}
