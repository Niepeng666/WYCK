package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/3/27
 */
public class UserInfoModel implements Serializable {

    /**
     * addrDetail :
     * agentId : DD4A88C90F1C4C40970685DB4C137BD0
     * agentSalesName :
     * artificialPerson :
     * artificialPersonCardNumber :
     * auditRecord :
     * authCardType : 94B9B2CD07054AD8966908C1EB25863E,9CE762E65EFD4E70962680F60DF928B2
     * authTrxType : SALE,QUERY
     * bankAccount :
     * bankAccountName :
     * bankAccountType :
     * bankCityId :
     * bankCode :
     * bankDetail :
     * bankDistrictId :
     * bankDot :
     * bankProvinceId :
     * bankSettleType : 0
     * businessLicenseBeginTime : null
     * businessLicenseEndTime : null
     * businessLicenseNo :
     * changeReason :
     * chinaRegionId :
     * cityId :
     * companyRegistrationAddress :
     * convertCount : 0
     * coordinate :
     * corporate :
     * createTime : {"date":27,"day":3,"hours":15,"minutes":53,"month":2,"nanos":0,"seconds":59,"time":1553673239000,"timezoneOffset":-480,"year":119}
     * createUserId : B56DA9AEE0DE48F888A3CD7A5491979F
     * creditMaxFee : 0
     * creditRate : 0
     * debitMaxFee : 0
     * debitRate : 0
     * departmentCode : 00010002000100000000000000000000
     * districtId :
     * email :
     * examinePassTime : null
     * examineResult :
     * freezeStatus : 10A
     * id : C643ECA472934D97B95975AFFF336F09
     * idCardNumber :
     * increaseLimitStatus :
     * invoicePrintName :
     * isChange : 0
     * isTuiguang : 1
     * isValid :
     * lastUpdateTime : null
     * level : 1
     * linkPerson :
     * linkPhone : 18257341578
     * maxFee : 0
     * mcc :
     * merchantCnName : 18257341578
     * merchantEnName :
     * merchantMcc :
     * merchantNo : 220573419034783
     * merchantShortName :
     * merchantSource : APP
     * merchantStatus :
     * merchantType :
     * parentPhone : 23210351654
     * passwd : e10adc3949ba59abbe56e057f20f883e
     * phone : 18257341578
     * procedureFee : 1
     * productShort : BFDD
     * provinceId :
     * rate : 0.6S
     * rcexamineResult :
     * reexamineResult :
     * remarks : S0-Q50+5
     * riskControlGrade : 0
     * salesmanId :
     * serviceScopeItem :
     * serviceScopeName :
     * settleCycle : 0
     * settleStatus : 0
     * status : 70A
     * submitExamineTime : null
     * submitReexamineTime : null
     * t0CreditMaxFee : 0
     * t0CreditRate : 0
     * t0DebitMaxFee : 0
     * t0DebitRate : 0
     * taxRegistrationNo :
     * totalMoney : 0
     * useStatus : 10A
     * validTime : null
     * withdrawStatus :
     * wkRate : 0.6
     * ykBalance : 0
     * ykRate : 0.85+1
     */

