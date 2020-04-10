package com.linglingyi.com.model;

import java.io.Serializable;
import java.util.List;

public class FeedbackEntity implements Serializable {

    /**
     * createTime : {"date":1,"day":4,"hours":18,"minutes":6,"month":10,"nanos":0,"seconds":16,"time":1541066776000,"timezoneOffset":-480,"year":118}
     * id : 304C29605B4941619E8D5D3729A52996
     * merchantId : 31A401AD08264F18A360C22CF99CAF3C
     * pics : [{"createTime":{"date":1,"day":4,"hours":18,"minutes":6,"month":10,"nanos":0,"seconds":16,"time":1541066776000,"timezoneOffset":-480,"year":118},"feedbackId":"304C29605B4941619E8D5D3729A52996","id":"0D53B7C0532D48D28D1697D3D3F3B7D1","url":""}]
     * problemCore : 来来来啦咯啦咯啦咯啦啦啦
     * problemDescription : 快活啊
     */

    private CreateTimeBean createTime;
    private String id;
    private String merchantId;
    private String problemCore;
    private String problemDescription;
    private List<PicsBean> pics;

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

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getProblemCore() {
        return problemCore;
    }

    public void setProblemCore(String problemCore) {
        this.problemCore = problemCore;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public List<PicsBean> getPics() {
        return pics;
    }

    public void setPics(List<PicsBean> pics) {
        this.pics = pics;
    }

    public static class CreateTimeBean implements Serializable {
        /**
         * date : 1
         * day : 4
         * hours : 18
         * minutes : 6
         * month : 10
         * nanos : 0
         * seconds : 16
         * time : 1541066776000
         * timezoneOffset : -480
         * year : 118
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

    public static class PicsBean implements Serializable {
        /**
         * createTime : {"date":1,"day":4,"hours":18,"minutes":6,"month":10,"nanos":0,"seconds":16,"time":1541066776000,"timezoneOffset":-480,"year":118}
         * feedbackId : 304C29605B4941619E8D5D3729A52996
         * id : 0D53B7C0532D48D28D1697D3D3F3B7D1
         * url :
         */

        private CreateTimeBeanX createTime;
        private String feedbackId;
        private String id;
        private String url;

        public CreateTimeBeanX getCreateTime() {
            return createTime;
        }

        public void setCreateTime(CreateTimeBeanX createTime) {
            this.createTime = createTime;
        }

        public String getFeedbackId() {
            return feedbackId;
        }

        public void setFeedbackId(String feedbackId) {
            this.feedbackId = feedbackId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public static class CreateTimeBeanX {
            /**
             * date : 1
             * day : 4
             * hours : 18
             * minutes : 6
             * month : 10
             * nanos : 0
             * seconds : 16
             * time : 1541066776000
             * timezoneOffset : -480
             * year : 118
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
}
