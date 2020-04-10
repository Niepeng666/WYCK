// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MakeNewDesignActivity_ViewBinding implements Unbinder {
  private MakeNewDesignActivity target;

  private View view2131296633;

  private View view2131297466;

  private View view2131297185;

  private View view2131297170;

  private View view2131296706;

  private View view2131296362;

  private View view2131296380;

  private View view2131296756;

  @UiThread
  public MakeNewDesignActivity_ViewBinding(MakeNewDesignActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MakeNewDesignActivity_ViewBinding(final MakeNewDesignActivity target, View source) {
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
    target.rvList = Utils.findRequiredViewAsType(source, R.id.rv_list, "field 'rvList'", RecyclerView.class);
    target.rlChannel = Utils.findRequiredViewAsType(source, R.id.rl_channel, "field 'rlChannel'", RelativeLayout.class);
    target.rbtChannelSmall = Utils.findRequiredViewAsType(source, R.id.rbt_channel_small, "field 'rbtChannelSmall'", RadioButton.class);
    target.rbtChannelBig = Utils.findRequiredViewAsType(source, R.id.rbt_channel_big, "field 'rbtChannelBig'", RadioButton.class);
    target.rbtChannelAll = Utils.findRequiredViewAsType(source, R.id.rbt_channel_all, "field 'rbtChannelAll'", RadioButton.class);
    target.rgChannel = Utils.findRequiredViewAsType(source, R.id.rg_channel, "field 'rgChannel'", RadioGroup.class);
    view = Utils.findRequiredView(source, R.id.view_close, "field 'viewClose' and method 'onViewClicked'");
    target.viewClose = view;
    view2131297466 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etInputPayAmount = Utils.findRequiredViewAsType(source, R.id.et_inputPayAmount, "field 'etInputPayAmount'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_date, "field 'tvDate' and method 'onViewClicked'");
    target.tvDate = Utils.castView(view, R.id.tv_date, "field 'tvDate'", TextView.class);
    view2131297185 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.spinnerPay = Utils.findRequiredViewAsType(source, R.id.spinner_pay, "field 'spinnerPay'", Spinner.class);
    target.llPayModel = Utils.findRequiredViewAsType(source, R.id.ll_pay_model, "field 'llPayModel'", LinearLayout.class);
    target.spinnerPayNumber = Utils.findRequiredViewAsType(source, R.id.spinner_pay_number, "field 'spinnerPayNumber'", Spinner.class);
    target.llPayNumber = Utils.findRequiredViewAsType(source, R.id.ll_pay_number, "field 'llPayNumber'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_city, "field 'tvCity' and method 'onViewClicked'");
    target.tvCity = Utils.castView(view, R.id.tv_city, "field 'tvCity'", TextView.class);
    view2131297170 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvTotalTitle = Utils.findRequiredViewAsType(source, R.id.tv_total_title, "field 'tvTotalTitle'", TextView.class);
    target.tvTotalPrice = Utils.findRequiredViewAsType(source, R.id.tv_total_price, "field 'tvTotalPrice'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_total_price_tip, "field 'ivTotalPriceTip' and method 'onViewClicked'");
    target.ivTotalPriceTip = Utils.castView(view, R.id.iv_total_price_tip, "field 'ivTotalPriceTip'", ImageView.class);
    view2131296706 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_calculate, "field 'btnCalculate' and method 'onViewClicked'");
    target.btnCalculate = Utils.castView(view, R.id.btn_calculate, "field 'btnCalculate'", TextView.class);
    view2131296362 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_previewPlan, "field 'btnPreviewPlan' and method 'onViewClicked'");
    target.btnPreviewPlan = Utils.castView(view, R.id.btn_previewPlan, "field 'btnPreviewPlan'", Button.class);
    view2131296380 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.llMakeDesign = Utils.findRequiredViewAsType(source, R.id.ll_make_design, "field 'llMakeDesign'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.ll_dialog_make_design, "method 'onViewClicked'");
    view2131296756 = view;
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
    MakeNewDesignActivity target = this.target;
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
    target.rvList = null;
    target.rlChannel = null;
    target.rbtChannelSmall = null;
    target.rbtChannelBig = null;
    target.rbtChannelAll = null;
    target.rgChannel = null;
    target.viewClose = null;
    target.etInputPayAmount = null;
    target.tvDate = null;
    target.spinnerPay = null;
    target.llPayModel = null;
    target.spinnerPayNumber = null;
    target.llPayNumber = null;
    target.tvCity = null;
    target.tvTotalTitle = null;
    target.tvTotalPrice = null;
    target.ivTotalPriceTip = null;
    target.btnCalculate = null;
    target.btnPreviewPlan = null;
    target.llMakeDesign = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131297466.setOnClickListener(null);
    view2131297466 = null;
    view2131297185.setOnClickListener(null);
    view2131297185 = null;
    view2131297170.setOnClickListener(null);
    view2131297170 = null;
    view2131296706.setOnClickListener(null);
    view2131296706 = null;
    view2131296362.setOnClickListener(null);
    view2131296362 = null;
    view2131296380.setOnClickListener(null);
    view2131296380 = null;
    view2131296756.setOnClickListener(null);
    view2131296756 = null;
  }
}
