// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wuyouchuangke.com.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RecordListActivity_ViewBinding implements Unbinder {
  private RecordListActivity target;

  private View view2131296633;

  @UiThread
  public RecordListActivity_ViewBinding(RecordListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RecordListActivity_ViewBinding(final RecordListActivity target, View source) {
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
    target.tlTab = Utils.findRequiredViewAsType(source, R.id.tl_tab, "field 'tlTab'", TabLayout.class);
    target.rvList = Utils.findRequiredViewAsType(source, R.id.rv_list, "field 'rvList'", RecyclerView.class);
    target.tvNo = Utils.findRequiredViewAsType(source, R.id.tv_no, "field 'tvNo'", TextView.class);
    target.llNo = Utils.findRequiredViewAsType(source, R.id.ll_no, "field 'llNo'", RelativeLayout.class);
    target.ivNo4 = Utils.findRequiredViewAsType(source, R.id.iv_no_4, "field 'ivNo4'", ImageView.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.tvNumber = Utils.findRequiredViewAsType(source, R.id.tv_number, "field 'tvNumber'", TextView.class);
    target.llInviteNum = Utils.findRequiredViewAsType(source, R.id.ll_invite_num, "field 'llInviteNum'", LinearLayout.class);
    target.llPhone = Utils.findRequiredViewAsType(source, R.id.ll_phone, "field 'llPhone'", LinearLayout.class);
    target.ivLevelIcon = Utils.findRequiredViewAsType(source, R.id.iv_level_icon, "field 'ivLevelIcon'", ImageView.class);
    target.tvInviteTitle1 = Utils.findRequiredViewAsType(source, R.id.tv_invite_title_1, "field 'tvInviteTitle1'", TextView.class);
    target.tvInviteTitle2 = Utils.findRequiredViewAsType(source, R.id.tv_invite_title_2, "field 'tvInviteTitle2'", TextView.class);
    target.clNo = Utils.findRequiredViewAsType(source, R.id.cl_no, "field 'clNo'", CardView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RecordListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.tlTab = null;
    target.rvList = null;
    target.tvNo = null;
    target.llNo = null;
    target.ivNo4 = null;
    target.tvPhone = null;
    target.tvNumber = null;
    target.llInviteNum = null;
    target.llPhone = null;
    target.ivLevelIcon = null;
    target.tvInviteTitle1 = null;
    target.tvInviteTitle2 = null;
    target.clNo = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
  }
}
