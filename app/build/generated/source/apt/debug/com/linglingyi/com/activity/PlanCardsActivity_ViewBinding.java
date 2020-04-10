// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PlanCardsActivity_ViewBinding implements Unbinder {
  private PlanCardsActivity target;

  private View view2131296633;

  private View view2131297368;

  @UiThread
  public PlanCardsActivity_ViewBinding(PlanCardsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PlanCardsActivity_ViewBinding(final PlanCardsActivity target, View source) {
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
    view = Utils.findRequiredView(source, R.id.tv_right, "field 'tvRight' and method 'onViewClicked'");
    target.tvRight = Utils.castView(view, R.id.tv_right, "field 'tvRight'", TextView.class);
    view2131297368 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ivRight = Utils.findRequiredViewAsType(source, R.id.iv_right, "field 'ivRight'", ImageView.class);
    target.tlTab = Utils.findRequiredViewAsType(source, R.id.tl_tab, "field 'tlTab'", TabLayout.class);
    target.rvList = Utils.findRequiredViewAsType(source, R.id.rv_list, "field 'rvList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PlanCardsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.tlTab = null;
    target.rvList = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131297368.setOnClickListener(null);
    view2131297368 = null;
  }
}
