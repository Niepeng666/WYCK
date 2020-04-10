// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.annotation.SuppressLint;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.linglingyi.com.viewone.UMExpandLayout;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VoiceActivity_ViewBinding implements Unbinder {
  private VoiceActivity target;

  private View view2131296633;

  private View view2131296603;

  private View view2131296604;

  private View view2131296602;

  private View view2131296615;

  @UiThread
  public VoiceActivity_ViewBinding(VoiceActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  @SuppressLint("ClickableViewAccessibility")
  public VoiceActivity_ViewBinding(final VoiceActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'ivBack' and method 'onClick'");
    target.ivBack = Utils.castView(view, R.id.iv_back, "field 'ivBack'", ImageView.class);
    view2131296633 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'tvRight'", TextView.class);
    target.ivRight = Utils.findRequiredViewAsType(source, R.id.iv_right, "field 'ivRight'", ImageView.class);
    target.imageView1 = Utils.findRequiredViewAsType(source, R.id.imageView1, "field 'imageView1'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.iat_recognize, "field 'iatRecognize' and method 'onClick'");
    target.iatRecognize = Utils.castView(view, R.id.iat_recognize, "field 'iatRecognize'", Button.class);
    view2131296603 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iat_stop, "field 'iatStop' and method 'onClick'");
    target.iatStop = Utils.castView(view, R.id.iat_stop, "field 'iatStop'", Button.class);
    view2131296604 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iat_cancel, "field 'iatCancel' and method 'onClick'");
    target.iatCancel = Utils.castView(view, R.id.iat_cancel, "field 'iatCancel'", Button.class);
    view2131296602 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.rvList = Utils.findRequiredViewAsType(source, R.id.rv_list, "field 'rvList'", RecyclerView.class);
    target.topBar = Utils.findRequiredViewAsType(source, R.id.ll_top, "field 'topBar'", UMExpandLayout.class);
    target.tvNotice = Utils.findRequiredViewAsType(source, R.id.tv_notice, "field 'tvNotice'", TextView.class);
    target.llRecord = Utils.findRequiredView(source, R.id.ll_record, "field 'llRecord'");
    target.mVolumeImg = Utils.findRequiredViewAsType(source, R.id.iv_volume, "field 'mVolumeImg'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.imgView, "field 'imgView', method 'longClick', and method 'onTouch'");
    target.imgView = Utils.castView(view, R.id.imgView, "field 'imgView'", ImageView.class);
    view2131296615 = view;
    view.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View p0) {
        return target.longClick();
      }
    });
    view.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View p0, MotionEvent p1) {
        return target.onTouch(p0, p1);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    VoiceActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.imageView1 = null;
    target.iatRecognize = null;
    target.iatStop = null;
    target.iatCancel = null;
    target.rvList = null;
    target.topBar = null;
    target.tvNotice = null;
    target.llRecord = null;
    target.mVolumeImg = null;
    target.imgView = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296603.setOnClickListener(null);
    view2131296603 = null;
    view2131296604.setOnClickListener(null);
    view2131296604 = null;
    view2131296602.setOnClickListener(null);
    view2131296602 = null;
    view2131296615.setOnLongClickListener(null);
    view2131296615.setOnTouchListener(null);
    view2131296615 = null;
  }
}
