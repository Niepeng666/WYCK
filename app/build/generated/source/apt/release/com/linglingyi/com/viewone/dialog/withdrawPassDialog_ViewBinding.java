// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.viewone.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.linglingyi.com.viewone.CustomInputView;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class withdrawPassDialog_ViewBinding implements Unbinder {
  private withdrawPassDialog target;

  private View view2131296359;

  private View view2131296363;

  @UiThread
  public withdrawPassDialog_ViewBinding(final withdrawPassDialog target, View source) {
    this.target = target;

    View view;
    target.inputJihuo = Utils.findRequiredViewAsType(source, R.id.input_jihuo, "field 'inputJihuo'", CustomInputView.class);
    view = Utils.findRequiredView(source, R.id.btn_admit, "field 'btnAdmit' and method 'onViewClicked'");
    target.btnAdmit = Utils.castView(view, R.id.btn_admit, "field 'btnAdmit'", Button.class);
    view2131296359 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_cancel, "field 'btnCancel' and method 'onViewClicked'");
    target.btnCancel = Utils.castView(view, R.id.btn_cancel, "field 'btnCancel'", Button.class);
    view2131296363 = view;
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
    withdrawPassDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.inputJihuo = null;
    target.btnAdmit = null;
    target.btnCancel = null;

    view2131296359.setOnClickListener(null);
    view2131296359 = null;
    view2131296363.setOnClickListener(null);
    view2131296363 = null;
  }
}
