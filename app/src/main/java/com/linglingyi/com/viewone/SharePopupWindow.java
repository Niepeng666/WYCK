package com.linglingyi.com.viewone;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wuyouchuangke.com.R;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/3
 */
public class SharePopupWindow extends PopupWindow {
    TextView wexin_tv, pengyouquan_tv, qq_tv, kongjian_tv, tv_dialog_quxiao;
    private View mMenuView;

    public SharePopupWindow(Activity context, View.OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.dialog_share, null);
        wexin_tv = (TextView) mMenuView.findViewById(R.id.tv_wechat);
        pengyouquan_tv = (TextView) mMenuView.findViewById(R.id.tv_wechat_friend);
        qq_tv = (TextView) mMenuView.findViewById(R.id.tv_qq);
        kongjian_tv = (TextView) mMenuView.findViewById(R.id.tv_qq_space);
        tv_dialog_quxiao = (TextView) mMenuView.findViewById(R.id.tv_cancel);
        //取消按钮
        tv_dialog_quxiao.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //销毁弹出框
                dismiss();
            }
        });
        //设置按钮监听
        wexin_tv.setOnClickListener(itemsOnClick);
        pengyouquan_tv.setOnClickListener(itemsOnClick);
        qq_tv.setOnClickListener(itemsOnClick);
        kongjian_tv.setOnClickListener(itemsOnClick);
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.DropDownUp);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(-00000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });

    }
}
