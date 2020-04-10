// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.viewone.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BindParentPhoneDialog_ViewBinding implements Unbinder {
  private BindParentPhoneDialog target;

  private View view2131297400;

  @UiThread
  public BindParentPhoneDialog_ViewBinding(final BindParentPhoneDialog target, View source) {
    this.target = target;

    View view;
    target.etInvitePhone = Utils.findRequiredViewAsType(source, R.id.et_invite_phone, "field 'etInvitePhone'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_submit, "field 'tvSubmit' and method 'onViewClicked'");
    target.tvSubmit = Utils.castView(view, R.id.tv_submit, "field 'tvSubmit'", TextView.class);
    view2131297400 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    BindParentPhoneDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etInvitePhone = null;
    target.tvSubmit = null;

    view2131297400.setOnClickListener(null);
    view2131297400 = null;
  }
}
