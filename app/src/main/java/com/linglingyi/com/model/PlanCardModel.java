package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/10/25
 */
public class PlanCardModel implements Serializable {

    /**
     * STATUS : 10C
     * bank : 招商银行(尾号3859)
     * ID : 03700F9FD4EC48078843A03C1DB42BE2
     * CREATE_TIME : {"date":15,"day":2,"hours":17,"minutes":35,"month":9,"nanos":0,"seconds":1,"time":1571132101000,"timezoneOffset":-480,"year":119}
     */

    private String STATUS;
    private String bank;
    private String ID;
    private CREATETIMEBean CREATE_TIME;

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public CREATETIMEBean getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(CREATETIMEBean CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }

    public static class CREATETIMEBean implements Serializable{
        /**
         * date : 15
         * day : 2
         * hours : 17
         * minutes : 35
         * month : 9
         * nanos : 0
         * seconds : 1
         * time : 1571132101000
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
