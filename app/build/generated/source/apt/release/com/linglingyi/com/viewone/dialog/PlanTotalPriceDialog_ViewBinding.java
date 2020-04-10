// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.viewone.dialog;

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

public class PlanTotalPriceDialog_ViewBinding implements Unbinder {
  private PlanTotalPriceDialog target;

  private View view2131296642;

  @UiThread
  public PlanTotalPriceDialog_ViewBinding(final PlanTotalPriceDialog target, View source) {
    this.target = target;

    View view;
    target.tvWorkingFund = Utils.findRequiredViewAsType(source, R.id.tv_workingFund, "field 'tvWorkingFund'", TextView.class);
    target.tvRepaymentFee = Utils.findRequiredViewAsType(source, R.id.tv_repayment_fee, "field 'tvRepaymentFee'", TextView.class);
    target.tvPayTimeFee = Utils.findRequiredViewAsType(source, R.id.tv_pay_time_fee, "field 'tvPayTimeFee'", TextView.class);
    target.tvTotalPrice = Utils.findRequiredViewAsType(source, R.id.tv_total_price, "field 'tvTotalPrice'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_close, "field 'ivClose' and method 'onViewClicked'");
    target.ivClose = Utils.castView(view, R.id.iv_close, "field 'ivClose'", ImageView.class);
    view2131296642 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.llWorkingfound = Utils.findRequiredViewAsType(source, R.id.ll_workingfound, "field 'llWorkingfound'", RelativeLayout.class);
    target.tvTotalTitle = Utils.findRequiredViewAsType(source, R.id.tv_total_title, "field 'tvTotalTitle'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PlanTotalPriceDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvWorkingFund = null;
    target.tvRepaymentFee = null;
    target.tvPayTimeFee = null;
    target.tvTotalPrice = null;
    target.ivClose = null;
    target.llWorkingfound = null;
    target.tvTotalTitle = null;

    view2131296642.setOnClickListener(null);
    view2131296642 = null;
  }
}
