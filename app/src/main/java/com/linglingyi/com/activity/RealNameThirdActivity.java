package com.linglingyi.com.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.RealPersonModel;
import com.linglingyi.com.utils.BitmapManage;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.Constant;
import com.linglingyi.com.utils.GlideUtils;
import com.linglingyi.com.utils.PermissionUtil;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @作者 chenlanxin
 * @创建日期 2019/10/10 17:11
 * @功能 实名认证手持身份证
 **/
public class RealNameThirdActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_people)
    ImageView ivPeople;

    private String handPic;
    private boolean isInfoComplete;
    private RealPersonModel realPersonModel;


    @Override
    public int initLayout() {
        return R.layout.activity_real_name_third;
    }

    @Override
    public void initData() {
        tvTitle.setText("实名认证");
        realPersonModel = (RealPersonModel) getIntent().getSerializableExtra("realPersonModel");
        isInfoComplete = getIntent().getBooleanExtra("isInfoComplete", false);

        if (isInfoComplete) {
            handPic = StorageCustomerInfo02Util.getInfo("infoImageUrl_10M", context);
            if (!StringUtil.isEmpty(handPic))
                GlideUtils.loadNoChacheImage(context, handPic, ivPeople);
        }
    }

    @OnClick({R.id.iv_back, R.id.iv_people, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_people:
                if (!PermissionUtil.CAMERA(context)) {
                    return;
                }
                startActivityForResult(new Intent(context, ImageGridActivity.class), 1);
                break;
            case R.id.btn_next:
                if (StringUtil.isEmpty(handPic)) {
                    ViewUtils.makeToast(context, "请先上传手持身份证", 1000);
                    return;
                }
                admit();
                break;
        }
    }

    /**
     * 提交信息
     */
    private void admit() {
        loadingDialog.show();
        HttpParams map = OkClient.getParamsInstance().getParams();
        map.put("1", realPersonModel.getPhone());
        map.put("2", getMerId());
        map.put("5", realPersonModel.getName());
        map.put("6", realPersonModel.getIdcard());
        map.put("7", realPersonModel.getBankAccount());
        map.put("43", realPersonModel.getBankCode());
        map.put("8",realPersonModel.getProvinceId());
        map.put("9",realPersonModel.getCityId());
        map.put("10",realPersonModel.getDistinctId());
        map.put("3", "190938");

        OkClient.getInstance().post(map, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                if ("00".equals(response.body().getStr39())) {
                    StorageCustomerInfo02Util.putInfo(context, "merchantCnName", realPersonModel.getName());
                    StorageCustomerInfo02Util.putInfo(context, "bankAccount", realPersonModel.getBankAccount());
                    StorageCustomerInfo02Util.putInfo(context, "bankAccountName", realPersonModel.getName());
                    StorageCustomerInfo02Util.putInfo(context, "idCardNumber", realPersonModel.getIdcard());
                    StorageCustomerInfo02Util.putInfo(context, "bankDetail", realPersonModel.getBankName());
                    StorageCustomerInfo02Util.putInfo(context, "phone", realPersonModel.getPhone());
                    StorageCustomerInfo02Util.putInfo(context, "bankCode", realPersonModel.getBankCode());
                    ViewUtils.makeToast2(context,
                            "信息已提交，请重新登录", 500, LoginNewActivity.class,
                            "REAL");
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data == null)
                return;
            ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            if (requestCode == 1) {
                uploadImage(BitmapManage.bitmapToString(images.get(0).path, 600, 600), "10M");
            } else {
                ViewUtils.makeToast(context, "没有数据", 500);
            }
        }
    }

    private void uploadImage(String signPath, String imageType) {
        // 检查网络状态
        if (CommonUtils.getConnectedType(context) == -1) {
            ViewUtils.makeToast(context,
                    getString(R.string.nonetwork), 1500);
            return;
        }
        loadingDialog.show();
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "190948");
        httpParams.put("9", imageType);
        httpParams.put("42", getMerNo());
        httpParams.put("40", signPath);
        OkClient.getInstance().post(Constant.UPLOADIMAGE, httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
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
                if ("00".equals(model.getStr39())) {
                    // 上传成功
                    String imageUrl = model.getStr57();
                    StorageCustomerInfo02Util.putInfo(context, "infoImageUrl_10M", imageUrl);
                    handPic = imageUrl;
                    GlideUtils.loadImage(context, handPic, ivPeople);
                }
            }
        });

    }
}
