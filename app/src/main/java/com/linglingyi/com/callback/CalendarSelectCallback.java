package com.linglingyi.com.callback;

import java.util.Date;
import java.util.List;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/5/27
 */
public interface CalendarSelectCallback {
    void success(List<Date> dateList,
                 String day);
}
