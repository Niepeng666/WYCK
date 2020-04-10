// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LookDataActivity_ViewBinding implements Unbinder {
  private LookDataActivity target;

  private View view2131296633;

  @UiThread
  public LookDataActivity_ViewBinding(LookDataActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LookDataActivity_ViewBinding(final LookDataActivity target, View source) {
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
    target.ivBankIcon = Utils.findRequiredViewAsType(source, R.id.iv_bank_icon, "field 'ivBankIcon'", ImageView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvBankAccount = Utils.findRequiredViewAsType(source, R.id.tv_bank_account, "field 'tvBankAccount'", TextView.class);
    target.clBankHead = Utils.findRequiredViewAsType(source, R.id.cl_bank_head, "field 'clBankHead'", ConstraintLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LookDataActivity target = this.target;
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
    target.ivBankIcon = null;
    target.tvName = null;
    target.tvBankAccount = null;
    target.clBankHead = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
  }
}
