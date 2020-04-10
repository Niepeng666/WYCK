// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShareFragment_ViewBinding implements Unbinder {
  private ShareFragment target;

  private View view2131296807;

  private View view2131296804;

  private View view2131296829;

  private View view2131296811;

  private View view2131296814;

  private View view2131296813;

  private View view2131296812;

  @UiThread
  public ShareFragment_ViewBinding(final ShareFragment target, View source) {
    this.target = target;

    View view;
    target.ivBack = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'ivBack'", ImageView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'tvRight'", TextView.class);
    target.ivRight = Utils.findRequiredViewAsType(source, R.id.iv_right, "field 'ivRight'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.ll_register, "field 'llRegister' and method 'onViewClicked'");
    target.llRegister = Utils.castView(view, R.id.ll_register, "field 'llRegister'", LinearLayout.class);
    view2131296807 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_qr_code, "field 'llQrCode' and method 'onViewClicked'");
    target.llQrCode = Utils.castView(view, R.id.ll_qr_code, "field 'llQrCode'", LinearLayout.class);
    view2131296804 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_vip, "field 'llVip' and method 'onViewClicked'");
    target.llVip = Utils.castView(view, R.id.ll_vip, "field 'llVip'", LinearLayout.class);
    view2131296829 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_share_friend, "field 'llShareFriend' and method 'onViewClicked'");
    target.llShareFriend = Utils.castView(view, R.id.ll_share_friend, "field 'llShareFriend'", LinearLayout.class);
    view2131296811 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_share_wechat, "field 'llShareWechat' and method 'onViewClicked'");
    target.llShareWechat = Utils.castView(view, R.id.ll_share_wechat, "field 'llShareWechat'", LinearLayout.class);
    view2131296814 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_share_qq, "field 'llShareQq' and method 'onViewClicked'");
    target.llShareQq = Utils.castView(view, R.id.ll_share_qq, "field 'llShareQq'", LinearLayout.class);
    view2131296813 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_share_kongjian, "field 'llShareKongjian' and method 'onViewClicked'");
    target.llShareKongjian = Utils.castView(view, R.id.ll_share_kongjian, "field 'llShareKongjian'", LinearLayout.class);
    view2131296812 = view;
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
    ShareFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.llRegister = null;
    target.llQrCode = null;
    target.llVip = null;
    target.llShareFriend = null;
    target.llShareWechat = null;
    target.llShareQq = null;
    target.llShareKongjian = null;

    view2131296807.setOnClickListener(null);
    view2131296807 = null;
    view2131296804.setOnClickListener(null);
    view2131296804 = null;
    view2131296829.setOnClickListener(null);
    view2131296829 = null;
    view2131296811.setOnClickListener(null);
    view2131296811 = null;
    view2131296814.setOnClickListener(null);
    view2131296814 = null;
    view2131296813.setOnClickListener(null);
    view2131296813 = null;
    view2131296812.setOnClickListener(null);
    view2131296812 = null;
  }
}
