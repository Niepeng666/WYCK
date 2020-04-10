// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.linglingyi.com.viewone.X5WebView;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class X5WebViewActivity_ViewBinding implements Unbinder {
  private X5WebViewActivity target;

  @UiThread
  public X5WebViewActivity_ViewBinding(X5WebViewActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public X5WebViewActivity_ViewBinding(X5WebViewActivity target, View source) {
    this.target = target;

    target.ivBack = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'ivBack'", ImageView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'tvRight'", TextView.class);
    target.ivRight = Utils.findRequiredViewAsType(source, R.id.iv_right, "field 'ivRight'", ImageView.class);
    target.progressBar1 = Utils.findRequiredViewAsType(source, R.id.progressBar1, "field 'progressBar1'", ProgressBar.class);
    target.webview = Utils.findRequiredViewAsType(source, R.id.webview, "field 'webview'", X5WebView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    X5WebViewActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.progressBar1 = null;
    target.webview = null;
  }
}
