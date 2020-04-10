// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
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

public class ApplyLordActivity_ViewBinding implements Unbinder {
  private ApplyLordActivity target;

  private View view2131296633;

  private View view2131296854;

  private View view2131296853;

  private View view2131296852;

  private View view2131296845;

  private View view2131296843;

  private View view2131296360;

  private View view2131296690;

  private View view2131296627;

  @UiThread
  public ApplyLordActivity_ViewBinding(ApplyLordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ApplyLordActivity_ViewBinding(final ApplyLordActivity target, View source) {
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
    target.tvLordLevel = Utils.findRequiredViewAsType(source, R.id.tv_lord_level, "field 'tvLordLevel'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ly_select_level, "field 'lySelectLevel' and method 'onViewClicked'");
    target.lySelectLevel = Utils.castView(view, R.id.ly_select_level, "field 'lySelectLevel'", LinearLayout.class);
    view2131296854 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvLordCity = Utils.findRequiredViewAsType(source, R.id.tv_lord_city, "field 'tvLordCity'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ly_select_city, "field 'lySelectCity' and method 'onViewClicked'");
    target.lySelectCity = Utils.castView(view, R.id.ly_select_city, "field 'lySelectCity'", LinearLayout.class);
    view2131296853 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvLordArea = Utils.findRequiredViewAsType(source, R.id.tv_lord_area, "field 'tvLordArea'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ly_select_area, "field 'lySelectArea' and method 'onViewClicked'");
    target.lySelectArea = Utils.castView(view, R.id.ly_select_area, "field 'lySelectArea'", LinearLayout.class);
    view2131296852 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvOfferNow = Utils.findRequiredViewAsType(source, R.id.tv_offer_now, "field 'tvOfferNow'", TextView.class);
    target.ivBankIcon = Utils.findRequiredViewAsType(source, R.id.iv_bank_icon, "field 'ivBankIcon'", FontIconView.class);
    view = Utils.findRequiredView(source, R.id.ly_bank_pay, "field 'lyBankPay' and method 'onViewClicked'");
    target.lyBankPay = Utils.castView(view, R.id.ly_bank_pay, "field 'lyBankPay'", LinearLayout.class);
    view2131296845 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ivBalanceIcon = Utils.findRequiredViewAsType(source, R.id.iv_balance_icon, "field 'ivBalanceIcon'", FontIconView.class);
    view = Utils.findRequiredView(source, R.id.ly_balance_pay, "field 'lyBalancePay' and method 'onViewClicked'");
    target.lyBalancePay = Utils.castView(view, R.id.ly_balance_pay, "field 'lyBalancePay'", LinearLayout.class);
    view2131296843 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvOffMoney = Utils.findRequiredViewAsType(source, R.id.tv_off_money, "field 'tvOffMoney'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_apply, "field 'btnApply' and method 'onViewClicked'");
    target.btnApply = Utils.castView(view, R.id.btn_apply, "field 'btnApply'", Button.class);
    view2131296360 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_reduce, "field 'ivReduce' and method 'onViewClicked'");
    target.ivReduce = Utils.castView(view, R.id.iv_reduce, "field 'ivReduce'", FontIconView.class);
    view2131296690 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_add, "field 'ivAdd' and method 'onViewClicked'");
    target.ivAdd = Utils.castView(view, R.id.iv_add, "field 'ivAdd'", FontIconView.class);
    view2131296627 = view;
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
    ApplyLordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.tvLordLevel = null;
    target.lySelectLevel = null;
    target.tvLordCity = null;
    target.lySelectCity = null;
    target.tvLordArea = null;
    target.lySelectArea = null;
    target.tvOfferNow = null;
    target.ivBankIcon = null;
    target.lyBankPay = null;
    target.ivBalanceIcon = null;
    target.lyBalancePay = null;
    target.tvOffMoney = null;
    target.btnApply = null;
    target.ivReduce = null;
    target.ivAdd = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296854.setOnClickListener(null);
    view2131296854 = null;
    view2131296853.setOnClickListener(null);
    view2131296853 = null;
    view2131296852.setOnClickListener(null);
    view2131296852 = null;
    view2131296845.setOnClickListener(null);
    view2131296845 = null;
    view2131296843.setOnClickListener(null);
    view2131296843 = null;
    view2131296360.setOnClickListener(null);
    view2131296360 = null;
    view2131296690.setOnClickListener(null);
    view2131296690 = null;
    view2131296627.setOnClickListener(null);
    view2131296627 = null;
  }
}
