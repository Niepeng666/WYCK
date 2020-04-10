// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
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

public class WorkingFundActivity_ViewBinding implements Unbinder {
  private WorkingFundActivity target;

  private View view2131296633;

  private View view2131296359;

  @UiThread
  public WorkingFundActivity_ViewBinding(WorkingFundActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WorkingFundActivity_ViewBinding(final WorkingFundActivity target, View source) {
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
    target.tvProFee = Utils.findRequiredViewAsType(source, R.id.tv_pro_fee, "field 'tvProFee'", TextView.class);
    target.tvServiceFee = Utils.findRequiredViewAsType(source, R.id.tv_service_fee, "field 'tvServiceFee'", TextView.class);
    target.tvWorkingFund = Utils.findRequiredViewAsType(source, R.id.tv_working_fund, "field 'tvWorkingFund'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_admit, "field 'btnAdmit' and method 'onViewClicked'");
    target.btnAdmit = Utils.castView(view, R.id.btn_admit, "field 'btnAdmit'", Button.class);
    view2131296359 = view;
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
    WorkingFundActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.tvProFee = null;
    target.tvServiceFee = null;
    target.tvWorkingFund = null;
    target.btnAdmit = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296359.setOnClickListener(null);
    view2131296359 = null;
  }
}
