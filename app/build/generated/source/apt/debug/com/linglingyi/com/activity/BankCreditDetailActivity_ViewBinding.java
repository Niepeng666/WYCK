// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BankCreditDetailActivity_ViewBinding implements Unbinder {
  private BankCreditDetailActivity target;

  private View view2131296633;

  private View view2131296364;

  private View view2131296367;

  @UiThread
  public BankCreditDetailActivity_ViewBinding(BankCreditDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BankCreditDetailActivity_ViewBinding(final BankCreditDetailActivity target, View source) {
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
    target.ivBankIcon = Utils.findRequiredViewAsType(source, R.id.iv_bank_icon, "field 'ivBankIcon'", ImageView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvBankAccount = Utils.findRequiredViewAsType(source, R.id.tv_bank_account, "field 'tvBankAccount'", TextView.class);
    target.clBankHead = Utils.findRequiredViewAsType(source, R.id.cl_bank_head, "field 'clBankHead'", ConstraintLayout.class);
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
    view = Utils.findRequiredView(source, R.id.btn_change, "field 'btnChange' and method 'onViewClicked'");
    target.btnChange = Utils.castView(view, R.id.btn_change, "field 'btnChange'", Button.class);
    view2131296364 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_del, "field 'btnDel' and method 'onViewClicked'");
    target.btnDel = Utils.castView(view, R.id.btn_del, "field 'btnDel'", Button.class);
    view2131296367 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.rl = Utils.findRequiredViewAsType(source, R.id.rl, "field 'rl'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BankCreditDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.ivBankIcon = null;
    target.tvName = null;
    target.tvBankAccount = null;
    target.clBankHead = null;
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
    target.btnChange = null;
    target.btnDel = null;
    target.rl = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296364.setOnClickListener(null);
    view2131296364 = null;
    view2131296367.setOnClickListener(null);
    view2131296367 = null;
  }
}
