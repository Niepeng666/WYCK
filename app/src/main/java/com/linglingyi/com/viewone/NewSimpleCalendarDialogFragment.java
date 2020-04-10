package com.linglingyi.com.viewone;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.linglingyi.com.callback.CalendarSelectCallback;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.ViewUtils;
import com.wuyouchuangke.com.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/1
 */
public class NewSimpleCalendarDialogFragment extends android.support.v4.app.DialogFragment implements OnDateSelectedListener {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
    private TextView tv_year;
    private TextView tv_monthAndDay;
    private TextView tv_weekDay;
    private Calendar calendar;
    private List<Date> selectedDates;
    private CalendarSelectCallback mCalendarSelectCallback;
    private boolean isZhia;

    public NewSimpleCalendarDialogFragment() {
    }

    public static NewSimpleCalendarDialogFragment getInstance(boolean zhia) {
        NewSimpleCalendarDialogFragment simpleCalendarDialogFragment = new NewSimpleCalendarDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("isZhia", zhia);
        simpleCalendarDialogFragment.setArguments(bundle);
        return simpleCalendarDialogFragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        isZhia = getArguments().getBoolean("isZhia");
        calendar = Calendar.getInstance();
//        if (isZhia) {
        calendar.add(Calendar.DAY_OF_MONTH, 1);
//        }
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_basic, null);
        final MaterialCalendarView calendarView = (MaterialCalendarView) view.findViewById(R.id.calendarView);
        tv_year = (TextView) view.findViewById(R.id.tv_year);
        tv_monthAndDay = (TextView) view.findViewById(R.id.tv_monthAndDay);
        tv_weekDay = (TextView) view.findViewById(R.id.tv_weekDay);
        tv_year.setText(calendar.get(Calendar.YEAR) + "年");
        tv_monthAndDay.setText(calendar.get(Calendar.MONTH) + 1 + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日");
        tv_weekDay.setText("周" + numToChinese(calendar.get(Calendar.DAY_OF_WEEK)));
        int[] attrArray1 = {R.attr.theme_bg_color};
        TypedArray mTypedArray1 = getActivity().obtainStyledAttributes(attrArray1);
        calendarView.setSelectionColor(mTypedArray1.getColor(0, 0xFF000000));
        try {
            calendarView.setOnDateChangedListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        calendarView.setSelectedDate(calendar);
//        calendarView.setCurrentDate(calendar);
        calendarView.state().edit().setMinimumDate(calendar)
                .commit();
        calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);

//        calendarView.addDecorator(new PrimeDayDisableDecorator());
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        List<CalendarDay> selectedDays = calendarView.getSelectedDates();
                        if (selectedDays.size() >= 1) {
                            selectedDates = new ArrayList<>();
                            for (CalendarDay day : selectedDays) {
                                selectedDates.add(day.getDate());
                            }
                            Collections.sort(selectedDates);
                            StringBuilder sb = new StringBuilder();
                            for (Date date : selectedDates) {
                                sb.append(sdf2.format(date)).append(",");
                            }
                            String str = sb.toString();
                            if (mCalendarSelectCallback != null) {
                                mCalendarSelectCallback.success(selectedDates, str.substring(0, str.length() - 1));
                            }
                        } else {
                            ViewUtils.makeToast(getActivity(), "至少选择1个日期", 1000);
                        }
                    }
                }).create();
    }

    private String numToChinese(int num) {
        String s = "";
        switch (num) {
            case 1:
                s = "日";
                break;
            case 2:
                s = "一";
                break;
            case 3:
                s = "二";
                break;
            case 4:
                s = "三";
                break;
            case 5:
                s = "四";
                break;
            case 6:
                s = "五";
                break;
            case 7:
                s = "六";
                break;
        }
        return s;

    }

    public void setCalendarSelectCallback(CalendarSelectCallback calendarSelectCallback) {
        mCalendarSelectCallback = calendarSelectCallback;
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget,
                               @NonNull CalendarDay date, boolean selected) {

        tv_year.setText(date.getYear() + "年");
        tv_monthAndDay.setText(date.getMonth() + 1 + "月" + date.getDay() + "日");
        tv_weekDay.setText("周" + numToChinese(date.getCalendar().get(Calendar.DAY_OF_WEEK)));
    }


    private static class PrimeDayDisableDecorator implements DayViewDecorator {

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            LogUtils.i("day=" + day + ",compare" + day.getCalendar().compareTo(Calendar.getInstance()));
            return -1 == day.getCalendar().compareTo(Calendar.getInstance());
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setDaysDisabled(true);
        }


    }
}