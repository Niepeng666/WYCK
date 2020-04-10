// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.viewone.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SharePicBottomDialog_ViewBinding implements Unbinder {
  private SharePicBottomDialog target;

  private View view2131297438;

  private View view2131297439;

  private View view2131297355;

  private View view2131297356;

  private View view2131297156;

  @UiThread
  public SharePicBottomDialog_ViewBinding(final SharePicBottomDialog target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.tv_wechat, "field 'tvWechat' and method 'onViewClicked'");
    target.tvWechat = Utils.castView(view, R.id.tv_wechat, "field 'tvWechat'", TextView.class);
    view2131297438 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_wechat_friend, "field 'tvWechatFriend' and method 'onViewClicked'");
    target.tvWechatFriend = Utils.castView(view, R.id.tv_wechat_friend, "field 'tvWechatFriend'", TextView.class);
    view2131297439 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_qq, "field 'tvQq' and method 'onViewClicked'");
    target.tvQq = Utils.castView(view, R.id.tv_qq, "field 'tvQq'", TextView.class);
    view2131297355 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_qq_space, "field 'tvQqSpace' and method 'onViewClicked'");
    target.tvQqSpace = Utils.castView(view, R.id.tv_qq_space, "field 'tvQqSpace'", TextView.class);
    view2131297356 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_cancel, "field 'tvCancel' and method 'onViewClicked'");
    target.tvCancel = Utils.castView(view, R.id.tv_cancel, "field 'tvCancel'", TextView.class);
    view2131297156 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.popLayout = Utils.findRequiredViewAsType(source, R.id.pop_layout, "field 'popLayout'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SharePicBottomDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvWechat = null;
    target.tvWechatFriend = null;
    target.tvQq = null;
    target.tvQqSpace = null;
    target.tvCancel = null;
    target.popLayout = null;

    view2131297438.setOnClickListener(null);
    view2131297438 = null;
    view2131297439.setOnClickListener(null);
    view2131297439 = null;
    view2131297355.setOnClickListener(null);
    view2131297355 = null;
    view2131297356.setOnClickListener(null);
    view2131297356 = null;
    view2131297156.setOnClickListener(null);
    view2131297156 = null;
  }
}
