// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DebitBankActivity_ViewBinding implements Unbinder {
  private DebitBankActivity target;

  private View view2131296633;

  private View view2131297433;

  @UiThread
  public DebitBankActivity_ViewBinding(DebitBankActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DebitBankActivity_ViewBinding(final DebitBankActivity target, View source) {
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
    target.tvBankName = Utils.findRequiredViewAsType(source, R.id.tv_bankName, "field 'tvBankName'", TextView.class);
    target.tvDebitCardName = Utils.findRequiredViewAsType(source, R.id.tv_debit_card_name, "field 'tvDebitCardName'", TextView.class);
    target.tvDebitCardAccount = Utils.findRequiredViewAsType(source, R.id.tv_debit_card_account, "field 'tvDebitCardAccount'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_update, "field 'tvUpdate' and method 'onViewClicked'");
    target.tvUpdate = Utils.castView(view, R.id.tv_update, "field 'tvUpdate'", TextView.class);
    view2131297433 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.rlDebitCard = Utils.findRequiredViewAsType(source, R.id.rl_debit_card, "field 'rlDebitCard'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DebitBankActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.ivBankIcon = null;
    target.tvBankName = null;
    target.tvDebitCardName = null;
    target.tvDebitCardAccount = null;
    target.tvUpdate = null;
    target.rlDebitCard = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131297433.setOnClickListener(null);
    view2131297433 = null;
  }
}
