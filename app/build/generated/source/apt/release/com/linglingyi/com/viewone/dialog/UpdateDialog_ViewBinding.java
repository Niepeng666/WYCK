// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.viewone.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UpdateDialog_ViewBinding implements Unbinder {
  private UpdateDialog target;

  private View view2131296388;

  private View view2131297156;

  private View view2131296642;

  private View view2131297154;

  @UiThread
  public UpdateDialog_ViewBinding(final UpdateDialog target, View source) {
    this.target = target;

    View view;
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvContent = Utils.findRequiredViewAsType(source, R.id.tv_content, "field 'tvContent'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_update, "field 'btnUpdate' and method 'onViewClicked'");
    target.btnUpdate = Utils.castView(view, R.id.btn_update, "field 'btnUpdate'", Button.class);
    view2131296388 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_cancel, "field 'tvCancel' and method 'onViewClicked'");
    target.tvCancel = Utils.castView(view, R.id.tv_cancel, "field 'tvCancel'", TextView.class);
    view2131297156 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.proBar = Utils.findRequiredViewAsType(source, R.id.pro_bar, "field 'proBar'", ProgressBar.class);
    target.tvProgress = Utils.findRequiredViewAsType(source, R.id.tv_progress, "field 'tvProgress'", TextView.class);
    target.llNoUpdate = Utils.findRequiredViewAsType(source, R.id.ll_no_update, "field 'llNoUpdate'", LinearLayout.class);
    target.llUpdateing = Utils.findRequiredViewAsType(source, R.id.ll_updateing, "field 'llUpdateing'", LinearLayout.class);
    target.rlUpdate = Utils.findRequiredViewAsType(source, R.id.rl_update, "field 'rlUpdate'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.iv_close, "field 'ivClose' and method 'onViewClicked'");
    target.ivClose = Utils.castView(view, R.id.iv_close, "field 'ivClose'", ImageView.class);
    view2131296642 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_browser, "field 'tvBrowser' and method 'onViewClicked'");
    target.tvBrowser = Utils.castView(view, R.id.tv_browser, "field 'tvBrowser'", TextView.class);
    view2131297154 = view;
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
    UpdateDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitle = null;
    target.tvContent = null;
    target.btnUpdate = null;
    target.tvCancel = null;
    target.proBar = null;
    target.tvProgress = null;
    target.llNoUpdate = null;
    target.llUpdateing = null;
    target.rlUpdate = null;
    target.ivClose = null;
    target.tvBrowser = null;

    view2131296388.setOnClickListener(null);
    view2131296388 = null;
    view2131297156.setOnClickListener(null);
    view2131297156 = null;
    view2131296642.setOnClickListener(null);
    view2131296642 = null;
    view2131297154.setOnClickListener(null);
    view2131297154 = null;
  }
}
