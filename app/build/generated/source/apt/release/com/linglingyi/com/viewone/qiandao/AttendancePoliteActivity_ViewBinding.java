// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.viewone.qiandao;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AttendancePoliteActivity_ViewBinding implements Unbinder {
  private AttendancePoliteActivity target;

  private View view2131296633;

  @UiThread
  public AttendancePoliteActivity_ViewBinding(AttendancePoliteActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AttendancePoliteActivity_ViewBinding(final AttendancePoliteActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_back, "method 'onClick'");
    view2131296633 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131296633.setOnClickListener(null);
    view2131296633 = null;
  }
}
