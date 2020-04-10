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

public class DebitBankChangeActivity_ViewBinding implements Unbinder {
  private DebitBankChangeActivity target;

  private View view2131296633;

  private View view2131296691;

  private View view2131296351;

  @UiThread
  public DebitBankChangeActivity_ViewBinding(DebitBankChangeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DebitBankChangeActivity_ViewBinding(final DebitBankChangeActivity target, View source) {
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
    view = Utils.findRequiredView(source, R.id.iv_right, "field 'ivRight' and method 'onViewClicked'");
    target.ivRight = Utils.castView(view, R.id.iv_right, "field 'ivRight'", ImageView.class);
    view2131296691 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvBankName = Utils.findRequiredViewAsType(source, R.id.tv_bank_name, "field 'tvBankName'", TextView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.etBankNum = Utils.findRequiredViewAsType(source, R.id.et_bank_num, "field 'etBankNum'", EditText.class);
    target.etPhone = Utils.findRequiredViewAsType(source, R.id.et_phone, "field 'etPhone'", EditText.class);
    view = Utils.findRequiredView(source, R.id.bt_submit, "field 'btSubmit' and method 'onViewClicked'");
    target.btSubmit = Utils.castView(view, R.id.bt_submit, "field 'btSubmit'", Button.class);
    view2131296351 = view;
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
    DebitBankChangeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.tvBankName = null;
    target.tvName = null;
    target.etBankNum = null;
    target.etPhone = null;
    target.btSubmit = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296691.setOnClickListener(null);
    view2131296691 = null;
    view2131296351.setOnClickListener(null);
    view2131296351 = null;
  }
}
