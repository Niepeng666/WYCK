// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LookPlanActivity_ViewBinding implements Unbinder {
  private LookPlanActivity target;

  private View view2131296633;

  @UiThread
  public LookPlanActivity_ViewBinding(LookPlanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LookPlanActivity_ViewBinding(final LookPlanActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'ivBack' and method 'onViewClicked'");
    target.ivBack = Utils.castView(view, R.id.iv_back, "field 'ivBack'", ImageView.class);
    view2131296633 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
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
    target.rvList = Utils.findRequiredViewAsType(source, R.id.rv_list, "field 'rvList'", RecyclerView.class);
    target.srlRefresh = Utils.findRequiredViewAsType(source, R.id.srl_refresh, "field 'srlRefresh'", SmartRefreshLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LookPlanActivity target = this.target;
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
    target.rvList = null;
    target.srlRefresh = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
  }
}
