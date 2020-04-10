// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.linglingyi.com.viewone.CustomCountDownTimerView;
import com.linglingyi.com.viewone.FontIconView;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderPayDetailActivity_ViewBinding implements Unbinder {
  private OrderPayDetailActivity target;

  private View view2131296633;

  private View view2131296830;

  private View view2131296732;

  private View view2131296747;

  private View view2131296387;

  @UiThread
  public OrderPayDetailActivity_ViewBinding(OrderPayDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OrderPayDetailActivity_ViewBinding(final OrderPayDetailActivity target, View source) {
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
    target.customTvCountDown = Utils.findRequiredViewAsType(source, R.id.custom_tv_count_down, "field 'customTvCountDown'", CustomCountDownTimerView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tvPrice'", TextView.class);
    target.tvOrderNo = Utils.findRequiredViewAsType(source, R.id.tv_order_no, "field 'tvOrderNo'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_weixin, "field 'llWeixin' and method 'onViewClicked'");
    target.llWeixin = Utils.castView(view, R.id.ll_weixin, "field 'llWeixin'", LinearLayout.class);
    view2131296830 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_alipay, "field 'llAlipay' and method 'onViewClicked'");
    target.llAlipay = Utils.castView(view, R.id.ll_alipay, "field 'llAlipay'", LinearLayout.class);
    view2131296732 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_change, "field 'llChange' and method 'onViewClicked'");
    target.llChange = Utils.castView(view, R.id.ll_change, "field 'llChange'", LinearLayout.class);
    view2131296747 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_submit, "field 'btnSubmit' and method 'onViewClicked'");
    target.btnSubmit = Utils.castView(view, R.id.btn_submit, "field 'btnSubmit'", Button.class);
    view2131296387 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvItemTitle = Utils.findRequiredViewAsType(source, R.id.tv_item_title, "field 'tvItemTitle'", TextView.class);
    target.wechatChoose = Utils.findRequiredViewAsType(source, R.id.wechat_choose, "field 'wechatChoose'", FontIconView.class);
    target.zhifubaoChoose = Utils.findRequiredViewAsType(source, R.id.zhifubao_choose, "field 'zhifubaoChoose'", FontIconView.class);
    target.cardChoose = Utils.findRequiredViewAsType(source, R.id.card_choose, "field 'cardChoose'", FontIconView.class);
    target.llCountDown = Utils.findRequiredViewAsType(source, R.id.ll_count_down, "field 'llCountDown'", LinearLayout.class);
    target.llOrderClose = Utils.findRequiredViewAsType(source, R.id.ll_order_close, "field 'llOrderClose'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OrderPayDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.customTvCountDown = null;
    target.tvPrice = null;
    target.tvOrderNo = null;
    target.llWeixin = null;
    target.llAlipay = null;
    target.llChange = null;
    target.btnSubmit = null;
    target.tvItemTitle = null;
    target.wechatChoose = null;
    target.zhifubaoChoose = null;
    target.cardChoose = null;
    target.llCountDown = null;
    target.llOrderClose = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296830.setOnClickListener(null);
    view2131296830 = null;
    view2131296732.setOnClickListener(null);
    view2131296732 = null;
    view2131296747.setOnClickListener(null);
    view2131296747 = null;
    view2131296387.setOnClickListener(null);
    view2131296387 = null;
  }
}
