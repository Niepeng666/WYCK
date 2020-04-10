// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BankCardListAdapter$ViewHolder_ViewBinding implements Unbinder {
  private BankCardListAdapter.ViewHolder target;

  @UiThread
  public BankCardListAdapter$ViewHolder_ViewBinding(BankCardListAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.ivBankLogo = Utils.findRequiredViewAsType(source, R.id.iv_bank_logo, "field 'ivBankLogo'", ImageView.class);
    target.tvBankName = Utils.findRequiredViewAsType(source, R.id.tv_bank_name, "field 'tvBankName'", TextView.class);
    target.tvBankNum = Utils.findRequiredViewAsType(source, R.id.tv_bank_num, "field 'tvBankNum'", TextView.class);
    target.ivDelete = Utils.findRequiredViewAsType(source, R.id.iv_delete, "field 'ivDelete'", ImageView.class);
    target.tvZdr = Utils.findRequiredViewAsType(source, R.id.tv_zdr, "field 'tvZdr'", TextView.class);
    target.tvHkr = Utils.findRequiredViewAsType(source, R.id.tv_hkr, "field 'tvHkr'", TextView.class);
    target.tvEd = Utils.findRequiredViewAsType(source, R.id.tv_ed, "field 'tvEd'", TextView.class);
    target.lyUpdateCard = Utils.findRequiredViewAsType(source, R.id.ly_update_card, "field 'lyUpdateCard'", LinearLayout.class);
    target.tvDate = Utils.findRequiredViewAsType(source, R.id.tv_date, "field 'tvDate'", TextView.class);
    target.tvMakePlan = Utils.findRequiredViewAsType(source, R.id.tv_make_plan, "field 'tvMakePlan'", TextView.class);
    target.tvLookPlan = Utils.findRequiredViewAsType(source, R.id.tv_look_plan, "field 'tvLookPlan'", TextView.class);
    target.lyItem = Utils.findRequiredViewAsType(source, R.id.ly_item, "field 'lyItem'", LinearLayout.class);
    target.tvChange = Utils.findRequiredViewAsType(source, R.id.tv_change, "field 'tvChange'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BankCardListAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBankLogo = null;
    target.tvBankName = null;
    target.tvBankNum = null;
    target.ivDelete = null;
    target.tvZdr = null;
    target.tvHkr = null;
    target.tvEd = null;
    target.lyUpdateCard = null;
    target.tvDate = null;
    target.tvMakePlan = null;
    target.tvLookPlan = null;
    target.lyItem = null;
    target.tvChange = null;
  }
}
