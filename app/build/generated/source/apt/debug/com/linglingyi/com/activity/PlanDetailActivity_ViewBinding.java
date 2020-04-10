// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.linglingyi.com.viewone.MyListView;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PlanDetailActivity_ViewBinding implements Unbinder {
  private PlanDetailActivity target;

  private View view2131296633;

  private View view2131297368;

  private View view2131296386;

  private View view2131296476;

  @UiThread
  public PlanDetailActivity_ViewBinding(PlanDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PlanDetailActivity_ViewBinding(final PlanDetailActivity target, View source) {
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
    target.ivBankIcon = Utils.findRequiredViewAsType(source, R.id.iv_bank_icon, "field 'ivBankIcon'", ImageView.class);
    target.tvBankName = Utils.findRequiredViewAsType(source, R.id.tv_bank_name, "field 'tvBankName'", TextView.class);
    target.llBankName = Utils.findRequiredViewAsType(source, R.id.ll_bank_name, "field 'llBankName'", LinearLayout.class);
    target.tvPayTitle = Utils.findRequiredViewAsType(source, R.id.tv_pay_title, "field 'tvPayTitle'", TextView.class);
    target.tvBankAccount = Utils.findRequiredViewAsType(source, R.id.tv_bank_account, "field 'tvBankAccount'", TextView.class);
    target.tvRepayCycle = Utils.findRequiredViewAsType(source, R.id.tv_repayCycle, "field 'tvRepayCycle'", TextView.class);
    target.bindItem = Utils.findRequiredViewAsType(source, R.id.bind_item, "field 'bindItem'", LinearLayout.class);
    target.tvPreRepayAmount = Utils.findRequiredViewAsType(source, R.id.tv_preRepayAmount, "field 'tvPreRepayAmount'", TextView.class);
    target.tvPayedAmount = Utils.findRequiredViewAsType(source, R.id.tv_payed_amount, "field 'tvPayedAmount'", TextView.class);
    target.tvOrderStatus = Utils.findRequiredViewAsType(source, R.id.tv_orderStatus, "field 'tvOrderStatus'", TextView.class);
    target.tvPlanProgress = Utils.findRequiredViewAsType(source, R.id.tv_planProgress, "field 'tvPlanProgress'", TextView.class);
    target.tvChannelName = Utils.findRequiredViewAsType(source, R.id.tv_channel_name, "field 'tvChannelName'", TextView.class);
    target.tvPayType = Utils.findRequiredViewAsType(source, R.id.tv_payType, "field 'tvPayType'", TextView.class);
    target.tvTotalPrice = Utils.findRequiredViewAsType(source, R.id.tv_total_price, "field 'tvTotalPrice'", TextView.class);
    target.tvPayFee = Utils.findRequiredViewAsType(source, R.id.tv_pay_fee, "field 'tvPayFee'", TextView.class);
    target.tvTimesFee = Utils.findRequiredViewAsType(source, R.id.tv_times_fee, "field 'tvTimesFee'", TextView.class);
    target.tvDecreaseMoney = Utils.findRequiredViewAsType(source, R.id.tv_decrease_money, "field 'tvDecreaseMoney'", TextView.class);
    target.myListView = Utils.findRequiredViewAsType(source, R.id.myListView, "field 'myListView'", MyListView.class);
    view = Utils.findRequiredView(source, R.id.btn_stop_start, "field 'btnStopStart' and method 'onViewClicked'");
    target.btnStopStart = Utils.castView(view, R.id.btn_stop_start, "field 'btnStopStart'", Button.class);
    view2131296386 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.delete_btn, "field 'deleteBtn' and method 'onViewClicked'");
    target.deleteBtn = Utils.castView(view, R.id.delete_btn, "field 'deleteBtn'", Button.class);
    view2131296476 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.scrollView = Utils.findRequiredViewAsType(source, R.id.scrollView, "field 'scrollView'", ScrollView.class);
    target.tvLevelFee = Utils.findRequiredViewAsType(source, R.id.tv_level_fee, "field 'tvLevelFee'", TextView.class);
    target.llBtn = Utils.findRequiredViewAsType(source, R.id.ll_btn, "field 'llBtn'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PlanDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.ivBankIcon = null;
    target.tvBankName = null;
    target.llBankName = null;
    target.tvPayTitle = null;
    target.tvBankAccount = null;
    target.tvRepayCycle = null;
    target.bindItem = null;
    target.tvPreRepayAmount = null;
    target.tvPayedAmount = null;
    target.tvOrderStatus = null;
    target.tvPlanProgress = null;
    target.tvChannelName = null;
    target.tvPayType = null;
    target.tvTotalPrice = null;
    target.tvPayFee = null;
    target.tvTimesFee = null;
    target.tvDecreaseMoney = null;
    target.myListView = null;
    target.btnStopStart = null;
    target.deleteBtn = null;
    target.scrollView = null;
    target.tvLevelFee = null;
    target.llBtn = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131297368.setOnClickListener(null);
    view2131297368 = null;
    view2131296386.setOnClickListener(null);
    view2131296386 = null;
    view2131296476.setOnClickListener(null);
    view2131296476 = null;
  }
}
