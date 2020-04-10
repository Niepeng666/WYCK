// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BindCardActivity_ViewBinding implements Unbinder {
  private BindCardActivity target;

  private View view2131296633;

  private View view2131296701;

  private View view2131296702;

  private View view2131296344;

  private View view2131296351;

  @UiThread
  public BindCardActivity_ViewBinding(BindCardActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BindCardActivity_ViewBinding(final BindCardActivity target, View source) {
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
    target.etBankNumber = Utils.findRequiredViewAsType(source, R.id.et_bank_number, "field 'etBankNumber'", EditText.class);
    target.etName = Utils.findRequiredViewAsType(source, R.id.et_name, "field 'etName'", EditText.class);
    target.etCardNumber = Utils.findRequiredViewAsType(source, R.id.et_card_number, "field 'etCardNumber'", EditText.class);
    target.etExpiryDate = Utils.findRequiredViewAsType(source, R.id.et_expiryDate, "field 'etExpiryDate'", EditText.class);
    view = Utils.findRequiredView(source, R.id.iv_showValidate, "field 'ivShowValidate' and method 'onViewClicked'");
    target.ivShowValidate = Utils.castView(view, R.id.iv_showValidate, "field 'ivShowValidate'", ImageView.class);
    view2131296701 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etStatement = Utils.findRequiredViewAsType(source, R.id.et_statement, "field 'etStatement'", EditText.class);
    view = Utils.findRequiredView(source, R.id.iv_showValidate1, "field 'ivShowValidate1' and method 'onViewClicked'");
    target.ivShowValidate1 = Utils.castView(view, R.id.iv_showValidate1, "field 'ivShowValidate1'", ImageView.class);
    view2131296702 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etPhoneNumber = Utils.findRequiredViewAsType(source, R.id.et_phone_number, "field 'etPhoneNumber'", EditText.class);
    target.etConfirmCode = Utils.findRequiredViewAsType(source, R.id.et_confirmCode, "field 'etConfirmCode'", EditText.class);
    view = Utils.findRequiredView(source, R.id.bt_getConfirmCode, "field 'btGetConfirmCode' and method 'onViewClicked'");
    target.btGetConfirmCode = Utils.castView(view, R.id.bt_getConfirmCode, "field 'btGetConfirmCode'", Button.class);
    view2131296344 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.layoutCode = Utils.findRequiredViewAsType(source, R.id.layout_code, "field 'layoutCode'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.bt_submit, "field 'btSubmit' and method 'onViewClicked'");
    target.btSubmit = Utils.castView(view, R.id.bt_submit, "field 'btSubmit'", Button.class);
    view2131296351 = view;
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
    BindCardActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.etBankNumber = null;
    target.etName = null;
    target.etCardNumber = null;
    target.etExpiryDate = null;
    target.ivShowValidate = null;
    target.etStatement = null;
    target.ivShowValidate1 = null;
    target.etPhoneNumber = null;
    target.etConfirmCode = null;
    target.btGetConfirmCode = null;
    target.layoutCode = null;
    target.btSubmit = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296701.setOnClickListener(null);
    view2131296701 = null;
    view2131296702.setOnClickListener(null);
    view2131296702 = null;
    view2131296344.setOnClickListener(null);
    view2131296344 = null;
    view2131296351.setOnClickListener(null);
    view2131296351 = null;
  }
}
