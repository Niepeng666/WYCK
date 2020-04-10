// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ContactServiceActivity_ViewBinding implements Unbinder {
  private ContactServiceActivity target;

  private View view2131296633;

  @UiThread
  public ContactServiceActivity_ViewBinding(ContactServiceActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ContactServiceActivity_ViewBinding(final ContactServiceActivity target, View source) {
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
    target.pg1 = Utils.findRequiredViewAsType(source, R.id.progressBar1, "field 'pg1'", ProgressBar.class);
    target.webview = Utils.findRequiredViewAsType(source, R.id.webview, "field 'webview'", WebView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ContactServiceActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.pg1 = null;
    target.webview = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
  }
}
