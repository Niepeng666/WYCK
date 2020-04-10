package com.linglingyi.com.model;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/3/31
 */
public class ChannelBean implements Serializable {
    private String rescode;
    private List<Channel> acqData;


    public String getRescode() {
        return rescode;
    }

    public void setRescode(String rescode) {
        this.rescode = rescode;
    }

    public List<Channel> getAcqData() {
        if (acqData == null) {
            return new ArrayList<>();
        }
        return acqData;
    }

    public void setAcqData(List<Channel> acqData) {
        this.acqData = acqData;
    }

    public static class Channel implements Serializable {
        private String channelName;
        private String tag = "1";
        private String acqMerchantNo;//行业编号，落地时可使用.选择商户后写入的。
        private String remark;//结算笔数费
        private String T0date;//刷卡时间
        private String rate;//费率
        private String acqcode;//通道编号
        private String limit;//刷卡单笔额度
        private String isld;//无卡返回，通道是否落地，1落地，0非落地
        private String number;//无卡返回，落地通道是传商户编号，还是商户简称，1商户编号，2商户简称，isld为0时忽略次值

        private String isluodi;//养卡返回
        private String iszixuan;//养卡返回isluodi，iszixuan都为1时开启自选
        /**
         * 判断是否选中
         */
        private boolean isCheck;
        /**
         * 通道是否开通
         */
        private String status;
        /**
         * 不支持的银行
         */
        private String noBank;
        /**
         * 限额
         */
        private String quota;
        /**
         * 是否是多通道
         */
        private boolean channels;
        /**
         * 大小额 10A 混合10B
         */
        private String channelType;

        public String getChannelType() {
            return channelType == null ? "" : channelType;
        }

        public void setChannelType(String channelType) {
            this.channelType = channelType;
        }

        public boolean isChannels() {
            return channels;
        }

        public void setChannels(boolean channels) {
            this.channels = channels;
        }

        public String getStatus() {
            return status == null ? "" : status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getNoBank() {
            return noBank == null ? "" : noBank;
        }

        public void setNoBank(String noBank) {
            this.noBank = noBank;
        }

        public String getQuota() {
            return quota == null ? "" : quota;
        }

        public void setQuota(String quota) {
            this.quota = quota;
        }

        public boolean isCheck() {
            return isCheck;
        }


        public void setCheck(boolean check) {
            isCheck = check;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getAcqMerchantNo() {
            return TextUtils.isEmpty(acqMerchantNo) ? "" : acqMerchantNo;
        }

        public void setAcqMerchantNo(String acqMerchantNo) {
            this.acqMerchantNo = acqMerchantNo;
        }

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getT0date() {
            return T0date;
        }

        public void setT0date(String t0date) {
            T0date = t0date;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getAcqcode() {
            return acqcode;
        }

        public void setAcqcode(String acqcode) {
            this.acqcode = acqcode;
        }


        public String getIsld() {
            return isld;
        }

        public void setIsld(String isld) {
            this.isld = isld;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }


        public String getLimit() {
            return limit;
        }

        public void setLimit(String limit) {
            this.limit = limit;
        }

        public String getIsluodi() {
            return isluodi;
        }

        public void setIsluodi(String isluodi) {
            this.isluodi = isluodi;
        }

        public String getIszixuan() {
            return iszixuan;
        }

        public void setIszixuan(String iszixuan) {
            this.iszixuan = iszixuan;
        }
    }
}
