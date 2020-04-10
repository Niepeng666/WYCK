// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

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

public class QrCodePayActivity_ViewBinding implements Unbinder {
  private QrCodePayActivity target;

  private View view2131296633;

  private View view2131296697;

  private View view2131296699;

  @UiThread
  public QrCodePayActivity_ViewBinding(QrCodePayActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public QrCodePayActivity_ViewBinding(final QrCodePayActivity target, View source) {
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
    target.ivPayType = Utils.findRequiredViewAsType(source, R.id.iv_pay_type, "field 'ivPayType'", ImageView.class);
    target.linear1 = Utils.findRequiredViewAsType(source, R.id.linear1, "field 'linear1'", LinearLayout.class);
    target.tvToast = Utils.findRequiredViewAsType(source, R.id.tv_toast, "field 'tvToast'", TextView.class);
    target.ivCode = Utils.findRequiredViewAsType(source, R.id.iv_code, "field 'ivCode'", ImageView.class);
    target.linear2 = Utils.findRequiredViewAsType(source, R.id.linear2, "field 'linear2'", LinearLayout.class);
    target.linear3 = Utils.findRequiredViewAsType(source, R.id.linear3, "field 'linear3'", LinearLayout.class);
    target.text1 = Utils.findRequiredViewAsType(source, R.id.text1, "field 'text1'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_save, "field 'ivSave' and method 'onViewClicked'");
    target.ivSave = Utils.castView(view, R.id.iv_save, "field 'ivSave'", LinearLayout.class);
    view2131296697 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_share, "field 'ivShare' and method 'onViewClicked'");
    target.ivShare = Utils.castView(view, R.id.iv_share, "field 'ivShare'", LinearLayout.class);
    view2131296699 = view;
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
    QrCodePayActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.ivPayType = null;
    target.linear1 = null;
    target.tvToast = null;
    target.ivCode = null;
    target.linear2 = null;
    target.linear3 = null;
    target.text1 = null;
    target.ivSave = null;
    target.ivShare = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296697.setOnClickListener(null);
    view2131296697 = null;
    view2131296699.setOnClickListener(null);
    view2131296699 = null;
  }
}
