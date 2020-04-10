// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CreditHonorFragment_ViewBinding implements Unbinder {
  private CreditHonorFragment target;

  private View view2131297449;

  private View view2131296387;

  private View view2131297233;

  @UiThread
  public CreditHonorFragment_ViewBinding(final CreditHonorFragment target, View source) {
    this.target = target;

    View view;
    target.etName = Utils.findRequiredViewAsType(source, R.id.et_name, "field 'etName'", EditText.class);
    target.etIdCard = Utils.findRequiredViewAsType(source, R.id.et_idCard, "field 'etIdCard'", EditText.class);
    target.etBankNumber = Utils.findRequiredViewAsType(source, R.id.et_bank_number, "field 'etBankNumber'", EditText.class);
    target.tvBankName = Utils.findRequiredViewAsType(source, R.id.tv_bank_name, "field 'tvBankName'", TextView.class);
    target.etPhone = Utils.findRequiredViewAsType(source, R.id.et_phone, "field 'etPhone'", EditText.class);
    target.etPhonePassword = Utils.findRequiredViewAsType(source, R.id.et_phone_password, "field 'etPhonePassword'", EditText.class);
    target.codeLl = Utils.findRequiredViewAsType(source, R.id.code_ll, "field 'codeLl'", LinearLayout.class);
    target.cbXieYi = Utils.findRequiredViewAsType(source, R.id.cb_xieYi, "field 'cbXieYi'", CheckBox.class);
    view = Utils.findRequiredView(source, R.id.tv_xieYi, "field 'tvXieYi' and method 'onViewClicked'");
    target.tvXieYi = Utils.castView(view, R.id.tv_xieYi, "field 'tvXieYi'", TextView.class);
    view2131297449 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvMoney = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tvMoney'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_submit, "field 'btnSubmit' and method 'onViewClicked'");
    target.btnSubmit = Utils.castView(view, R.id.btn_submit, "field 'btnSubmit'", Button.class);
    view2131296387 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvCardScore = Utils.findRequiredViewAsType(source, R.id.tv_card_score, "field 'tvCardScore'", TextView.class);
    target.tvCardHonor = Utils.findRequiredViewAsType(source, R.id.tv_card_honor, "field 'tvCardHonor'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_history, "method 'onViewClicked'");
    view2131297233 = view;
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
    CreditHonorFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etName = null;
    target.etIdCard = null;
    target.etBankNumber = null;
    target.tvBankName = null;
    target.etPhone = null;
    target.etPhonePassword = null;
    target.codeLl = null;
    target.cbXieYi = null;
    target.tvXieYi = null;
    target.tvMoney = null;
    target.btnSubmit = null;
    target.tvCardScore = null;
    target.tvCardHonor = null;

    view2131297449.setOnClickListener(null);
    view2131297449 = null;
    view2131296387.setOnClickListener(null);
    view2131296387 = null;
    view2131297233.setOnClickListener(null);
    view2131297233 = null;
  }
}
