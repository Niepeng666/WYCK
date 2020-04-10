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

public class BankCreditDetailChangeActivity_ViewBinding implements Unbinder {
  private BankCreditDetailChangeActivity target;

  private View view2131296633;

  private View view2131296503;

  private View view2131296526;

  private View view2131296364;

  @UiThread
  public BankCreditDetailChangeActivity_ViewBinding(BankCreditDetailChangeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BankCreditDetailChangeActivity_ViewBinding(final BankCreditDetailChangeActivity target,
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
    target.tvBankName = Utils.findRequiredViewAsType(source, R.id.tv_bank_name, "field 'tvBankName'", TextView.class);
    target.tvBankAccount2 = Utils.findRequiredViewAsType(source, R.id.tv_bank_account_2, "field 'tvBankAccount2'", TextView.class);
    target.tvName2 = Utils.findRequiredViewAsType(source, R.id.tv_name_2, "field 'tvName2'", TextView.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.tvIdCard = Utils.findRequiredViewAsType(source, R.id.tv_id_card, "field 'tvIdCard'", TextView.class);
    target.tvLimit = Utils.findRequiredViewAsType(source, R.id.tv_limit, "field 'tvLimit'", TextView.class);
    target.tvBillDay = Utils.findRequiredViewAsType(source, R.id.tv_bill_day, "field 'tvBillDay'", TextView.class);
    target.tvPayDay = Utils.findRequiredViewAsType(source, R.id.tv_pay_day, "field 'tvPayDay'", TextView.class);
    target.tvDeadLine = Utils.findRequiredViewAsType(source, R.id.tv_dead_line, "field 'tvDeadLine'", TextView.class);
    target.tvCvv = Utils.findRequiredViewAsType(source, R.id.tv_cvv, "field 'tvCvv'", TextView.class);
    target.llBankInfo = Utils.findRequiredViewAsType(source, R.id.ll_bank_info, "field 'llBankInfo'", LinearLayout.class);
    target.etLimit = Utils.findRequiredViewAsType(source, R.id.et_limit, "field 'etLimit'", EditText.class);
    view = Utils.findRequiredView(source, R.id.et_bill_day, "field 'etBillDay' and method 'onViewClicked'");
    target.etBillDay = Utils.castView(view, R.id.et_bill_day, "field 'etBillDay'", TextView.class);
    view2131296503 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.et_pay_day, "field 'etPayDay' and method 'onViewClicked'");
    target.etPayDay = Utils.castView(view, R.id.et_pay_day, "field 'etPayDay'", TextView.class);
    view2131296526 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.llCardChange = Utils.findRequiredViewAsType(source, R.id.ll_card_change, "field 'llCardChange'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.btn_change, "field 'btnChange' and method 'onViewClicked'");
    target.btnChange = Utils.castView(view, R.id.btn_change, "field 'btnChange'", Button.class);
    view2131296364 = view;
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
    BankCreditDetailChangeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.tvBankName = null;
    target.tvBankAccount2 = null;
    target.tvName2 = null;
    target.tvPhone = null;
    target.tvIdCard = null;
    target.tvLimit = null;
    target.tvBillDay = null;
    target.tvPayDay = null;
    target.tvDeadLine = null;
    target.tvCvv = null;
    target.llBankInfo = null;
    target.etLimit = null;
    target.etBillDay = null;
    target.etPayDay = null;
    target.llCardChange = null;
    target.btnChange = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296503.setOnClickListener(null);
    view2131296503 = null;
    view2131296526.setOnClickListener(null);
    view2131296526 = null;
    view2131296364.setOnClickListener(null);
    view2131296364 = null;
  }
}
