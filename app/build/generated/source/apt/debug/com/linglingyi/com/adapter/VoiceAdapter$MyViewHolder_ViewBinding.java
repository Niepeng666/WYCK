// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VoiceAdapter$MyViewHolder_ViewBinding implements Unbinder {
  private VoiceAdapter.MyViewHolder target;

  @UiThread
  public VoiceAdapter$MyViewHolder_ViewBinding(VoiceAdapter.MyViewHolder target, View source) {
    this.target = target;

    target.tvContent = Utils.findOptionalViewAsType(source, R.id.tv_content, "field 'tvContent'", TextView.class);
    target.tvMoney = Utils.findOptionalViewAsType(source, R.id.tv_money, "field 'tvMoney'", TextView.class);
    target.llAmount = Utils.findOptionalViewAsType(source, R.id.ll_amount, "field 'llAmount'", LinearLayout.class);
    target.llTime = Utils.findOptionalViewAsType(source, R.id.ll_time, "field 'llTime'", LinearLayout.class);
    target.tvStart = Utils.findOptionalViewAsType(source, R.id.tv_start, "field 'tvStart'", TextView.class);
    target.tvEnd = Utils.findOptionalViewAsType(source, R.id.tv_end, "field 'tvEnd'", TextView.class);
    target.llFare = Utils.findOptionalViewAsType(source, R.id.ll_fare, "field 'llFare'", LinearLayout.class);
    target.tvAddress = Utils.findOptionalViewAsType(source, R.id.tv_address, "field 'tvAddress'", TextView.class);
    target.llArea = Utils.findOptionalViewAsType(source, R.id.ll_area, "field 'llArea'", LinearLayout.class);
    target.tvSave = Utils.findOptionalViewAsType(source, R.id.tv_save, "field 'tvSave'", TextView.class);
    target.llContent = Utils.findOptionalViewAsType(source, R.id.ll_content, "field 'llContent'", LinearLayout.class);
    target.clParent = source.findViewById(R.id.cl_parent);
  }

  @Override
  @CallSuper
  public void unbind() {
    VoiceAdapter.MyViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvContent = null;
    target.tvMoney = null;
    target.llAmount = null;
    target.llTime = null;
    target.tvStart = null;
    target.tvEnd = null;
    target.llFare = null;
    target.tvAddress = null;
    target.llArea = null;
    target.tvSave = null;
    target.llContent = null;
    target.clParent = null;
  }
}
