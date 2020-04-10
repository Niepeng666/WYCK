// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
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

public class OrderConfirmActivity_ViewBinding implements Unbinder {
  private OrderConfirmActivity target;

  private View view2131296633;

  private View view2131296437;

  private View view2131296978;

  private View view2131296381;

  @UiThread
  public OrderConfirmActivity_ViewBinding(OrderConfirmActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OrderConfirmActivity_ViewBinding(final OrderConfirmActivity target, View source) {
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
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.tvAddress = Utils.findRequiredViewAsType(source, R.id.tv_address, "field 'tvAddress'", TextView.class);
    view = Utils.findRequiredView(source, R.id.cl_address, "field 'clAddress' and method 'onViewClicked'");
    target.clAddress = Utils.castView(view, R.id.cl_address, "field 'clAddress'", ConstraintLayout.class);
    view2131296437 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_address_add, "field 'rlAddressAdd' and method 'onViewClicked'");
    target.rlAddressAdd = Utils.castView(view, R.id.rl_address_add, "field 'rlAddressAdd'", RelativeLayout.class);
    view2131296978 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.rlAddressManager = Utils.findRequiredViewAsType(source, R.id.rl_address_manager, "field 'rlAddressManager'", RelativeLayout.class);
    target.ivProduct = Utils.findRequiredViewAsType(source, R.id.iv_product, "field 'ivProduct'", ImageView.class);
    target.tvProductName = Utils.findRequiredViewAsType(source, R.id.tv_product_name, "field 'tvProductName'", TextView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tvPrice'", TextView.class);
    target.tvMoney2 = Utils.findRequiredViewAsType(source, R.id.tv_money_2, "field 'tvMoney2'", TextView.class);
    target.tvDeliverFee = Utils.findRequiredViewAsType(source, R.id.tv_deliver_fee, "field 'tvDeliverFee'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_purchase, "field 'btnPurchase' and method 'onViewClicked'");
    target.btnPurchase = Utils.castView(view, R.id.btn_purchase, "field 'btnPurchase'", Button.class);
    view2131296381 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.llBottom = Utils.findRequiredViewAsType(source, R.id.ll_bottom, "field 'llBottom'", LinearLayout.class);
    target.tvSpecification = Utils.findRequiredViewAsType(source, R.id.tv_specification, "field 'tvSpecification'", TextView.class);
    target.tvRemark = Utils.findRequiredViewAsType(source, R.id.tv_remark, "field 'tvRemark'", TextView.class);
    target.tvStock = Utils.findRequiredViewAsType(source, R.id.tv_stock, "field 'tvStock'", TextView.class);
    target.tvItemNum = Utils.findRequiredViewAsType(source, R.id.tv_item_num, "field 'tvItemNum'", TextView.class);
    target.tvTotalPrice = Utils.findRequiredViewAsType(source, R.id.tv_total_price, "field 'tvTotalPrice'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OrderConfirmActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.tvName = null;
    target.tvPhone = null;
    target.tvAddress = null;
    target.clAddress = null;
    target.rlAddressAdd = null;
    target.rlAddressManager = null;
    target.ivProduct = null;
    target.tvProductName = null;
    target.tvPrice = null;
    target.tvMoney2 = null;
    target.tvDeliverFee = null;
    target.btnPurchase = null;
    target.llBottom = null;
    target.tvSpecification = null;
    target.tvRemark = null;
    target.tvStock = null;
    target.tvItemNum = null;
    target.tvTotalPrice = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296437.setOnClickListener(null);
    view2131296437 = null;
    view2131296978.setOnClickListener(null);
    view2131296978 = null;
    view2131296381.setOnClickListener(null);
    view2131296381 = null;
  }
}
