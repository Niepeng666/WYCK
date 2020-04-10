// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.fragment;

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

public class LingzhuFragment_ViewBinding implements Unbinder {
  private LingzhuFragment target;

  private View view2131296633;

  private View view2131296734;

  private View view2131296831;

  private View view2131296760;

  private View view2131296789;

  @UiThread
  public LingzhuFragment_ViewBinding(final LingzhuFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'ivBack' and method 'goBack'");
    target.ivBack = Utils.castView(view, R.id.iv_back, "field 'ivBack'", ImageView.class);
    view2131296633 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goBack();
      }
    });
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'tvRight'", TextView.class);
    target.ivRight = Utils.findRequiredViewAsType(source, R.id.iv_right, "field 'ivRight'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.ll_apply, "field 'llApply' and method 'onViewClicked'");
    target.llApply = Utils.castView(view, R.id.ll_apply, "field 'llApply'", LinearLayout.class);
    view2131296734 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_winning, "field 'llWinning' and method 'onViewClicked'");
    target.llWinning = Utils.castView(view, R.id.ll_winning, "field 'llWinning'", LinearLayout.class);
    view2131296831 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_earnings, "field 'llEarnings' and method 'onViewClicked'");
    target.llEarnings = Utils.castView(view, R.id.ll_earnings, "field 'llEarnings'", LinearLayout.class);
    view2131296760 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my, "field 'llMy' and method 'onViewClicked'");
    target.llMy = Utils.castView(view, R.id.ll_my, "field 'llMy'", LinearLayout.class);
    view2131296789 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
    target.smartRefreshLayout = Utils.findRequiredViewAsType(source, R.id.smartRefreshLayout, "field 'smartRefreshLayout'", SmartRefreshLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LingzhuFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.llApply = null;
    target.llWinning = null;
    target.llEarnings = null;
    target.llMy = null;
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
