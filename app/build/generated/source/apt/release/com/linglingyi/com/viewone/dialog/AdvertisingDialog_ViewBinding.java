// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.viewone.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AdvertisingDialog_ViewBinding implements Unbinder {
  private AdvertisingDialog target;

  private View view2131296629;

  private View view2131296642;

  @UiThread
  public AdvertisingDialog_ViewBinding(final AdvertisingDialog target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_advertising, "field 'ivAdvertising' and method 'onViewClicked'");
    target.ivAdvertising = Utils.castView(view, R.id.iv_advertising, "field 'ivAdvertising'", ImageView.class);
    view2131296629 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_close, "method 'onViewClicked'");
    view2131296642 = view;
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
    AdvertisingDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivAdvertising = null;

    view2131296629.setOnClickListener(null);
    view2131296629 = null;
    view2131296642.setOnClickListener(null);
    view2131296642 = null;
  }
}
