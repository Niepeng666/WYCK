package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @作者 chenlanxin
 * @创建日期 2019/2/25 17:32
 * @功能
 **/
public class User implements Serializable {


    /**
     * agent : {"accountName":"测试","address":"河南省","agentName":"测试","agentNo":"001","agentUserId":0,"bankAccount":"6225568945698796","createDate":{"date":24,"day":0,"hours":16,"minutes":3,"month":1,"seconds":4,"time":1550995384000,"timezoneOffset":-480,"year":119},"createId":2,"createUser":null,"delFlag":false,"departmentCode":"","id":2,"idCardNo":"45456465465465464","issbankId":"","phone":"123456789","remarks":"","status":"启用","totalMoney":100,"updateDate":{"date":24,"day":0,"hours":16,"minutes":3,"month":1,"seconds":20,"time":1550995400000,"timezoneOffset":-480,"year":119},"updateId":2,"updateUser":null,"upstreamNo":"2800052","upstreamTmk":"040EB410850B40039107A38972B7F3E4"}
     * merchant : {"acqMerchantNo":"","agentId":2,"bankAccount":"","bankAccountName":"123","bankCode":"","bankDetail":"","bankDot":"123","createDate":{"date":25,"day":1,"hours":16,"minutes":53,"month":1,"seconds":42,"time":1551084822000,"timezoneOffset":-480,"year":119},"createId":0,"createUser":null,"delFlag":false,"departmentCode":"123","examinePassTime":{"date":25,"day":1,"hours":16,"minutes":53,"month":1,"seconds":42,"time":1551084822000,"timezoneOffset":-480,"year":119},"headUrl":"","id":3,"idCardNumber":"123","identityMatchDegree":"","isAddress":false,"isChsiOriginal":false,"isCreditCard":false,"isHfundOriginal":false,"isJdOriginal":false,"isLinkPerson":false,"isMnoOrigianl":false,"isZmDetail":false,"linkPerson":"","linkPhone":"15706844093","liviMatchDegree":"123","loanAmount":0,"merchantCnName":"15706844093","merchantNo":"220068419026482","merchantSource":"APP","passwd":"e10adc3949ba59abbe56e057f20f883e","phone":"15706844093","productShort":"xdtx","promoterId":123,"rate":0.6,"remarks":"","settleStatus":"","status":"10A","totalMoney":0,"updateDate":{"date":25,"day":1,"hours":16,"minutes":53,"month":1,"seconds":42,"time":1551084822000,"timezoneOffset":-480,"year":119},"updateId":0,"updateUser":null,"usableAmount":0,"useStatus":"10A"}
     */

    private AgentBean agent;
    private MerchantBean merchant;
    private String accreditStatus;//是否需要上传联系人，短信等 0:未上传，1：已经上传
    private String authenticationStatus;//是否认证完成 0：未认证 ，1： 已经认证

    public String getAccreditStatus() {
        return accreditStatus;
    }

    public void setAccreditStatus(String accreditStatus) {
        this.accreditStatus = accreditStatus;
    }

    public String getAuthenticationStatus() {
        return authenticationStatus;
    }

    public void setAuthenticationStatus(String authenticationStatus) {
        this.authenticationStatus = authenticationStatus;
    }

    public AgentBean getAgent() {
        return agent;
    }

    public void setAgent(AgentBean agent) {
        this.agent = agent;
    }

