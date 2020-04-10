package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/3/27
 */
public class AgentInfoModel implements Serializable {

    /**
     * accountName : 叶明华
     * activateMoney : 0
     * address : 车
     * agentCode : 00010000000000000000000000000000
     * agentName : 福腾壹家
     * agentNo : 1800001
     * agentType :
     * agentUserId : B56DA9AEE0DE48F888A3CD7A5491979F
     * appIdentity : 10A
     * appRate : 0
     * areaId : 7E350B892E724EB686CB0B1BC1B06446
     * bankAccount : 621755555665666555
     * bankAreaId : 7E350B892E724EB686CB0B1BC1B06446
     * bankCityId : 27050E421BA14F53A41A748FB2C1260A
     * bankProvinceId : C5E6B9C87D374A8B8A504E4EF7D1CFD6
     * cityId : 27050E421BA14F53A41A748FB2C1260A
     * clientArea : 1
     * contractEndDate : null
     * contractStartDate : null
     * convertCount : 0
     * corpName : 叶明华
     * corpPhone : 18858656827
     * createTime : {"date":25,"day":1,"hours":21,"minutes":4,"month":2,"nanos":0,"seconds":33,"time":1553519073000,"timezoneOffset":-480,"year":119}
     * createUserId : F9946CAE0D574FB1BDFDAE922F7821E1
     * dailyLimit : 0
     * dailyWithdrawalCount : 0
     * dayTradeLimit : 0
     * departmentCode : 00010002000100000000000000000000
     * employStatus : 10A
     * fastBusinessTimeFrom : null
     * fastBusinessTimeTo : null
     * fastCount : 0
     * fastLimit : 0
     * fastRate : 0
     * freeStatus :
     * holidaysWithdraw :
     * holidaysWithdrawConfig : 0
     * huabeiCost : 2.5+1
     * id : DD4A88C90F1C4C40970685DB4C137BD0
     * idCardNo : 410381198003302010
     * issbankId : A76F1A0F1AB84D58975A3CC7E8889D80
     * level : 0
     * level1Rate : 0
     * level2Rate : 0
     * level3Rate : 0
     * logoText :
     * lowerStatus : 50
     * minWithdrawAmt : 0
     * openSecondLevel : 10B
     * parentId :
     * paymentInterface :
     * profitRatio : 0
     * provinceId : C5E6B9C87D374A8B8A504E4EF7D1CFD6
     * raiseLimit : 0
     * singleLimit : 0
     * status : 10B
     * supportRaiseLimitStatus :
     * totalMoney : 0
     * totalWithdrawAmt : 0
     * tradeCount : 0
     * tradeLimit : 0
     * uncertifiedTradeCount : 0
     * uncertifiedTradeLimit : 0
     * uncertifiedWithdrawCount : 0
     * uncertifiedWithdrawLimit : 0
     * uploadContract : 50
     * uploadContractTime : null
     * withdrawBusinessTimeForm :
     * withdrawBusinessTimeTo :
     * withdrawCount : 0
     * withdrawProfitRatio : 0
     * withdrawStatus :
     * zmk : A75EBE0239164845
     */

    private String accountName;
    private int activateMoney;
    private String address;
    private String agentCode;
    private String agentName;
    private String agentNo;
    private String agentType;
    private String agentUserId;
    private String appIdentity;
    private int appRate;
    private String areaId;
    private String bankAccount;
    private String bankAreaId;
    private String bankCityId;
    private String bankProvinceId;
    private String cityId;
    private String clientArea;
    private Object contractEndDate;
    private Object contractStartDate;
    private int convertCount;
    private String corpName;
    private String corpPhone;
    private CreateTimeBean createTime;
    private String createUserId;
    private int dailyLimit;
    private int dailyWithdrawalCount;
    private int dayTradeLimit;
    private String departmentCode;
    private String employStatus;
    private Object fastBusinessTimeFrom;
    private Object fastBusinessTimeTo;
    private int fastCount;
    private int fastLimit;
    private int fastRate;
    private String freeStatus;
    private String holidaysWithdraw;
    private int holidaysWithdrawConfig;
    private String huabeiCost;
    private String id;
    private String idCardNo;
    private String issbankId;
    private String level;
    private int level1Rate;
    private int level2Rate;
    private int level3Rate;
    private String logoText;
    private String lowerStatus;
    private int minWithdrawAmt;
    private String openSecondLevel;
    private String parentId;
    private String paymentInterface;
    private int profitRatio;
    private String provinceId;
    private int raiseLimit;
    private int singleLimit;
    private String status;
    private String supportRaiseLimitStatus;
    private int totalMoney;
    private int totalWithdrawAmt;
    private int tradeCount;
    private int tradeLimit;
    private int uncertifiedTradeCount;
    private int uncertifiedTradeLimit;
    private int uncertifiedWithdrawCount;
    private int uncertifiedWithdrawLimit;
    private String uploadContract;
    private Object uploadContractTime;
    private String withdrawBusinessTimeForm;
    private String withdrawBusinessTimeTo;
    private int withdrawCount;
    private int withdrawProfitRatio;
    private String withdrawStatus;
    private String zmk;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getActivateMoney() {
        return activateMoney;
    }

