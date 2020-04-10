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

public class WithdrawalActivity_ViewBinding implements Unbinder {
  private WithdrawalActivity target;

  private View view2131296633;

  private View view2131297368;

  private View view2131297134;

  private View view2131296387;

  @UiThread
  public WithdrawalActivity_ViewBinding(WithdrawalActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WithdrawalActivity_ViewBinding(final WithdrawalActivity target, View source) {
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
    view = Utils.findRequiredView(source, R.id.tv_right, "field 'tvRight' and method 'onViewClicked'");
    target.tvRight = Utils.castView(view, R.id.tv_right, "field 'tvRight'", TextView.class);
    view2131297368 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ivRight = Utils.findRequiredViewAsType(source, R.id.iv_right, "field 'ivRight'", ImageView.class);
    target.textView01 = Utils.findRequiredViewAsType(source, R.id.textView01, "field 'textView01'", TextView.class);
    target.tvBankAccount = Utils.findRequiredViewAsType(source, R.id.tv_bank_account, "field 'tvBankAccount'", TextView.class);
    target.textView02 = Utils.findRequiredViewAsType(source, R.id.textView02, "field 'textView02'", TextView.class);
    target.etMoney = Utils.findRequiredViewAsType(source, R.id.et_money, "field 'etMoney'", EditText.class);
    target.imageView01 = Utils.findRequiredView(source, R.id.imageView01, "field 'imageView01'");
    target.tvMoney = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tvMoney'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_allMoney, "field 'tvAllMoney' and method 'onViewClicked'");
    target.tvAllMoney = Utils.castView(view, R.id.tv_allMoney, "field 'tvAllMoney'", TextView.class);
    view2131297134 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_submit, "field 'btnSubmit' and method 'onViewClicked'");
    target.btnSubmit = Utils.castView(view, R.id.btn_submit, "field 'btnSubmit'", Button.class);
    view2131296387 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.bankIv = Utils.findRequiredViewAsType(source, R.id.bank_iv, "field 'bankIv'", ImageView.class);
    target.bankTv = Utils.findRequiredViewAsType(source, R.id.bank_tv, "field 'bankTv'", TextView.class);
    target.bankLlt = Utils.findRequiredViewAsType(source, R.id.bank_llt, "field 'bankLlt'", LinearLayout.class);
    target.tvFee = Utils.findRequiredViewAsType(source, R.id.tv_fee, "field 'tvFee'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WithdrawalActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.textView01 = null;
    target.tvBankAccount = null;
    target.textView02 = null;
    target.etMoney = null;
    target.imageView01 = null;
    target.tvMoney = null;
    target.tvAllMoney = null;
    target.btnSubmit = null;
    target.bankIv = null;
    target.bankTv = null;
    target.bankLlt = null;
    target.tvFee = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131297368.setOnClickListener(null);
    view2131297368 = null;
    view2131297134.setOnClickListener(null);
    view2131297134 = null;
    view2131296387.setOnClickListener(null);
    view2131296387 = null;
  }
}
