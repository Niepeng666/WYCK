package com.linglingyi.com.callback;

import android.graphics.RectF;

import zhy.com.highlight.HighLight;
import zhy.com.highlight.position.OnBaseCallback;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/5/7
 */
public class OnTopCallback2 extends OnBaseCallback {
    public OnTopCallback2() {
    }

    public OnTopCallback2(float offset) {
        super(offset);
    }

    @Override
    public void getPosition(float rightMargin, float bottomMargin, RectF rectF, HighLight.MarginInfo marginInfo) {
        marginInfo.bottomMargin = bottomMargin+rectF.height()+offset;
    }
}