    public MerchantBean getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantBean merchant) {
        this.merchant = merchant;
    }

    public static class AgentBean {
        /**
         * accountName : 测试
         * address : 河南省
         * agentName : 测试
         * agentNo : 001
         * agentUserId : 0
         * bankAccount : 6225568945698796
         * createDate : {"date":24,"day":0,"hours":16,"minutes":3,"month":1,"seconds":4,"time":1550995384000,"timezoneOffset":-480,"year":119}
         * createId : 2
         * createUser : null
         * delFlag : false
         * departmentCode :
         * id : 2
         * idCardNo : 45456465465465464
         * issbankId :
         * phone : 123456789
         * remarks :
         * status : 启用
         * totalMoney : 100
         * updateDate : {"date":24,"day":0,"hours":16,"minutes":3,"month":1,"seconds":20,"time":1550995400000,"timezoneOffset":-480,"year":119}
         * updateId : 2
         * updateUser : null
         * upstreamNo : 2800052
         * upstreamTmk : 040EB410850B40039107A38972B7F3E4
         */

        private String accountName;
        private String address;
        private String agentName;
        private String agentNo;
        private int agentUserId;
        private String bankAccount;
        private DateBean createDate;
        private int createId;
        private Object createUser;
        private boolean delFlag;
        private String departmentCode;
        private int id;
        private String idCardNo;
        private String issbankId;
        private String phone;
        private String remarks;
        private String status;
        private int totalMoney;
        private DateBean updateDate;
        private int updateId;
        private Object updateUser;
        private String upstreamNo;
        private String upstreamTmk;

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAgentName() {
            return agentName;
        }

        public void setAgentName(String agentName) {
            this.agentName = agentName;
        }

        public String getAgentNo() {
            return agentNo;
        }

        public void setAgentNo(String agentNo) {
            this.agentNo = agentNo;
        }

        public int getAgentUserId() {
            return agentUserId;
        }

        public void setAgentUserId(int agentUserId) {
            this.agentUserId = agentUserId;
        }

        public String getBankAccount() {
            return bankAccount;
        }

        public void setBankAccount(String bankAccount) {
            this.bankAccount = bankAccount;
        }

        public DateBean getCreateDate() {
            return createDate;
        }

        public void setCreateDate(DateBean createDate) {
            this.createDate = createDate;
        }

        public int getCreateId() {
            return createId;
        }

        public void setCreateId(int createId) {
            this.createId = createId;
        }

        public Object getCreateUser() {
            return createUser;
        }

        public void setCreateUser(Object createUser) {
            this.createUser = createUser;
        }

        public boolean isDelFlag() {
            return delFlag;
        }

        public void setDelFlag(boolean delFlag) {
            this.delFlag = delFlag;
        }

        public String getDepartmentCode() {
            return departmentCode;
        }

        public void setDepartmentCode(String departmentCode) {
            this.departmentCode = departmentCode;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIdCardNo() {
            return idCardNo;
        }

        public void setIdCardNo(String idCardNo) {
            this.idCardNo = idCardNo;
        }

        public String getIssbankId() {
            return issbankId;
        }

        public void setIssbankId(String issbankId) {
            this.issbankId = issbankId;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(int totalMoney) {
            this.totalMoney = totalMoney;
        }

        public DateBean getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(DateBean updateDate) {
            this.updateDate = updateDate;
        }

        public int getUpdateId() {
            return updateId;
        }

        public void setUpdateId(int updateId) {
            this.updateId = updateId;
        }

        public Object getUpdateUser() {
            return updateUser;
        }

        public void setUpdateUser(Object updateUser) {
            this.updateUser = updateUser;
        }

        public String getUpstreamNo() {
            return upstreamNo;
        }

        public void setUpstreamNo(String upstreamNo) {
            this.upstreamNo = upstreamNo;
        }

        public String getUpstreamTmk() {
            return upstreamTmk;
        }

        public void setUpstreamTmk(String upstreamTmk) {
            this.upstreamTmk = upstreamTmk;
        }

        public static class DateBean {
            /**
             * date : 24
             * day : 0
             * hours : 16
             * minutes : 3
             * month : 1
             * seconds : 4
             * time : 1550995384000
             * timezoneOffset : -480
             * year : 119
             */

            private int date;
            private int day;
            private int hours;
            private int minutes;
            private int month;
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

    public static class MerchantBean {
        /**
         * acqMerchantNo :
         * agentId : 2
         * bankAccount :
         * bankAccountName : 123
         * bankCode :
         * bankDetail :
         * bankDot : 123
         * createDate : {"date":25,"day":1,"hours":16,"minutes":53,"month":1,"seconds":42,"time":1551084822000,"timezoneOffset":-480,"year":119}
         * createId : 0
         * createUser : null
         * delFlag : false
         * departmentCode : 123
         * examinePassTime : {"date":25,"day":1,"hours":16,"minutes":53,"month":1,"seconds":42,"time":1551084822000,"timezoneOffset":-480,"year":119}
         * headUrl :
         * id : 3
         * idCardNumber : 123
         * identityMatchDegree :
         * isAddress : false
         * isChsiOriginal : false
         * isCreditCard : false
         * isHfundOriginal : false
         * isJdOriginal : false
         * isLinkPerson : false
         * isMnoOrigianl : false
         * isZmDetail : false
         * linkPerson :
         * linkPhone : 15706844093
         * liviMatchDegree : 123
         * loanAmount : 0
         * merchantCnName : 15706844093
         * merchantNo : 220068419026482
         * merchantSource : APP
         * passwd : e10adc3949ba59abbe56e057f20f883e
         * phone : 15706844093
         * productShort : xdtx
         * promoterId : 123
         * rate : 0.6
         * remarks :
         * settleStatus :
         * status : 10A
         * totalMoney : 0
         * updateDate : {"date":25,"day":1,"hours":16,"minutes":53,"month":1,"seconds":42,"time":1551084822000,"timezoneOffset":-480,"year":119}
         * updateId : 0
         * updateUser : null
         * usableAmount : 0
         * useStatus : 10A
         */

        private String acqMerchantNo;
        private int agentId;
        private String bankAccount;
        private String bankAccountName;
        private String bankCode;
        private String bankDetail;
        private String bankDot;
        private DateBean createDate;
        private int createId;
        private Object createUser;
        private boolean delFlag;
        private String departmentCode;
        private DateBean examinePassTime;
        private String headUrl;
        private int id;
        private String idCardNumber;
        private String identityMatchDegree;
        private boolean isAddress;
        private boolean isChsiOriginal;
        private boolean isCreditCard;
        private boolean isHfundOriginal;
        private boolean isJdOriginal;
        private boolean isLinkPerson;
        private boolean isMnoOrigianl;
        private boolean isZmDetail;
        private String linkPerson;
        private String linkPhone;
        private String liviMatchDegree;
        private int loanAmount;
        private String merchantCnName;
        private String merchantNo;
        private String merchantSource;
        private String passwd;
        private String phone;
        private String productShort;
        private int promoterId;
        private double rate;
        private String remarks;
        private String settleStatus;
        private String status;
        private int totalMoney;
        private DateBean updateDate;
        private int updateId;
        private Object updateUser;
        private int usableAmount;
        private String useStatus;

        public String getAcqMerchantNo() {
            return acqMerchantNo;
        }

        public void setAcqMerchantNo(String acqMerchantNo) {
            this.acqMerchantNo = acqMerchantNo;
        }

        public int getAgentId() {
            return agentId;
        }

        public void setAgentId(int agentId) {
            this.agentId = agentId;
        }

        public String getBankAccount() {
            return bankAccount;
        }

        public void setBankAccount(String bankAccount) {
            this.bankAccount = bankAccount;
        }

        public String getBankAccountName() {
            return bankAccountName;
        }

        public void setBankAccountName(String bankAccountName) {
            this.bankAccountName = bankAccountName;
        }

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public String getBankDetail() {
            return bankDetail;
        }

        public void setBankDetail(String bankDetail) {
            this.bankDetail = bankDetail;
        }

        public String getBankDot() {
            return bankDot;
        }

        public void setBankDot(String bankDot) {
            this.bankDot = bankDot;
        }

        public DateBean getCreateDate() {
            return createDate;
        }

        public void setCreateDate(DateBean createDate) {
            this.createDate = createDate;
        }

        public int getCreateId() {
            return createId;
        }

        public void setCreateId(int createId) {
            this.createId = createId;
        }

        public Object getCreateUser() {
            return createUser;
        }

        public void setCreateUser(Object createUser) {
            this.createUser = createUser;
        }

        public boolean isDelFlag() {
            return delFlag;
        }

        public void setDelFlag(boolean delFlag) {
            this.delFlag = delFlag;
        }

        public String getDepartmentCode() {
            return departmentCode;
        }

        public void setDepartmentCode(String departmentCode) {
            this.departmentCode = departmentCode;
        }

        public DateBean getExaminePassTime() {
            return examinePassTime;
        }

        public void setExaminePassTime(DateBean examinePassTime) {
            this.examinePassTime = examinePassTime;
        }

        public String getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIdCardNumber() {
            return idCardNumber;
        }

        public void setIdCardNumber(String idCardNumber) {
            this.idCardNumber = idCardNumber;
        }

        public String getIdentityMatchDegree() {
            return identityMatchDegree;
        }

        public void setIdentityMatchDegree(String identityMatchDegree) {
            this.identityMatchDegree = identityMatchDegree;
        }

        public boolean isIsAddress() {
            return isAddress;
        }

        public void setIsAddress(boolean isAddress) {
            this.isAddress = isAddress;
        }

        public boolean isIsChsiOriginal() {
            return isChsiOriginal;
        }

        public void setIsChsiOriginal(boolean isChsiOriginal) {
            this.isChsiOriginal = isChsiOriginal;
        }

        public boolean isIsCreditCard() {
            return isCreditCard;
        }

        public void setIsCreditCard(boolean isCreditCard) {
            this.isCreditCard = isCreditCard;
        }

        public boolean isIsHfundOriginal() {
            return isHfundOriginal;
        }

        public void setIsHfundOriginal(boolean isHfundOriginal) {
            this.isHfundOriginal = isHfundOriginal;
        }

        public boolean isIsJdOriginal() {
            return isJdOriginal;
        }

        public void setIsJdOriginal(boolean isJdOriginal) {
            this.isJdOriginal = isJdOriginal;
        }

        public boolean isIsLinkPerson() {
            return isLinkPerson;
        }

        public void setIsLinkPerson(boolean isLinkPerson) {
            this.isLinkPerson = isLinkPerson;
        }

        public boolean isIsMnoOrigianl() {
            return isMnoOrigianl;
        }

        public void setIsMnoOrigianl(boolean isMnoOrigianl) {
            this.isMnoOrigianl = isMnoOrigianl;
        }

        public boolean isIsZmDetail() {
            return isZmDetail;
        }

        public void setIsZmDetail(boolean isZmDetail) {
            this.isZmDetail = isZmDetail;
        }

        public String getLinkPerson() {
            return linkPerson;
        }

        public void setLinkPerson(String linkPerson) {
            this.linkPerson = linkPerson;
        }

        public String getLinkPhone() {
            return linkPhone;
        }

        public void setLinkPhone(String linkPhone) {
            this.linkPhone = linkPhone;
        }

        public String getLiviMatchDegree() {
            return liviMatchDegree;
        }

        public void setLiviMatchDegree(String liviMatchDegree) {
            this.liviMatchDegree = liviMatchDegree;
        }

        public int getLoanAmount() {
            return loanAmount;
        }

        public void setLoanAmount(int loanAmount) {
            this.loanAmount = loanAmount;
        }

        public String getMerchantCnName() {
            return merchantCnName;
        }

        public void setMerchantCnName(String merchantCnName) {
            this.merchantCnName = merchantCnName;
        }

        public String getMerchantNo() {
            return merchantNo;
        }

        public void setMerchantNo(String merchantNo) {
            this.merchantNo = merchantNo;
        }

        public String getMerchantSource() {
            return merchantSource;
        }

        public void setMerchantSource(String merchantSource) {
            this.merchantSource = merchantSource;
        }

        public String getPasswd() {
            return passwd;
        }

        public void setPasswd(String passwd) {
            this.passwd = passwd;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProductShort() {
            return productShort;
        }

        public void setProductShort(String productShort) {
            this.productShort = productShort;
        }

        public int getPromoterId() {
            return promoterId;
        }

        public void setPromoterId(int promoterId) {
            this.promoterId = promoterId;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getSettleStatus() {
            return settleStatus;
        }

        public void setSettleStatus(String settleStatus) {
            this.settleStatus = settleStatus;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(int totalMoney) {
            this.totalMoney = totalMoney;
        }

        public DateBean getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(DateBean updateDate) {
            this.updateDate = updateDate;
        }

        public int getUpdateId() {
            return updateId;
        }

        public void setUpdateId(int updateId) {
            this.updateId = updateId;
        }

        public Object getUpdateUser() {
            return updateUser;
        }

        public void setUpdateUser(Object updateUser) {
            this.updateUser = updateUser;
        }

        public int getUsableAmount() {
            return usableAmount;
        }

        public void setUsableAmount(int usableAmount) {
            this.usableAmount = usableAmount;
        }

        public String getUseStatus() {
            return useStatus;
        }

        public void setUseStatus(String useStatus) {
            this.useStatus = useStatus;
        }

        public static class DateBean {
            /**
             * date : 25
             * day : 1
             * hours : 16
             * minutes : 53
             * month : 1
             * seconds : 42
             * time : 1551084822000
             * timezoneOffset : -480
             * year : 119
             */

            private int date;
            private int day;
            private int hours;
            private int minutes;
            private int month;
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
