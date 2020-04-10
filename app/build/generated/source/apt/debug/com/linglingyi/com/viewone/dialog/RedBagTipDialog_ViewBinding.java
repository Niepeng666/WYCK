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

public class RedBagTipDialog_ViewBinding implements Unbinder {
  private RedBagTipDialog target;

  private View view2131297230;

  private View view2131296642;

  @UiThread
  public RedBagTipDialog_ViewBinding(final RedBagTipDialog target, View source) {
    this.target = target;

    View view;
    target.ivBg = Utils.findRequiredViewAsType(source, R.id.iv_bg, "field 'ivBg'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.tv_guafen, "field 'tvGuafen' and method 'onViewClicked'");
    target.tvGuafen = Utils.castView(view, R.id.tv_guafen, "field 'tvGuafen'", TextView.class);
    view2131297230 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvMoney = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tvMoney'", TextView.class);
    target.tvMoneyLabel = Utils.findRequiredViewAsType(source, R.id.tv_money_label, "field 'tvMoneyLabel'", TextView.class);
    target.rlRed = Utils.findRequiredViewAsType(source, R.id.rl_red, "field 'rlRed'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.iv_close, "field 'ivClose' and method 'onViewClicked'");
    target.ivClose = Utils.castView(view, R.id.iv_close, "field 'ivClose'", ImageView.class);
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
    RedBagTipDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBg = null;
    target.tvGuafen = null;
    target.tvMoney = null;
    target.tvMoneyLabel = null;
    target.rlRed = null;
    target.ivClose = null;

    view2131297230.setOnClickListener(null);
    view2131297230 = null;
    view2131296642.setOnClickListener(null);
    view2131296642 = null;
  }
}
