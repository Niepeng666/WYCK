// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class AddressAddActivity_ViewBinding implements Unbinder {
  private AddressAddActivity target;

  private View view2131296633;

  private View view2131297137;

  private View view2131296358;

  @UiThread
  public AddressAddActivity_ViewBinding(AddressAddActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddressAddActivity_ViewBinding(final AddressAddActivity target, View source) {
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
    target.etAddressName = Utils.findRequiredViewAsType(source, R.id.et_address_name, "field 'etAddressName'", EditText.class);
    target.etAddressPhone = Utils.findRequiredViewAsType(source, R.id.et_address_phone, "field 'etAddressPhone'", EditText.class);
    target.tvAddressTxt = Utils.findRequiredViewAsType(source, R.id.tv_address_txt, "field 'tvAddressTxt'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_area, "field 'tvArea' and method 'onViewClicked'");
    target.tvArea = Utils.castView(view, R.id.tv_area, "field 'tvArea'", TextView.class);
    view2131297137 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etAddressDetail = Utils.findRequiredViewAsType(source, R.id.et_address_detail, "field 'etAddressDetail'", EditText.class);
    target.checkbox = Utils.findRequiredViewAsType(source, R.id.checkbox, "field 'checkbox'", CheckBox.class);
    target.lyCheck = Utils.findRequiredViewAsType(source, R.id.ly_check, "field 'lyCheck'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.btn_address_save, "field 'btnAddressSave' and method 'onViewClicked'");
    target.btnAddressSave = Utils.castView(view, R.id.btn_address_save, "field 'btnAddressSave'", Button.class);
    view2131296358 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.llContainer = Utils.findRequiredViewAsType(source, R.id.ll_container, "field 'llContainer'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddressAddActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.etAddressName = null;
    target.etAddressPhone = null;
    target.tvAddressTxt = null;
    target.tvArea = null;
    target.etAddressDetail = null;
    target.checkbox = null;
    target.lyCheck = null;
    target.btnAddressSave = null;
    target.llContainer = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131297137.setOnClickListener(null);
    view2131297137 = null;
    view2131296358.setOnClickListener(null);
    view2131296358 = null;
  }
}
