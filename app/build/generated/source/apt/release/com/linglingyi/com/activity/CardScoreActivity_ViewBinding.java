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
import com.linglingyi.com.viewone.FontIconView;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CardScoreActivity_ViewBinding implements Unbinder {
  private CardScoreActivity target;

  private View view2131296633;

  private View view2131297368;

  private View view2131297363;

  private View view2131296365;

  private View view2131296431;

  @UiThread
  public CardScoreActivity_ViewBinding(CardScoreActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CardScoreActivity_ViewBinding(final CardScoreActivity target, View source) {
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
    target.tvHistory = Utils.findRequiredViewAsType(source, R.id.tv_history, "field 'tvHistory'", TextView.class);
    target.etName = Utils.findRequiredViewAsType(source, R.id.et_name, "field 'etName'", EditText.class);
    target.etIdCard = Utils.findRequiredViewAsType(source, R.id.et_id_card, "field 'etIdCard'", EditText.class);
    target.etBankAccount = Utils.findRequiredViewAsType(source, R.id.et_bank_account, "field 'etBankAccount'", EditText.class);
    target.tvMoney = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tvMoney'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_registration_protocol, "field 'tvRegistrationProtocol' and method 'onViewClicked'");
    target.tvRegistrationProtocol = Utils.castView(view, R.id.tv_registration_protocol, "field 'tvRegistrationProtocol'", TextView.class);
    view2131297363 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.lyCheck = Utils.findRequiredViewAsType(source, R.id.ly_check, "field 'lyCheck'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.btn_check, "field 'btnCheck' and method 'onViewClicked'");
    target.btnCheck = Utils.castView(view, R.id.btn_check, "field 'btnCheck'", Button.class);
    view2131296365 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.llInfo = Utils.findRequiredViewAsType(source, R.id.ll_info, "field 'llInfo'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.checkbox, "field 'checkbox' and method 'onViewClicked'");
    target.checkbox = Utils.castView(view, R.id.checkbox, "field 'checkbox'", FontIconView.class);
    view2131296431 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etPhone = Utils.findRequiredViewAsType(source, R.id.et_phone, "field 'etPhone'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CardScoreActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.tvHistory = null;
    target.etName = null;
    target.etIdCard = null;
    target.etBankAccount = null;
    target.tvMoney = null;
    target.tvRegistrationProtocol = null;
    target.lyCheck = null;
    target.btnCheck = null;
    target.llInfo = null;
    target.checkbox = null;
    target.etPhone = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131297368.setOnClickListener(null);
    view2131297368 = null;
    view2131297363.setOnClickListener(null);
    view2131297363 = null;
    view2131296365.setOnClickListener(null);
    view2131296365 = null;
    view2131296431.setOnClickListener(null);
    view2131296431 = null;
  }
}
