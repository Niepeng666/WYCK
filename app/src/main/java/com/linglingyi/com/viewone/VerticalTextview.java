package com.linglingyi.com.viewone;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

/**
 * @作者 chenlanxin
 * @创建日期 2019/2/14 19:10
 * @功能
 **/
public class VerticalTextview extends TextSwitcher implements ViewSwitcher.ViewFactory {
    private static final int FLAG_START_AUTO_SCROLL = 0;
    private static final int FLAG_STOP_AUTO_SCROLL = 1;
    private int mTextSize;
    private int mPadding;
    private int textColor;
    private OnItemClickListener itemClickListener;
    private Context mContext;
    private int currentId;
    private ArrayList<String> textList;
    private Handler handler;

    public void setText(int textSize, int padding, int textColor) {
        this.mTextSize = textSize;
        this.mPadding = padding;
        this.textColor = textColor;
    }

    public VerticalTextview(Context context) {
        this(context, null);
        this.mContext = context;
    }

    public VerticalTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mTextSize = 14;
        this.mPadding = 5;
        this.textColor = -16777216;
        this.currentId = -1;
        this.mContext = context;
        this.textList = new ArrayList();
    }

    public void setAnimTime(long animDuration) {
        this.setFactory(this);
        Animation in = new TranslateAnimation(0.0F, 0.0F, (float) animDuration, 0.0F);
        in.setDuration(animDuration);
        in.setInterpolator(new AccelerateInterpolator());
        Animation out = new TranslateAnimation(0.0F, 0.0F, 0.0F, (float) (-animDuration));
        out.setDuration(animDuration);
        out.setInterpolator(new AccelerateInterpolator());
        this.setInAnimation(in);
        this.setOutAnimation(out);
    }

    public void setTextStillTime(final long time) {
        this.handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        if (textList.size() > 0) {
                            currentId++;
                            setText((CharSequence) textList.get(currentId % textList.size()));
                        }

                        handler.sendEmptyMessageDelayed(0, time);
                        break;
                    case 1:
                        handler.removeMessages(0);
                        if (textList != null && textList.size() > 0)
                            setText((CharSequence) textList.get(0));
                        break;
                }

            }
        };
    }

    public void setTextList(ArrayList<String> titles) {
        this.textList.clear();
        this.textList.addAll(titles);
        this.currentId = -1;
    }

    public void startAutoScroll() {
        this.handler.sendEmptyMessage(0);
    }

    public void stopAutoScroll() {
        this.handler.sendEmptyMessage(1);
    }

    public View makeView() {
        TextView t = new TextView(this.mContext);
        t.setGravity(Gravity.CENTER_VERTICAL);
        t.setMaxLines(1);
        t.setPadding(this.mPadding, this.mPadding, this.mPadding, this.mPadding);
        t.setTextColor(this.textColor);
        t.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        t.setClickable(true);
        t.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (itemClickListener != null && textList.size() > 0 && currentId != -1) {
                    itemClickListener.onItemClick(currentId % textList.size());
                }

            }
        });
        return t;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int var1);
    }
}