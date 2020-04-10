// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.viewone.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SharePicDialog_ViewBinding implements Unbinder {
  private SharePicDialog target;

  private View view2131296642;

  private View view2131297385;

  @UiThread
  public SharePicDialog_ViewBinding(final SharePicDialog target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_close, "field 'ivClose' and method 'onViewClicked'");
    target.ivClose = Utils.castView(view, R.id.iv_close, "field 'ivClose'", ImageView.class);
    view2131296642 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ivBg = Utils.findRequiredViewAsType(source, R.id.iv_bg, "field 'ivBg'", ImageView.class);
    target.ivQrCode = Utils.findRequiredViewAsType(source, R.id.iv_qr_code, "field 'ivQrCode'", ImageView.class);
    target.rlRed = Utils.findRequiredViewAsType(source, R.id.rl_red, "field 'rlRed'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_share, "field 'tvShare' and method 'onViewClicked'");
    target.tvShare = Utils.castView(view, R.id.tv_share, "field 'tvShare'", TextView.class);
    view2131297385 = view;
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
    SharePicDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivClose = null;
    target.ivBg = null;
    target.ivQrCode = null;
    target.rlRed = null;
    target.tvShare = null;

    view2131296642.setOnClickListener(null);
    view2131296642 = null;
    view2131297385.setOnClickListener(null);
    view2131297385 = null;
  }
}
