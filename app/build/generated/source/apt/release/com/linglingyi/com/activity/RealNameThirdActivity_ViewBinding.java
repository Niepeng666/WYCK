// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RealNameThirdActivity_ViewBinding implements Unbinder {
  private RealNameThirdActivity target;

  private View view2131296633;

  private View view2131296683;

  private View view2131296374;

  @UiThread
  public RealNameThirdActivity_ViewBinding(RealNameThirdActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RealNameThirdActivity_ViewBinding(final RealNameThirdActivity target, View source) {
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
    view = Utils.findRequiredView(source, R.id.iv_people, "field 'ivPeople' and method 'onViewClicked'");
    target.ivPeople = Utils.castView(view, R.id.iv_people, "field 'ivPeople'", ImageView.class);
    view2131296683 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_next, "method 'onViewClicked'");
    view2131296374 = view;
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
    RealNameThirdActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.ivPeople = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296683.setOnClickListener(null);
    view2131296683 = null;
    view2131296374.setOnClickListener(null);
    view2131296374 = null;
  }
}
