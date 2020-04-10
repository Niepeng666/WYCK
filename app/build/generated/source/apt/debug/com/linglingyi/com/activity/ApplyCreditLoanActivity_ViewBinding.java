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

public class ApplyCreditLoanActivity_ViewBinding implements Unbinder {
  private ApplyCreditLoanActivity target;

  private View view2131296633;

  private View view2131296387;

  @UiThread
  public ApplyCreditLoanActivity_ViewBinding(ApplyCreditLoanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ApplyCreditLoanActivity_ViewBinding(final ApplyCreditLoanActivity target, View source) {
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
    target.tvDes = Utils.findRequiredViewAsType(source, R.id.tv_des, "field 'tvDes'", TextView.class);
    target.etName = Utils.findRequiredViewAsType(source, R.id.et_name, "field 'etName'", EditText.class);
    target.etIdCard = Utils.findRequiredViewAsType(source, R.id.et_id_card, "field 'etIdCard'", EditText.class);
    target.etMobile = Utils.findRequiredViewAsType(source, R.id.et_mobile, "field 'etMobile'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_submit, "field 'btnSubmit' and method 'onViewClicked'");
    target.btnSubmit = Utils.castView(view, R.id.btn_submit, "field 'btnSubmit'", Button.class);
    view2131296387 = view;
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
    ApplyCreditLoanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.tvDes = null;
    target.etName = null;
    target.etIdCard = null;
    target.etMobile = null;
    target.btnSubmit = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296387.setOnClickListener(null);
    view2131296387 = null;
  }
}
