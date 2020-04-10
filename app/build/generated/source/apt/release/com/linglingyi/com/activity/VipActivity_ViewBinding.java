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
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VipActivity_ViewBinding implements Unbinder {
  private VipActivity target;

  private View view2131296633;

  private View view2131296732;

  private View view2131296830;

  private View view2131296745;

  private View view2131296387;

  @UiThread
  public VipActivity_ViewBinding(VipActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public VipActivity_ViewBinding(final VipActivity target, View source) {
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
    target.ivVipPic = Utils.findRequiredViewAsType(source, R.id.iv_vip_pic, "field 'ivVipPic'", ImageView.class);
    target.zhifubaoChoose = Utils.findRequiredViewAsType(source, R.id.zhifubao_choose, "field 'zhifubaoChoose'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.ll_alipay, "field 'llAlipay' and method 'onViewClicked'");
    target.llAlipay = Utils.castView(view, R.id.ll_alipay, "field 'llAlipay'", LinearLayout.class);
    view2131296732 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.wechatChoose = Utils.findRequiredViewAsType(source, R.id.wechat_choose, "field 'wechatChoose'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.ll_weixin, "field 'llWeixin' and method 'onViewClicked'");
    target.llWeixin = Utils.castView(view, R.id.ll_weixin, "field 'llWeixin'", LinearLayout.class);
    view2131296830 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.cardChoose = Utils.findRequiredViewAsType(source, R.id.card_choose, "field 'cardChoose'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.ll_card, "field 'llCard' and method 'onViewClicked'");
    target.llCard = Utils.castView(view, R.id.ll_card, "field 'llCard'", LinearLayout.class);
    view2131296745 = view;
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
    target.tvLevelName = Utils.findRequiredViewAsType(source, R.id.tv_level_name, "field 'tvLevelName'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VipActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.ivVipPic = null;
    target.zhifubaoChoose = null;
    target.llAlipay = null;
    target.wechatChoose = null;
    target.llWeixin = null;
    target.cardChoose = null;
    target.llCard = null;
    target.btnSubmit = null;
    target.tvLevelName = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296732.setOnClickListener(null);
    view2131296732 = null;
    view2131296830.setOnClickListener(null);
    view2131296830 = null;
    view2131296745.setOnClickListener(null);
    view2131296745 = null;
    view2131296387.setOnClickListener(null);
    view2131296387 = null;
  }
}
