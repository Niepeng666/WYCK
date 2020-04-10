// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CardsPlanDetailActivity_ViewBinding implements Unbinder {
  private CardsPlanDetailActivity target;

  private View view2131296633;

  private View view2131296387;

  @UiThread
  public CardsPlanDetailActivity_ViewBinding(CardsPlanDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CardsPlanDetailActivity_ViewBinding(final CardsPlanDetailActivity target, View source) {
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
    view = Utils.findRequiredView(source, R.id.btn_submit, "field 'btnSubmit' and method 'onViewClicked'");
    target.btnSubmit = Utils.castView(view, R.id.btn_submit, "field 'btnSubmit'", Button.class);
    view2131296387 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.llBottom = Utils.findRequiredViewAsType(source, R.id.ll_bottom, "field 'llBottom'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CardsPlanDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.rvList = null;
    target.btnSubmit = null;
    target.llBottom = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296387.setOnClickListener(null);
    view2131296387 = null;
  }
}