    private String addrDetail;
    private String agentId;
    private String agentSalesName;
    private String artificialPerson;
    private String artificialPersonCardNumber;
    private String auditRecord;
    private String authCardType;
    private String authTrxType;
    private String bankAccount;
    private String bankAccountName;
    private String bankAccountType;
    private String bankCityId;
    private String bankCode;
    private String bankDetail;
    private String bankDistrictId;
    private String bankDot;
    private String bankProvinceId;
    private int bankSettleType;
    private Object businessLicenseBeginTime;
    private Object businessLicenseEndTime;
    private String businessLicenseNo;
    private String changeReason;
    private String chinaRegionId;
    private String cityId;
    private String companyRegistrationAddress;
    private int convertCount;
    private String coordinate;
    private String corporate;
    private CreateTimeBean createTime;
    private String createUserId;
    private int creditMaxFee;
    private int creditRate;
    private int debitMaxFee;
    private int debitRate;
    private String departmentCode;
    private String districtId;
    private String email;
    private Object examinePassTime;
    private String examineResult;
    private String freezeStatus;
    private String id;
    private String idCardNumber;
    private String increaseLimitStatus;
    private String invoicePrintName;
    private String isChange;
    private int isTuiguang;
    private String isValid;
    private Object lastUpdateTime;
    private String level;
    private String linkPerson;
    private String linkPhone;
    private double maxFee;
    private String mcc;
    private String merchantCnName;
    private String merchantEnName;
    private String merchantMcc;
    private String merchantNo;
    private String merchantShortName;
    private String merchantSource;
    private String merchantStatus;
    private String merchantType;
    private String parentPhone;
    private String passwd;
    private String phone;
    private double procedureFee;
    private String productShort;
    private String provinceId;
    private double rate;
    private String rcexamineResult;
    private String reexamineResult;
    private String remarks;
    private int riskControlGrade;
    private String salesmanId;
    private String serviceScopeItem;
    private String serviceScopeName;
    private int settleCycle;
    private String settleStatus;
    private String status;
    private Object submitExamineTime;
    private Object submitReexamineTime;
    private double t0CreditMaxFee;
    private double t0CreditRate;
    private double t0DebitMaxFee;
    private double t0DebitRate;
    private String taxRegistrationNo;
    private double totalMoney;
    private String useStatus;
    private Object validTime;
    private String withdrawStatus;
    private double wkRate;
    private double ykBalance;
    private String ykRate;
    private String showLevel;
    private double integral;
    private double point;

    public double getIntegral() {
        return integral;
    }

