// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Group;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyWalletActivity_ViewBinding implements Unbinder {
  private MyWalletActivity target;

  private View view2131296633;

  private View view2131296391;

  private View view2131296450;

  private View view2131296452;

  private View view2131296451;

  private View view2131296441;

  @UiThread
  public MyWalletActivity_ViewBinding(MyWalletActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyWalletActivity_ViewBinding(final MyWalletActivity target, View source) {
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
    target.tvBenefit = Utils.findRequiredViewAsType(source, R.id.tv_benefit, "field 'tvBenefit'", TextView.class);
    target.rlTop = Utils.findRequiredViewAsType(source, R.id.rl_top, "field 'rlTop'", ConstraintLayout.class);
    view = Utils.findRequiredView(source, R.id.btn_withdraw, "field 'btnWithdraw' and method 'onViewClicked'");
    target.btnWithdraw = Utils.castView(view, R.id.btn_withdraw, "field 'btnWithdraw'", Button.class);
    view2131296391 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvBenefitTitle = Utils.findRequiredViewAsType(source, R.id.tv_benefit_title, "field 'tvBenefitTitle'", TextView.class);
    target.tvCommissionTitle = Utils.findRequiredViewAsType(source, R.id.tv_commission_title, "field 'tvCommissionTitle'", TextView.class);
    target.tvTodayBenefit = Utils.findRequiredViewAsType(source, R.id.tv_today_benefit, "field 'tvTodayBenefit'", TextView.class);
    view = Utils.findRequiredView(source, R.id.cl_today_benefit, "field 'clTodayBenefit' and method 'onViewClicked'");
    target.clTodayBenefit = Utils.castView(view, R.id.cl_today_benefit, "field 'clTodayBenefit'", ConstraintLayout.class);
    view2131296450 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvProfitTitle = Utils.findRequiredViewAsType(source, R.id.tv_profit_title, "field 'tvProfitTitle'", TextView.class);
    target.tvYesterdayBenefit = Utils.findRequiredViewAsType(source, R.id.tv_yesterday_benefit, "field 'tvYesterdayBenefit'", TextView.class);
    view = Utils.findRequiredView(source, R.id.cl_yesterday_benefit, "field 'clYesterdayBenefit' and method 'onViewClicked'");
    target.clYesterdayBenefit = Utils.castView(view, R.id.cl_yesterday_benefit, "field 'clYesterdayBenefit'", ConstraintLayout.class);
    view2131296452 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvTotalPrice = Utils.findRequiredViewAsType(source, R.id.tv_total_price, "field 'tvTotalPrice'", TextView.class);
    view = Utils.findRequiredView(source, R.id.cl_total_price, "field 'clTotalPrice' and method 'onViewClicked'");
    target.clTotalPrice = Utils.castView(view, R.id.cl_total_price, "field 'clTotalPrice'", ConstraintLayout.class);
    view2131296451 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvWithdrewTitle = Utils.findRequiredViewAsType(source, R.id.tv_withdrew_title, "field 'tvWithdrewTitle'", TextView.class);
    target.tvCompanyBenefit = Utils.findRequiredViewAsType(source, R.id.tv_company_benefit, "field 'tvCompanyBenefit'", TextView.class);
    view = Utils.findRequiredView(source, R.id.cl_company_benefit, "field 'clCompanyBenefit' and method 'onViewClicked'");
    target.clCompanyBenefit = Utils.castView(view, R.id.cl_company_benefit, "field 'clCompanyBenefit'", ConstraintLayout.class);
    view2131296441 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.groupBenefit = Utils.findRequiredViewAsType(source, R.id.group_benefit, "field 'groupBenefit'", Group.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyWalletActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.tvBenefit = null;
    target.rlTop = null;
    target.btnWithdraw = null;
    target.tvBenefitTitle = null;
    target.tvCommissionTitle = null;
    target.tvTodayBenefit = null;
    target.clTodayBenefit = null;
    target.tvProfitTitle = null;
    target.tvYesterdayBenefit = null;
    target.clYesterdayBenefit = null;
    target.tvTotalPrice = null;
    target.clTotalPrice = null;
    target.tvWithdrewTitle = null;
    target.tvCompanyBenefit = null;
    target.clCompanyBenefit = null;
    target.groupBenefit = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296391.setOnClickListener(null);
    view2131296391 = null;
    view2131296450.setOnClickListener(null);
    view2131296450 = null;
    view2131296452.setOnClickListener(null);
    view2131296452 = null;
    view2131296451.setOnClickListener(null);
    view2131296451 = null;
    view2131296441.setOnClickListener(null);
    view2131296441 = null;
  }
}
