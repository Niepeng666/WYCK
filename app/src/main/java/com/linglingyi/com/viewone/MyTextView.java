package com.linglingyi.com.viewone;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * @作者 chenlanxin
 * @创建日期 2019/2/10 11:27
 * @功能
 **/
public class MyTextView extends android.support.v7.widget.AppCompatTextView {
    public MyTextView(Context context) {
        super(context);
        setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/cervo-medium.otf"));
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/cervo-medium.otf"));
    }
}
