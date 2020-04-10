// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderDetailActivity_ViewBinding implements Unbinder {
  private OrderDetailActivity target;

  private View view2131296633;

  private View view2131296381;

  @UiThread
  public OrderDetailActivity_ViewBinding(OrderDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OrderDetailActivity_ViewBinding(final OrderDetailActivity target, View source) {
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
    target.tvOrderStatus = Utils.findRequiredViewAsType(source, R.id.tv_order_status, "field 'tvOrderStatus'", TextView.class);
    target.ivOrderStatus = Utils.findRequiredViewAsType(source, R.id.iv_order_status, "field 'ivOrderStatus'", ImageView.class);
    target.ivAddressIcon = Utils.findRequiredViewAsType(source, R.id.iv_address_icon, "field 'ivAddressIcon'", ImageView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.tvAddress = Utils.findRequiredViewAsType(source, R.id.tv_address, "field 'tvAddress'", TextView.class);
    target.ivProduct = Utils.findRequiredViewAsType(source, R.id.iv_product, "field 'ivProduct'", ImageView.class);
    target.tvProductName = Utils.findRequiredViewAsType(source, R.id.tv_product_name, "field 'tvProductName'", TextView.class);
    target.tvSpecification = Utils.findRequiredViewAsType(source, R.id.tv_specification, "field 'tvSpecification'", TextView.class);
    target.tvStock = Utils.findRequiredViewAsType(source, R.id.tv_stock, "field 'tvStock'", TextView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tvPrice'", TextView.class);
    target.tvDelivery = Utils.findRequiredViewAsType(source, R.id.tv_delivery, "field 'tvDelivery'", TextView.class);
    target.tvTotalPrice = Utils.findRequiredViewAsType(source, R.id.tv_total_price, "field 'tvTotalPrice'", TextView.class);
    target.tvOrderNo = Utils.findRequiredViewAsType(source, R.id.tv_order_no, "field 'tvOrderNo'", TextView.class);
    target.tvOrderTime = Utils.findRequiredViewAsType(source, R.id.tv_order_time, "field 'tvOrderTime'", TextView.class);
    target.tvDeliverNumber = Utils.findRequiredViewAsType(source, R.id.tv_deliver_number, "field 'tvDeliverNumber'", TextView.class);
    target.rlDeliverNumber = Utils.findRequiredViewAsType(source, R.id.rl_deliver_number, "field 'rlDeliverNumber'", RelativeLayout.class);
    target.tvMoneyTitle = Utils.findRequiredViewAsType(source, R.id.tv_money_title, "field 'tvMoneyTitle'", TextView.class);
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
    target.llBottomPay = Utils.findRequiredViewAsType(source, R.id.ll_bottom_pay, "field 'llBottomPay'", RelativeLayout.class);
    target.tvSinglePrice = Utils.findRequiredViewAsType(source, R.id.tv_single_price, "field 'tvSinglePrice'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OrderDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.tvOrderStatus = null;
    target.ivOrderStatus = null;
    target.ivAddressIcon = null;
    target.tvName = null;
    target.tvPhone = null;
    target.tvAddress = null;
    target.ivProduct = null;
    target.tvProductName = null;
    target.tvSpecification = null;
    target.tvStock = null;
    target.tvPrice = null;
    target.tvDelivery = null;
    target.tvTotalPrice = null;
    target.tvOrderNo = null;
    target.tvOrderTime = null;
    target.tvDeliverNumber = null;
    target.rlDeliverNumber = null;
    target.tvMoneyTitle = null;
    target.tvMoney2 = null;
    target.tvDeliverFee = null;
    target.btnPurchase = null;
    target.llBottomPay = null;
    target.tvSinglePrice = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296381.setOnClickListener(null);
    view2131296381 = null;
  }
}
