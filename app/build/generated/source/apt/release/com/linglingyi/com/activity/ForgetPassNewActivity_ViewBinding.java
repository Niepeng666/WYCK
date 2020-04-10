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

public class ForgetPassNewActivity_ViewBinding implements Unbinder {
  private ForgetPassNewActivity target;

  private View view2131297229;

  private View view2131296633;

  private View view2131296342;

  @UiThread
  public ForgetPassNewActivity_ViewBinding(ForgetPassNewActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ForgetPassNewActivity_ViewBinding(final ForgetPassNewActivity target, View source) {
    this.target = target;

    View view;
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
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
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'ivBack' and method 'onClick'");
    target.ivBack = Utils.castView(view, R.id.iv_back, "field 'ivBack'", ImageView.class);
    view2131296633 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'tvRight'", TextView.class);
    target.ivRight = Utils.findRequiredViewAsType(source, R.id.iv_right, "field 'ivRight'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.bt_determine, "field 'btDetermine' and method 'onClick'");
    target.btDetermine = Utils.castView(view, R.id.bt_determine, "field 'btDetermine'", Button.class);
    view2131296342 = view;
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
    ForgetPassNewActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitle = null;
    target.etPhone = null;
    target.etCode = null;
    target.tvGetCode = null;
    target.etPassword = null;
    target.etRepassword = null;
    target.ivBack = null;
    target.tvRight = null;
    target.ivRight = null;
    target.btDetermine = null;

    view2131297229.setOnClickListener(null);
    view2131297229 = null;
    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296342.setOnClickListener(null);
    view2131296342 = null;
  }
}
