// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SchoolBusinessAdapter$ViewHolder_ViewBinding implements Unbinder {
  private SchoolBusinessAdapter.ViewHolder target;

  @UiThread
  public SchoolBusinessAdapter$ViewHolder_ViewBinding(SchoolBusinessAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.ivImg = Utils.findRequiredViewAsType(source, R.id.iv_img, "field 'ivImg'", ImageView.class);
    target.lyItem = Utils.findRequiredViewAsType(source, R.id.ly_item, "field 'lyItem'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SchoolBusinessAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitle = null;
    target.ivImg = null;
    target.lyItem = null;
  }
}
