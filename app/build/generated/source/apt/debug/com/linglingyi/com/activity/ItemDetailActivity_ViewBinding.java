// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ItemDetailActivity_ViewBinding implements Unbinder {
  private ItemDetailActivity target;

  private View view2131296381;

  private View view2131296633;

  @UiThread
  public ItemDetailActivity_ViewBinding(ItemDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ItemDetailActivity_ViewBinding(final ItemDetailActivity target, View source) {
    this.target = target;

    View view;
    target.image = Utils.findRequiredViewAsType(source, R.id.image, "field 'image'", ImageView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tvPrice'", TextView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvStock = Utils.findRequiredViewAsType(source, R.id.tv_stock, "field 'tvStock'", TextView.class);
    target.tvDeliverFee1 = Utils.findRequiredViewAsType(source, R.id.tv_deliver_fee_1, "field 'tvDeliverFee1'", TextView.class);
    target.introduceShopping = Utils.findRequiredViewAsType(source, R.id.introduce_shopping, "field 'introduceShopping'", WebView.class);
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
    target.llBottom = Utils.findRequiredViewAsType(source, R.id.ll_bottom, "field 'llBottom'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ItemDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.image = null;
    target.tvPrice = null;
    target.tvName = null;
    target.tvStock = null;
    target.tvDeliverFee1 = null;
    target.introduceShopping = null;
    target.tvMoney2 = null;
    target.tvDeliverFee = null;
    target.btnPurchase = null;
    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.llBottom = null;

    view2131296381.setOnClickListener(null);
    view2131296381 = null;
    view2131296633.setOnClickListener(null);
    view2131296633 = null;
  }
}
