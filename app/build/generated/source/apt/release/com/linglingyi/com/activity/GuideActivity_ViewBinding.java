// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GuideActivity_ViewBinding implements Unbinder {
  private GuideActivity target;

  @UiThread
  public GuideActivity_ViewBinding(GuideActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GuideActivity_ViewBinding(GuideActivity target, View source) {
    this.target = target;

    target.guideVp = Utils.findRequiredViewAsType(source, R.id.guide_vp, "field 'guideVp'", ViewPager.class);
    target.btGo = Utils.findRequiredViewAsType(source, R.id.bt_go, "field 'btGo'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    GuideActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.guideVp = null;
    target.btGo = null;
  }
}
