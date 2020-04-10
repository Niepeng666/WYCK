// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RealNameSecondActivity_ViewBinding implements Unbinder {
  private RealNameSecondActivity target;

  private View view2131296633;

  private View view2131296983;

  private View view2131296374;

  @UiThread
  public RealNameSecondActivity_ViewBinding(RealNameSecondActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RealNameSecondActivity_ViewBinding(final RealNameSecondActivity target, View source) {
    this.target = target;

    View view;
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.ivBank1 = Utils.findRequiredViewAsType(source, R.id.iv_bank_1, "field 'ivBank1'", ImageView.class);
    target.etBankCode = Utils.findRequiredViewAsType(source, R.id.et_bank_code, "field 'etBankCode'", EditText.class);
    target.tvBankName = Utils.findRequiredViewAsType(source, R.id.tv_bank_name, "field 'tvBankName'", TextView.class);
    target.etPhone = Utils.findRequiredViewAsType(source, R.id.et_phone, "field 'etPhone'", EditText.class);
    view = Utils.findRequiredView(source, R.id.iv_back, "method 'onViewClicked'");
    view2131296633 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_bank_1, "method 'onViewClicked'");
    view2131296983 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_next, "method 'onViewClicked'");
    view2131296374 = view;
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
    RealNameSecondActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitle = null;
    target.ivBank1 = null;
    target.etBankCode = null;
    target.tvBankName = null;
    target.etPhone = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296983.setOnClickListener(null);
    view2131296983 = null;
    view2131296374.setOnClickListener(null);
    view2131296374 = null;
  }
}
