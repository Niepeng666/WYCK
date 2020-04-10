package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/25
 */
public class ActModel implements Serializable {

    /**
     * createTime : {"date":10,"day":5,"hours":15,"minutes":38,"month":4,"nanos":0,"seconds":35,"time":1557473915000,"timezoneOffset":-480,"year":119}
     * id : A20299699BA3460BBFA60DABB28D9EB4
     * imageUrl : http://aikatianxia.llyzf.cn/gg.png
     * informationUrl : QQ
     * isHot : 1
     * level : 2
     * levelName :
     * note :
     * readCount : 22
     * title : 测试2
     * updateTime : null
     */

    private CreateTimeBean createTime;
    private String id;
    private String imageUrl;
    private String informationUrl;
    private String isHot;
    private String level;
    private String levelName;
    private String note;
    private int readCount;
    private String title;
    private Object updateTime;
    /**
     * 分享链接
     */
    private String shareUrl;

    public String getShareUrl() {
        return shareUrl == null ? "" : shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public CreateTimeBean getCreateTime() {
        return createTime;
    }

    public void setCreateTime(CreateTimeBean createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getInformationUrl() {
        return informationUrl;
    }

    public void setInformationUrl(String informationUrl) {
        this.informationUrl = informationUrl;
    }

    public String getIsHot() {
        return isHot;
    }

    public void setIsHot(String isHot) {
        this.isHot = isHot;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public static class CreateTimeBean implements Serializable{
        /**
         * date : 10
         * day : 5
         * hours : 15
         * minutes : 38
         * month : 4
         * nanos : 0
         * seconds : 35
         * time : 1557473915000
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
