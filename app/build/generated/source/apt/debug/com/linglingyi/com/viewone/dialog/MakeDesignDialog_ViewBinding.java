// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.viewone.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MakeDesignDialog_ViewBinding implements Unbinder {
  private MakeDesignDialog target;

  private View view2131297185;

  private View view2131297170;

  private View view2131296362;

  private View view2131296380;

  private View view2131296706;

  @UiThread
  public MakeDesignDialog_ViewBinding(final MakeDesignDialog target, View source) {
    this.target = target;

    View view;
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
    target.tvTotalPrice = Utils.findRequiredViewAsType(source, R.id.tv_total_price, "field 'tvTotalPrice'", TextView.class);
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
    view = Utils.findRequiredView(source, R.id.iv_total_price_tip, "field 'ivTotalPriceTip' and method 'onViewClicked'");
    target.ivTotalPriceTip = Utils.castView(view, R.id.iv_total_price_tip, "field 'ivTotalPriceTip'", ImageView.class);
    view2131296706 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvTotalTitle = Utils.findRequiredViewAsType(source, R.id.tv_total_title, "field 'tvTotalTitle'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MakeDesignDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etInputPayAmount = null;
    target.tvDate = null;
    target.spinnerPay = null;
    target.llPayModel = null;
    target.spinnerPayNumber = null;
    target.llPayNumber = null;
    target.tvCity = null;
    target.tvTotalPrice = null;
    target.btnCalculate = null;
    target.btnPreviewPlan = null;
    target.ivTotalPriceTip = null;
    target.tvTotalTitle = null;

    view2131297185.setOnClickListener(null);
    view2131297185 = null;
    view2131297170.setOnClickListener(null);
    view2131297170 = null;
    view2131296362.setOnClickListener(null);
    view2131296362 = null;
    view2131296380.setOnClickListener(null);
    view2131296380 = null;
    view2131296706.setOnClickListener(null);
    view2131296706 = null;
  }
}
