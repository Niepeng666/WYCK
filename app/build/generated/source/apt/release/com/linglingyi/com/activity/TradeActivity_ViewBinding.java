// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TradeActivity_ViewBinding implements Unbinder {
  private TradeActivity target;

  private View view2131296633;

  @UiThread
  public TradeActivity_ViewBinding(TradeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TradeActivity_ViewBinding(final TradeActivity target, View source) {
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
    target.totalMoney = Utils.findRequiredViewAsType(source, R.id.total_money, "field 'totalMoney'", TextView.class);
    target.tvTotalMoney = Utils.findRequiredViewAsType(source, R.id.tv_total_money, "field 'tvTotalMoney'", TextView.class);
    target.tvSevenTitle = Utils.findRequiredViewAsType(source, R.id.tv_seven_title, "field 'tvSevenTitle'", TextView.class);
    target.tvSevenNum = Utils.findRequiredViewAsType(source, R.id.tv_seven_num, "field 'tvSevenNum'", TextView.class);
    target.viewCenterLine = Utils.findRequiredView(source, R.id.view_center_line, "field 'viewCenterLine'");
    target.tvSevenMoneyTitle = Utils.findRequiredViewAsType(source, R.id.tv_seven_money_title, "field 'tvSevenMoneyTitle'", TextView.class);
    target.tvSevenMoney = Utils.findRequiredViewAsType(source, R.id.tv_seven_money, "field 'tvSevenMoney'", TextView.class);
    target.rvList = Utils.findRequiredViewAsType(source, R.id.rv_list, "field 'rvList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TradeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.tlTab = null;
    target.totalMoney = null;
    target.tvTotalMoney = null;
    target.tvSevenTitle = null;
    target.tvSevenNum = null;
    target.viewCenterLine = null;
    target.tvSevenMoneyTitle = null;
    target.tvSevenMoney = null;
    target.rvList = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
  }
}
