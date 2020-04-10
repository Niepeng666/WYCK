package com.linglingyi.com.viewone;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.widget.PopupWindow;

/**
 * 作者：陈烂鑫 on 2017/11/16 08:28
 * 作用：
 */

public class PopWindow extends PopupWindow {

    public PopWindow(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
    }

    @Override
    public void showAsDropDown(View anchor) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            setHeight(height);
        }
        super.showAsDropDown(anchor);
    }
}
