// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RealNameFirstActivity_ViewBinding implements Unbinder {
  private RealNameFirstActivity target;

  private View view2131296633;

  private View view2131296993;

  private View view2131296994;

  private View view2131296842;

  private View view2131296374;

  @UiThread
  public RealNameFirstActivity_ViewBinding(RealNameFirstActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RealNameFirstActivity_ViewBinding(final RealNameFirstActivity target, View source) {
    this.target = target;

    View view;
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.etName = Utils.findRequiredViewAsType(source, R.id.et_name, "field 'etName'", TextView.class);
    target.etIdcard = Utils.findRequiredViewAsType(source, R.id.et_idcard, "field 'etIdcard'", EditText.class);
    target.tvCustomerStatusDesc = Utils.findRequiredViewAsType(source, R.id.tv_customer_status_desc, "field 'tvCustomerStatusDesc'", TextView.class);
    target.llCustomerStatus = Utils.findRequiredViewAsType(source, R.id.ll_customer_status, "field 'llCustomerStatus'", LinearLayout.class);
    target.tvCheckedInfo = Utils.findRequiredViewAsType(source, R.id.tv_checked_info, "field 'tvCheckedInfo'", TextView.class);
    target.llCheckedInfo = Utils.findRequiredViewAsType(source, R.id.ll_checked_info, "field 'llCheckedInfo'", LinearLayout.class);
    target.ivId1 = Utils.findRequiredViewAsType(source, R.id.iv_id_card_a, "field 'ivId1'", ImageView.class);
    target.ivId2 = Utils.findRequiredViewAsType(source, R.id.iv_id_card_b, "field 'ivId2'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'ivBack' and method 'onViewClicked'");
    target.ivBack = Utils.castView(view, R.id.iv_back, "field 'ivBack'", ImageView.class);
    view2131296633 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'tvRight'", TextView.class);
    target.ivRight = Utils.findRequiredViewAsType(source, R.id.iv_right, "field 'ivRight'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.rl_id_card_a, "field 'rlIdCardA' and method 'onViewClicked'");
    target.rlIdCardA = Utils.castView(view, R.id.rl_id_card_a, "field 'rlIdCardA'", RelativeLayout.class);
    view2131296993 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_id_card_b, "field 'rlIdCardB' and method 'onViewClicked'");
    target.rlIdCardB = Utils.castView(view, R.id.rl_id_card_b, "field 'rlIdCardB'", RelativeLayout.class);
    view2131296994 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvArea = Utils.findRequiredViewAsType(source, R.id.tv_area, "field 'tvArea'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ly_area, "field 'lyArea' and method 'onViewClicked'");
    target.lyArea = Utils.castView(view, R.id.ly_area, "field 'lyArea'", LinearLayout.class);
    view2131296842 = view;
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
    RealNameFirstActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitle = null;
    target.etName = null;
    target.etIdcard = null;
    target.tvCustomerStatusDesc = null;
    target.llCustomerStatus = null;
    target.tvCheckedInfo = null;
    target.llCheckedInfo = null;
    target.ivId1 = null;
    target.ivId2 = null;
    target.ivBack = null;
    target.tvRight = null;
    target.ivRight = null;
    target.rlIdCardA = null;
    target.rlIdCardB = null;
    target.tvArea = null;
    target.lyArea = null;
    target.btnNext = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296993.setOnClickListener(null);
    view2131296993 = null;
    view2131296994.setOnClickListener(null);
    view2131296994 = null;
    view2131296842.setOnClickListener(null);
    view2131296842 = null;
    view2131296374.setOnClickListener(null);
    view2131296374 = null;
  }
}
