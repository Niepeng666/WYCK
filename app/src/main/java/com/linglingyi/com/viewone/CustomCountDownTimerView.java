package com.linglingyi.com.viewone;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuyouchuangke.com.R;

import java.util.Timer;
import java.util.TimerTask;



@SuppressLint("HandlerLeak")
public class CustomCountDownTimerView extends LinearLayout {

    private final View mRootView;
    // 天，十位
//    private TextView tv_day_decade;
//    // 天，个位
//    private TextView tv_day_unit;
//    // 小时，十位
//    private TextView tv_hour_decade;
//    // 小时，个位
//    private TextView tv_hour_unit;
    // 分钟，十位
    private TextView tv_min_decade;
    // 分钟，个位
    private TextView tv_min_unit;
    // 秒，十位
    private TextView tv_sec_decade;
    // 秒，个位
    private TextView tv_sec_unit;

    private long day_decade;
    private long day_unit;
    private long hour_decade;
    private long hour_unit;
    private long min_decade;
    private long min_unit;
    private long sec_decade;
    private long sec_unit;

    // 计时器
    private Timer timer;

    private TimerFinishListener listener;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            countDown();
            return false;
        }
    });


    public CustomCountDownTimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView = inflater.inflate(R.layout.view_custom_count_down_timer, this);
//        tv_day_decade = mRootView.findViewById(R.id.tv_day_decade);
//        tv_day_unit = mRootView.findViewById(R.id.tv_day_unit);
//        tv_hour_decade = mRootView.findViewById(R.id.tv_hour_decade);
//        tv_hour_unit = mRootView.findViewById(R.id.tv_hour_unit);
        tv_min_decade = mRootView.findViewById(R.id.tv_min_decade);
        tv_min_unit = mRootView.findViewById(R.id.tv_min_unit);
        tv_sec_decade = mRootView.findViewById(R.id.tv_sec_decade);
        tv_sec_unit = mRootView.findViewById(R.id.tv_sec_unit);
    }

    public void setTimerFinishListener(TimerFinishListener listener) {
        this.listener = listener;
    }

    /**
     * 计时开始
     */
    private void start() {
        if (timer == null) {
            timer = new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    handler.sendEmptyMessage(0);
                }
            }, 0, 1000);
        }
    }

    //毁灭计时器
    public void destroy() {
        handler.removeMessages(0);
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    // 如果:sum = 12345678
    public void start(long sum) {
        //这里时毫秒数 先除以1000
        sum /= 1000;
        // 求出天数
        long day = sum / 60 / 60 / 24;
        // int day_time = sum % 24;
        // Log.e("小时", day + "");
        // Log.e("小时", sum % 24 + "");

        // 求出小时
        // int hour = day_time / 60;
        // int hour_time = day_time % 60;
        //
        // Log.e("小时", hour + "");
        //
        // 先获取个秒数值
        long sec = sum % 60;
        // 如果大于60秒，获取分钟。（秒数）
        long sec_time = sum / 60;
        // 再获取分钟
        long min = sec_time % 60;
        // 如果大于60分钟，获取小时（分钟数）。
        long min_time = sec_time / 60;
        // 获取小时
        long hour = min_time % 24;
        // 剩下的自然是天数
        day = min_time / 24;


        // Log.e("分钟", min + "");
        // // 求出秒数
        // Log.e("秒数", sec + "");
//        Logger.i("time", day, hour, min, sec);
        start(day, hour, min, sec);
    }

    /**
     * 设置倒计时的时长
     */
    public void start(long day, long hour, long min, long sec) {
        if (hour >= 24 || min >= 60 || sec >= 60 || day < 0
                || hour < 0 || min < 0 || sec < 0) {
            throw new RuntimeException(
                    "Time format is error,please check out your code");
        }
        // day 的十位数
        day_decade = day / 10;
        // day的个位数,这里求余就行
        day_unit = day - day_decade * 10;

        hour_decade = hour / 10;
        hour_unit = hour - hour_decade * 10;

        min_decade = min / 10;
        min_unit = min - min_decade * 10;

        sec_decade = sec / 10;
        sec_unit = sec - sec_decade * 10;
        // 第个time 进行初始化
        initTime();
    }

    /**
     * 初始化界面数据 开始
     */
    private void initTime() {
//        tv_day_decade.setText(day_decade + "");
//        tv_day_unit.setText(day_unit + "");
//        tv_hour_decade.setText(hour_decade + "");
//        tv_hour_unit.setText(hour_unit + "");
        tv_min_decade.setText(min_decade + "");
        tv_min_unit.setText(min_unit + "");
        tv_sec_decade.setText(sec_decade + "");
        tv_sec_unit.setText(sec_unit + "");
        start();
    }

    @NonNull
    private Boolean countDown() {
        if (isCarry4Unit(tv_sec_unit)) {
            if (isCarry4Decade(tv_sec_decade)) {
                if (isCarry4Unit(tv_min_unit)) {
                    if (isCarry4Decade(tv_min_decade)) {
//                        if (isDay4Unit(tv_hour_unit)) {
//                            if (isDay4Decade(tv_hour_decade)) {
//                                if (isDay4Unit(tv_day_unit)) {
//                                    if (isDay4Decade(tv_day_decade)) {
//                                        tv_day_decade.setText("0");
//                                        tv_day_unit.setText("0");
//                                        tv_hour_decade.setText("0");
//                                        tv_hour_unit.setText("0");
                                        tv_min_decade.setText("0");
                                        tv_min_unit.setText("0");
                                        tv_sec_decade.setText("0");
                                        tv_sec_unit.setText("0");
                                        listener.onTimerFinish();
                                        destroy();
                                        return false;
//                                    }
//                                }
//                            }
//                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * 进行——时分秒，判断个位数
     *
     * @return boolean 变化十位，并判断是否需要进位
     */
    private boolean isCarry4Decade(TextView tv) {
        int time = Integer.valueOf(tv.getText().toString());
        time = time - 1;
        if (time < 0) {
            time = 5;
            tv.setText(time + "");
            return true;
        } else {
            tv.setText(time + "");
            return false;
        }

    }

    /**
     * 进行——时分秒，判断个位数
     *
     * @return boolean
     * 变化个位，并判断是否需要进位
     */
    private boolean isCarry4Unit(TextView tv) {

        int time = Integer.valueOf(tv.getText().toString());
        time = time - 1;
        if (time < 0) {
            time = 9;
            tv.setText(time + "");
            return true;
        } else {
            tv.setText(time + "");
            return false;
        }

    }

    /**
     * 进行——时分秒，判断个位数
     *
     * @return boolean
     * 变化十位，并判断是否需要进位
     */
    private boolean isDay4Unit(TextView tv) {
        int time = Integer.valueOf(tv.getText().toString());
        time = time - 1;
        if (time < 0) {
            time = 3;
            tv.setText(time + "");
            return true;
        } else {
            tv.setText(time + "");
            return false;
        }

    }

    /**
     * 进行——时分秒，判断个位数
     *
     * @return boolean
     * 变化个位，并判断是否需要进位
     */
    private boolean isDay4Decade(TextView tv) {
        int time = Integer.valueOf(tv.getText().toString());
        time = time - 1;
        if (time < 0) {
            time = 2;
            tv.setText(time + "");
            return true;
        } else {
            tv.setText(time + "");
            return false;
        }

    }

    public interface TimerFinishListener {
        void onTimerFinish();
    }

}
