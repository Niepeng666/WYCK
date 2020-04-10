package com.linglingyi.com.viewone;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

import com.wuyouchuangke.com.R;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/2
 */
public class MyListView extends ListView {
    public MyListView(Context context) {
        super(context);
        init(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        if (!"noLoadMore".equals(getTag())){
            View view = View.inflate(context, R.layout.item_look_more, null);
            addFooterView(view);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
