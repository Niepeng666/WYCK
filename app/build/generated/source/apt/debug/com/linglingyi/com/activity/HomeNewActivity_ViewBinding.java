// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.linglingyi.com.viewone.FontIconView;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeNewActivity_ViewBinding implements Unbinder {
  private HomeNewActivity target;

  private View view2131296777;

  private View view2131296829;

  private View view2131296810;

  private View view2131296763;

  private View view2131296785;

  @UiThread
  public HomeNewActivity_ViewBinding(HomeNewActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HomeNewActivity_ViewBinding(final HomeNewActivity target, View source) {
    this.target = target;

    View view;
    target.flContent = Utils.findRequiredViewAsType(source, R.id.fl_content, "field 'flContent'", FrameLayout.class);
    target.tvMainIcon = Utils.findRequiredViewAsType(source, R.id.tv_main_icon, "field 'tvMainIcon'", FontIconView.class);
    target.tvMain = Utils.findRequiredViewAsType(source, R.id.tv_main, "field 'tvMain'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_main, "field 'llMain' and method 'onViewClicked'");
    target.llMain = Utils.castView(view, R.id.ll_main, "field 'llMain'", LinearLayout.class);
    view2131296777 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvVipIcon = Utils.findRequiredViewAsType(source, R.id.tv_vip_icon, "field 'tvVipIcon'", FontIconView.class);
    target.tvVip = Utils.findRequiredViewAsType(source, R.id.tv_vip, "field 'tvVip'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_vip, "field 'llVip' and method 'onViewClicked'");
    target.llVip = Utils.castView(view, R.id.ll_vip, "field 'llVip'", LinearLayout.class);
    view2131296829 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvShareIcon = Utils.findRequiredViewAsType(source, R.id.tv_share_icon, "field 'tvShareIcon'", FontIconView.class);
    target.tvShare = Utils.findRequiredViewAsType(source, R.id.tv_share, "field 'tvShare'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_share, "field 'llShare' and method 'onViewClicked'");
    target.llShare = Utils.castView(view, R.id.ll_share, "field 'llShare'", LinearLayout.class);
    view2131296810 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvGuideIcon = Utils.findRequiredViewAsType(source, R.id.tv_guide_icon, "field 'tvGuideIcon'", FontIconView.class);
    target.tvGuide = Utils.findRequiredViewAsType(source, R.id.tv_guide, "field 'tvGuide'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_guide, "field 'llGuide' and method 'onViewClicked'");
    target.llGuide = Utils.castView(view, R.id.ll_guide, "field 'llGuide'", LinearLayout.class);
    view2131296763 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvMineIcon = Utils.findRequiredViewAsType(source, R.id.tv_mine_icon, "field 'tvMineIcon'", FontIconView.class);
    target.tvMine = Utils.findRequiredViewAsType(source, R.id.tv_mine, "field 'tvMine'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_mine, "field 'llMine' and method 'onViewClicked'");
    target.llMine = Utils.castView(view, R.id.ll_mine, "field 'llMine'", LinearLayout.class);
    view2131296785 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.rgTab = Utils.findRequiredViewAsType(source, R.id.rg_tab, "field 'rgTab'", LinearLayout.class);
    target.clContainer = Utils.findRequiredViewAsType(source, R.id.cl_container, "field 'clContainer'", ConstraintLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeNewActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.flContent = null;
    target.tvMainIcon = null;
    target.tvMain = null;
    target.llMain = null;
    target.tvVipIcon = null;
    target.tvVip = null;
    target.llVip = null;
    target.tvShareIcon = null;
    target.tvShare = null;
    target.llShare = null;
    target.tvGuideIcon = null;
    target.tvGuide = null;
    target.llGuide = null;
    target.tvMineIcon = null;
    target.tvMine = null;
    target.llMine = null;
    target.rgTab = null;
    target.clContainer = null;

    view2131296777.setOnClickListener(null);
    view2131296777 = null;
    view2131296829.setOnClickListener(null);
    view2131296829 = null;
    view2131296810.setOnClickListener(null);
    view2131296810 = null;
    view2131296763.setOnClickListener(null);
    view2131296763 = null;
    view2131296785.setOnClickListener(null);
    view2131296785 = null;
  }
}
