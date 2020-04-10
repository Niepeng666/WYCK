package com.linglingyi.com.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.adapter.GuidePageAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.utils.StorageAppInfoUtil;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * @作者 chenlanxin
 * @创建日期 2019/2/11 19:11
 * @功能 引导页
 **/
public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.guide_vp)
    ViewPager guideVp;
    @BindView(R.id.bt_go)
    Button btGo;

    private int[] imageIdArray;//图片资源的数组
    private List<View> viewList;//图片资源的集合

    @Override
    public int initLayout() {
        return R.layout.activity_guide;
    }

    @Override
    public void initData() {
        btGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StorageAppInfoUtil.putInfo(context, "isFirst", false);
                Intent intent = new Intent(context, LoginNewActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //加载ViewPager
        initViewPager();
    }

    private void initViewPager() {
        //实例化图片资源
        imageIdArray = new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
        viewList = new ArrayList<>();
        //获取一个Layout参数，设置为全屏
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        //循环创建View并加入到集合中
        int len = imageIdArray.length;
        for (int i = 0; i < len; i++) {
            //new ImageView并设置全屏和图片资源
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setBackgroundResource(imageIdArray[i]);

            //将ImageView加入到集合中
            viewList.add(imageView);
        }

        //View集合初始化好后，设置Adapter
        guideVp.setAdapter(new GuidePageAdapter(viewList));
        //设置滑动监听
        guideVp.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //循环设置当前页的标记图
        if (position == imageIdArray.length - 1) {
            btGo.setVisibility(View.VISIBLE);
        } else {
            btGo.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
