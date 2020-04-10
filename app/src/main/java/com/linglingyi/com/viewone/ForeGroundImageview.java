package com.linglingyi.com.viewone;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;


/**
 * 添加前景色的imageview
 */
public class ForeGroundImageview extends ImageView {
    int color = Color.parseColor("#55000000");

    public ForeGroundImageview(Context context) {
        super(context);
    }

    public ForeGroundImageview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ForeGroundImageview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setForeColor(int color) {
        this.color = getResources().getColor(color);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(color);
    }
}
