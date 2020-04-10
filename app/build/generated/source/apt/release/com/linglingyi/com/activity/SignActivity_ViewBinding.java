// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.linglingyi.com.viewone.qiandao.SignDate;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SignActivity_ViewBinding implements Unbinder {
  private SignActivity target;

  private View view2131296633;

  private View view2131296382;

  @UiThread
  public SignActivity_ViewBinding(SignActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SignActivity_ViewBinding(final SignActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'ivBack' and method 'onViewClicked'");
    target.ivBack = Utils.castView(view, R.id.iv_back, "field 'ivBack'", ImageView.class);
    view2131296633 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'tvRight'", TextView.class);
    target.ivRight = Utils.findRequiredViewAsType(source, R.id.iv_right, "field 'ivRight'", ImageView.class);
    target.signDate = Utils.findRequiredViewAsType(source, R.id.signDate, "field 'signDate'", SignDate.class);
    view = Utils.findRequiredView(source, R.id.btn_qiandao, "field 'btnQiandao' and method 'onViewClicked'");
    target.btnQiandao = Utils.castView(view, R.id.btn_qiandao, "field 'btnQiandao'", Button.class);
    view2131296382 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvSignMoney = Utils.findRequiredViewAsType(source, R.id.tv_sign_money, "field 'tvSignMoney'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SignActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.signDate = null;
    target.btnQiandao = null;
    target.tvSignMoney = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296382.setOnClickListener(null);
    view2131296382 = null;
  }
}
