// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.viewone.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.NumberPicker;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AreaSelectionDialog_ViewBinding implements Unbinder {
  private AreaSelectionDialog target;

  private View view2131297203;

  @UiThread
  public AreaSelectionDialog_ViewBinding(final AreaSelectionDialog target, View source) {
    this.target = target;

    View view;
    target.npProvince = Utils.findRequiredViewAsType(source, R.id.np_province, "field 'npProvince'", NumberPicker.class);
    target.npCity = Utils.findRequiredViewAsType(source, R.id.np_city, "field 'npCity'", NumberPicker.class);
    target.npArea = Utils.findRequiredViewAsType(source, R.id.np_area, "field 'npArea'", NumberPicker.class);
    view = Utils.findRequiredView(source, R.id.tv_determine, "method 'onViewClicked'");
    view2131297203 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    AreaSelectionDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.npProvince = null;
    target.npCity = null;
    target.npArea = null;

    view2131297203.setOnClickListener(null);
    view2131297203 = null;
  }
}
