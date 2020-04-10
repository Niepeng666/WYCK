// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyClientDetailActivity_ViewBinding implements Unbinder {
  private MyClientDetailActivity target;

  private View view2131296633;

  private View view2131296350;

  @UiThread
  public MyClientDetailActivity_ViewBinding(MyClientDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyClientDetailActivity_ViewBinding(final MyClientDetailActivity target, View source) {
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
    target.tlTab = Utils.findRequiredViewAsType(source, R.id.tl_tab, "field 'tlTab'", TabLayout.class);
    target.etSearchPhone = Utils.findRequiredViewAsType(source, R.id.et_search_phone, "field 'etSearchPhone'", EditText.class);
    view = Utils.findRequiredView(source, R.id.bt_search, "field 'btSearch' and method 'onViewClicked'");
    target.btSearch = Utils.castView(view, R.id.bt_search, "field 'btSearch'", Button.class);
    view2131296350 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.rvList = Utils.findRequiredViewAsType(source, R.id.rv_list, "field 'rvList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyClientDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.tlTab = null;
    target.etSearchPhone = null;
    target.btSearch = null;
    target.rvList = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296350.setOnClickListener(null);
    view2131296350 = null;
  }
}
