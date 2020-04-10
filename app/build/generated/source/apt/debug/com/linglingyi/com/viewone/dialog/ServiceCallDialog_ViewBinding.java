// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.viewone.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ServiceCallDialog_ViewBinding implements Unbinder {
  private ServiceCallDialog target;

  private View view2131296353;

  private View view2131296338;

  @UiThread
  public ServiceCallDialog_ViewBinding(final ServiceCallDialog target, View source) {
    this.target = target;

    View view;
    target.tvDialogTitle = Utils.findRequiredViewAsType(source, R.id.tv_dialogTitle, "field 'tvDialogTitle'", TextView.class);
    target.phoneNum = Utils.findRequiredViewAsType(source, R.id.phoneNum, "field 'phoneNum'", TextView.class);
    view = Utils.findRequiredView(source, R.id.bt_suspendCancel, "field 'btSuspendCancel' and method 'onViewClicked'");
    target.btSuspendCancel = Utils.castView(view, R.id.bt_suspendCancel, "field 'btSuspendCancel'", Button.class);
    view2131296353 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.bt_cancelPlan, "field 'btCancelPlan' and method 'onViewClicked'");
    target.btCancelPlan = Utils.castView(view, R.id.bt_cancelPlan, "field 'btCancelPlan'", Button.class);
    view2131296338 = view;
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
    ServiceCallDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvDialogTitle = null;
    target.phoneNum = null;
    target.btSuspendCancel = null;
    target.btCancelPlan = null;

    view2131296353.setOnClickListener(null);
    view2131296353 = null;
    view2131296338.setOnClickListener(null);
    view2131296338 = null;
  }
}
