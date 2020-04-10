// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.viewone.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NoticeDialog_ViewBinding implements Unbinder {
  private NoticeDialog target;

  private View view2131296337;

  private View view2131296341;

  @UiThread
  public NoticeDialog_ViewBinding(final NoticeDialog target, View source) {
    this.target = target;

    View view;
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvBody = Utils.findRequiredViewAsType(source, R.id.tv_body, "field 'tvBody'", TextView.class);
    target.tvTitle1 = Utils.findRequiredViewAsType(source, R.id.tv_title_1, "field 'tvTitle1'", TextView.class);
    view = Utils.findRequiredView(source, R.id.bt_cancel, "field 'btCancel' and method 'onViewClicked'");
    target.btCancel = Utils.castView(view, R.id.bt_cancel, "field 'btCancel'", Button.class);
    view2131296337 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.bt_details, "field 'btDetails' and method 'onViewClicked'");
    target.btDetails = Utils.castView(view, R.id.bt_details, "field 'btDetails'", Button.class);
    view2131296341 = view;
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
    NoticeDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitle = null;
    target.tvBody = null;
    target.tvTitle1 = null;
    target.btCancel = null;
    target.btDetails = null;

    view2131296337.setOnClickListener(null);
    view2131296337 = null;
    view2131296341.setOnClickListener(null);
    view2131296341 = null;
  }
}
