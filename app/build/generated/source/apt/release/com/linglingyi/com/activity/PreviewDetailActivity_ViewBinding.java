// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PreviewDetailActivity_ViewBinding implements Unbinder {
  private PreviewDetailActivity target;

  private View view2131296633;

  private View view2131297418;

  private View view2131296352;

  @UiThread
  public PreviewDetailActivity_ViewBinding(PreviewDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PreviewDetailActivity_ViewBinding(final PreviewDetailActivity target, View source) {
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
    target.rlHeader = Utils.findRequiredViewAsType(source, R.id.rl_header, "field 'rlHeader'", RelativeLayout.class);
    target.ivBankIcon = Utils.findRequiredViewAsType(source, R.id.iv_bank_icon, "field 'ivBankIcon'", ImageView.class);
    target.tvBankName = Utils.findRequiredViewAsType(source, R.id.tv_bank_name, "field 'tvBankName'", TextView.class);
    target.llBankName = Utils.findRequiredViewAsType(source, R.id.ll_bank_name, "field 'llBankName'", LinearLayout.class);
    target.tvPayTitle = Utils.findRequiredViewAsType(source, R.id.tv_pay_title, "field 'tvPayTitle'", TextView.class);
    target.tvBankAccount = Utils.findRequiredViewAsType(source, R.id.tv_bank_account, "field 'tvBankAccount'", TextView.class);
    target.tvRepayCycle = Utils.findRequiredViewAsType(source, R.id.tv_repayCycle, "field 'tvRepayCycle'", TextView.class);
    target.bindItem = Utils.findRequiredViewAsType(source, R.id.bind_item, "field 'bindItem'", LinearLayout.class);
    target.tvChannelName = Utils.findRequiredViewAsType(source, R.id.tv_channel_name, "field 'tvChannelName'", TextView.class);
    target.tvLimit = Utils.findRequiredViewAsType(source, R.id.tv_limit, "field 'tvLimit'", TextView.class);
    target.tvTotalPrice = Utils.findRequiredViewAsType(source, R.id.tv_total_price, "field 'tvTotalPrice'", TextView.class);
    target.llTotalPrice = Utils.findRequiredViewAsType(source, R.id.ll_total_price, "field 'llTotalPrice'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_total_service_fee, "field 'tvTotalServiceFee' and method 'onViewClicked'");
    target.tvTotalServiceFee = Utils.castView(view, R.id.tv_total_service_fee, "field 'tvTotalServiceFee'", TextView.class);
    view2131297418 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.llTotalServiceFee = Utils.findRequiredViewAsType(source, R.id.ll_total_service_fee, "field 'llTotalServiceFee'", LinearLayout.class);
    target.lvPlanDetail = Utils.findRequiredViewAsType(source, R.id.lv_plan_detail, "field 'lvPlanDetail'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.bt_submit_plan, "field 'btSubmitPlan' and method 'onViewClicked'");
    target.btSubmitPlan = Utils.castView(view, R.id.bt_submit_plan, "field 'btSubmitPlan'", Button.class);
    view2131296352 = view;
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
    PreviewDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.rlHeader = null;
    target.ivBankIcon = null;
    target.tvBankName = null;
    target.llBankName = null;
    target.tvPayTitle = null;
    target.tvBankAccount = null;
    target.tvRepayCycle = null;
    target.bindItem = null;
    target.tvChannelName = null;
    target.tvLimit = null;
    target.tvTotalPrice = null;
    target.llTotalPrice = null;
    target.tvTotalServiceFee = null;
    target.llTotalServiceFee = null;
    target.lvPlanDetail = null;
    target.btSubmitPlan = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131297418.setOnClickListener(null);
    view2131297418 = null;
    view2131296352.setOnClickListener(null);
    view2131296352 = null;
  }
}
