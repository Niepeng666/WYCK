// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.viewone.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WithdrawalDialog_ViewBinding implements Unbinder {
  private WithdrawalDialog target;

  private View view2131297177;

  private View view2131297156;

  @UiThread
  public WithdrawalDialog_ViewBinding(final WithdrawalDialog target, View source) {
    this.target = target;

    View view;
    target.tvBody = Utils.findRequiredViewAsType(source, R.id.tv_withdraw_tip, "field 'tvBody'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_confirm, "method 'onViewClicked'");
    view2131297177 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_cancel, "method 'onViewClicked'");
    view2131297156 = view;
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
    WithdrawalDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvBody = null;

    view2131297177.setOnClickListener(null);
    view2131297177 = null;
    view2131297156.setOnClickListener(null);
    view2131297156 = null;
  }
}
