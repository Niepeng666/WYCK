// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
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

public class PreviewPlanActivity_ViewBinding implements Unbinder {
  private PreviewPlanActivity target;

  private View view2131296633;

  private View view2131296347;

  private View view2131296340;

  private View view2131296387;

  @UiThread
  public PreviewPlanActivity_ViewBinding(PreviewPlanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PreviewPlanActivity_ViewBinding(final PreviewPlanActivity target, View source) {
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
    target.ivBankIcon = Utils.findRequiredViewAsType(source, R.id.iv_bank_icon, "field 'ivBankIcon'", ImageView.class);
    target.tvBankName = Utils.findRequiredViewAsType(source, R.id.tv_bank_name, "field 'tvBankName'", TextView.class);
    target.llBankName = Utils.findRequiredViewAsType(source, R.id.ll_bank_name, "field 'llBankName'", LinearLayout.class);
    target.tvBankAccount = Utils.findRequiredViewAsType(source, R.id.tv_bank_account, "field 'tvBankAccount'", TextView.class);
    target.tvUserName = Utils.findRequiredViewAsType(source, R.id.tv_userName, "field 'tvUserName'", TextView.class);
    target.tvLimit = Utils.findRequiredViewAsType(source, R.id.tv_limit, "field 'tvLimit'", TextView.class);
    target.tvBillDay = Utils.findRequiredViewAsType(source, R.id.tv_billDay, "field 'tvBillDay'", TextView.class);
    target.tvPayDay = Utils.findRequiredViewAsType(source, R.id.tv_payDay, "field 'tvPayDay'", TextView.class);
    target.bindItem = Utils.findRequiredViewAsType(source, R.id.bind_item, "field 'bindItem'", LinearLayout.class);
    target.tvLimit2 = Utils.findRequiredViewAsType(source, R.id.tv_limit2, "field 'tvLimit2'", TextView.class);
    target.tvRepayCyc = Utils.findRequiredViewAsType(source, R.id.tv_repay_cyc, "field 'tvRepayCyc'", TextView.class);
    target.tvDayTimes = Utils.findRequiredViewAsType(source, R.id.tv_day_times, "field 'tvDayTimes'", TextView.class);
    target.tvMoneyCyc = Utils.findRequiredViewAsType(source, R.id.tv_money_cyc, "field 'tvMoneyCyc'", TextView.class);
    target.tvFee = Utils.findRequiredViewAsType(source, R.id.tv_fee, "field 'tvFee'", TextView.class);
    target.tvFee2 = Utils.findRequiredViewAsType(source, R.id.tv_fee2, "field 'tvFee2'", TextView.class);
    target.tvZhouzhuanjin = Utils.findRequiredViewAsType(source, R.id.tv_zhouzhuanjin, "field 'tvZhouzhuanjin'", TextView.class);
    target.diqu = Utils.findRequiredViewAsType(source, R.id.diqu, "field 'diqu'", TextView.class);
    target.tvFeeLossAmount = Utils.findRequiredViewAsType(source, R.id.tv_feeLossAmount, "field 'tvFeeLossAmount'", TextView.class);
    target.tvTotalMoney = Utils.findRequiredViewAsType(source, R.id.tv_total_money, "field 'tvTotalMoney'", TextView.class);
    view = Utils.findRequiredView(source, R.id.bt_plan_detail, "field 'btPlanDetail' and method 'onViewClicked'");
    target.btPlanDetail = Utils.castView(view, R.id.bt_plan_detail, "field 'btPlanDetail'", Button.class);
    view2131296347 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.bt_changePlan, "field 'btChangePlan' and method 'onViewClicked'");
    target.btChangePlan = Utils.castView(view, R.id.bt_changePlan, "field 'btChangePlan'", Button.class);
    view2131296340 = view;
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
    target.llFee2 = Utils.findRequiredViewAsType(source, R.id.ll_fee2, "field 'llFee2'", LinearLayout.class);
    target.tvWorkingFundName = Utils.findRequiredViewAsType(source, R.id.tv_workingFund_name, "field 'tvWorkingFundName'", TextView.class);
    target.llWorkfound = Utils.findRequiredViewAsType(source, R.id.ll_workfound, "field 'llWorkfound'", LinearLayout.class);
    target.llDayTimes = Utils.findRequiredViewAsType(source, R.id.ll_day_times, "field 'llDayTimes'", LinearLayout.class);
    target.llMoneyCyc = Utils.findRequiredViewAsType(source, R.id.ll_money_cyc, "field 'llMoneyCyc'", LinearLayout.class);
    target.llDiqu = Utils.findRequiredViewAsType(source, R.id.ll_diqu, "field 'llDiqu'", LinearLayout.class);
    target.rlHeader = Utils.findRequiredViewAsType(source, R.id.rl_header, "field 'rlHeader'", RelativeLayout.class);
    target.tvChannelName = Utils.findRequiredViewAsType(source, R.id.tv_channel_name, "field 'tvChannelName'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PreviewPlanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.ivBankIcon = null;
    target.tvBankName = null;
    target.llBankName = null;
    target.tvBankAccount = null;
    target.tvUserName = null;
    target.tvLimit = null;
    target.tvBillDay = null;
    target.tvPayDay = null;
    target.bindItem = null;
    target.tvLimit2 = null;
    target.tvRepayCyc = null;
    target.tvDayTimes = null;
    target.tvMoneyCyc = null;
    target.tvFee = null;
    target.tvFee2 = null;
    target.tvZhouzhuanjin = null;
    target.diqu = null;
    target.tvFeeLossAmount = null;
    target.tvTotalMoney = null;
    target.btPlanDetail = null;
    target.btChangePlan = null;
    target.btnSubmit = null;
    target.llFee2 = null;
    target.tvWorkingFundName = null;
    target.llWorkfound = null;
    target.llDayTimes = null;
    target.llMoneyCyc = null;
    target.llDiqu = null;
    target.rlHeader = null;
    target.tvChannelName = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296347.setOnClickListener(null);
    view2131296347 = null;
    view2131296340.setOnClickListener(null);
    view2131296340 = null;
    view2131296387.setOnClickListener(null);
    view2131296387 = null;
  }
}
