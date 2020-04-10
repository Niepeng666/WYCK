package com.linglingyi.com.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.fragment.HomeLingzhuFragment;
import com.linglingyi.com.fragment.LingzhuFragment;
import com.linglingyi.com.fragment.MainFragment;
import com.linglingyi.com.fragment.MineNewFragment;
import com.linglingyi.com.fragment.OperateFragment;
import com.linglingyi.com.fragment.ShareFragment;
import com.linglingyi.com.fragment.VIPFragment;
import com.linglingyi.com.viewone.FontIconView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @作者 chenlanxin
 * @创建日期 2019/2/22 9:53
 * @功能
 **/
public class HomeNewActivity extends BaseActivity {


    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.tv_main_icon)
    FontIconView tvMainIcon;
    @BindView(R.id.tv_main)
    TextView tvMain;
    @BindView(R.id.ll_main)
    LinearLayout llMain;
    @BindView(R.id.tv_vip_icon)
    FontIconView tvVipIcon;
    @BindView(R.id.tv_vip)
    TextView tvVip;
    @BindView(R.id.ll_vip)
    LinearLayout llVip;
    @BindView(R.id.tv_share_icon)
    FontIconView tvShareIcon;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.ll_share)
    LinearLayout llShare;
    @BindView(R.id.tv_guide_icon)
    FontIconView tvGuideIcon;
    @BindView(R.id.tv_guide)
    TextView tvGuide;
    @BindView(R.id.ll_guide)
    LinearLayout llGuide;
    @BindView(R.id.tv_mine_icon)
    FontIconView tvMineIcon;
    @BindView(R.id.tv_mine)
    TextView tvMine;
    @BindView(R.id.ll_mine)
    LinearLayout llMine;
    @BindView(R.id.rg_tab)
    LinearLayout rgTab;
    @BindView(R.id.cl_container)
    ConstraintLayout clContainer;

    private FragmentManager fm;
    private MainFragment mMainFragment = null;//首页
    private VIPFragment mVIPFragment = null;//vip升级页面
    private ShareFragment mShareFragment = null;//分享页面
    private MineNewFragment mineNewFragment = null;//我的页面
    private HomeLingzhuFragment mLingzhuFragment = null;//领主页面

    private long firstime;
    private TypedArray mTypedArray;
    private int defaultColor = 0xFF000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void initData() {
        fm = getSupportFragmentManager();
        int[] attrArray = {R.attr.theme_bg_color};
        mTypedArray = context.obtainStyledAttributes(attrArray);
        showFrag(1);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            /** 设置双击退出 */
            long secondtime = System.currentTimeMillis();
            if (secondtime - firstime > 3000) {
                Toast.makeText(this, "再按一次返回键退出", Toast.LENGTH_SHORT).show();
                firstime = System.currentTimeMillis();
                return true;
            } else {
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showFrag(int i) {
        FragmentTransaction transaction = fm.beginTransaction();
        switch (i) {
            case 1:
                if (mVIPFragment != null)
                    transaction.hide(mVIPFragment);
                if (mShareFragment != null)
                    transaction.hide(mShareFragment);
                if (mLingzhuFragment != null)
                    transaction.hide(mLingzhuFragment);
                if (mineNewFragment != null)
                    transaction.hide(mineNewFragment);
                if (mMainFragment == null) {
                    mMainFragment = MainFragment.newInstance();
                    transaction.add(R.id.fl_content, mMainFragment);
                } else {
                    transaction.show(mMainFragment);
                }
                tvMainIcon.setTextColor(mTypedArray.getColor(0, defaultColor));
                tvMain.setTextColor(mTypedArray.getColor(0, defaultColor));
                tvVip.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvVipIcon.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvShareIcon.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvShare.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvGuide.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvGuideIcon.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvMine.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvMineIcon.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                break;
            case 2:
                if (mMainFragment != null)
                    transaction.hide(mMainFragment);
                if (mShareFragment != null)
                    transaction.hide(mShareFragment);
                if (mLingzhuFragment != null)
                    transaction.hide(mLingzhuFragment);
                if (mineNewFragment != null)
                    transaction.hide(mineNewFragment);
                if (mVIPFragment == null) {
                    mVIPFragment = VIPFragment.newInstance();
                    transaction.add(R.id.fl_content, mVIPFragment);
                } else {
                    transaction.show(mVIPFragment);
                }
                tvVip.setTextColor(mTypedArray.getColor(0, defaultColor));
                tvVipIcon.setTextColor(mTypedArray.getColor(0, defaultColor));
                tvMain.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvMainIcon.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvShareIcon.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvShare.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvGuide.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvGuideIcon.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvMine.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvMineIcon.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                break;

            case 3:
                if (mVIPFragment != null)
                    transaction.hide(mVIPFragment);
                if (mMainFragment != null)
                    transaction.hide(mMainFragment);
                if (mLingzhuFragment != null)
                    transaction.hide(mLingzhuFragment);
                if (mineNewFragment != null)
                    transaction.hide(mineNewFragment);
                if (mShareFragment == null) {
                    mShareFragment = ShareFragment.newInstance();
                    transaction.add(R.id.fl_content, mShareFragment);
                } else {
                    transaction.show(mShareFragment);
                }
                tvShare.setTextColor(mTypedArray.getColor(0, defaultColor));
                tvShareIcon.setTextColor(mTypedArray.getColor(0, defaultColor));
                tvVip.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvVipIcon.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvMainIcon.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvMain.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvGuide.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvGuideIcon.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvMine.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvMineIcon.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                break;
            case 4:
                if (mVIPFragment != null)
                    transaction.hide(mVIPFragment);
                if (mShareFragment != null)
                    transaction.hide(mShareFragment);
                if (mineNewFragment != null)
                    transaction.hide(mineNewFragment);
                if (mMainFragment != null)
                    transaction.hide(mMainFragment);
                if (mLingzhuFragment == null) {
                    mLingzhuFragment = HomeLingzhuFragment.newInstance();
                    transaction.add(R.id.fl_content, mLingzhuFragment);
                } else {
                    transaction.show(mLingzhuFragment);
                }
                tvGuideIcon.setTextColor(mTypedArray.getColor(0, defaultColor));
                tvGuide.setTextColor(mTypedArray.getColor(0, defaultColor));
                tvVip.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvVipIcon.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvShareIcon.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvShare.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvMain.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvMainIcon.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvMine.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvMineIcon.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                break;
            case 5:
                if (mVIPFragment != null)
                    transaction.hide(mVIPFragment);
                if (mShareFragment != null)
                    transaction.hide(mShareFragment);
                if (mLingzhuFragment != null)
                    transaction.hide(mLingzhuFragment);
                if (mMainFragment != null)
                    transaction.hide(mMainFragment);
                if (mineNewFragment == null) {
                    mineNewFragment = new MineNewFragment();
                    transaction.add(R.id.fl_content, mineNewFragment);
                } else {
                    transaction.show(mineNewFragment);
                }
                tvMineIcon.setTextColor(mTypedArray.getColor(0, defaultColor));
                tvMine.setTextColor(mTypedArray.getColor(0, defaultColor));
                tvVip.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvVipIcon.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvShareIcon.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvShare.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvGuide.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvGuideIcon.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvMainIcon.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                tvMain.setTextColor(ContextCompat.getColor(context, R.color.text_color));
                break;
            default:
                break;
        }
        transaction.commit();
    }

    @OnClick({R.id.ll_main, R.id.ll_vip, R.id.ll_share, R.id.ll_guide, R.id.ll_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.ll_main:
                showFrag(1);

                break;
            case R.id.ll_vip:
                showFrag(2);

                break;
            case R.id.ll_share:
                showFrag(3);

                break;
            case R.id.ll_guide:
                showFrag(4);

                break;
            case R.id.ll_mine:
                showFrag(5);

                break;
        }
    }

    /**
     * 去领主模块
     */
    public void goLingzhu() {
        showFrag(4);
    }
}
