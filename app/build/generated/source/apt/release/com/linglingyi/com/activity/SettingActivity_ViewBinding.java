// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingActivity_ViewBinding implements Unbinder {
  private SettingActivity target;

  private View view2131296633;

  private View view2131296998;

  private View view2131296987;

  private View view2131296369;

  private View view2131296996;

  private View view2131296985;

  @UiThread
  public SettingActivity_ViewBinding(SettingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SettingActivity_ViewBinding(final SettingActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'ivBack' and method 'onViewClicked'");
    target.ivBack = Utils.castView(view, R.id.iv_back, "field 'ivBack'", ImageView.class);
    view2131296633 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'tvRight'", TextView.class);
    target.ivRight = Utils.findRequiredViewAsType(source, R.id.iv_right, "field 'ivRight'", ImageView.class);
    target.modifypwd = Utils.findRequiredViewAsType(source, R.id.modifypwd, "field 'modifypwd'", ImageView.class);
    target.ivService = Utils.findRequiredViewAsType(source, R.id.iv_service, "field 'ivService'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.rl_service, "field 'rlService' and method 'onViewClicked'");
    target.rlService = Utils.castView(view, R.id.rl_service, "field 'rlService'", RelativeLayout.class);
    view2131296998 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ivCache = Utils.findRequiredViewAsType(source, R.id.iv_cache, "field 'ivCache'", ImageView.class);
    target.tvCache = Utils.findRequiredViewAsType(source, R.id.tv_cache, "field 'tvCache'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_clear_cache, "field 'rlClearCache' and method 'onViewClicked'");
    target.rlClearCache = Utils.castView(view, R.id.rl_clear_cache, "field 'rlClearCache'", RelativeLayout.class);
    view2131296987 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ivVersion = Utils.findRequiredViewAsType(source, R.id.iv_version, "field 'ivVersion'", ImageView.class);
    target.tvVersion = Utils.findRequiredViewAsType(source, R.id.tv_version, "field 'tvVersion'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_exit, "field 'btnExit' and method 'onViewClicked'");
    target.btnExit = Utils.castView(view, R.id.btn_exit, "field 'btnExit'", Button.class);
    view2131296369 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_pass_login, "field 'rlPassLogin' and method 'onViewClicked'");
    target.rlPassLogin = Utils.castView(view, R.id.rl_pass_login, "field 'rlPassLogin'", RelativeLayout.class);
    view2131296996 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.auth = Utils.findRequiredViewAsType(source, R.id.auth, "field 'auth'", ImageView.class);
    target.switchDefalut = Utils.findRequiredViewAsType(source, R.id.switch_defalut, "field 'switchDefalut'", Switch.class);
    target.rlAuthChange = Utils.findRequiredViewAsType(source, R.id.rl_auth_change, "field 'rlAuthChange'", RelativeLayout.class);
    target.theme = Utils.findRequiredViewAsType(source, R.id.theme, "field 'theme'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.rl_change_theme, "field 'rlChangeTheme' and method 'onViewClicked'");
    target.rlChangeTheme = Utils.castView(view, R.id.rl_change_theme, "field 'rlChangeTheme'", RelativeLayout.class);
    view2131296985 = view;
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
    SettingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.modifypwd = null;
    target.ivService = null;
    target.rlService = null;
    target.ivCache = null;
    target.tvCache = null;
    target.rlClearCache = null;
    target.ivVersion = null;
    target.tvVersion = null;
    target.btnExit = null;
    target.rlPassLogin = null;
    target.auth = null;
    target.switchDefalut = null;
    target.rlAuthChange = null;
    target.theme = null;
    target.rlChangeTheme = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296998.setOnClickListener(null);
    view2131296998 = null;
    view2131296987.setOnClickListener(null);
    view2131296987 = null;
    view2131296369.setOnClickListener(null);
    view2131296369 = null;
    view2131296996.setOnClickListener(null);
    view2131296996 = null;
    view2131296985.setOnClickListener(null);
    view2131296985 = null;
  }
}
