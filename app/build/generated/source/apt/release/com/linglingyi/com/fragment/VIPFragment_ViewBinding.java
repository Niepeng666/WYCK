// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.fragment;

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

public class VIPFragment_ViewBinding implements Unbinder {
  private VIPFragment target;

  private View view2131296663;

  private View view2131296664;

  private View view2131296665;

  private View view2131296666;

  private View view2131296667;

  @UiThread
  public VIPFragment_ViewBinding(final VIPFragment target, View source) {
    this.target = target;

    View view;
    target.ivBack = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'ivBack'", ImageView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'tvRight'", TextView.class);
    target.ivRight = Utils.findRequiredViewAsType(source, R.id.iv_right, "field 'ivRight'", ImageView.class);
    target.ivLevel1 = Utils.findRequiredViewAsType(source, R.id.iv_level_1, "field 'ivLevel1'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.iv_level_2, "field 'ivLevel2' and method 'onViewClicked'");
    target.ivLevel2 = Utils.castView(view, R.id.iv_level_2, "field 'ivLevel2'", ImageView.class);
    view2131296663 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_level_3, "field 'ivLevel3' and method 'onViewClicked'");
    target.ivLevel3 = Utils.castView(view, R.id.iv_level_3, "field 'ivLevel3'", ImageView.class);
    view2131296664 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_level_4, "field 'ivLevel4' and method 'onViewClicked'");
    target.ivLevel4 = Utils.castView(view, R.id.iv_level_4, "field 'ivLevel4'", ImageView.class);
    view2131296665 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_level_5, "field 'ivLevel5' and method 'onViewClicked'");
    target.ivLevel5 = Utils.castView(view, R.id.iv_level_5, "field 'ivLevel5'", ImageView.class);
    view2131296666 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_level_6, "field 'ivLevel6' and method 'onViewClicked'");
    target.ivLevel6 = Utils.castView(view, R.id.iv_level_6, "field 'ivLevel6'", ImageView.class);
    view2131296667 = view;
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
    VIPFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.ivLevel1 = null;
    target.ivLevel2 = null;
    target.ivLevel3 = null;
    target.ivLevel4 = null;
    target.ivLevel5 = null;
    target.ivLevel6 = null;

    view2131296663.setOnClickListener(null);
    view2131296663 = null;
    view2131296664.setOnClickListener(null);
    view2131296664 = null;
    view2131296665.setOnClickListener(null);
    view2131296665 = null;
    view2131296666.setOnClickListener(null);
    view2131296666 = null;
    view2131296667.setOnClickListener(null);
    view2131296667 = null;
  }
}