    public void setActivateMoney(int activateMoney) {
        this.activateMoney = activateMoney;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
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

    public String getAgentType() {
        return agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }

    public String getAgentUserId() {
        return agentUserId;
    }

    public void setAgentUserId(String agentUserId) {
        this.agentUserId = agentUserId;
    }

    public String getAppIdentity() {
        return appIdentity;
    }

    public void setAppIdentity(String appIdentity) {
        this.appIdentity = appIdentity;
    }

    public int getAppRate() {
        return appRate;
    }

    public void setAppRate(int appRate) {
        this.appRate = appRate;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankAreaId() {
        return bankAreaId;
    }

    public void setBankAreaId(String bankAreaId) {
        this.bankAreaId = bankAreaId;
    }

    public String getBankCityId() {
        return bankCityId;
    }

    public void setBankCityId(String bankCityId) {
        this.bankCityId = bankCityId;
    }

    public String getBankProvinceId() {
        return bankProvinceId;
    }

    public void setBankProvinceId(String bankProvinceId) {
        this.bankProvinceId = bankProvinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getClientArea() {
        return clientArea;
    }

    public void setClientArea(String clientArea) {
        this.clientArea = clientArea;
    }

    public Object getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(Object contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public Object getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(Object contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public int getConvertCount() {
        return convertCount;
    }

    public void setConvertCount(int convertCount) {
        this.convertCount = convertCount;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getCorpPhone() {
        return corpPhone;
    }

    public void setCorpPhone(String corpPhone) {
        this.corpPhone = corpPhone;
    }

    public CreateTimeBean getCreateTime() {
        return createTime;
    }

    public void setCreateTime(CreateTimeBean createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(int dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public int getDailyWithdrawalCount() {
        return dailyWithdrawalCount;
    }

    public void setDailyWithdrawalCount(int dailyWithdrawalCount) {
        this.dailyWithdrawalCount = dailyWithdrawalCount;
    }

    public int getDayTradeLimit() {
        return dayTradeLimit;
    }

    public void setDayTradeLimit(int dayTradeLimit) {
        this.dayTradeLimit = dayTradeLimit;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getEmployStatus() {
        return employStatus;
    }

    public void setEmployStatus(String employStatus) {
        this.employStatus = employStatus;
    }

    public Object getFastBusinessTimeFrom() {
        return fastBusinessTimeFrom;
    }

    public void setFastBusinessTimeFrom(Object fastBusinessTimeFrom) {
        this.fastBusinessTimeFrom = fastBusinessTimeFrom;
    }

    public Object getFastBusinessTimeTo() {
        return fastBusinessTimeTo;
    }

    public void setFastBusinessTimeTo(Object fastBusinessTimeTo) {
        this.fastBusinessTimeTo = fastBusinessTimeTo;
    }

    public int getFastCount() {
        return fastCount;
    }

    public void setFastCount(int fastCount) {
        this.fastCount = fastCount;
    }

    public int getFastLimit() {
        return fastLimit;
    }

    public void setFastLimit(int fastLimit) {
        this.fastLimit = fastLimit;
    }

    public int getFastRate() {
        return fastRate;
    }

    public void setFastRate(int fastRate) {
        this.fastRate = fastRate;
    }

    public String getFreeStatus() {
        return freeStatus;
    }

    public void setFreeStatus(String freeStatus) {
        this.freeStatus = freeStatus;
    }

    public String getHolidaysWithdraw() {
        return holidaysWithdraw;
    }

    public void setHolidaysWithdraw(String holidaysWithdraw) {
        this.holidaysWithdraw = holidaysWithdraw;
    }

    public int getHolidaysWithdrawConfig() {
        return holidaysWithdrawConfig;
    }

    public void setHolidaysWithdrawConfig(int holidaysWithdrawConfig) {
        this.holidaysWithdrawConfig = holidaysWithdrawConfig;
    }

    public String getHuabeiCost() {
        return huabeiCost;
    }

    public void setHuabeiCost(String huabeiCost) {
        this.huabeiCost = huabeiCost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getLevel1Rate() {
        return level1Rate;
    }

    public void setLevel1Rate(int level1Rate) {
        this.level1Rate = level1Rate;
    }

    public int getLevel2Rate() {
        return level2Rate;
    }

    public void setLevel2Rate(int level2Rate) {
        this.level2Rate = level2Rate;
    }

    public int getLevel3Rate() {
        return level3Rate;
    }

    public void setLevel3Rate(int level3Rate) {
        this.level3Rate = level3Rate;
    }

    public String getLogoText() {
        return logoText;
    }

    public void setLogoText(String logoText) {
        this.logoText = logoText;
    }

    public String getLowerStatus() {
        return lowerStatus;
    }

    public void setLowerStatus(String lowerStatus) {
        this.lowerStatus = lowerStatus;
    }

    public int getMinWithdrawAmt() {
        return minWithdrawAmt;
    }

    public void setMinWithdrawAmt(int minWithdrawAmt) {
        this.minWithdrawAmt = minWithdrawAmt;
    }

    public String getOpenSecondLevel() {
        return openSecondLevel;
    }

    public void setOpenSecondLevel(String openSecondLevel) {
        this.openSecondLevel = openSecondLevel;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPaymentInterface() {
        return paymentInterface;
    }

    public void setPaymentInterface(String paymentInterface) {
        this.paymentInterface = paymentInterface;
    }

    public int getProfitRatio() {
        return profitRatio;
    }

    public void setProfitRatio(int profitRatio) {
        this.profitRatio = profitRatio;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public int getRaiseLimit() {
        return raiseLimit;
    }

    public void setRaiseLimit(int raiseLimit) {
        this.raiseLimit = raiseLimit;
    }

    public int getSingleLimit() {
        return singleLimit;
    }

    public void setSingleLimit(int singleLimit) {
        this.singleLimit = singleLimit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSupportRaiseLimitStatus() {
        return supportRaiseLimitStatus;
    }

    public void setSupportRaiseLimitStatus(String supportRaiseLimitStatus) {
        this.supportRaiseLimitStatus = supportRaiseLimitStatus;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getTotalWithdrawAmt() {
        return totalWithdrawAmt;
    }

    public void setTotalWithdrawAmt(int totalWithdrawAmt) {
        this.totalWithdrawAmt = totalWithdrawAmt;
    }

    public int getTradeCount() {
        return tradeCount;
    }

    public void setTradeCount(int tradeCount) {
        this.tradeCount = tradeCount;
    }

    public int getTradeLimit() {
        return tradeLimit;
    }

    public void setTradeLimit(int tradeLimit) {
        this.tradeLimit = tradeLimit;
    }

    public int getUncertifiedTradeCount() {
        return uncertifiedTradeCount;
    }

    public void setUncertifiedTradeCount(int uncertifiedTradeCount) {
        this.uncertifiedTradeCount = uncertifiedTradeCount;
    }

    public int getUncertifiedTradeLimit() {
        return uncertifiedTradeLimit;
    }

    public void setUncertifiedTradeLimit(int uncertifiedTradeLimit) {
        this.uncertifiedTradeLimit = uncertifiedTradeLimit;
    }

    public int getUncertifiedWithdrawCount() {
        return uncertifiedWithdrawCount;
    }

    public void setUncertifiedWithdrawCount(int uncertifiedWithdrawCount) {
        this.uncertifiedWithdrawCount = uncertifiedWithdrawCount;
    }

    public int getUncertifiedWithdrawLimit() {
        return uncertifiedWithdrawLimit;
    }

    public void setUncertifiedWithdrawLimit(int uncertifiedWithdrawLimit) {
        this.uncertifiedWithdrawLimit = uncertifiedWithdrawLimit;
    }

    public String getUploadContract() {
        return uploadContract;
    }

    public void setUploadContract(String uploadContract) {
        this.uploadContract = uploadContract;
    }

    public Object getUploadContractTime() {
        return uploadContractTime;
    }

    public void setUploadContractTime(Object uploadContractTime) {
        this.uploadContractTime = uploadContractTime;
    }

    public String getWithdrawBusinessTimeForm() {
        return withdrawBusinessTimeForm;
    }

    public void setWithdrawBusinessTimeForm(String withdrawBusinessTimeForm) {
        this.withdrawBusinessTimeForm = withdrawBusinessTimeForm;
    }

    public String getWithdrawBusinessTimeTo() {
        return withdrawBusinessTimeTo;
    }

    public void setWithdrawBusinessTimeTo(String withdrawBusinessTimeTo) {
        this.withdrawBusinessTimeTo = withdrawBusinessTimeTo;
    }

    public int getWithdrawCount() {
        return withdrawCount;
    }

    public void setWithdrawCount(int withdrawCount) {
        this.withdrawCount = withdrawCount;
    }

    public int getWithdrawProfitRatio() {
        return withdrawProfitRatio;
    }

    public void setWithdrawProfitRatio(int withdrawProfitRatio) {
        this.withdrawProfitRatio = withdrawProfitRatio;
    }

    public String getWithdrawStatus() {
        return withdrawStatus;
    }

    public void setWithdrawStatus(String withdrawStatus) {
        this.withdrawStatus = withdrawStatus;
    }

    public String getZmk() {
        return zmk;
    }

    public void setZmk(String zmk) {
        this.zmk = zmk;
    }

    public static class CreateTimeBean {
        /**
         * date : 25
         * day : 1
         * hours : 21
         * minutes : 4
         * month : 2
         * nanos : 0
         * seconds : 33
         * time : 1553519073000
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
