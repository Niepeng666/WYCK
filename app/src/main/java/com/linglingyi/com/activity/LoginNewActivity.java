package com.linglingyi.com.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.linglingyi.com.event.AuthEvent;
import com.linglingyi.com.utils.Constant;
import com.linglingyi.com.utils.LogUtil;
import com.linglingyi.com.utils.PermissionsUtils;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.AutoControlModel;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.ImageTypeModel;
import com.linglingyi.com.model.UserInfoModel;
import com.linglingyi.com.utils.AppUtils;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.DataCleanManager;
import com.linglingyi.com.utils.FileUtils;
import com.linglingyi.com.utils.KeyBoardUtils;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.StorageAppInfoUtil;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.viewone.dialog.TipDialog;
import com.linglingyi.com.viewone.dialog.UpdateDialog;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import okhttp3.Request;

/**
 * @作者 chenlanxin
 * @创建日期 2019/2/10 16:02
 * @功能 登录
 **/
public class LoginNewActivity extends BaseActivity {

    Dialog checkDialog;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.iv_txt_clear)
    ImageView ivTxtClear;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.iv_pass_show)
    ImageView ivPassShow;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_forget_pass)
    TextView tvForgetPass;


    private Dialog Disabledialog;
    private boolean isPassclose;
    private String phone, password;
    private String checkvalue1;
    private String qQname;

    @OnClick({R.id.tv_forget_pass, R.id.btn_login, R.id.iv_txt_clear,
            R.id.tv_register, R.id.iv_pass_show, R.id.iv_login_wechat, R.id.iv_login_qq})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_txt_clear:
                etPhone.setText("");
                break;
            case R.id.tv_forget_pass:
                startActivityForResult(new Intent(context, ForgetPassNewActivity.class), 1);
                break;
            case R.id.btn_login:
