// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.viewone.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CardsMainMakeDesignDialog_ViewBinding implements Unbinder {
  private CardsMainMakeDesignDialog target;

  private View view2131297170;

  private View view2131296380;

  private View view2131296642;

  @UiThread
  public CardsMainMakeDesignDialog_ViewBinding(final CardsMainMakeDesignDialog target,
      View source) {
    this.target = target;

    View view;
    target.etInputPayAmount = Utils.findRequiredViewAsType(source, R.id.et_inputPayAmount, "field 'etInputPayAmount'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_city, "field 'tvCity' and method 'onViewClicked'");
    target.tvCity = Utils.castView(view, R.id.tv_city, "field 'tvCity'", TextView.class);
    view2131297170 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvDate = Utils.findRequiredViewAsType(source, R.id.tv_date, "field 'tvDate'", TextView.class);
    target.etReserveMoney = Utils.findRequiredViewAsType(source, R.id.et_reserve_money, "field 'etReserveMoney'", EditText.class);
    target.switchDefalut = Utils.findRequiredViewAsType(source, R.id.switch_defalut, "field 'switchDefalut'", Switch.class);
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
    CardsMainMakeDesignDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etInputPayAmount = null;
    target.tvCity = null;
    target.tvDate = null;
    target.etReserveMoney = null;
    target.switchDefalut = null;
    target.btnPreviewPlan = null;
    target.ivClose = null;

    view2131297170.setOnClickListener(null);
    view2131297170 = null;
    view2131296380.setOnClickListener(null);
    view2131296380 = null;
    view2131296642.setOnClickListener(null);
    view2131296642 = null;
  }
}
