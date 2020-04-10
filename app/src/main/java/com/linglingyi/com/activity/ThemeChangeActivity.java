package com.linglingyi.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.utils.StorageAppInfoUtil;
import com.linglingyi.com.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/10/17
 */
public class ThemeChangeActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.ll_theme_blue)
    LinearLayout llThemeBlue;
    @BindView(R.id.ll_theme_black)
    LinearLayout llThemeBlack;
    @BindView(R.id.ll_theme_orange)
    LinearLayout llThemeOrange;
    @BindView(R.id.ll_theme_yellow)
    LinearLayout llThemeYellow;
    @BindView(R.id.ll_theme_green)
    LinearLayout llThemeGreen;
    @BindView(R.id.btn_next)
    Button btnNext;
    private String theme;

    @Override
    public int initLayout() {
        return R.layout.act_theme_change;
    }

    @Override
    public void initData() {
        tvTitle.setText("更换主题");
        String theme = StorageAppInfoUtil.getInfo("theme", context);
        switch (theme) {
            case "08bdcd":
                llThemeGreen.setBackgroundResource(R.drawable.shape_strike_blue_solid_white);
                break;
            case "38a3f7":
                llThemeBlue.setBackgroundResource(R.drawable.shape_strike_blue_solid_white);
                break;
            case "221814":
                llThemeBlack.setBackgroundResource(R.drawable.shape_strike_blue_solid_white);
                break;
            case "f08519":
                llThemeOrange.setBackgroundResource(R.drawable.shape_strike_blue_solid_white);
                break;
            case "f1b900":
                llThemeYellow.setBackgroundResource(R.drawable.shape_strike_blue_solid_white);
                break;
            default:
                llThemeGreen.setBackgroundResource(R.drawable.shape_strike_blue_solid_white);
                break;
        }
    }


    @OnClick({R.id.iv_back, R.id.ll_theme_blue, R.id.ll_theme_black, R.id.ll_theme_orange, R.id.ll_theme_yellow, R.id.ll_theme_green, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.ll_theme_blue:
                theme = "38a3f7";
                llThemeBlue.setBackgroundResource(R.drawable.shape_strike_blue_solid_white);
                llThemeBlack.setBackgroundResource(R.drawable.shape_solid_white);
                llThemeOrange.setBackgroundResource(R.drawable.shape_solid_white);
                llThemeYellow.setBackgroundResource(R.drawable.shape_solid_white);
                llThemeGreen.setBackgroundResource(R.drawable.shape_solid_white);
                break;
            case R.id.ll_theme_black:
                theme = "221814";
                llThemeBlack.setBackgroundResource(R.drawable.shape_strike_blue_solid_white);
                llThemeBlue.setBackgroundResource(R.drawable.shape_solid_white);
                llThemeOrange.setBackgroundResource(R.drawable.shape_solid_white);
                llThemeYellow.setBackgroundResource(R.drawable.shape_solid_white);
                llThemeGreen.setBackgroundResource(R.drawable.shape_solid_white);
                break;
            case R.id.ll_theme_orange:
                theme = "f08519";
                llThemeOrange.setBackgroundResource(R.drawable.shape_strike_blue_solid_white);
                llThemeBlack.setBackgroundResource(R.drawable.shape_solid_white);
                llThemeBlue.setBackgroundResource(R.drawable.shape_solid_white);
                llThemeYellow.setBackgroundResource(R.drawable.shape_solid_white);
                llThemeGreen.setBackgroundResource(R.drawable.shape_solid_white);
                break;
            case R.id.ll_theme_yellow:
                theme = "f1b900";
                llThemeYellow.setBackgroundResource(R.drawable.shape_strike_blue_solid_white);
                llThemeBlack.setBackgroundResource(R.drawable.shape_solid_white);
                llThemeOrange.setBackgroundResource(R.drawable.shape_solid_white);
                llThemeBlue.setBackgroundResource(R.drawable.shape_solid_white);
                llThemeGreen.setBackgroundResource(R.drawable.shape_solid_white);
                break;
            case R.id.ll_theme_green:
                theme = "08bdcd";
                llThemeGreen.setBackgroundResource(R.drawable.shape_strike_blue_solid_white);
                llThemeBlack.setBackgroundResource(R.drawable.shape_solid_white);
                llThemeOrange.setBackgroundResource(R.drawable.shape_solid_white);
                llThemeYellow.setBackgroundResource(R.drawable.shape_solid_white);
                llThemeBlue.setBackgroundResource(R.drawable.shape_solid_white);
                break;
            case R.id.btn_next:
                StorageAppInfoUtil.putInfo(context, "theme", theme);
                recreate();
                goLogin();
                break;
        }
    }

    private void goLogin() {
        Intent intent = new Intent(context, LoginNewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
