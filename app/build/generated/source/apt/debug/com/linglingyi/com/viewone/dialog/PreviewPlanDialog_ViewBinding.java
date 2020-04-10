// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.viewone.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PreviewPlanDialog_ViewBinding implements Unbinder {
  private PreviewPlanDialog target;

  private View view2131296339;

  private View view2131296352;

  @UiThread
  public PreviewPlanDialog_ViewBinding(final PreviewPlanDialog target, View source) {
    this.target = target;

    View view;
    target.lvPlanDetail = Utils.findRequiredViewAsType(source, R.id.lv_plan_detail, "field 'lvPlanDetail'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.bt_cancel_plan, "method 'cancel'");
    view2131296339 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.cancel();
      }
    });
    view = Utils.findRequiredView(source, R.id.bt_submit_plan, "method 'submit'");
    view2131296352 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.submit();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    PreviewPlanDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.lvPlanDetail = null;

    view2131296339.setOnClickListener(null);
    view2131296339 = null;
    view2131296352.setOnClickListener(null);
    view2131296352 = null;
  }
}
