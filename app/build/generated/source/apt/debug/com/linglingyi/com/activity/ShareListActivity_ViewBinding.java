// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShareListActivity_ViewBinding implements Unbinder {
  private ShareListActivity target;

  private View view2131296633;

  private View view2131297368;

  @UiThread
  public ShareListActivity_ViewBinding(ShareListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ShareListActivity_ViewBinding(final ShareListActivity target, View source) {
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
    view = Utils.findRequiredView(source, R.id.tv_right, "field 'tvRight' and method 'onViewClicked'");
    target.tvRight = Utils.castView(view, R.id.tv_right, "field 'tvRight'", TextView.class);
    view2131297368 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ivRight = Utils.findRequiredViewAsType(source, R.id.iv_right, "field 'ivRight'", ImageView.class);
    target.ivShareBg = Utils.findRequiredViewAsType(source, R.id.iv_share_bg, "field 'ivShareBg'", ImageView.class);
    target.qrCodeIv = Utils.findRequiredViewAsType(source, R.id.qr_code_iv, "field 'qrCodeIv'", ImageView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.rl = Utils.findRequiredViewAsType(source, R.id.rl, "field 'rl'", ConstraintLayout.class);
    target.icon = Utils.findRequiredViewAsType(source, R.id.icon, "field 'icon'", ImageView.class);
    target.ivQrCode = Utils.findRequiredViewAsType(source, R.id.iv_qr_code, "field 'ivQrCode'", ImageView.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
    target.llContainer = Utils.findRequiredViewAsType(source, R.id.ll_container, "field 'llContainer'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ShareListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.ivShareBg = null;
    target.qrCodeIv = null;
    target.tvName = null;
    target.rl = null;
    target.icon = null;
    target.ivQrCode = null;
    target.recyclerView = null;
    target.llContainer = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131297368.setOnClickListener(null);
    view2131297368 = null;
  }
}
