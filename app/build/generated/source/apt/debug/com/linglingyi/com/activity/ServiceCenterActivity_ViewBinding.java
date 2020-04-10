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

public class ServiceCenterActivity_ViewBinding implements Unbinder {
  private ServiceCenterActivity target;

  private View view2131296633;

  private View view2131296376;

  private View view2131296800;

  private View view2131296390;

  @UiThread
  public ServiceCenterActivity_ViewBinding(ServiceCenterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ServiceCenterActivity_ViewBinding(final ServiceCenterActivity target, View source) {
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
    view = Utils.findRequiredView(source, R.id.btn_online_service, "field 'btnOnlineService' and method 'onViewClicked'");
    target.btnOnlineService = Utils.castView(view, R.id.btn_online_service, "field 'btnOnlineService'", Button.class);
    view2131296376 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvServicePhone = Utils.findRequiredViewAsType(source, R.id.tv_service_phone, "field 'tvServicePhone'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_phone, "field 'llPhone' and method 'onViewClicked'");
    target.llPhone = Utils.castView(view, R.id.ll_phone, "field 'llPhone'", LinearLayout.class);
    view2131296800 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvWechat = Utils.findRequiredViewAsType(source, R.id.tv_wechat, "field 'tvWechat'", TextView.class);
    target.llServiceWechat = Utils.findRequiredViewAsType(source, R.id.ll_service_wechat, "field 'llServiceWechat'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.btn_wechat_copy, "method 'onViewClicked'");
    view2131296390 = view;
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
    ServiceCenterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.btnOnlineService = null;
    target.tvServicePhone = null;
    target.llPhone = null;
    target.tvWechat = null;
    target.llServiceWechat = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296376.setOnClickListener(null);
    view2131296376 = null;
    view2131296800.setOnClickListener(null);
    view2131296800 = null;
    view2131296390.setOnClickListener(null);
    view2131296390 = null;
  }
}
