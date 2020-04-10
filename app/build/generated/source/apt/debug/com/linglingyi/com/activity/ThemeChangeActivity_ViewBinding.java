// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ThemeChangeActivity_ViewBinding implements Unbinder {
  private ThemeChangeActivity target;

  private View view2131296633;

  private View view2131296818;

  private View view2131296817;

  private View view2131296820;

  private View view2131296821;

  private View view2131296819;

  private View view2131296374;

  @UiThread
  public ThemeChangeActivity_ViewBinding(ThemeChangeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ThemeChangeActivity_ViewBinding(final ThemeChangeActivity target, View source) {
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
    view = Utils.findRequiredView(source, R.id.ll_theme_blue, "field 'llThemeBlue' and method 'onViewClicked'");
    target.llThemeBlue = Utils.castView(view, R.id.ll_theme_blue, "field 'llThemeBlue'", LinearLayout.class);
    view2131296818 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_theme_black, "field 'llThemeBlack' and method 'onViewClicked'");
    target.llThemeBlack = Utils.castView(view, R.id.ll_theme_black, "field 'llThemeBlack'", LinearLayout.class);
    view2131296817 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_theme_orange, "field 'llThemeOrange' and method 'onViewClicked'");
    target.llThemeOrange = Utils.castView(view, R.id.ll_theme_orange, "field 'llThemeOrange'", LinearLayout.class);
    view2131296820 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_theme_yellow, "field 'llThemeYellow' and method 'onViewClicked'");
    target.llThemeYellow = Utils.castView(view, R.id.ll_theme_yellow, "field 'llThemeYellow'", LinearLayout.class);
    view2131296821 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_theme_green, "field 'llThemeGreen' and method 'onViewClicked'");
    target.llThemeGreen = Utils.castView(view, R.id.ll_theme_green, "field 'llThemeGreen'", LinearLayout.class);
    view2131296819 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_next, "field 'btnNext' and method 'onViewClicked'");
    target.btnNext = Utils.castView(view, R.id.btn_next, "field 'btnNext'", Button.class);
    view2131296374 = view;
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
    ThemeChangeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.llThemeBlue = null;
    target.llThemeBlack = null;
    target.llThemeOrange = null;
    target.llThemeYellow = null;
    target.llThemeGreen = null;
    target.btnNext = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296818.setOnClickListener(null);
    view2131296818 = null;
    view2131296817.setOnClickListener(null);
    view2131296817 = null;
    view2131296820.setOnClickListener(null);
    view2131296820 = null;
    view2131296821.setOnClickListener(null);
    view2131296821 = null;
    view2131296819.setOnClickListener(null);
    view2131296819 = null;
    view2131296374.setOnClickListener(null);
    view2131296374 = null;
  }
}
