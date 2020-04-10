// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UserInfoActivity_ViewBinding implements Unbinder {
  private UserInfoActivity target;

  private View view2131296633;

  @UiThread
  public UserInfoActivity_ViewBinding(UserInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UserInfoActivity_ViewBinding(final UserInfoActivity target, View source) {
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
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.clCleanCache = Utils.findRequiredViewAsType(source, R.id.cl_clean_cache, "field 'clCleanCache'", ConstraintLayout.class);
    target.tvIdCard = Utils.findRequiredViewAsType(source, R.id.tv_id_card, "field 'tvIdCard'", TextView.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.tvAuthStatus = Utils.findRequiredViewAsType(source, R.id.tv_auth_status, "field 'tvAuthStatus'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    UserInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.tvName = null;
    target.clCleanCache = null;
    target.tvIdCard = null;
    target.tvPhone = null;
    target.tvAuthStatus = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
  }
}
