// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TradeRecordDetailActivity_ViewBinding implements Unbinder {
  private TradeRecordDetailActivity target;

  private View view2131296633;

  @UiThread
  public TradeRecordDetailActivity_ViewBinding(TradeRecordDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TradeRecordDetailActivity_ViewBinding(final TradeRecordDetailActivity target,
      View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'ivBack' and method 'onViewClicked'");
    target.ivBack = Utils.castView(view, R.id.iv_back, "field 'ivBack'", ImageView.class);
    view2131296633 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'tvRight'", TextView.class);
    target.ivRight = Utils.findRequiredViewAsType(source, R.id.iv_right, "field 'ivRight'", ImageView.class);
    target.tvMoney = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tvMoney'", TextView.class);
    target.tvMerchantName = Utils.findRequiredViewAsType(source, R.id.tv_merchant_name, "field 'tvMerchantName'", TextView.class);
    target.tvTradeType = Utils.findRequiredViewAsType(source, R.id.tv_trade_type, "field 'tvTradeType'", TextView.class);
    target.tvTradeTime = Utils.findRequiredViewAsType(source, R.id.tv_trade_time, "field 'tvTradeTime'", TextView.class);
    target.tvTradeAccount = Utils.findRequiredViewAsType(source, R.id.tv_trade_account, "field 'tvTradeAccount'", TextView.class);
    target.tvTradeNo = Utils.findRequiredViewAsType(source, R.id.tv_trade_no, "field 'tvTradeNo'", TextView.class);
    target.tvTradeStatus = Utils.findRequiredViewAsType(source, R.id.tv_trade_status, "field 'tvTradeStatus'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TradeRecordDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.tvMoney = null;
    target.tvMerchantName = null;
    target.tvTradeType = null;
    target.tvTradeTime = null;
    target.tvTradeAccount = null;
    target.tvTradeNo = null;
    target.tvTradeStatus = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
  }
}
