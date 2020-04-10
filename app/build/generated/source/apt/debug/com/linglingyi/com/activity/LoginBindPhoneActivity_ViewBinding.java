// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginBindPhoneActivity_ViewBinding implements Unbinder {
  private LoginBindPhoneActivity target;

  private View view2131296633;

  private View view2131297229;

  private View view2131296335;

  @UiThread
  public LoginBindPhoneActivity_ViewBinding(LoginBindPhoneActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginBindPhoneActivity_ViewBinding(final LoginBindPhoneActivity target, View source) {
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
    target.etPhone = Utils.findRequiredViewAsType(source, R.id.et_phone, "field 'etPhone'", EditText.class);
    target.etCode = Utils.findRequiredViewAsType(source, R.id.et_code, "field 'etCode'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_get_code, "field 'tvGetCode' and method 'onViewClicked'");
    target.tvGetCode = Utils.castView(view, R.id.tv_get_code, "field 'tvGetCode'", TextView.class);
    view2131297229 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etPassword = Utils.findRequiredViewAsType(source, R.id.et_password, "field 'etPassword'", EditText.class);
    view = Utils.findRequiredView(source, R.id.bt_bind, "field 'btBind' and method 'onViewClicked'");
    target.btBind = Utils.castView(view, R.id.bt_bind, "field 'btBind'", Button.class);
    view2131296335 = view;
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
    LoginBindPhoneActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.etPhone = null;
    target.etCode = null;
    target.tvGetCode = null;
    target.etPassword = null;
    target.btBind = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131297229.setOnClickListener(null);
    view2131297229 = null;
    view2131296335.setOnClickListener(null);
    view2131296335 = null;
  }
}
