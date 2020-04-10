// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.viewone.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VipBottomDialog_ViewBinding implements Unbinder {
  private VipBottomDialog target;

  private View view2131296642;

  private View view2131297003;

  private View view2131296981;

  private View view2131296377;

  @UiThread
  public VipBottomDialog_ViewBinding(final VipBottomDialog target, View source) {
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
    target.rbWechat = Utils.findRequiredViewAsType(source, R.id.rb_wechat, "field 'rbWechat'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.rl_wechat, "field 'rlWechat' and method 'onViewClicked'");
    target.rlWechat = Utils.castView(view, R.id.rl_wechat, "field 'rlWechat'", LinearLayout.class);
    view2131297003 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.rbAlipay = Utils.findRequiredViewAsType(source, R.id.rb_alipay, "field 'rbAlipay'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.rl_alipay, "field 'rlAlipay' and method 'onViewClicked'");
    target.rlAlipay = Utils.castView(view, R.id.rl_alipay, "field 'rlAlipay'", LinearLayout.class);
    view2131296981 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.rgPay = Utils.findRequiredViewAsType(source, R.id.rg_pay, "field 'rgPay'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.btn_pay, "field 'btnPay' and method 'onViewClicked'");
    target.btnPay = Utils.castView(view, R.id.btn_pay, "field 'btnPay'", TextView.class);
    view2131296377 = view;
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
    VipBottomDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivClose = null;
    target.rbWechat = null;
    target.rlWechat = null;
    target.rbAlipay = null;
    target.rlAlipay = null;
    target.rgPay = null;
    target.btnPay = null;

    view2131296642.setOnClickListener(null);
    view2131296642 = null;
    view2131297003.setOnClickListener(null);
    view2131297003 = null;
    view2131296981.setOnClickListener(null);
    view2131296981 = null;
    view2131296377.setOnClickListener(null);
    view2131296377 = null;
  }
}
