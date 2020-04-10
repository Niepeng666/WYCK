// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.viewone.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TipDialog_ViewBinding implements Unbinder {
  private TipDialog target;

  private View view2131296363;

  private View view2131296359;

  @UiThread
  public TipDialog_ViewBinding(final TipDialog target, View source) {
    this.target = target;

    View view;
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.tvContent = Utils.findRequiredViewAsType(source, R.id.tv_content, "field 'tvContent'", TextView.class);
    target.llContent = Utils.findRequiredViewAsType(source, R.id.ll_content, "field 'llContent'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.btn_cancel, "field 'btnCancel' and method 'onViewClicked'");
    target.btnCancel = Utils.castView(view, R.id.btn_cancel, "field 'btnCancel'", Button.class);
    view2131296363 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_admit, "field 'btnAdmit' and method 'onViewClicked'");
    target.btnAdmit = Utils.castView(view, R.id.btn_admit, "field 'btnAdmit'", Button.class);
    view2131296359 = view;
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
    TipDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitle = null;
    target.line = null;
    target.tvContent = null;
    target.llContent = null;
    target.btnCancel = null;
    target.btnAdmit = null;

    view2131296363.setOnClickListener(null);
    view2131296363 = null;
    view2131296359.setOnClickListener(null);
    view2131296359 = null;
  }
}
