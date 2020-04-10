package com.linglingyi.com.utils;

import com.linglingyi.com.MyApplication;

public class Constant {
    public static final int IMAGE_PICKER = 100;//拍照

    public static final String mainKey = "21E4ACD4CD5D4619B063F40C5A454F7D";

    public static final String BASE_IP = "http://futengyijia.llyzf.cn";


//    public static final String BASE_IP = "http://192.168.3.117";

    /**
     * 服务器IP和端口
     * http://futengyijia.llyzf.cn:6442/lly-posp-proxy/wyck.apk
     */
    public static final String BASE_URL = BASE_IP + ":6442";


//    public static final String REQUEST_API = BASE_URL + "/lly-posp-proxy-demo/request.app?";
    /**
     * 服务器基础请求的项目和请求方法
     */
    public static final String REQUEST_API = BASE_URL + "/lly-posp-proxy/request.app?";
    /**
     * 服务器图片上传的请求的项目和请求方法
     */
    public static final String UPLOADIMAGE = BASE_URL + "/lly-posp-proxy/uploadImage.app?";

    /**
     * 三个通道说明
     */
    public static final String REQUEST_IMAGE = BASE_IP + ":80/";
    /**
     * 版本编号，要和服务器的保持一致。格式：产品名+A+版本号
     */
    public static String VERSION = "WYCK-A-" + AppUtils.packageName(MyApplication.applicationContext);


    /**
     * 一级APP代理商编号，根据具体情况判断是否输入
     */
    public static String AGENCY_CODE44 = "1800012";
    /**
     * APP下载地址
     */
    public static String DOWNLOAD_APK = Constant.BASE_URL + "/wyck.apk";

    public static String SHARE_LOGO = BASE_IP + "/down/wyck/ic_launcher.png";
    public static String product_name = "WYCK";

    /**
     * 启动图片地址
     */
    public static String LAUNCH_IAMGE = Constant.BASE_URL + "/lly-posp-proxy/resources/images/index.png";
    /**
     * 注册地址
     */
    public static String register_url = Constant.BASE_IP + "/wyck/register_protocol.html";

    /**
     * 操作说明
     */
    public static String protocol_url = "https://b.eqxiu.com/s/6mTctQAe";

    /**
     * 卡测评协议
     */
    public static String card_score_url = BASE_IP + "/wyck/kcp_protocol.html";
    public static String online_service = "https://tb.53kf.com/code/client/d7c63f99499a44203050d36c8ea4b95b3/1";
    public static String honor_url = BASE_IP + "/wyck/zx_protocol.html";

    public static String VOICE_NOTICE = "HI,我是您的智能代还助理，将帮助您规划预留还款计划，请告诉我您要的计划安排。请按照以下文字进行语音输入：\n金额：2000\n开始时间：2019年X月X日\n结束时间：2019年X月X日\n地区：浙江省台州市";

    public static String Secret = "http://120.77.156.217/living_privacy_policy.html";
    public static String descrition = "http://120.77.156.217/confirmation_%20applicant_%20qualificatio.html";
}
