// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.fragment;

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

public class BankNormalCardFragment_ViewBinding implements Unbinder {
  private BankNormalCardFragment target;

  private View view2131296364;

  @UiThread
  public BankNormalCardFragment_ViewBinding(final BankNormalCardFragment target, View source) {
    this.target = target;

    View view;
    target.ivBankIcon = Utils.findRequiredViewAsType(source, R.id.iv_bank_icon, "field 'ivBankIcon'", ImageView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvBankAccount = Utils.findRequiredViewAsType(source, R.id.tv_bank_account, "field 'tvBankAccount'", TextView.class);
    target.tvBankName = Utils.findRequiredViewAsType(source, R.id.tv_bank_name, "field 'tvBankName'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_change, "field 'btnChange' and method 'onViewClicked'");
    target.btnChange = Utils.castView(view, R.id.btn_change, "field 'btnChange'", Button.class);
    view2131296364 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    BankNormalCardFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBankIcon = null;
    target.tvName = null;
    target.tvBankAccount = null;
    target.tvBankName = null;
    target.btnChange = null;

    view2131296364.setOnClickListener(null);
    view2131296364 = null;
  }
}
