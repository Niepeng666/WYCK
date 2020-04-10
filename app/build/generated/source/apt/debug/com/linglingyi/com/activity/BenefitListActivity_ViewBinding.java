// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BenefitListActivity_ViewBinding implements Unbinder {
  private BenefitListActivity target;

  private View view2131296633;

  @UiThread
  public BenefitListActivity_ViewBinding(BenefitListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BenefitListActivity_ViewBinding(final BenefitListActivity target, View source) {
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
    target.tlTab = Utils.findRequiredViewAsType(source, R.id.tl_tab, "field 'tlTab'", TabLayout.class);
    target.rvList = Utils.findRequiredViewAsType(source, R.id.rv_list, "field 'rvList'", RecyclerView.class);
    target.tvTotalMoney = Utils.findRequiredViewAsType(source, R.id.tv_total_money, "field 'tvTotalMoney'", TextView.class);
    target.tvSevenNum = Utils.findRequiredViewAsType(source, R.id.tv_seven_num, "field 'tvSevenNum'", TextView.class);
    target.viewCenterLine = Utils.findRequiredView(source, R.id.view_center_line, "field 'viewCenterLine'");
    target.tvSevenMoneyTitle = Utils.findRequiredViewAsType(source, R.id.tv_seven_money_title, "field 'tvSevenMoneyTitle'", TextView.class);
    target.tvSevenMoneyLeft = Utils.findRequiredViewAsType(source, R.id.tv_seven_money_left, "field 'tvSevenMoneyLeft'", TextView.class);
    target.tvSevenMoney = Utils.findRequiredViewAsType(source, R.id.tv_seven_money, "field 'tvSevenMoney'", TextView.class);
    target.llMoney = Utils.findRequiredViewAsType(source, R.id.ll_money, "field 'llMoney'", LinearLayout.class);
    target.tvTotalMoneyTitle = Utils.findRequiredViewAsType(source, R.id.tv_total_money_title, "field 'tvTotalMoneyTitle'", TextView.class);
    target.tvSevenNumTitle = Utils.findRequiredViewAsType(source, R.id.tv_seven_num_title, "field 'tvSevenNumTitle'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BenefitListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.tlTab = null;
    target.rvList = null;
    target.tvTotalMoney = null;
    target.tvSevenNum = null;
    target.viewCenterLine = null;
    target.tvSevenMoneyTitle = null;
    target.tvSevenMoneyLeft = null;
    target.tvSevenMoney = null;
    target.llMoney = null;
    target.tvTotalMoneyTitle = null;
    target.tvSevenNumTitle = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
  }
}
