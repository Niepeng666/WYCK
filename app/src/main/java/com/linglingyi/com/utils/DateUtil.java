package com.linglingyi.com.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/2/7.
 */

public class DateUtil {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat sdfYM = new SimpleDateFormat("yyyy年MM月");
    private static SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy年MM月dd号");
    public static String formatHM(String date) {
        return formatDateToHM(parseDateToLong(date));
    }

    public static Long parseDateToLong(String date) {
        try {
            return sdf3.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }

    public static String formatDateToHM(long time) {
        return sdf1.format(time);
    }

    public static String formatHM3(String date) {
        return formatDateToHMD2(ymdParseDateToLong(date));
    }

    public static Long ymdParseDateToLong(String date) {
        try {
            return sdf.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }
    public static String formatDateToHMD2(long time) {
        return sdfYMD.format(time);
    }

    public static String formatDateToHM2(long time) {
        return sdfYM.format(time);
    }

    public static String formatHMD(String date) {
        return formateDateTOYMD(parseDateToLong(date));
    }

    public static String formateDateTOYMD(long time) {
        return sdf.format(time);
    }

    public static String formatHM2(String date) {
        return formatDateToHM2(parseDateToLong(date));
    }

    public static Date parseDateYMD(String date) throws ParseException {
        return sdf.parse(date);
    }

    public static Date parseDateYMDHM(String date) throws ParseException {
        return sdf1.parse(date);
    }

    public static Long parseYMDToLong(String date) {
        try {
            return sdf.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String formatDateToHMS(long time) {
        return sdf3.format(time);
    }

    public static String formateDateTOYMD(Date time) {
        return sdf.format(time);
    }

    public static String getDateBetweenTwoDate(String startDate, String endDate) {
        String s = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);
            List<Date> lists = dateSplit(start, end);
            if (!lists.isEmpty()) {
                for (Date date : lists) {
                    s += sdf.format(date) + ",";
                    System.out.println(sdf.format(date));
                }
            }
        } catch (Exception e) {
        }
        if (!TextUtils.isEmpty(s)) {
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }

    private static List<Date> dateSplit(Date startDate, Date endDate)
            throws Exception {
        if (!startDate.before(endDate))
            throw new Exception("开始时间应该在结束时间之后");
        Long spi = endDate.getTime() - startDate.getTime();
        Long step = spi / (24 * 60 * 60 * 1000);
        List<Date> dateList = new ArrayList<>();
        dateList.add(startDate);
        for (int i = 1; i <= step; i++) {
            dateList.add(new Date(dateList.get(i - 1).getTime()
                    + (24 * 60 * 60 * 1000)));
        }
        return dateList;
    }

    /**
     * 判断时间是否在时间段内
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        return date.after(begin) && date.before(end);
    }

    public static long getDayBetweenTWodDate(String startDate, String endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null, end = null;
        try {
            start = sdf.parse(startDate);
            end = sdf.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long spi = end.getTime() - start.getTime();
        long step = spi / (24 * 60 * 60 * 1000);
        return step + 1;
    }

}
