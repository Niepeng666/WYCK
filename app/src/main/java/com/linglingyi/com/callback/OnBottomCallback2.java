package com.linglingyi.com.callback;

import android.graphics.RectF;

import zhy.com.highlight.HighLight;
import zhy.com.highlight.position.OnBaseCallback;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/5/7
 */
public class OnBottomCallback2 extends OnBaseCallback {
    public OnBottomCallback2() {
    }

    public OnBottomCallback2(float offset) {
        super(offset);
    }

    @Override
    public void getPosition(float rightMargin, float bottomMargin, RectF rectF, HighLight.MarginInfo marginInfo) {
        marginInfo.topMargin = rectF.top + rectF.height()+offset;
    }
}
