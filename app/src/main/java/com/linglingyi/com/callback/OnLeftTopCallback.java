package com.linglingyi.com.callback;

import android.graphics.RectF;

import zhy.com.highlight.HighLight;
import zhy.com.highlight.position.OnBaseCallback;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/5/7
 */
public class OnLeftTopCallback extends OnBaseCallback {
    public OnLeftTopCallback() {
    }

    public OnLeftTopCallback(float offset) {
        super(offset);
    }

    @Override
    public void getPosition(float rightMargin, float bottomMargin, RectF rectF, HighLight.MarginInfo marginInfo) {
        marginInfo.rightMargin = rightMargin+rectF.width()+offset;
        marginInfo.topMargin = rectF.top;
        marginInfo.leftMargin = rectF.right - rectF.width() / 2;
        marginInfo.bottomMargin = bottomMargin+rectF.height()+offset;
    }
}
