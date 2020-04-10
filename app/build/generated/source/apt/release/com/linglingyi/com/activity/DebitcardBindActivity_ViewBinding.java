// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DebitcardBindActivity_ViewBinding implements Unbinder {
  private DebitcardBindActivity target;

  private View view2131296374;

  private View view2131296633;

  private View view2131296635;

  private View view2131297145;

  private View view2131296842;

  @UiThread
  public DebitcardBindActivity_ViewBinding(DebitcardBindActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DebitcardBindActivity_ViewBinding(final DebitcardBindActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_next, "field 'btnNext' and method 'onViewClicked'");
    target.btnNext = Utils.castView(view, R.id.btn_next, "field 'btnNext'", Button.class);
    view2131296374 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
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
    view = Utils.findRequiredView(source, R.id.iv_bank, "field 'ivBank' and method 'onViewClicked'");
    target.ivBank = Utils.castView(view, R.id.iv_bank, "field 'ivBank'", ImageView.class);
    view2131296635 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etBankAccount = Utils.findRequiredViewAsType(source, R.id.et_bank_account, "field 'etBankAccount'", EditText.class);
    target.etBankPhone = Utils.findRequiredViewAsType(source, R.id.et_bank_phone, "field 'etBankPhone'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_bank_name, "field 'tvBankName' and method 'onViewClicked'");
    target.tvBankName = Utils.castView(view, R.id.tv_bank_name, "field 'tvBankName'", TextView.class);
    view2131297145 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.enterIv = Utils.findRequiredViewAsType(source, R.id.enter_iv, "field 'enterIv'", ImageView.class);
    target.lyBank = Utils.findRequiredViewAsType(source, R.id.ly_bank, "field 'lyBank'", LinearLayout.class);
    target.tvArea = Utils.findRequiredViewAsType(source, R.id.tv_area, "field 'tvArea'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ly_area, "field 'lyArea' and method 'onViewClicked'");
    target.lyArea = Utils.castView(view, R.id.ly_area, "field 'lyArea'", LinearLayout.class);
    view2131296842 = view;
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
    DebitcardBindActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnNext = null;
    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.ivBank = null;
    target.etBankAccount = null;
    target.etBankPhone = null;
    target.tvBankName = null;
    target.enterIv = null;
    target.lyBank = null;
    target.tvArea = null;
    target.lyArea = null;

    view2131296374.setOnClickListener(null);
    view2131296374 = null;
    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296635.setOnClickListener(null);
    view2131296635 = null;
    view2131297145.setOnClickListener(null);
    view2131297145 = null;
    view2131296842.setOnClickListener(null);
    view2131296842 = null;
  }
}
