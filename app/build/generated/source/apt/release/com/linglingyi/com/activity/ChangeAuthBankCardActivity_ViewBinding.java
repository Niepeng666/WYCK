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
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChangeAuthBankCardActivity_ViewBinding implements Unbinder {
  private ChangeAuthBankCardActivity target;

  private View view2131296633;

  private View view2131296359;

  @UiThread
  public ChangeAuthBankCardActivity_ViewBinding(ChangeAuthBankCardActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChangeAuthBankCardActivity_ViewBinding(final ChangeAuthBankCardActivity target,
      View source) {
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
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvIdCard = Utils.findRequiredViewAsType(source, R.id.tv_id_card, "field 'tvIdCard'", TextView.class);
    target.etBankAccount = Utils.findRequiredViewAsType(source, R.id.et_bank_account, "field 'etBankAccount'", EditText.class);
    target.tvBankName = Utils.findRequiredViewAsType(source, R.id.tv_bank_name, "field 'tvBankName'", TextView.class);
    target.etPhone = Utils.findRequiredViewAsType(source, R.id.et_phone, "field 'etPhone'", EditText.class);
    target.llCardChange = Utils.findRequiredViewAsType(source, R.id.ll_card_change, "field 'llCardChange'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.btn_admit, "field 'btnAdmit' and method 'onViewClicked'");
    target.btnAdmit = Utils.castView(view, R.id.btn_admit, "field 'btnAdmit'", Button.class);
    view2131296359 = view;
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
    ChangeAuthBankCardActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.tvName = null;
    target.tvIdCard = null;
    target.etBankAccount = null;
    target.tvBankName = null;
    target.etPhone = null;
    target.llCardChange = null;
    target.btnAdmit = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296359.setOnClickListener(null);
    view2131296359 = null;
  }
}
