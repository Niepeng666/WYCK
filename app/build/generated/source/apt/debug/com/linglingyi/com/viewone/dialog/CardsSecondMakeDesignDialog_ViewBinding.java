// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.viewone.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CardsSecondMakeDesignDialog_ViewBinding implements Unbinder {
  private CardsSecondMakeDesignDialog target;

  private View view2131296380;

  private View view2131296642;

  @UiThread
  public CardsSecondMakeDesignDialog_ViewBinding(final CardsSecondMakeDesignDialog target,
      View source) {
    this.target = target;

    View view;
    target.etInputPayAmount = Utils.findRequiredViewAsType(source, R.id.et_inputPayAmount, "field 'etInputPayAmount'", EditText.class);
    target.tvDate = Utils.findRequiredViewAsType(source, R.id.tv_date, "field 'tvDate'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_previewPlan, "field 'btnPreviewPlan' and method 'onViewClicked'");
    target.btnPreviewPlan = Utils.castView(view, R.id.btn_previewPlan, "field 'btnPreviewPlan'", Button.class);
    view2131296380 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_close, "field 'ivClose' and method 'onViewClicked'");
    target.ivClose = Utils.castView(view, R.id.iv_close, "field 'ivClose'", ImageView.class);
    view2131296642 = view;
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
    CardsSecondMakeDesignDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etInputPayAmount = null;
    target.tvDate = null;
    target.btnPreviewPlan = null;
    target.ivClose = null;

    view2131296380.setOnClickListener(null);
    view2131296380 = null;
    view2131296642.setOnClickListener(null);
    view2131296642 = null;
  }
}
