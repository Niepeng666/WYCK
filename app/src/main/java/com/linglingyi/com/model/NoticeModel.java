package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @作者 chenlanxin
 * @创建日期 2019/2/27 11:41
 * @功能
 **/
public class NoticeModel implements Serializable {

    /**
     * content : 32323232
     * effectiveFromTime : {"date":22,"day":5,"hours":0,"minutes":0,"month":2,"nanos":0,"seconds":0,"time":1553184000000,"timezoneOffset":-480,"year":119}
     * effectiveFromTimeStr : 2019-03-22 00:00:00
     * effectiveToTime : {"date":4,"day":4,"hours":0,"minutes":0,"month":3,"nanos":0,"seconds":0,"time":1554307200000,"timezoneOffset":-480,"year":119}
     * effectiveToTimeStr : 2019-04-04 00:00:00
     * hasRead : 0
     * id : CEC864F669CC4C4AA93F48183FE29E0C
     * status :
     * title : 434
     * updateDate : {"date":3,"day":3,"hours":17,"minutes":57,"month":3,"nanos":0,"seconds":58,"time":1554285478000,"timezoneOffset":-480,"year":119}
     * updateDateStr : 2019-04-03 17:57:58
     * updateUser :
     */

    private String content;
    private EffectiveFromTimeBean effectiveFromTime;
    private String effectiveFromTimeStr;
    private EffectiveToTimeBean effectiveToTime;
    private String effectiveToTimeStr;
    private int hasRead;
    private String id;
    private String status;
    private String title;
    private UpdateDateBean updateDate;
    private String updateDateStr;
    private String updateUser;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public EffectiveFromTimeBean getEffectiveFromTime() {
        return effectiveFromTime;
    }

    public void setEffectiveFromTime(EffectiveFromTimeBean effectiveFromTime) {
        this.effectiveFromTime = effectiveFromTime;
    }

    public String getEffectiveFromTimeStr() {
        return effectiveFromTimeStr;
    }

    public void setEffectiveFromTimeStr(String effectiveFromTimeStr) {
        this.effectiveFromTimeStr = effectiveFromTimeStr;
    }

    public EffectiveToTimeBean getEffectiveToTime() {
        return effectiveToTime;
    }

    public void setEffectiveToTime(EffectiveToTimeBean effectiveToTime) {
        this.effectiveToTime = effectiveToTime;
    }

    public String getEffectiveToTimeStr() {
        return effectiveToTimeStr;
    }

    public void setEffectiveToTimeStr(String effectiveToTimeStr) {
        this.effectiveToTimeStr = effectiveToTimeStr;
    }

    public int getHasRead() {
        return hasRead;
    }

    public void setHasRead(int hasRead) {
        this.hasRead = hasRead;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UpdateDateBean getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(UpdateDateBean updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateDateStr() {
        return updateDateStr;
    }

    public void setUpdateDateStr(String updateDateStr) {
        this.updateDateStr = updateDateStr;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public static class EffectiveFromTimeBean implements Serializable{
        /**
         * date : 22
         * day : 5
         * hours : 0
         * minutes : 0
         * month : 2
         * nanos : 0
         * seconds : 0
         * time : 1553184000000
         * timezoneOffset : -480
         * year : 119
         */

        private int date;
        private int day;
        private int hours;
        private int minutes;
        private int month;
        private int nanos;
        private int seconds;
        private long time;
        private int timezoneOffset;
        private int year;

        public int getDate() {
            return date;
        }

        public void setDate(int date) {
            this.date = date;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getHours() {
            return hours;
        }

        public void setHours(int hours) {
            this.hours = hours;
        }

        public int getMinutes() {
            return minutes;
        }

        public void setMinutes(int minutes) {
            this.minutes = minutes;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getNanos() {
            return nanos;
        }

        public void setNanos(int nanos) {
            this.nanos = nanos;
        }

        public int getSeconds() {
            return seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getTimezoneOffset() {
            return timezoneOffset;
        }

        public void setTimezoneOffset(int timezoneOffset) {
            this.timezoneOffset = timezoneOffset;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }
    }

    public static class EffectiveToTimeBean implements Serializable{
        /**
         * date : 4
         * day : 4
         * hours : 0
         * minutes : 0
         * month : 3
         * nanos : 0
         * seconds : 0
         * time : 1554307200000
         * timezoneOffset : -480
         * year : 119
         */

        private int date;
        private int day;
        private int hours;
        private int minutes;
        private int month;
        private int nanos;
        private int seconds;
        private long time;
        private int timezoneOffset;
        private int year;

        public int getDate() {
            return date;
        }

        public void setDate(int date) {
            this.date = date;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getHours() {
            return hours;
        }

        public void setHours(int hours) {
            this.hours = hours;
        }

        public int getMinutes() {
            return minutes;
        }

        public void setMinutes(int minutes) {
            this.minutes = minutes;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getNanos() {
            return nanos;
        }

        public void setNanos(int nanos) {
            this.nanos = nanos;
        }

        public int getSeconds() {
            return seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getTimezoneOffset() {
            return timezoneOffset;
        }

        public void setTimezoneOffset(int timezoneOffset) {
            this.timezoneOffset = timezoneOffset;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }
    }

    public static class UpdateDateBean implements Serializable{
        /**
         * date : 3
         * day : 3
         * hours : 17
         * minutes : 57
         * month : 3
         * nanos : 0
         * seconds : 58
         * time : 1554285478000
         * timezoneOffset : -480
         * year : 119
         */

        private int date;
        private int day;
        private int hours;
        private int minutes;
        private int month;
        private int nanos;
        private int seconds;
        private long time;
        private int timezoneOffset;
        private int year;

        public int getDate() {
            return date;
        }

        public void setDate(int date) {
            this.date = date;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getHours() {
            return hours;
        }

        public void setHours(int hours) {
            this.hours = hours;
        }

        public int getMinutes() {
            return minutes;
        }

        public void setMinutes(int minutes) {
            this.minutes = minutes;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getNanos() {
            return nanos;
        }

        public void setNanos(int nanos) {
            this.nanos = nanos;
        }

        public int getSeconds() {
            return seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getTimezoneOffset() {
            return timezoneOffset;
        }

        public void setTimezoneOffset(int timezoneOffset) {
            this.timezoneOffset = timezoneOffset;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }
    }
}
