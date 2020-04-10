// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
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

public class SwipeCardActivity_ViewBinding implements Unbinder {
  private SwipeCardActivity target;

  private View view2131296633;

  private View view2131296401;

  private View view2131296397;

  private View view2131296395;

  private View view2131296396;

  private View view2131296398;

  private View view2131296402;

  private View view2131296399;

  private View view2131296406;

  private View view2131296404;

  private View view2131296400;

  private View view2131296403;

  private View view2131296405;

  private View view2131296795;

  @UiThread
  public SwipeCardActivity_ViewBinding(SwipeCardActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SwipeCardActivity_ViewBinding(final SwipeCardActivity target, View source) {
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
    target.bankIv = Utils.findRequiredViewAsType(source, R.id.bank_iv, "field 'bankIv'", ImageView.class);
    target.bankTv = Utils.findRequiredViewAsType(source, R.id.bank_tv, "field 'bankTv'", TextView.class);
    target.tvBankAccount = Utils.findRequiredViewAsType(source, R.id.tv_bank_account, "field 'tvBankAccount'", TextView.class);
    target.llBank = Utils.findRequiredViewAsType(source, R.id.ll_bank, "field 'llBank'", LinearLayout.class);
    target.shoukuan = Utils.findRequiredViewAsType(source, R.id.shoukuan, "field 'shoukuan'", TextView.class);
    target.tvMoney = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tvMoney'", EditText.class);
    target.llMoney = Utils.findRequiredViewAsType(source, R.id.ll_money, "field 'llMoney'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.calculator_seven_menu, "field 'calculatorSevenMenu' and method 'onViewClicked'");
    target.calculatorSevenMenu = Utils.castView(view, R.id.calculator_seven_menu, "field 'calculatorSevenMenu'", LinearLayout.class);
    view2131296401 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.calculator_four_menu, "field 'calculatorFourMenu' and method 'onViewClicked'");
    target.calculatorFourMenu = Utils.castView(view, R.id.calculator_four_menu, "field 'calculatorFourMenu'", LinearLayout.class);
    view2131296397 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.calculator_eight_menu, "field 'calculatorEightMenu' and method 'onViewClicked'");
    target.calculatorEightMenu = Utils.castView(view, R.id.calculator_eight_menu, "field 'calculatorEightMenu'", LinearLayout.class);
    view2131296395 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.calculator_five_menu, "field 'calculatorFiveMenu' and method 'onViewClicked'");
    target.calculatorFiveMenu = Utils.castView(view, R.id.calculator_five_menu, "field 'calculatorFiveMenu'", LinearLayout.class);
    view2131296396 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.calculator_nine_menu, "field 'calculatorNineMenu' and method 'onViewClicked'");
    target.calculatorNineMenu = Utils.castView(view, R.id.calculator_nine_menu, "field 'calculatorNineMenu'", LinearLayout.class);
    view2131296398 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.calculator_six_menu, "field 'calculatorSixMenu' and method 'onViewClicked'");
    target.calculatorSixMenu = Utils.castView(view, R.id.calculator_six_menu, "field 'calculatorSixMenu'", LinearLayout.class);
    view2131296402 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.number1Ll = Utils.findRequiredViewAsType(source, R.id.number1_ll, "field 'number1Ll'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.calculator_one_menu, "field 'calculatorOneMenu' and method 'onViewClicked'");
    target.calculatorOneMenu = Utils.castView(view, R.id.calculator_one_menu, "field 'calculatorOneMenu'", LinearLayout.class);
    view2131296399 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.calculator_zero_menu, "field 'calculatorZeroMenu' and method 'onViewClicked'");
    target.calculatorZeroMenu = Utils.castView(view, R.id.calculator_zero_menu, "field 'calculatorZeroMenu'", LinearLayout.class);
    view2131296406 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.calculator_two_menu, "field 'calculatorTwoMenu' and method 'onViewClicked'");
    target.calculatorTwoMenu = Utils.castView(view, R.id.calculator_two_menu, "field 'calculatorTwoMenu'", LinearLayout.class);
    view2131296404 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.calculator_point_menu, "field 'calculatorPointMenu' and method 'onViewClicked'");
    target.calculatorPointMenu = Utils.castView(view, R.id.calculator_point_menu, "field 'calculatorPointMenu'", LinearLayout.class);
    view2131296400 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.calculator_three_menu, "field 'calculatorThreeMenu' and method 'onViewClicked'");
    target.calculatorThreeMenu = Utils.castView(view, R.id.calculator_three_menu, "field 'calculatorThreeMenu'", LinearLayout.class);
    view2131296403 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.calculator_twozero_menu, "field 'calculatorTwozeroMenu' and method 'onViewClicked'");
    target.calculatorTwozeroMenu = Utils.castView(view, R.id.calculator_twozero_menu, "field 'calculatorTwozeroMenu'", LinearLayout.class);
    view2131296405 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.number2Ll = Utils.findRequiredViewAsType(source, R.id.number2_ll, "field 'number2Ll'", LinearLayout.class);
    target.numberLl = Utils.findRequiredViewAsType(source, R.id.number_ll, "field 'numberLl'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.ll_pay, "method 'onViewClicked'");
    view2131296795 = view;
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
    SwipeCardActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.bankIv = null;
    target.bankTv = null;
    target.tvBankAccount = null;
    target.llBank = null;
    target.shoukuan = null;
    target.tvMoney = null;
    target.llMoney = null;
    target.calculatorSevenMenu = null;
    target.calculatorFourMenu = null;
    target.calculatorEightMenu = null;
    target.calculatorFiveMenu = null;
    target.calculatorNineMenu = null;
    target.calculatorSixMenu = null;
    target.number1Ll = null;
    target.calculatorOneMenu = null;
    target.calculatorZeroMenu = null;
    target.calculatorTwoMenu = null;
    target.calculatorPointMenu = null;
    target.calculatorThreeMenu = null;
    target.calculatorTwozeroMenu = null;
    target.number2Ll = null;
    target.numberLl = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296401.setOnClickListener(null);
    view2131296401 = null;
    view2131296397.setOnClickListener(null);
    view2131296397 = null;
    view2131296395.setOnClickListener(null);
    view2131296395 = null;
    view2131296396.setOnClickListener(null);
    view2131296396 = null;
    view2131296398.setOnClickListener(null);
    view2131296398 = null;
    view2131296402.setOnClickListener(null);
    view2131296402 = null;
    view2131296399.setOnClickListener(null);
    view2131296399 = null;
    view2131296406.setOnClickListener(null);
    view2131296406 = null;
    view2131296404.setOnClickListener(null);
    view2131296404 = null;
    view2131296400.setOnClickListener(null);
    view2131296400 = null;
    view2131296403.setOnClickListener(null);
    view2131296403 = null;
    view2131296405.setOnClickListener(null);
    view2131296405 = null;
    view2131296795.setOnClickListener(null);
    view2131296795 = null;
  }
}
