// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.viewone.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.linglingyi.com.viewone.WarpLinearLayout;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BuyGoodsDialog_ViewBinding implements Unbinder {
  private BuyGoodsDialog target;

  private View view2131296642;

  private View view2131297248;

  private View view2131297247;

  private View view2131296343;

  @UiThread
  public BuyGoodsDialog_ViewBinding(final BuyGoodsDialog target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_close, "field 'ivClose' and method 'onViewClicked'");
    target.ivClose = Utils.castView(view, R.id.iv_close, "field 'ivClose'", ImageView.class);
    view2131296642 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ivGoodsImg = Utils.findRequiredViewAsType(source, R.id.iv_goods_img, "field 'ivGoodsImg'", ImageView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tvPrice'", TextView.class);
    target.tvStack = Utils.findRequiredViewAsType(source, R.id.tv_stack, "field 'tvStack'", TextView.class);
    target.tvSize = Utils.findRequiredViewAsType(source, R.id.tv_size, "field 'tvSize'", TextView.class);
    target.warpLayout = Utils.findRequiredViewAsType(source, R.id.warp_layout, "field 'warpLayout'", WarpLinearLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_item_sub, "field 'tvItemSub' and method 'onViewClicked'");
    target.tvItemSub = Utils.castView(view, R.id.tv_item_sub, "field 'tvItemSub'", Button.class);
    view2131297248 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvEditNum = Utils.findRequiredViewAsType(source, R.id.tv_edit_num, "field 'tvEditNum'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_item_plus, "field 'tvItemPlus' and method 'onViewClicked'");
    target.tvItemPlus = Utils.castView(view, R.id.tv_item_plus, "field 'tvItemPlus'", Button.class);
    view2131297247 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.bt_exchange, "field 'btExchange' and method 'onViewClicked'");
    target.btExchange = Utils.castView(view, R.id.bt_exchange, "field 'btExchange'", Button.class);
    view2131296343 = view;
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
    BuyGoodsDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivClose = null;
    target.ivGoodsImg = null;
    target.tvPrice = null;
    target.tvStack = null;
    target.tvSize = null;
    target.warpLayout = null;
    target.tvItemSub = null;
    target.tvEditNum = null;
    target.tvItemPlus = null;
    target.btExchange = null;

    view2131296642.setOnClickListener(null);
    view2131296642 = null;
    view2131297248.setOnClickListener(null);
    view2131297248 = null;
    view2131297247.setOnClickListener(null);
    view2131297247 = null;
    view2131296343.setOnClickListener(null);
    view2131296343 = null;
  }
}