//                goMainActivity();
                KeyBoardUtils.hideKeyboard(view);
                phone = etPhone.getText().toString().trim();
                password = etPass.getText().toString().trim();
                if (StringUtil.isEmpty(phone)) {
                    ViewUtils.makeToast(context, "请输入手机号", 1000);
                    return;
                }
                if (StringUtil.isEmpty(password)) {
                    ViewUtils.makeToast(context, "请输入密码", 1000);
                    return;
                }
                if (phone.length() != 11) {
                    ViewUtils.makeToast(context, "请输入正确的手机号", 1000);
                    return;
                }
                if (checkbox.isChecked()) {
                    StorageAppInfoUtil.putInfo(context, "rememberPass", "1");
                } else {
                    StorageAppInfoUtil.removeKey("rememberPass", context);
                }

                loginPhone(phone, password);
                break;

            case R.id.tv_register:
                startActivity(new Intent(context, RegisterNewActivity.class));
                break;
            case R.id.iv_pass_show:
                String password1 = etPass.getText().toString().trim();
                if (StringUtil.isEmpty(password1)) {
                    return;
                }
                if (isPassclose) {
                    isPassclose = false;
                    ivPassShow.setImageResource(R.drawable.pass_show);
                    etPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    isPassclose = true;
                    ivPassShow.setImageResource(R.drawable.pass_close);
                    etPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                etPass.setSelection(password1.length());
                break;
            case R.id.iv_login_qq:
                // TODO: 2019/10/17 qq联合登录
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                authorize(qq);
                break;
            case R.id.iv_login_wechat:
                // TODO: 2019/10/17联合登录
                Platform weChat = ShareSDK.getPlatform(Wechat.NAME);
                authorize(weChat);
                break;
            default:
                break;
        }
    }

    private void loginPhone(String phone, String password) {
        HttpParams map = OkClient.getParamsInstance().getParams();
        map.put("1", phone);
        map.put("8", CommonUtils.Md5(password));
        map.put("3", "190928");
        login(map);
    }

    /**
     * 登录
     *
     * @param map
     */
    private void login(HttpParams map) {
        loadingDialog.show();

        OkClient.getInstance().post(map, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                loadingDialog.dismiss();
                BaseEntity baseEntity = response.body();
                if (baseEntity == null) {
                    return;
                }
                String result = baseEntity.getStr39();
                if ("00".equals(result) || "W8".equals(result)) {
                    List<UserInfoModel> list = JSONArray.parseArray(baseEntity.getStr42(), UserInfoModel.class);
                    if (list == null || list.size() == 0) {
                        return;
                    }
                    UserInfoModel userInfoModel = list.get(0);
                    saveOtherData(baseEntity);
                    AutoControlModel autoControlModel = JSONObject.parseObject(baseEntity.getStr23(), AutoControlModel.class);
                    saveAuthControlData(autoControlModel);

                    if (StringUtil.isEmpty(userInfoModel.getPhone())) {
                        startActivity(new Intent(context, LoginBindPhoneActivity.class).putExtra("customerNum", userInfoModel.getMerchantNo()));
                        return;
                    }
                    saveUserData(userInfoModel, result);
                    if ("10B".equals(userInfoModel.getUseStatus())) {
                        showDangerDialog();
                        return;
                    }
                    StorageCustomerInfo02Util.putInfo(context, "phoneNum", phone);
                    StorageCustomerInfo02Util.putInfo(context, "passwd", password);
                    if ("W8".equals(result)) {
                        // : 2019/4/16 审核不通过
//                        RPSDK.initialize(LoginNewActivity.this);
                        checkDialog();
                    } else {
                        if (!TextUtils.isEmpty(baseEntity.getStr44())) {
                            String constant = baseEntity.getStr44();
                            String version = constant.split("-")[2];
                            int newVerCode = Integer.parseInt(version.replace(".", ""));
                            int curVerCode = Integer.parseInt(AppUtils.packageName(context).replace(".", ""));
                            if (newVerCode > curVerCode) {
                                UpdateDialog.getInstance(false, baseEntity.getStr45()).show(getSupportFragmentManager(), "update");
                            } else {
                                goMainActivity();
                            }
                        } else {
                            goMainActivity();
                        }
                    }
                } else if ("ZV".equals(result)) {
                    // : 2019/3/27 强制版本更新
                    StorageCustomerInfo02Util.putInfo(context, "apkUrl", baseEntity.getStr47());
                    UpdateDialog.getInstance(true, baseEntity.getStr45()).show(getSupportFragmentManager(), "update");
                } else {
                    ViewUtils.makeToast(context, result, 500);
                }

            }
        });
    }

    /**
     *在线商城，龙虎榜，签到，酷友圈，商学院后台控制
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
     * 进入主页面
     */
    private void goMainActivity() {
        Intent intent_start = new Intent();
        intent_start.setClass(context, HomeNewActivity.class);
        intent_start.putExtra("fromLogin", true);
        startActivity(intent_start);
        ViewUtils.overridePendingTransitionComeFinish(context);
    }

    /**
     * 危险账号弹框
     */
    private void showDangerDialog() {
        Disabledialog = ViewUtils.showChoseDialog(context, false, "风险账号，暂被停用", View.GONE, new ViewUtils.OnChoseDialogClickCallback() {
            @Override
            public void clickOk() {
                Disabledialog.dismiss();
            }

            @Override
            public void clickCancel() {
            }
        });
        Disabledialog.show();
    }

    /**
     * 风控审核提示框
     */
    private void checkDialog() {
        TipDialog tipDialog = TipDialog.getInstance("商户资料审核不通过\n请重新提交", "recertification");
        tipDialog.show(getSupportFragmentManager(), "recertification");
    }

    /**
     * 保存其他信息
     *
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
        StorageCustomerInfo02Util.putInfo(context, "apkUrl", model.getStr47());
        List<ImageTypeModel> list = JSONArray.parseArray(model.getStr57(), ImageTypeModel.class);
        for (ImageTypeModel item :
                list) {
            StorageCustomerInfo02Util.putInfo(context, "infoImageUrl_" + item.getType(), item.getImageUrl());
        }

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
        StorageCustomerInfo02Util.putInfo(context, "bankDetail", item.getBankDetail());
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

    private void authorize(final Platform plat) {
        if (plat == null) {
            return;
        }
        //判断指定平台是否已经完成授权
        if (plat.isAuthValid()) {
            String userId = plat.getDb().getUserId();
            if (userId != null) {
                LogUtils.i("name=" + plat.getName() + ",userId=" + userId);
//                loginWeixin(plat.getDb());
                plat.removeAccount(true);
            }
        }
        plat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                LogUtils.i("onComplete,");
                if (i == Platform.ACTION_USER_INFOR) {
                    final PlatformDb platDB = platform.getDb();
                    //通过DB获取各种数据
                    platDB.getToken();
                    platDB.getUserGender();
                    platDB.getUserIcon();
                    platDB.getUserId();
                    platDB.getUserName();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loginWeixin(platDB);
                        }
                    });
                }
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                //删除可能的授权缓存数据
                plat.removeAccount(true);
            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });
        // true不使用SSO授权，false使用SSO授权
        plat.SSOSetting(false);
        //获取用户资料
        plat.showUser(null);
    }

    /**
     * qq/微信联合登录
     *
     * @param platDB
     */
    private void loginWeixin(PlatformDb platDB) {
//        HttpParams map = OkClient.getParamsInstance().getParams();
//        map.put("3", "190928");
//        map.put("5",platDB.getUserName() );
//        map.put("6", platDB.getUserIcon());
//        if (platDB.getPlatformNname().equals(QQ.NAME)) {
//            map.put("10", platDB.getUserId());
//        } else {
//            map.put("11", platDB.getUserId());
//        }

   //     login(map);



        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "190928");
        httpParams.put("5",platDB.getUserName() );
        httpParams.put("6", platDB.getUserIcon());
        if (platDB.getPlatformNname().equals(QQ.NAME)) {
            httpParams.put("10", platDB.getUserId());
        } else {
            httpParams.put("11", platDB.getUserId());
        }
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                BaseEntity model = response.body();



                BaseEntity baseEntity = response.body();
                if (baseEntity == null) {
                    return;
                }
                String result = baseEntity.getStr39();
                if ("00".equals(result) || "W8".equals(result)) {
                    List<UserInfoModel> list = JSONArray.parseArray(baseEntity.getStr42(), UserInfoModel.class);
                    if (list == null || list.size() == 0) {
                        return;
                    }
                    UserInfoModel userInfoModel = list.get(0);
                    saveOtherData(baseEntity);
                    AutoControlModel autoControlModel = JSONObject.parseObject(baseEntity.getStr23(), AutoControlModel.class);
                    saveAuthControlData(autoControlModel);

                    if (StringUtil.isEmpty(userInfoModel.getPhone())) {
                        startActivity(new Intent(context, LoginBindPhoneActivity.class).putExtra("customerNum", userInfoModel.getMerchantNo()));
                        return;
                    }
                    saveUserData(userInfoModel, result);
                    if ("10B".equals(userInfoModel.getUseStatus())) {
                        showDangerDialog();
                        return;
                    }
                    StorageCustomerInfo02Util.putInfo(context, "phoneNum", phone);
                    StorageCustomerInfo02Util.putInfo(context, "passwd", password);
                    if ("W8".equals(result)) {
                        // : 2019/4/16 审核不通过
//                        RPSDK.initialize(LoginNewActivity.this);
                        checkDialog();
                    } else {
                        if (!TextUtils.isEmpty(baseEntity.getStr44())) {
                            String constant = baseEntity.getStr44();
                            String version = constant.split("-")[2];
                            int newVerCode = Integer.parseInt(version.replace(".", ""));
                            int curVerCode = Integer.parseInt(AppUtils.packageName(context).replace(".", ""));
                            if (newVerCode > curVerCode) {
                                UpdateDialog.getInstance(false, baseEntity.getStr45()).show(getSupportFragmentManager(), "update");
                            } else {
                                goMainActivity();
                            }
                        } else {
                            goMainActivity();
                        }
                    }
                } else if ("ZV".equals(result)) {
                    // : 2019/3/27 强制版本更新
                    StorageCustomerInfo02Util.putInfo(context, "apkUrl", baseEntity.getStr47());
                    UpdateDialog.getInstance(true, baseEntity.getStr45()).show(getSupportFragmentManager(), "update");
                } else {
                    ViewUtils.makeToast(context, result, 500);
                }



            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        etPass.setText("");
        switch (requestCode) {
            case 0:
                etPhone.setText(data.getStringExtra("phone"));
                break;
            case 1:
                etPhone.setText(data.getStringExtra("phone"));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        PermissionsUtils.requestPermissionselect(this);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_new_login;
    }

    @Override
    public void initData() {
        String phoneNum = StorageCustomerInfo02Util.getInfo("phoneNum", this);
        String password = StorageCustomerInfo02Util.getInfo("passwd", this);
        if (!StringUtil.isEmpty(phoneNum)) {
            etPhone.setText(phoneNum);
            etPhone.setSelection(phoneNum.length());
        }
//        String isRememberPass = StorageAppInfoUtil.getInfo("rememberPass", context);
//         && "1".equals(isRememberPass)
        if (!StringUtil.isEmpty(password)) {
            etPass.setText(password);
        }
//        if (StringUtil.isEmpty(isRememberPass)) {
//            checkbox.setChecked(false);
//        }
        deleteCache();
        isShowConfirmButton();
        initTextChangedListener();
    }

    /**
     * 清除安装包
     */
    private void deleteCache() {
        DataCleanManager.clearAllCache(context);
    }

    private void initTextChangedListener() {
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                etPass.setText("");
            }
        });

        etPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                isShowConfirmButton();
            }
        });


    }

    private void isShowConfirmButton() {
        String pwdValue = etPass.getText().toString().trim();
        String phoneNum = etPhone.getText().toString().trim();
        if (phoneNum.length() >= 1) {
            ivTxtClear.setVisibility(View.VISIBLE);
        } else {
            ivTxtClear.setVisibility(View.INVISIBLE);
        }
        ivPassShow.setVisibility(pwdValue.length() > 0 ? View.VISIBLE : View.GONE);
    }
}
