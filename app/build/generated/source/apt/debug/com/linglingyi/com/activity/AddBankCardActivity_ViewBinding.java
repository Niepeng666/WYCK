// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddBankCardActivity_ViewBinding implements Unbinder {
  private AddBankCardActivity target;

  private View view2131296633;

  private View view2131296691;

  private View view2131296511;

  private View view2131296387;

  private View view2131297189;

  private View view2131297184;

  @UiThread
  public AddBankCardActivity_ViewBinding(AddBankCardActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddBankCardActivity_ViewBinding(final AddBankCardActivity target, View source) {
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
    target.etName = Utils.findRequiredViewAsType(source, R.id.et_name, "field 'etName'", EditText.class);
    target.etCreditCardNo = Utils.findRequiredViewAsType(source, R.id.et_credit_card_no, "field 'etCreditCardNo'", EditText.class);
    target.etBankName = Utils.findRequiredViewAsType(source, R.id.et_bank_name, "field 'etBankName'", EditText.class);
    target.etLimit = Utils.findRequiredViewAsType(source, R.id.et_limit, "field 'etLimit'", EditText.class);
    target.etDayBill = Utils.findRequiredViewAsType(source, R.id.et_day_bill, "field 'etDayBill'", EditText.class);
    target.etDayPay = Utils.findRequiredViewAsType(source, R.id.et_day_pay, "field 'etDayPay'", EditText.class);
    view = Utils.findRequiredView(source, R.id.et_dead_line, "field 'etDeadLine' and method 'onViewClicked'");
    target.etDeadLine = Utils.castView(view, R.id.et_dead_line, "field 'etDeadLine'", EditText.class);
    view2131296511 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etCvv = Utils.findRequiredViewAsType(source, R.id.et_cvv, "field 'etCvv'", EditText.class);
    target.etPhone = Utils.findRequiredViewAsType(source, R.id.et_phone, "field 'etPhone'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_submit, "field 'btnSubmit' and method 'onViewClicked'");
    target.btnSubmit = Utils.castView(view, R.id.btn_submit, "field 'btnSubmit'", Button.class);
    view2131296387 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.rlContainer = Utils.findRequiredViewAsType(source, R.id.rl_container, "field 'rlContainer'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_dead_line, "method 'onViewClicked'");
    view2131297189 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_cvv, "method 'onViewClicked'");
    view2131297184 = view;
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
    AddBankCardActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.etName = null;
    target.etCreditCardNo = null;
    target.etBankName = null;
    target.etLimit = null;
    target.etDayBill = null;
    target.etDayPay = null;
    target.etDeadLine = null;
    target.etCvv = null;
    target.etPhone = null;
    target.btnSubmit = null;
    target.rlContainer = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296691.setOnClickListener(null);
    view2131296691 = null;
    view2131296511.setOnClickListener(null);
    view2131296511 = null;
    view2131296387.setOnClickListener(null);
    view2131296387 = null;
    view2131297189.setOnClickListener(null);
    view2131297189 = null;
    view2131297184.setOnClickListener(null);
    view2131297184 = null;
  }
}
