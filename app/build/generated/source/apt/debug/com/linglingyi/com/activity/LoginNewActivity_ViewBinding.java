// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginNewActivity_ViewBinding implements Unbinder {
  private LoginNewActivity target;

  private View view2131296708;

  private View view2131296681;

  private View view2131296370;

  private View view2131297361;

  private View view2131297227;

  private View view2131296671;

  private View view2131296670;

  @UiThread
  public LoginNewActivity_ViewBinding(LoginNewActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginNewActivity_ViewBinding(final LoginNewActivity target, View source) {
    this.target = target;

    View view;
    target.etPhone = Utils.findRequiredViewAsType(source, R.id.et_phone, "field 'etPhone'", EditText.class);
    view = Utils.findRequiredView(source, R.id.iv_txt_clear, "field 'ivTxtClear' and method 'onClick'");
    target.ivTxtClear = Utils.castView(view, R.id.iv_txt_clear, "field 'ivTxtClear'", ImageView.class);
    view2131296708 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.etPass = Utils.findRequiredViewAsType(source, R.id.et_pass, "field 'etPass'", EditText.class);
    view = Utils.findRequiredView(source, R.id.iv_pass_show, "field 'ivPassShow' and method 'onClick'");
    target.ivPassShow = Utils.castView(view, R.id.iv_pass_show, "field 'ivPassShow'", ImageView.class);
    view2131296681 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.checkbox = Utils.findRequiredViewAsType(source, R.id.checkbox, "field 'checkbox'", CheckBox.class);
    view = Utils.findRequiredView(source, R.id.btn_login, "field 'btnLogin' and method 'onClick'");
    target.btnLogin = Utils.castView(view, R.id.btn_login, "field 'btnLogin'", Button.class);
    view2131296370 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_register, "field 'tvRegister' and method 'onClick'");
    target.tvRegister = Utils.castView(view, R.id.tv_register, "field 'tvRegister'", TextView.class);
    view2131297361 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_forget_pass, "field 'tvForgetPass' and method 'onClick'");
    target.tvForgetPass = Utils.castView(view, R.id.tv_forget_pass, "field 'tvForgetPass'", TextView.class);
    view2131297227 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_login_wechat, "method 'onClick'");
    view2131296671 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_login_qq, "method 'onClick'");
    view2131296670 = view;
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
    LoginNewActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etPhone = null;
    target.ivTxtClear = null;
    target.etPass = null;
    target.ivPassShow = null;
    target.checkbox = null;
    target.btnLogin = null;
    target.tvRegister = null;
    target.tvForgetPass = null;

    view2131296708.setOnClickListener(null);
    view2131296708 = null;
    view2131296681.setOnClickListener(null);
    view2131296681 = null;
    view2131296370.setOnClickListener(null);
    view2131296370 = null;
    view2131297361.setOnClickListener(null);
    view2131297361 = null;
    view2131297227.setOnClickListener(null);
    view2131297227 = null;
    view2131296671.setOnClickListener(null);
    view2131296671 = null;
    view2131296670.setOnClickListener(null);
    view2131296670 = null;
  }
}
