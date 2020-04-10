package com.linglingyi.com.activity;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.zxing.common.StringUtils;
import com.linglingyi.com.MyApplication;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.AutoControlModel;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.ImageModel;
import com.linglingyi.com.model.ImageTypeModel;
import com.linglingyi.com.model.UserInfoModel;
import com.linglingyi.com.utils.AppUtils;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.Constant;
import com.linglingyi.com.utils.FileUtils;
import com.linglingyi.com.utils.GlideUtils;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.PermissionUtil;
import com.linglingyi.com.utils.StorageAppInfoUtil;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.wuyouchuangke.com.R;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class LaunchActivity extends BaseActivity implements Animation.AnimationListener {

    /**
     * 设置缩放动画
     */
    final ScaleAnimation animation = new ScaleAnimation(1.0f, 1.0f, 1.0f, 1.0f,
            Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f);
    @BindView(R.id.iv_launch)
    ImageView ivLaunch;

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        String phone = StorageCustomerInfo02Util.getInfo("phoneNum", context);
        String pass = StorageCustomerInfo02Util.getInfo("passwd", context);
        LogUtils.i("phone=" + phone + "pass=" + phone);
        String isRememberPass = StorageAppInfoUtil.getInfo("rememberPass", context);
        if (StringUtil.isEmpty(phone) || StringUtil.isEmpty(isRememberPass)) {
            goLoginActivity();
        } else {
            loadLoginData(phone, pass);
        }
    }

    /**
     * 进入登录页
     */
    private void goLoginActivity() {
        Intent intent = new Intent();
        intent.setClass(this, LoginNewActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    /**
     * 登录接口
     *
     * @param phone
     * @param pass
     */
    private void loadLoginData(String phone, String pass) {
        HttpParams map = OkClient.getParamsInstance().getParams();
        if (phone.length() == 11) {
            map.put("1", phone);
            map.put("8", CommonUtils.Md5(pass));
            map.put("41", "");
            map.put("42", "");
            map.put("12", "M");
        }
        map.put("3", "190928");

        OkClient.getInstance().post(map, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                goLoginActivity();
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                BaseEntity baseEntity = response.body();
                if (baseEntity == null) {
                    goLoginActivity();
                    return;
                }
                String result = baseEntity.getStr39();
                if ("00".equals(result)) {
                    List<UserInfoModel> list = JSONArray.parseArray(baseEntity.getStr42(), UserInfoModel.class);
                    if (list == null || list.size() == 0) {
                        goLoginActivity();
                        return;
                    }
                    UserInfoModel userInfoModel = list.get(0);
                    if ("10B".equals(userInfoModel.getUseStatus())) {
                        goLoginActivity();
                        return;
                    }
                    AutoControlModel autoControlModel = JSONObject.parseObject(baseEntity.getStr23(), AutoControlModel.class);
                    saveAuthControlData(autoControlModel);
                    saveUserData(userInfoModel, result);
                    saveOtherData(baseEntity);
                    if ("W8".equals(result)) {
                        // : 2019/4/16 审核不通过
                        goLoginActivity();
                    } else {
                        if (!TextUtils.isEmpty(baseEntity.getStr44())) {
                            String constant = baseEntity.getStr44();
                            String version = constant.split("-")[2];
                            int newVerCode = Integer.parseInt(version.replace(".", ""));
                            int curVerCode = Integer.parseInt(AppUtils.packageName(context).replace(".", ""));
                            if (newVerCode > curVerCode) {
                                goLoginActivity();
                            } else {
                                goMainActivity();
                            }
                        } else {
                            goMainActivity();
                        }
                    }
                } else {
                    goLoginActivity();
                }

            }
        });
    }

    /**
     * 在线商城，龙虎榜，签到，酷友圈，商学院后台控制
     *
     * @param autoControlModel
     */
    private void saveAuthControlData(AutoControlModel autoControlModel) {
        StorageCustomerInfo02Util.putInfo(context, "bk", autoControlModel.getBk());
        StorageCustomerInfo02Util.putInfo(context, "dk", autoControlModel.getDk());
        StorageCustomerInfo02Util.putInfo(context, "bx", autoControlModel.getBx());
        StorageCustomerInfo02Util.putInfo(context, "kcp", autoControlModel.getKcp());
        StorageCustomerInfo02Util.putInfo(context, "zx", autoControlModel.getZx());
        StorageCustomerInfo02Util.putInfo(context, "sc", autoControlModel.getSc());
        StorageCustomerInfo02Util.putInfo(context, "lz", autoControlModel.getLz());
        StorageCustomerInfo02Util.putInfo(context, "kyq", autoControlModel.getKyq());
        StorageCustomerInfo02Util.putInfo(context, "kf", autoControlModel.getKf());
        StorageCustomerInfo02Util.putInfo(context, "zb", autoControlModel.getZb());
        StorageCustomerInfo02Util.putInfo(context, "sxy", autoControlModel.getSxy());
        StorageCustomerInfo02Util.putInfo(context, "qd", autoControlModel.getQd());
        StorageCustomerInfo02Util.putInfo(context, "lhb", autoControlModel.getLhb());
        StorageCustomerInfo02Util.putInfo(context, "jf", autoControlModel.getJf());
    }

    /**
     * 保存商户数据
     *
     * @param item
     * @param result
     */
    private void saveUserData(UserInfoModel item, String result) {
        StorageCustomerInfo02Util.putInfo(context, "merchantId", item.getId());
        StorageCustomerInfo02Util.putInfo(context, "customerNum", item.getMerchantNo());

        StorageCustomerInfo02Util.putInfo(context, "level", StringUtil.stringToInt(item.getLevel()));
        StorageCustomerInfo02Util.putInfo(context, "merchantCnName", item.getMerchantCnName());
        StorageCustomerInfo02Util.putInfo(context, "bankAccount", item.getBankAccount());
        StorageCustomerInfo02Util.putInfo(context, "bankAccountName", item.getBankAccountName());
        StorageCustomerInfo02Util.putInfo(context, "idCardNumber", item.getIdCardNumber());
        StorageCustomerInfo02Util.putInfo(context, "bankDetail", MyApplication.getBankName(item.getBankCode()));
        StorageCustomerInfo02Util.putInfo(context, "bankCode", item.getBankCode());
        StorageCustomerInfo02Util.putInfo(context, "phone", item.getPhone());
        StorageCustomerInfo02Util.putInfo(context, "source", item.getMerchantSource());
        StorageCustomerInfo02Util.putInfo(context, "useStatus", item.getUseStatus());
        StorageCustomerInfo02Util.putInfo(context, "parentPhone", item.getParentPhone());
        JPushInterface.setAlias(context, 1, item.getMerchantNo());

//10A 未审核，10B 审核通过，10C 审核拒绝，10D 重新审核
        String freezeStatus = item.getFreezeStatus();
        StorageCustomerInfo02Util.putInfo(context, "freezeStatus", freezeStatus);
        String examineResult = item.getRcexamineResult();
        if ("W8".equals(result)) {
            StorageCustomerInfo02Util.putInfo(context, "examineResult", examineResult);
            //审核状态
            StorageCustomerInfo02Util.putInfo(context, "examineState", "W8");
        }
    }

    /**
     * @param
     */
    private void saveOtherData(BaseEntity model) {
        StorageCustomerInfo02Util.putInfo(context, "redMoney", model.getStr17());
        StorageCustomerInfo02Util.putInfo(context, "serviceNumber", model.getStr18());
        StorageCustomerInfo02Util.putInfo(context, "scoreCost", model.getStr19());
        StorageCustomerInfo02Util.putInfo(context, "honorCost", model.getStr20());
        StorageCustomerInfo02Util.putInfo(context, "wechat", model.getStr17());
        StorageCustomerInfo02Util.putInfo(context, "level2Cost", model.getStr21());
        StorageCustomerInfo02Util.putInfo(context, "level3Cost", model.getStr22());

        List<ImageTypeModel> list = JSONArray.parseArray(model.getStr57(), ImageTypeModel.class);
        for (ImageTypeModel item :
                list) {
            StorageCustomerInfo02Util.putInfo(context, "infoImageUrl_" + item.getType(), item.getImageUrl());
        }

    }

    /**
     * 进入主页面
     */
    private void goMainActivity() {
        Intent intent_start = new Intent();
        intent_start.setClass(context, HomeNewActivity.class);
        intent_start.putExtra("fromLogin", true);
        startActivity(intent_start);
        ViewUtils.overridePendingTransitionComeFinish(context);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PermissionUtil.ALL:
                int i = 0;
                for (i = 0; i < grantResults.length; i++) {
                    boolean isTip = ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i]);
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        if (isTip) {
                            //表明用户没有彻底禁止弹出权限请求
                            ViewUtils.makeToast(context, "请赋予应用该权限", 1000);
                            PermissionUtil.ALL(context);
                        } else {//表明用户已经彻底禁止弹出权限请求
                            if (context != null && !context.isFinishing()) {
                                new AlertDialog.Builder(context)
                                        .setMessage("请赋予应用权限,否则可能会导致未知错误,赋予权限之后,请重新打开应用！")
                                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                openSetting(context);
                                            }
                                        }).show();
                            }
                        }
                        return;
                    }
                }
                int length = permissions.length;
                if (i == length) {
                    initData();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 打开设置
     */
    public void openSetting(Context mContext) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + mContext.getPackageName()));
        mContext.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_launch;
    }

    @Override
    public void initData() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            showImage();
        } else {
            if (PermissionUtil.ALL(context)) {
                showImage();
            }
        }

    }
//        Glide.with(context).load(Constant.LAUNCH_IAMGE).asBitmap().into(new SimpleTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                try {
//                    FileUtils.saveFile(resource, FileUtils.getAppExternalFilePath(context, "ftyj") + File.separator + "launch.jpg");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

    private void showImage() {
//        String filepath = FileUtils.getAppExternalFilePath(context, "ftyj") + File.separator + "launch.jpg";
//        File file = new File(filepath);
//        if (file.exists()) {
//            Bitmap bm = BitmapFactory.decodeFile(filepath);
//            ivLaunch.setImageBitmap(bm);
//        } else {
       // ivLaunch.setImageResource(R.drawable.launch);福滕壹家
        ivLaunch.setImageResource(R.drawable.output_logo);//无忧创客
//    }
        animation.setDuration(2000);
        ivLaunch.startAnimation(animation);
        /** 开始动画 */
//        animation.startNow();
        animation.setAnimationListener(this);
    }


}
