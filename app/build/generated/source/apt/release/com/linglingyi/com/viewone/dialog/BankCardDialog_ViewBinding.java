// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.viewone.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BankCardDialog_ViewBinding implements Unbinder {
  private BankCardDialog target;

  private View view2131296784;

  private View view2131296783;

  private View view2131296781;

  private View view2131297279;

  private View view2131297278;

  private View view2131296642;

  private View view2131296780;

  @UiThread
  public BankCardDialog_ViewBinding(final BankCardDialog target, View source) {
    this.target = target;

    View view;
    target.ivBankIcon = Utils.findRequiredViewAsType(source, R.id.iv_bank_icon, "field 'ivBankIcon'", ImageView.class);
    target.tvBankName = Utils.findRequiredViewAsType(source, R.id.tv_bank_name, "field 'tvBankName'", TextView.class);
    target.tvBankAccount = Utils.findRequiredViewAsType(source, R.id.tv_bank_account, "field 'tvBankAccount'", TextView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvMakeYkDesign = Utils.findRequiredViewAsType(source, R.id.tv_make_yk_design, "field 'tvMakeYkDesign'", TextView.class);
    target.tvYkPlanTip = Utils.findRequiredViewAsType(source, R.id.tv_yk_plan_tip, "field 'tvYkPlanTip'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_make_yk_design, "field 'llMakeYkDesign' and method 'onViewClicked'");
    target.llMakeYkDesign = Utils.castView(view, R.id.ll_make_yk_design, "field 'llMakeYkDesign'", RelativeLayout.class);
    view2131296784 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvMakeQykDesign = Utils.findRequiredViewAsType(source, R.id.tv_make_qyk_design, "field 'tvMakeQykDesign'", TextView.class);
    target.tvQykPlanTip = Utils.findRequiredViewAsType(source, R.id.tv_qyk_plan_tip, "field 'tvQykPlanTip'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_make_qyk_design, "field 'llMakeQykDesign' and method 'onViewClicked'");
    target.llMakeQykDesign = Utils.castView(view, R.id.ll_make_qyk_design, "field 'llMakeQykDesign'", RelativeLayout.class);
    view2131296783 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvMakeDesign = Utils.findRequiredViewAsType(source, R.id.tv_make_design, "field 'tvMakeDesign'", TextView.class);
    target.tvChannelsPlanTip = Utils.findRequiredViewAsType(source, R.id.tv_channels_plan_tip, "field 'tvChannelsPlanTip'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_make_channels_design, "field 'llMakeChannelsDesign' and method 'onViewClicked'");
    target.llMakeChannelsDesign = Utils.castView(view, R.id.ll_make_channels_design, "field 'llMakeChannelsDesign'", RelativeLayout.class);
    view2131296781 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_look_plan, "field 'tvLookPlan' and method 'onViewClicked'");
    target.tvLookPlan = Utils.castView(view, R.id.tv_look_plan, "field 'tvLookPlan'", TextView.class);
    view2131297279 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_look_data, "field 'tvLookData' and method 'onViewClicked'");
    target.tvLookData = Utils.castView(view, R.id.tv_look_data, "field 'tvLookData'", TextView.class);
    view2131297278 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_close, "field 'ivClose' and method 'onViewClicked'");
    target.ivClose = Utils.castView(view, R.id.iv_close, "field 'ivClose'", ImageView.class);
    view2131296642 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.tvCardsDesign = Utils.findRequiredViewAsType(source, R.id.tv_cards_design, "field 'tvCardsDesign'", TextView.class);
    target.tvCardsPlanTip = Utils.findRequiredViewAsType(source, R.id.tv_cards_plan_tip, "field 'tvCardsPlanTip'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_make_cards_design, "field 'llMakeCardsDesign' and method 'onViewClicked'");
    target.llMakeCardsDesign = Utils.castView(view, R.id.ll_make_cards_design, "field 'llMakeCardsDesign'", RelativeLayout.class);
    view2131296780 = view;
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
    BankCardDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBankIcon = null;
    target.tvBankName = null;
    target.tvBankAccount = null;
    target.tvName = null;
    target.tvMakeYkDesign = null;
    target.tvYkPlanTip = null;
    target.llMakeYkDesign = null;
    target.tvMakeQykDesign = null;
    target.tvQykPlanTip = null;
    target.llMakeQykDesign = null;
    target.tvMakeDesign = null;
    target.tvChannelsPlanTip = null;
    target.llMakeChannelsDesign = null;
    target.tvLookPlan = null;
    target.tvLookData = null;
    target.ivClose = null;
    target.tvPhone = null;
    target.tvCardsDesign = null;
    target.tvCardsPlanTip = null;
    target.llMakeCardsDesign = null;

    view2131296784.setOnClickListener(null);
    view2131296784 = null;
    view2131296783.setOnClickListener(null);
    view2131296783 = null;
    view2131296781.setOnClickListener(null);
    view2131296781 = null;
    view2131297279.setOnClickListener(null);
    view2131297279 = null;
    view2131297278.setOnClickListener(null);
    view2131297278 = null;
    view2131296642.setOnClickListener(null);
    view2131296642 = null;
    view2131296780.setOnClickListener(null);
    view2131296780 = null;
  }
}
