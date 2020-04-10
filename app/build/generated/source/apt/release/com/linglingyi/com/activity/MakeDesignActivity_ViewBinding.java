// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MakeDesignActivity_ViewBinding implements Unbinder {
  private MakeDesignActivity target;

  private View view2131296633;

  private View view2131297329;

  private View view2131296797;

  private View view2131296796;

  private View view2131297169;

  private View view2131296336;

  private View view2131296348;

  @UiThread
  public MakeDesignActivity_ViewBinding(MakeDesignActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MakeDesignActivity_ViewBinding(final MakeDesignActivity target, View source) {
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
    target.tvBankAccount = Utils.findRequiredViewAsType(source, R.id.tv_bank_account, "field 'tvBankAccount'", TextView.class);
    target.tvUserName = Utils.findRequiredViewAsType(source, R.id.tv_userName, "field 'tvUserName'", TextView.class);
    target.tvLimit = Utils.findRequiredViewAsType(source, R.id.tv_limit, "field 'tvLimit'", TextView.class);
    target.tvBillDay = Utils.findRequiredViewAsType(source, R.id.tv_billDay, "field 'tvBillDay'", TextView.class);
    target.tvPayDay = Utils.findRequiredViewAsType(source, R.id.tv_payDay, "field 'tvPayDay'", TextView.class);
    target.bindItem = Utils.findRequiredViewAsType(source, R.id.bind_item, "field 'bindItem'", LinearLayout.class);
    target.etInputPayAmount = Utils.findRequiredViewAsType(source, R.id.et_inputPayAmount, "field 'etInputPayAmount'", EditText.class);
    target.tvMoneyLimit = Utils.findRequiredViewAsType(source, R.id.tv_moneyLimit, "field 'tvMoneyLimit'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_payStartDay, "field 'tvPayStartDay' and method 'onViewClicked'");
    target.tvPayStartDay = Utils.castView(view, R.id.tv_payStartDay, "field 'tvPayStartDay'", TextView.class);
    view2131297329 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_payStartDay, "field 'llPayStartDay' and method 'onViewClicked'");
    target.llPayStartDay = Utils.castView(view, R.id.ll_payStartDay, "field 'llPayStartDay'", LinearLayout.class);
    view2131296797 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvPayEndDay = Utils.findRequiredViewAsType(source, R.id.tv_payEndDay, "field 'tvPayEndDay'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_payEndDay, "field 'llPayEndDay' and method 'onViewClicked'");
    target.llPayEndDay = Utils.castView(view, R.id.ll_payEndDay, "field 'llPayEndDay'", LinearLayout.class);
    view2131296796 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvPayCycleLimitDesc = Utils.findRequiredViewAsType(source, R.id.tv_payCycleLimitDesc, "field 'tvPayCycleLimitDesc'", TextView.class);
    target.spinner = Utils.findRequiredViewAsType(source, R.id.spinner, "field 'spinner'", Spinner.class);
    target.caidan = Utils.findRequiredViewAsType(source, R.id.caidan, "field 'caidan'", RelativeLayout.class);
    target.tishiyu = Utils.findRequiredViewAsType(source, R.id.tishiyu, "field 'tishiyu'", TextView.class);
    target.rbSavePsw = Utils.findRequiredViewAsType(source, R.id.rb_save_psw, "field 'rbSavePsw'", CheckBox.class);
    view = Utils.findRequiredView(source, R.id.tv_choiceArea, "field 'tvChoiceArea' and method 'onViewClicked'");
    target.tvChoiceArea = Utils.castView(view, R.id.tv_choiceArea, "field 'tvChoiceArea'", TextView.class);
    view2131297169 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.llOpenAddress = Utils.findRequiredViewAsType(source, R.id.ll_open_address, "field 'llOpenAddress'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.bt_calculateWorkingFund, "field 'btCalculateWorkingFund' and method 'onViewClicked'");
    target.btCalculateWorkingFund = Utils.castView(view, R.id.bt_calculateWorkingFund, "field 'btCalculateWorkingFund'", Button.class);
    view2131296336 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvWorkingFundDesc = Utils.findRequiredViewAsType(source, R.id.tv_workingFundDesc, "field 'tvWorkingFundDesc'", TextView.class);
    target.tvWorkingFund = Utils.findRequiredViewAsType(source, R.id.tv_workingFund, "field 'tvWorkingFund'", TextView.class);
    target.zhouzhuanlay = Utils.findRequiredViewAsType(source, R.id.zhouzhuanlay, "field 'zhouzhuanlay'", RelativeLayout.class);
    target.tvPayFeeDesc = Utils.findRequiredViewAsType(source, R.id.tv_payFeeDesc, "field 'tvPayFeeDesc'", TextView.class);
    target.tvPayFee = Utils.findRequiredViewAsType(source, R.id.tv_payFee, "field 'tvPayFee'", TextView.class);
    target.tvPayTimesFeeDesc = Utils.findRequiredViewAsType(source, R.id.tv_payTimesFeeDesc, "field 'tvPayTimesFeeDesc'", TextView.class);
    target.tvPayTimesFee = Utils.findRequiredViewAsType(source, R.id.tv_payTimesFee, "field 'tvPayTimesFee'", TextView.class);
    target.tvFeeLossAmountDesc = Utils.findRequiredViewAsType(source, R.id.tv_feeLossAmountDesc, "field 'tvFeeLossAmountDesc'", TextView.class);
    target.tvFeeLossAmount = Utils.findRequiredViewAsType(source, R.id.tv_feeLossAmount, "field 'tvFeeLossAmount'", TextView.class);
    target.viewa = Utils.findRequiredView(source, R.id.viewa, "field 'viewa'");
    target.tvFees = Utils.findRequiredViewAsType(source, R.id.tv_fees, "field 'tvFees'", TextView.class);
    target.tvPendingPayAmount = Utils.findRequiredViewAsType(source, R.id.tv_pendingPayAmount, "field 'tvPendingPayAmount'", TextView.class);
    target.zonge = Utils.findRequiredViewAsType(source, R.id.zonge, "field 'zonge'", RelativeLayout.class);
    target.llCalculateWorkingFund = Utils.findRequiredViewAsType(source, R.id.ll_calculateWorkingFund, "field 'llCalculateWorkingFund'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.bt_previewPlan, "field 'btPreviewPlan' and method 'onViewClicked'");
    target.btPreviewPlan = Utils.castView(view, R.id.bt_previewPlan, "field 'btPreviewPlan'", Button.class);
    view2131296348 = view;
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
    MakeDesignActivity target = this.target;
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
    target.tvBankAccount = null;
    target.tvUserName = null;
    target.tvLimit = null;
    target.tvBillDay = null;
    target.tvPayDay = null;
    target.bindItem = null;
    target.etInputPayAmount = null;
    target.tvMoneyLimit = null;
    target.tvPayStartDay = null;
    target.llPayStartDay = null;
    target.tvPayEndDay = null;
    target.llPayEndDay = null;
    target.tvPayCycleLimitDesc = null;
    target.spinner = null;
    target.caidan = null;
    target.tishiyu = null;
    target.rbSavePsw = null;
    target.tvChoiceArea = null;
    target.llOpenAddress = null;
    target.btCalculateWorkingFund = null;
    target.tvWorkingFundDesc = null;
    target.tvWorkingFund = null;
    target.zhouzhuanlay = null;
    target.tvPayFeeDesc = null;
    target.tvPayFee = null;
    target.tvPayTimesFeeDesc = null;
    target.tvPayTimesFee = null;
    target.tvFeeLossAmountDesc = null;
    target.tvFeeLossAmount = null;
    target.viewa = null;
    target.tvFees = null;
    target.tvPendingPayAmount = null;
    target.zonge = null;
    target.llCalculateWorkingFund = null;
    target.btPreviewPlan = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131297329.setOnClickListener(null);
    view2131297329 = null;
    view2131296797.setOnClickListener(null);
    view2131296797 = null;
    view2131296796.setOnClickListener(null);
    view2131296796 = null;
    view2131297169.setOnClickListener(null);
    view2131297169 = null;
    view2131296336.setOnClickListener(null);
    view2131296336 = null;
    view2131296348.setOnClickListener(null);
    view2131296348 = null;
  }
}
