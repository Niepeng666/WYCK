// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ClientSingleDetailActivity_ViewBinding implements Unbinder {
  private ClientSingleDetailActivity target;

  private View view2131296633;

  @UiThread
  public ClientSingleDetailActivity_ViewBinding(ClientSingleDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ClientSingleDetailActivity_ViewBinding(final ClientSingleDetailActivity target,
      View source) {
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
    target.ivAvatar = Utils.findRequiredViewAsType(source, R.id.iv_avatar, "field 'ivAvatar'", ImageView.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.tvLevelName = Utils.findRequiredViewAsType(source, R.id.tv_level_name, "field 'tvLevelName'", TextView.class);
    target.tvBenefit = Utils.findRequiredViewAsType(source, R.id.tv_benefit, "field 'tvBenefit'", TextView.class);
    target.tvInvite = Utils.findRequiredViewAsType(source, R.id.tv_invite, "field 'tvInvite'", TextView.class);
    target.tvAuthName = Utils.findRequiredViewAsType(source, R.id.tv_auth_name, "field 'tvAuthName'", TextView.class);
    target.tvRegisterTime = Utils.findRequiredViewAsType(source, R.id.tv_register_time, "field 'tvRegisterTime'", TextView.class);
    target.tvMonthBenefit = Utils.findRequiredViewAsType(source, R.id.tv_month_benefit, "field 'tvMonthBenefit'", TextView.class);
    target.tvTodayBenefit = Utils.findRequiredViewAsType(source, R.id.tv_today_benefit, "field 'tvTodayBenefit'", TextView.class);
    target.tvYesterdayBenefit = Utils.findRequiredViewAsType(source, R.id.tv_yesterday_benefit, "field 'tvYesterdayBenefit'", TextView.class);
    target.tvClientTotal = Utils.findRequiredViewAsType(source, R.id.tv_client_total, "field 'tvClientTotal'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ClientSingleDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.ivAvatar = null;
    target.tvPhone = null;
    target.tvLevelName = null;
    target.tvBenefit = null;
    target.tvInvite = null;
    target.tvAuthName = null;
    target.tvRegisterTime = null;
    target.tvMonthBenefit = null;
    target.tvTodayBenefit = null;
    target.tvYesterdayBenefit = null;
    target.tvClientTotal = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
  }
}
