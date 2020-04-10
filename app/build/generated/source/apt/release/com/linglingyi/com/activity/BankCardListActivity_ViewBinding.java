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

public class BankCardListActivity_ViewBinding implements Unbinder {
  private BankCardListActivity target;

  private View view2131296633;

  private View view2131296691;

  private View view2131296357;

  @UiThread
  public BankCardListActivity_ViewBinding(BankCardListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BankCardListActivity_ViewBinding(final BankCardListActivity target, View source) {
    this.target = target;

    View view;
    target.rvList = Utils.findRequiredViewAsType(source, R.id.rv_list, "field 'rvList'", RecyclerView.class);
    target.srlRefresh = Utils.findRequiredViewAsType(source, R.id.srl_refresh, "field 'srlRefresh'", SmartRefreshLayout.class);
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
    view = Utils.findRequiredView(source, R.id.iv_right, "field 'ivRight' and method 'onViewClicked'");
    target.ivRight = Utils.castView(view, R.id.iv_right, "field 'ivRight'", ImageView.class);
    view2131296691 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.clContainer = Utils.findRequiredViewAsType(source, R.id.cl_container, "field 'clContainer'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.btn_add, "method 'onViewClicked'");
    view2131296357 = view;
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
    BankCardListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvList = null;
    target.srlRefresh = null;
    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.clContainer = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296691.setOnClickListener(null);
    view2131296691 = null;
    view2131296357.setOnClickListener(null);
    view2131296357 = null;
  }
}
