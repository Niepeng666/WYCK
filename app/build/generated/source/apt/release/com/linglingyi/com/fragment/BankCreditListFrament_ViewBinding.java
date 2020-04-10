// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BankCreditListFrament_ViewBinding implements Unbinder {
  private BankCreditListFrament target;

  @UiThread
  public BankCreditListFrament_ViewBinding(BankCreditListFrament target, View source) {
    this.target = target;

    target.rvCreditList = Utils.findRequiredViewAsType(source, R.id.rv_credit_list, "field 'rvCreditList'", RecyclerView.class);
    target.rlContainer = Utils.findRequiredViewAsType(source, R.id.rl_container, "field 'rlContainer'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BankCreditListFrament target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvCreditList = null;
    target.rlContainer = null;
  }
}
