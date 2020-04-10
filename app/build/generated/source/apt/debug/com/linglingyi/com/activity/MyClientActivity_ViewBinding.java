// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
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

public class MyClientActivity_ViewBinding implements Unbinder {
  private MyClientActivity target;

  private View view2131296633;

  private View view2131296759;

  private View view2131296764;

  private View view2131296768;

  private View view2131296769;

  private View view2131296770;

  private View view2131296771;

  private View view2131296772;

  private View view2131296773;

  @UiThread
  public MyClientActivity_ViewBinding(MyClientActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyClientActivity_ViewBinding(final MyClientActivity target, View source) {
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
    target.tvTotal = Utils.findRequiredViewAsType(source, R.id.tv_total, "field 'tvTotal'", TextView.class);
    target.tvExtendName = Utils.findRequiredViewAsType(source, R.id.tv_extend_name, "field 'tvExtendName'", TextView.class);
    target.tvExtendPhone = Utils.findRequiredViewAsType(source, R.id.tv_extend_phone, "field 'tvExtendPhone'", TextView.class);
    target.tvAgentName = Utils.findRequiredViewAsType(source, R.id.tv_agent_name, "field 'tvAgentName'", TextView.class);
    target.tvAgentPhone = Utils.findRequiredViewAsType(source, R.id.tv_agent_phone, "field 'tvAgentPhone'", TextView.class);
    target.tlTab = Utils.findRequiredViewAsType(source, R.id.tl_tab, "field 'tlTab'", TabLayout.class);
    target.tvDirectNum = Utils.findRequiredViewAsType(source, R.id.tv_direct_num, "field 'tvDirectNum'", TextView.class);
    target.tvDirectToday = Utils.findRequiredViewAsType(source, R.id.tv_direct_today, "field 'tvDirectToday'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_direct, "field 'llDirect' and method 'onViewClicked'");
    target.llDirect = Utils.castView(view, R.id.ll_direct, "field 'llDirect'", LinearLayout.class);
    view2131296759 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvIndirectNum = Utils.findRequiredViewAsType(source, R.id.tv_indirect_num, "field 'tvIndirectNum'", TextView.class);
    target.tvIndirectToday = Utils.findRequiredViewAsType(source, R.id.tv_indirect_today, "field 'tvIndirectToday'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_indirect, "field 'llIndirect' and method 'onViewClicked'");
    target.llIndirect = Utils.castView(view, R.id.ll_indirect, "field 'llIndirect'", LinearLayout.class);
    view2131296764 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.llClient = Utils.findRequiredViewAsType(source, R.id.ll_client, "field 'llClient'", LinearLayout.class);
    target.tvLevel1Num = Utils.findRequiredViewAsType(source, R.id.tv_level_1_num, "field 'tvLevel1Num'", TextView.class);
    target.tvLevel1Today = Utils.findRequiredViewAsType(source, R.id.tv_level_1_today, "field 'tvLevel1Today'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_level_1, "field 'llLevel1' and method 'onViewClicked'");
    target.llLevel1 = Utils.castView(view, R.id.ll_level_1, "field 'llLevel1'", LinearLayout.class);
    view2131296768 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvLevel2Num = Utils.findRequiredViewAsType(source, R.id.tv_level_2_num, "field 'tvLevel2Num'", TextView.class);
    target.tvLevel2Today = Utils.findRequiredViewAsType(source, R.id.tv_level_2_today, "field 'tvLevel2Today'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_level_2, "field 'llLevel2' and method 'onViewClicked'");
    target.llLevel2 = Utils.castView(view, R.id.ll_level_2, "field 'llLevel2'", LinearLayout.class);
    view2131296769 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvLevel3Num = Utils.findRequiredViewAsType(source, R.id.tv_level_3_num, "field 'tvLevel3Num'", TextView.class);
    target.tvLevel3Today = Utils.findRequiredViewAsType(source, R.id.tv_level_3_today, "field 'tvLevel3Today'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_level_3, "field 'llLevel3' and method 'onViewClicked'");
    target.llLevel3 = Utils.castView(view, R.id.ll_level_3, "field 'llLevel3'", LinearLayout.class);
    view2131296770 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvLevel4Num = Utils.findRequiredViewAsType(source, R.id.tv_level_4_num, "field 'tvLevel4Num'", TextView.class);
    target.tvLevel4Today = Utils.findRequiredViewAsType(source, R.id.tv_level_4_today, "field 'tvLevel4Today'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_level_4, "field 'llLevel4' and method 'onViewClicked'");
    target.llLevel4 = Utils.castView(view, R.id.ll_level_4, "field 'llLevel4'", LinearLayout.class);
    view2131296771 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvLevel5Num = Utils.findRequiredViewAsType(source, R.id.tv_level_5_num, "field 'tvLevel5Num'", TextView.class);
    target.tvLevel5Today = Utils.findRequiredViewAsType(source, R.id.tv_level_5_today, "field 'tvLevel5Today'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_level_5, "field 'llLevel5' and method 'onViewClicked'");
    target.llLevel5 = Utils.castView(view, R.id.ll_level_5, "field 'llLevel5'", LinearLayout.class);
    view2131296772 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.llVip = Utils.findRequiredViewAsType(source, R.id.ll_vip, "field 'llVip'", LinearLayout.class);
    target.tvLevel6Num = Utils.findRequiredViewAsType(source, R.id.tv_level_6_num, "field 'tvLevel6Num'", TextView.class);
    target.tvLevel6Today = Utils.findRequiredViewAsType(source, R.id.tv_level_6_today, "field 'tvLevel6Today'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_level_6, "field 'llLevel6' and method 'onViewClicked'");
    target.llLevel6 = Utils.castView(view, R.id.ll_level_6, "field 'llLevel6'", LinearLayout.class);
    view2131296773 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvLevel7Num = Utils.findRequiredViewAsType(source, R.id.tv_level_7_num, "field 'tvLevel7Num'", TextView.class);
    target.tvLevel7Today = Utils.findRequiredViewAsType(source, R.id.tv_level_7_today, "field 'tvLevel7Today'", TextView.class);
    target.llLevel7 = Utils.findRequiredViewAsType(source, R.id.ll_level_7, "field 'llLevel7'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyClientActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.tvTotal = null;
    target.tvExtendName = null;
    target.tvExtendPhone = null;
    target.tvAgentName = null;
    target.tvAgentPhone = null;
    target.tlTab = null;
    target.tvDirectNum = null;
    target.tvDirectToday = null;
    target.llDirect = null;
    target.tvIndirectNum = null;
    target.tvIndirectToday = null;
    target.llIndirect = null;
    target.llClient = null;
    target.tvLevel1Num = null;
    target.tvLevel1Today = null;
    target.llLevel1 = null;
    target.tvLevel2Num = null;
    target.tvLevel2Today = null;
    target.llLevel2 = null;
    target.tvLevel3Num = null;
    target.tvLevel3Today = null;
    target.llLevel3 = null;
    target.tvLevel4Num = null;
    target.tvLevel4Today = null;
    target.llLevel4 = null;
    target.tvLevel5Num = null;
    target.tvLevel5Today = null;
    target.llLevel5 = null;
    target.llVip = null;
    target.tvLevel6Num = null;
    target.tvLevel6Today = null;
    target.llLevel6 = null;
    target.tvLevel7Num = null;
    target.tvLevel7Today = null;
    target.llLevel7 = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296759.setOnClickListener(null);
    view2131296759 = null;
    view2131296764.setOnClickListener(null);
    view2131296764 = null;
    view2131296768.setOnClickListener(null);
    view2131296768 = null;
    view2131296769.setOnClickListener(null);
    view2131296769 = null;
    view2131296770.setOnClickListener(null);
    view2131296770 = null;
    view2131296771.setOnClickListener(null);
    view2131296771 = null;
    view2131296772.setOnClickListener(null);
    view2131296772 = null;
    view2131296773.setOnClickListener(null);
    view2131296773 = null;
  }
}
