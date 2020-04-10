// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.linglingyi.com.viewone.FontIconView;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegisterNewActivity_ViewBinding implements Unbinder {
  private RegisterNewActivity target;

  private View view2131296633;

  private View view2131297229;

  private View view2131297363;

  private View view2131296349;

  private View view2131296431;

  private View view2131297375;

  private View view2131297201;

  @UiThread
  public RegisterNewActivity_ViewBinding(RegisterNewActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegisterNewActivity_ViewBinding(final RegisterNewActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'ivBack' and method 'onClick'");
    target.ivBack = Utils.castView(view, R.id.iv_back, "field 'ivBack'", ImageView.class);
    view2131296633 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'tvRight'", TextView.class);
    target.ivRight = Utils.findRequiredViewAsType(source, R.id.iv_right, "field 'ivRight'", ImageView.class);
    target.etPhone = Utils.findRequiredViewAsType(source, R.id.et_phone, "field 'etPhone'", EditText.class);
    target.etCode = Utils.findRequiredViewAsType(source, R.id.et_code, "field 'etCode'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_get_code, "field 'tvGetCode' and method 'onClick'");
    target.tvGetCode = Utils.castView(view, R.id.tv_get_code, "field 'tvGetCode'", TextView.class);
    view2131297229 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.etPassword = Utils.findRequiredViewAsType(source, R.id.et_password, "field 'etPassword'", EditText.class);
    target.etRepassword = Utils.findRequiredViewAsType(source, R.id.et_repassword, "field 'etRepassword'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_registration_protocol, "field 'tvRegistrationProtocol' and method 'onClick'");
    target.tvRegistrationProtocol = Utils.castView(view, R.id.tv_registration_protocol, "field 'tvRegistrationProtocol'", TextView.class);
    view2131297363 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.lyCheck = Utils.findRequiredViewAsType(source, R.id.ly_check, "field 'lyCheck'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.bt_register, "field 'btRegister' and method 'onClick'");
    target.btRegister = Utils.castView(view, R.id.bt_register, "field 'btRegister'", Button.class);
    view2131296349 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.etInvitePhone = Utils.findRequiredViewAsType(source, R.id.et_invite_phone, "field 'etInvitePhone'", EditText.class);
    view = Utils.findRequiredView(source, R.id.checkbox, "field 'checkbox' and method 'onClick'");
    target.checkbox = Utils.castView(view, R.id.checkbox, "field 'checkbox'", FontIconView.class);
    view2131296431 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_secret, "field 'tvSecret' and method 'onClick'");
    target.tvSecret = Utils.castView(view, R.id.tv_secret, "field 'tvSecret'", TextView.class);
    view2131297375 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_descrition, "field 'tvDescrition' and method 'onClick'");
    target.tvDescrition = Utils.castView(view, R.id.tv_descrition, "field 'tvDescrition'", TextView.class);
    view2131297201 = view;
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
    RegisterNewActivity target = this.target;
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
    target.etRepassword = null;
    target.tvRegistrationProtocol = null;
    target.lyCheck = null;
    target.btRegister = null;
    target.etInvitePhone = null;
    target.checkbox = null;
    target.tvSecret = null;
    target.tvDescrition = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131297229.setOnClickListener(null);
    view2131297229 = null;
    view2131297363.setOnClickListener(null);
    view2131297363 = null;
    view2131296349.setOnClickListener(null);
    view2131296349 = null;
    view2131296431.setOnClickListener(null);
    view2131296431 = null;
    view2131297375.setOnClickListener(null);
    view2131297375 = null;
    view2131297201.setOnClickListener(null);
    view2131297201 = null;
  }
}
