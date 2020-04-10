// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.linglingyi.com.viewone.FontIconView;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ClientListAdapter$ViewHolder_ViewBinding implements Unbinder {
  private ClientListAdapter.ViewHolder target;

  @UiThread
  public ClientListAdapter$ViewHolder_ViewBinding(ClientListAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.ivLevel = Utils.findRequiredViewAsType(source, R.id.iv_level, "field 'ivLevel'", ImageView.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.tvCreateTime = Utils.findRequiredViewAsType(source, R.id.tv_create_time, "field 'tvCreateTime'", TextView.class);
    target.iconPhone = Utils.findRequiredViewAsType(source, R.id.icon_phone, "field 'iconPhone'", FontIconView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ClientListAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvName = null;
    target.ivLevel = null;
    target.tvPhone = null;
    target.tvCreateTime = null;
    target.iconPhone = null;
  }
}
