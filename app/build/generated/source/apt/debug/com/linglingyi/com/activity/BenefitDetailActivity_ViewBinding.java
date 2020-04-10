// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
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

public class BenefitDetailActivity_ViewBinding implements Unbinder {
  private BenefitDetailActivity target;

  private View view2131296633;

  @UiThread
  public BenefitDetailActivity_ViewBinding(BenefitDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BenefitDetailActivity_ViewBinding(final BenefitDetailActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'ivBack' and method 'onViewClicked'");
    target.ivBack = Utils.castView(view, R.id.iv_back, "field 'ivBack'", ImageView.class);
    view2131296633 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'tvRight'", TextView.class);
    target.ivRight = Utils.findRequiredViewAsType(source, R.id.iv_right, "field 'ivRight'", ImageView.class);
    target.tvMoneyType = Utils.findRequiredViewAsType(source, R.id.tv_money_type, "field 'tvMoneyType'", TextView.class);
    target.tvMoneyFrom = Utils.findRequiredViewAsType(source, R.id.tv_money_from, "field 'tvMoneyFrom'", TextView.class);
    target.tvMoney = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tvMoney'", TextView.class);
    target.tvDate = Utils.findRequiredViewAsType(source, R.id.tv_date, "field 'tvDate'", TextView.class);
    target.tvMoneyBenefit = Utils.findRequiredViewAsType(source, R.id.tv_money_benefit, "field 'tvMoneyBenefit'", TextView.class);
    target.tvTypeName = Utils.findRequiredViewAsType(source, R.id.tv_type_name, "field 'tvTypeName'", TextView.class);
    target.tvFromName = Utils.findRequiredViewAsType(source, R.id.tv_from_name, "field 'tvFromName'", TextView.class);
    target.llMoney = Utils.findRequiredViewAsType(source, R.id.ll_money, "field 'llMoney'", LinearLayout.class);
    target.tvBenefitName = Utils.findRequiredViewAsType(source, R.id.tv_benefit_name, "field 'tvBenefitName'", TextView.class);
    target.view = Utils.findRequiredView(source, R.id.view, "field 'view'");
    target.tvTradeDateTitle = Utils.findRequiredViewAsType(source, R.id.tv_trade_date_title, "field 'tvTradeDateTitle'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BenefitDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.tvMoneyType = null;
    target.tvMoneyFrom = null;
    target.tvMoney = null;
    target.tvDate = null;
    target.tvMoneyBenefit = null;
    target.tvTypeName = null;
    target.tvFromName = null;
    target.llMoney = null;
    target.tvBenefitName = null;
    target.view = null;
    target.tvTradeDateTitle = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
  }
}
