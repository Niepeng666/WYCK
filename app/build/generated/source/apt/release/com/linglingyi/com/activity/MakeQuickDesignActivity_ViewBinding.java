// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

public class MakeQuickDesignActivity_ViewBinding implements Unbinder {
  private MakeQuickDesignActivity target;

  private View view2131296633;

  private View view2131296977;

  private View view2131296374;

  @UiThread
  public MakeQuickDesignActivity_ViewBinding(MakeQuickDesignActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MakeQuickDesignActivity_ViewBinding(final MakeQuickDesignActivity target, View source) {
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
    target.etTotalMoney = Utils.findRequiredViewAsType(source, R.id.et_total_money, "field 'etTotalMoney'", EditText.class);
    target.tvAddress = Utils.findRequiredViewAsType(source, R.id.tv_address, "field 'tvAddress'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_address, "field 'rlAddress' and method 'onViewClicked'");
    target.rlAddress = Utils.castView(view, R.id.rl_address, "field 'rlAddress'", RelativeLayout.class);
    view2131296977 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.rvList = Utils.findRequiredViewAsType(source, R.id.rv_list, "field 'rvList'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.btn_next, "field 'btnNext' and method 'onViewClicked'");
    target.btnNext = Utils.castView(view, R.id.btn_next, "field 'btnNext'", Button.class);
    view2131296374 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etWorkingFund = Utils.findRequiredViewAsType(source, R.id.et_working_fund, "field 'etWorkingFund'", EditText.class);
    target.tvTip = Utils.findRequiredViewAsType(source, R.id.tv_tip, "field 'tvTip'", TextView.class);
    target.rbSavePsw = Utils.findRequiredViewAsType(source, R.id.rb_save_psw, "field 'rbSavePsw'", CheckBox.class);
    target.llOpenAddress = Utils.findRequiredViewAsType(source, R.id.ll_open_address, "field 'llOpenAddress'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MakeQuickDesignActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.etTotalMoney = null;
    target.tvAddress = null;
    target.rlAddress = null;
    target.rvList = null;
    target.btnNext = null;
    target.etWorkingFund = null;
    target.tvTip = null;
    target.rbSavePsw = null;
    target.llOpenAddress = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296977.setOnClickListener(null);
    view2131296977 = null;
    view2131296374.setOnClickListener(null);
    view2131296374 = null;
  }
}
