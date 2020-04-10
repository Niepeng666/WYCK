// Generated code from Butter Knife. Do not modify!
package com.linglingyi.com.fragment;

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
import com.youth.banner.Banner;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainFragment_ViewBinding implements Unbinder {
  private MainFragment target;

  private View view2131297368;

  private View view2131297157;

  private View view2131297401;

  private View view2131297372;

  private View view2131297235;

  private View view2131297387;

  private View view2131297376;

  private View view2131297180;

  private View view2131297275;

  private View view2131297317;

  private View view2131296792;

  private View view2131296669;

  private View view2131296689;

  private View view2131296710;

  private View view2131296648;

  private View view2131296714;

  private View view2131296640;

  private View view2131297456;

  @UiThread
  public MainFragment_ViewBinding(final MainFragment target, View source) {
    this.target = target;

    View view;
    target.ivBack = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'ivBack'", ImageView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_right, "field 'tvRight' and method 'onViewClicked'");
    target.tvRight = Utils.castView(view, R.id.tv_right, "field 'tvRight'", TextView.class);
    view2131297368 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ivRight = Utils.findRequiredViewAsType(source, R.id.iv_right, "field 'ivRight'", ImageView.class);
    target.banner = Utils.findRequiredViewAsType(source, R.id.banner, "field 'banner'", Banner.class);
    view = Utils.findRequiredView(source, R.id.tv_card, "field 'tvCard' and method 'onViewClicked'");
    target.tvCard = Utils.castView(view, R.id.tv_card, "field 'tvCard'", TextView.class);
    view2131297157 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_swipe, "field 'tvSwipe' and method 'onViewClicked'");
    target.tvSwipe = Utils.castView(view, R.id.tv_swipe, "field 'tvSwipe'", TextView.class);
    view2131297401 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_score, "field 'tvScore' and method 'onViewClicked'");
    target.tvScore = Utils.castView(view, R.id.tv_score, "field 'tvScore'", TextView.class);
    view2131297372 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_honor, "field 'tvHonor' and method 'onViewClicked'");
    target.tvHonor = Utils.castView(view, R.id.tv_honor, "field 'tvHonor'", TextView.class);
    view2131297235 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_shop, "field 'tvShop' and method 'onViewClicked'");
    target.tvShop = Utils.castView(view, R.id.tv_shop, "field 'tvShop'", TextView.class);
    view2131297387 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_security, "field 'tvSecurity' and method 'onViewClicked'");
    target.tvSecurity = Utils.castView(view, R.id.tv_security, "field 'tvSecurity'", TextView.class);
    view2131297376 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_credit, "field 'tvCredit' and method 'onViewClicked'");
    target.tvCredit = Utils.castView(view, R.id.tv_credit, "field 'tvCredit'", TextView.class);
    view2131297180 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_loan, "field 'tvLoan' and method 'onViewClicked'");
    target.tvLoan = Utils.castView(view, R.id.tv_loan, "field 'tvLoan'", TextView.class);
    view2131297275 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_online_service, "field 'tvOnlineService' and method 'onViewClicked'");
    target.tvOnlineService = Utils.castView(view, R.id.tv_online_service, "field 'tvOnlineService'", TextView.class);
    view2131297317 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvNotice = Utils.findRequiredViewAsType(source, R.id.tv_notice, "field 'tvNotice'", TextView.class);
    target.tvNoticeDate = Utils.findRequiredViewAsType(source, R.id.tv_notice_date, "field 'tvNoticeDate'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_notice, "field 'llNotice' and method 'onViewClicked'");
    target.llNotice = Utils.castView(view, R.id.ll_notice, "field 'llNotice'", LinearLayout.class);
    view2131296792 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_lingzhu, "field 'ivLingzhu' and method 'onViewClicked'");
    target.ivLingzhu = Utils.castView(view, R.id.iv_lingzhu, "field 'ivLingzhu'", ImageView.class);
    view2131296669 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_record, "field 'ivRecord' and method 'onViewClicked'");
    target.ivRecord = Utils.castView(view, R.id.iv_record, "field 'ivRecord'", ImageView.class);
    view2131296689 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_video, "field 'ivVideo' and method 'onViewClicked'");
    target.ivVideo = Utils.castView(view, R.id.iv_video, "field 'ivVideo'", ImageView.class);
    view2131296710 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_friend, "field 'ivFriend' and method 'onViewClicked'");
    target.ivFriend = Utils.castView(view, R.id.iv_friend, "field 'ivFriend'", ImageView.class);
    view2131296648 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_zhongjie, "field 'ivZhongjie' and method 'onViewClicked'");
    target.ivZhongjie = Utils.castView(view, R.id.iv_zhongjie, "field 'ivZhongjie'", ImageView.class);
    view2131296714 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_business, "field 'ivBusiness' and method 'onViewClicked'");
    target.ivBusiness = Utils.castView(view, R.id.iv_business, "field 'ivBusiness'", ImageView.class);
    view2131296640 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_zhongjie, "field 'tvZhongjie' and method 'onViewClicked'");
    target.tvZhongjie = Utils.castView(view, R.id.tv_zhongjie, "field 'tvZhongjie'", TextView.class);
    view2131297456 = view;
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
    MainFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.ivRight = null;
    target.banner = null;
    target.tvCard = null;
    target.tvSwipe = null;
    target.tvScore = null;
    target.tvHonor = null;
    target.tvShop = null;
    target.tvSecurity = null;
    target.tvCredit = null;
    target.tvLoan = null;
    target.tvOnlineService = null;
    target.tvNotice = null;
    target.tvNoticeDate = null;
    target.llNotice = null;
    target.ivLingzhu = null;
    target.ivRecord = null;
    target.ivVideo = null;
    target.ivFriend = null;
    target.ivZhongjie = null;
    target.ivBusiness = null;
    target.tvZhongjie = null;

    view2131297368.setOnClickListener(null);
    view2131297368 = null;
    view2131297157.setOnClickListener(null);
    view2131297157 = null;
    view2131297401.setOnClickListener(null);
    view2131297401 = null;
    view2131297372.setOnClickListener(null);
    view2131297372 = null;
    view2131297235.setOnClickListener(null);
    view2131297235 = null;
    view2131297387.setOnClickListener(null);
    view2131297387 = null;
    view2131297376.setOnClickListener(null);
    view2131297376 = null;
    view2131297180.setOnClickListener(null);
    view2131297180 = null;
    view2131297275.setOnClickListener(null);
    view2131297275 = null;
    view2131297317.setOnClickListener(null);
    view2131297317 = null;
    view2131296792.setOnClickListener(null);
    view2131296792 = null;
    view2131296669.setOnClickListener(null);
    view2131296669 = null;
    view2131296689.setOnClickListener(null);
    view2131296689 = null;
    view2131296710.setOnClickListener(null);
    view2131296710 = null;
    view2131296648.setOnClickListener(null);
    view2131296648 = null;
    view2131296714.setOnClickListener(null);
    view2131296714 = null;
    view2131296640.setOnClickListener(null);
    view2131296640 = null;
    view2131297456.setOnClickListener(null);
    view2131297456 = null;
  }
}