    public void setIntegral(double integral) {
        this.integral = integral;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public void setPoint(int point) {
        this.point = point;
    }



    public String getShowLevel() {
        return showLevel == null ? "" : showLevel;
    }

    public void setShowLevel(String showLevel) {
        this.showLevel = showLevel;
    }

    public double getMaxFee() {
        return maxFee;
    }

    public void setMaxFee(int maxFee) {
        this.maxFee = maxFee;
    }

    public void setMaxFee(double maxFee) {
        this.maxFee = maxFee;
    }

    public double getProcedureFee() {
        return procedureFee;
    }

    public void setProcedureFee(int procedureFee) {
        this.procedureFee = procedureFee;
    }

    public void setProcedureFee(double procedureFee) {
        this.procedureFee = procedureFee;
    }

    public double getT0CreditMaxFee() {
        return t0CreditMaxFee;
    }

    public void setT0CreditMaxFee(int t0CreditMaxFee) {
        this.t0CreditMaxFee = t0CreditMaxFee;
    }

    public void setT0CreditMaxFee(double t0CreditMaxFee) {
        this.t0CreditMaxFee = t0CreditMaxFee;
    }

    public double getT0CreditRate() {
        return t0CreditRate;
    }

    public void setT0CreditRate(int t0CreditRate) {
        this.t0CreditRate = t0CreditRate;
    }

    public void setT0CreditRate(double t0CreditRate) {
        this.t0CreditRate = t0CreditRate;
    }

    public double getT0DebitMaxFee() {
        return t0DebitMaxFee;
    }

    public void setT0DebitMaxFee(double t0DebitMaxFee) {
        this.t0DebitMaxFee = t0DebitMaxFee;
    }

    public double getT0DebitRate() {
        return t0DebitRate;
    }

    public void setT0DebitRate(int t0DebitRate) {
        this.t0DebitRate = t0DebitRate;
    }

    public void setT0DebitRate(double t0DebitRate) {
        this.t0DebitRate = t0DebitRate;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public double getYkBalance() {
        return ykBalance;
    }

    public void setYkBalance(int ykBalance) {
        this.ykBalance = ykBalance;
    }

    public void setYkBalance(double ykBalance) {
        this.ykBalance = ykBalance;
    }

    public String getAddrDetail() {
        return addrDetail;
    }

    public void setAddrDetail(String addrDetail) {
        this.addrDetail = addrDetail;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getAgentSalesName() {
        return agentSalesName;
    }

    public void setAgentSalesName(String agentSalesName) {
        this.agentSalesName = agentSalesName;
    }

    public String getArtificialPerson() {
        return artificialPerson;
    }

    public void setArtificialPerson(String artificialPerson) {
        this.artificialPerson = artificialPerson;
    }

    public String getArtificialPersonCardNumber() {
        return artificialPersonCardNumber;
    }

    public void setArtificialPersonCardNumber(String artificialPersonCardNumber) {
        this.artificialPersonCardNumber = artificialPersonCardNumber;
    }

    public String getAuditRecord() {
        return auditRecord;
    }

    public void setAuditRecord(String auditRecord) {
        this.auditRecord = auditRecord;
    }

    public String getAuthCardType() {
        return authCardType;
    }

    public void setAuthCardType(String authCardType) {
        this.authCardType = authCardType;
    }

    public String getAuthTrxType() {
        return authTrxType;
    }

    public void setAuthTrxType(String authTrxType) {
        this.authTrxType = authTrxType;
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

    public String getBankAccountType() {
        return bankAccountType;
    }

    public void setBankAccountType(String bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    public String getBankCityId() {
        return bankCityId;
    }

    public void setBankCityId(String bankCityId) {
        this.bankCityId = bankCityId;
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

    public String getBankDistrictId() {
        return bankDistrictId;
    }

    public void setBankDistrictId(String bankDistrictId) {
        this.bankDistrictId = bankDistrictId;
    }

    public String getBankDot() {
        return bankDot;
    }

    public void setBankDot(String bankDot) {
        this.bankDot = bankDot;
    }

    public String getBankProvinceId() {
        return bankProvinceId;
    }

    public void setBankProvinceId(String bankProvinceId) {
        this.bankProvinceId = bankProvinceId;
    }

    public int getBankSettleType() {
        return bankSettleType;
    }

    public void setBankSettleType(int bankSettleType) {
        this.bankSettleType = bankSettleType;
    }

    public Object getBusinessLicenseBeginTime() {
        return businessLicenseBeginTime;
    }

    public void setBusinessLicenseBeginTime(Object businessLicenseBeginTime) {
        this.businessLicenseBeginTime = businessLicenseBeginTime;
    }

    public Object getBusinessLicenseEndTime() {
        return businessLicenseEndTime;
    }

    public void setBusinessLicenseEndTime(Object businessLicenseEndTime) {
        this.businessLicenseEndTime = businessLicenseEndTime;
    }

    public String getBusinessLicenseNo() {
        return businessLicenseNo;
    }

    public void setBusinessLicenseNo(String businessLicenseNo) {
        this.businessLicenseNo = businessLicenseNo;
    }

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    public String getChinaRegionId() {
        return chinaRegionId;
    }

    public void setChinaRegionId(String chinaRegionId) {
        this.chinaRegionId = chinaRegionId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCompanyRegistrationAddress() {
        return companyRegistrationAddress;
    }

    public void setCompanyRegistrationAddress(String companyRegistrationAddress) {
        this.companyRegistrationAddress = companyRegistrationAddress;
    }

    public int getConvertCount() {
        return convertCount;
    }

    public void setConvertCount(int convertCount) {
        this.convertCount = convertCount;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getCorporate() {
        return corporate;
    }

    public void setCorporate(String corporate) {
        this.corporate = corporate;
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

    public int getCreditMaxFee() {
        return creditMaxFee;
    }

    public void setCreditMaxFee(int creditMaxFee) {
        this.creditMaxFee = creditMaxFee;
    }

    public int getCreditRate() {
        return creditRate;
    }

    public void setCreditRate(int creditRate) {
        this.creditRate = creditRate;
    }

    public int getDebitMaxFee() {
        return debitMaxFee;
    }

    public void setDebitMaxFee(int debitMaxFee) {
        this.debitMaxFee = debitMaxFee;
    }

    public int getDebitRate() {
        return debitRate;
    }

    public void setDebitRate(int debitRate) {
        this.debitRate = debitRate;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getExaminePassTime() {
        return examinePassTime;
    }

    public void setExaminePassTime(Object examinePassTime) {
        this.examinePassTime = examinePassTime;
    }

    public String getExamineResult() {
        return examineResult;
    }

    public void setExamineResult(String examineResult) {
        this.examineResult = examineResult;
    }

    public String getFreezeStatus() {
        return freezeStatus;
    }

    public void setFreezeStatus(String freezeStatus) {
        this.freezeStatus = freezeStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getIncreaseLimitStatus() {
        return increaseLimitStatus;
    }

    public void setIncreaseLimitStatus(String increaseLimitStatus) {
        this.increaseLimitStatus = increaseLimitStatus;
    }

    public String getInvoicePrintName() {
        return invoicePrintName;
    }

    public void setInvoicePrintName(String invoicePrintName) {
        this.invoicePrintName = invoicePrintName;
    }

    public String getIsChange() {
        return isChange;
    }

    public void setIsChange(String isChange) {
        this.isChange = isChange;
    }

    public int getIsTuiguang() {
        return isTuiguang;
    }

    public void setIsTuiguang(int isTuiguang) {
        this.isTuiguang = isTuiguang;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public Object getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Object lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMerchantCnName() {
        return merchantCnName;
    }

    public void setMerchantCnName(String merchantCnName) {
        this.merchantCnName = merchantCnName;
    }

    public String getMerchantEnName() {
        return merchantEnName;
    }

    public void setMerchantEnName(String merchantEnName) {
        this.merchantEnName = merchantEnName;
    }

    public String getMerchantMcc() {
        return merchantMcc;
    }

    public void setMerchantMcc(String merchantMcc) {
        this.merchantMcc = merchantMcc;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getMerchantShortName() {
        return merchantShortName;
    }

    public void setMerchantShortName(String merchantShortName) {
        this.merchantShortName = merchantShortName;
    }

    public String getMerchantSource() {
        return merchantSource;
    }

    public void setMerchantSource(String merchantSource) {
        this.merchantSource = merchantSource;
    }

    public String getMerchantStatus() {
        return merchantStatus;
    }

    public void setMerchantStatus(String merchantStatus) {
        this.merchantStatus = merchantStatus;
    }

    public String getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
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

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getRcexamineResult() {
        return rcexamineResult;
    }

    public void setRcexamineResult(String rcexamineResult) {
        this.rcexamineResult = rcexamineResult;
    }

    public String getReexamineResult() {
        return reexamineResult;
    }

    public void setReexamineResult(String reexamineResult) {
        this.reexamineResult = reexamineResult;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getRiskControlGrade() {
        return riskControlGrade;
    }

    public void setRiskControlGrade(int riskControlGrade) {
        this.riskControlGrade = riskControlGrade;
    }

    public String getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(String salesmanId) {
        this.salesmanId = salesmanId;
    }

    public String getServiceScopeItem() {
        return serviceScopeItem;
    }

    public void setServiceScopeItem(String serviceScopeItem) {
        this.serviceScopeItem = serviceScopeItem;
    }

    public String getServiceScopeName() {
        return serviceScopeName;
    }

    public void setServiceScopeName(String serviceScopeName) {
        this.serviceScopeName = serviceScopeName;
    }

    public int getSettleCycle() {
        return settleCycle;
    }

    public void setSettleCycle(int settleCycle) {
        this.settleCycle = settleCycle;
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

    public Object getSubmitExamineTime() {
        return submitExamineTime;
    }

    public void setSubmitExamineTime(Object submitExamineTime) {
        this.submitExamineTime = submitExamineTime;
    }

    public Object getSubmitReexamineTime() {
        return submitReexamineTime;
    }

    public void setSubmitReexamineTime(Object submitReexamineTime) {
        this.submitReexamineTime = submitReexamineTime;
    }

    public String getTaxRegistrationNo() {
        return taxRegistrationNo;
    }

    public void setTaxRegistrationNo(String taxRegistrationNo) {
        this.taxRegistrationNo = taxRegistrationNo;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public Object getValidTime() {
        return validTime;
    }

    public void setValidTime(Object validTime) {
        this.validTime = validTime;
    }

    public String getWithdrawStatus() {
        return withdrawStatus;
    }

    public void setWithdrawStatus(String withdrawStatus) {
        this.withdrawStatus = withdrawStatus;
    }

    public double getWkRate() {
        return wkRate;
    }

    public void setWkRate(double wkRate) {
        this.wkRate = wkRate;
    }

    public String getYkRate() {
        return ykRate;
    }

    public void setYkRate(String ykRate) {
        this.ykRate = ykRate;
    }

    public static class CreateTimeBean {
        /**
         * date : 27
         * day : 3
         * hours : 15
         * minutes : 53
         * month : 2
         * nanos : 0
         * seconds : 59
         * time : 1553673239000
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
