// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
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

public class MineNewFragment_ViewBinding implements Unbinder {
  private MineNewFragment target;

  private View view2131296691;

  private View view2131296632;

  private View view2131296855;

  private View view2131296857;

  private View view2131296841;

  private View view2131297443;

  private View view2131296826;

  private View view2131296736;

  private View view2131296745;

  private View view2131296816;

  private View view2131296763;

  private View view2131296808;

  @UiThread
  public MineNewFragment_ViewBinding(final MineNewFragment target, View source) {
    this.target = target;

    View view;
    target.ivBack = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'ivBack'", ImageView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'tvRight'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_right, "field 'ivRight' and method 'onViewClicked'");
    target.ivRight = Utils.castView(view, R.id.iv_right, "field 'ivRight'", ImageView.class);
    view2131296691 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_avatar, "field 'ivAvatar' and method 'onViewClicked'");
    target.ivAvatar = Utils.castView(view, R.id.iv_avatar, "field 'ivAvatar'", ImageView.class);
    view2131296632 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.tvLevel = Utils.findRequiredViewAsType(source, R.id.tv_level, "field 'tvLevel'", TextView.class);
    target.tvTodayEarnings = Utils.findRequiredViewAsType(source, R.id.tv_today_earnings, "field 'tvTodayEarnings'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ly_today_earnings, "field 'lyTodayEarnings' and method 'onViewClicked'");
    target.lyTodayEarnings = Utils.castView(view, R.id.ly_today_earnings, "field 'lyTodayEarnings'", LinearLayout.class);
    view2131296855 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvYesterdayEarnings = Utils.findRequiredViewAsType(source, R.id.tv_yesterday_earnings, "field 'tvYesterdayEarnings'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ly_yesterday_earnings, "field 'lyYesterdayEarnings' and method 'onViewClicked'");
    target.lyYesterdayEarnings = Utils.castView(view, R.id.ly_yesterday_earnings, "field 'lyYesterdayEarnings'", LinearLayout.class);
    view2131296857 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvCumulativeEarnings = Utils.findRequiredViewAsType(source, R.id.tv_cumulative_earnings, "field 'tvCumulativeEarnings'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ly_accumulated_earnings, "field 'lyAccumulatedEarnings' and method 'onViewClicked'");
    target.lyAccumulatedEarnings = Utils.castView(view, R.id.ly_accumulated_earnings, "field 'lyAccumulatedEarnings'", LinearLayout.class);
    view2131296841 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvBalance = Utils.findRequiredViewAsType(source, R.id.tv_balance, "field 'tvBalance'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_withdrawal, "field 'tvWithdrawal' and method 'onViewClicked'");
    target.tvWithdrawal = Utils.castView(view, R.id.tv_withdrawal, "field 'tvWithdrawal'", TextView.class);
    view2131297443 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_trade, "field 'llTrade' and method 'onViewClicked'");
    target.llTrade = Utils.castView(view, R.id.ll_trade, "field 'llTrade'", LinearLayout.class);
    view2131296826 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_auth, "field 'llAuth' and method 'onViewClicked'");
    target.llAuth = Utils.castView(view, R.id.ll_auth, "field 'llAuth'", LinearLayout.class);
    view2131296736 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_card, "field 'llCard' and method 'onViewClicked'");
    target.llCard = Utils.castView(view, R.id.ll_card, "field 'llCard'", LinearLayout.class);
    view2131296745 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_team, "field 'llTeam' and method 'onViewClicked'");
    target.llTeam = Utils.castView(view, R.id.ll_team, "field 'llTeam'", LinearLayout.class);
    view2131296816 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_guide, "field 'llGuide' and method 'onViewClicked'");
    target.llGuide = Utils.castView(view, R.id.ll_guide, "field 'llGuide'", LinearLayout.class);
    view2131296763 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_security, "field 'llSecurity' and method 'onViewClicked'");
    target.llSecurity = Utils.castView(view, R.id.ll_security, "field 'llSecurity'", LinearLayout.class);
    view2131296808 = view;
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
    MineNewFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.ivAvatar = null;
    target.tvName = null;
    target.tvPhone = null;
    target.tvLevel = null;
    target.tvTodayEarnings = null;
    target.lyTodayEarnings = null;
    target.tvYesterdayEarnings = null;
    target.lyYesterdayEarnings = null;
    target.tvCumulativeEarnings = null;
    target.lyAccumulatedEarnings = null;
    target.tvBalance = null;
    target.tvWithdrawal = null;
    target.llTrade = null;
    target.llAuth = null;
    target.llCard = null;
    target.llTeam = null;
    target.llGuide = null;
    target.llSecurity = null;

    view2131296691.setOnClickListener(null);
    view2131296691 = null;
    view2131296632.setOnClickListener(null);
    view2131296632 = null;
    view2131296855.setOnClickListener(null);
    view2131296855 = null;
    view2131296857.setOnClickListener(null);
    view2131296857 = null;
    view2131296841.setOnClickListener(null);
    view2131296841 = null;
    view2131297443.setOnClickListener(null);
    view2131297443 = null;
    view2131296826.setOnClickListener(null);
    view2131296826 = null;
    view2131296736.setOnClickListener(null);
    view2131296736 = null;
    view2131296745.setOnClickListener(null);
    view2131296745 = null;
    view2131296816.setOnClickListener(null);
    view2131296816 = null;
    view2131296763.setOnClickListener(null);
    view2131296763 = null;
    view2131296808.setOnClickListener(null);
    view2131296808 = null;
  }
}
