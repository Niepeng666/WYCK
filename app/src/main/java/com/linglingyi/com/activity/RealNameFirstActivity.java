package com.linglingyi.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.Area;
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
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @作者 chenlanxin
 * @创建日期 2019/10/10 16:02
 * @功能 实名认证拍摄身份证
 **/
public class RealNameFirstActivity extends BaseActivity {
    private static final int SELECTAREA = 2;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_name)
    TextView etName;
    @BindView(R.id.et_idcard)
    EditText etIdcard;
    @BindView(R.id.tv_customer_status_desc)
    TextView tvCustomerStatusDesc;
    @BindView(R.id.ll_customer_status)
    LinearLayout llCustomerStatus;
    @BindView(R.id.tv_checked_info)
    TextView tvCheckedInfo;
    @BindView(R.id.ll_checked_info)
    LinearLayout llCheckedInfo;
    @BindView(R.id.iv_id_card_a)
    ImageView ivId1;
    @BindView(R.id.iv_id_card_b)
    ImageView ivId2;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.rl_id_card_a)
    RelativeLayout rlIdCardA;
    @BindView(R.id.rl_id_card_b)
    RelativeLayout rlIdCardB;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.ly_area)
    LinearLayout lyArea;
    @BindView(R.id.btn_next)
    Button btnNext;

    private boolean isInfoComplete;
    private String idcardUrl_a;
    private String idcardUrl_b;
    private Map<String, Area> map = null;

    @OnClick({R.id.iv_back, R.id.rl_id_card_a, R.id.rl_id_card_b, R.id.btn_next, R.id.ly_area})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_id_card_a:
                if (!PermissionUtil.CAMERA(context)) {
                    return;
                }
                startActivityForResult(new Intent(context, ImageGridActivity.class), 1);
                break;
            case R.id.rl_id_card_b:
                if (!PermissionUtil.CAMERA(context)) {
                    return;
                }
                startActivityForResult(new Intent(context, ImageGridActivity.class), 2);
                break;
            case R.id.btn_next:
                String name = etName.getText().toString();
                String idcard = etIdcard.getText().toString();

                if (StringUtil.isEmpty(name)) {
                    ViewUtils.makeToast(context, "姓名不能为空", 1000);
                    return;
                }
                if (StringUtil.isEmpty(idcard)) {
                    ViewUtils.makeToast(context, "身份证号不能为空", 1000);
                    return;
                }
                if (StringUtil.isEmpty(idcardUrl_a) || StringUtil.isEmpty(idcardUrl_b)) {
                    ViewUtils.makeToast(context, "请上传身份证正反面", 1000);
                    return;
                }
                if (!CommonUtils.isIdCard(idcard)) {
                    ViewUtils.makeToast(context, "请输入正确的身份证号", 1000);
                    return;
                }
                if (map == null) {
                    ViewUtils.makeToast(context, "请选择地区", 1000);
                    return;
                }
                Area area = map.get("city");
                if (area == null) {
                    ViewUtils.makeToast(context, "请选择地区", 1000);
                    return;
                }
                String cityId = area.getId();
                if (StringUtil.isEmpty(cityId)) {
                    ViewUtils.makeToast(context, "请选择地区", 1000);
                    return;
                }
                RealPersonModel realPersonModel = new RealPersonModel();
                realPersonModel.setName(name);
                realPersonModel.setIdcard(idcard);
                realPersonModel.setCityId(cityId);
                realPersonModel.setProvinceId(map.get("province").getId());
                realPersonModel.setDistinctId(map.get("distinct").getId());

                Intent intent = new Intent(context, RealNameSecondActivity.class);
                intent.putExtra("isInfoComplete", isInfoComplete);
                intent.putExtra("realPersonModel", realPersonModel);
                startActivity(intent);
                break;
            case R.id.ly_area:
                // 选择地区
                startActivityForResult(new Intent().setClass(context, ProvinceCityActivity.class), SELECTAREA);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data == null)
                return;
            ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            if (requestCode == 1) {
                uploadImage(BitmapManage.bitmapToString(images.get(0).path, 600, 600), "10E");
            } else if (requestCode == 2) {
                uploadImage(BitmapManage.bitmapToString(images.get(0).path, 600, 600), "10F");
            } else {
                ViewUtils.makeToast(context, "没有数据", 500);
            }
        } else if (resultCode == RESULT_OK && requestCode == SELECTAREA) {
            map = (HashMap<String, Area>) data.getSerializableExtra("data");
            tvArea.setText(map.get("province").getDivisionName() + "-" + map.get("city").getDivisionName() + "-" + map.get("distinct").getDivisionName());
        }
    }

    private void uploadImage(String signPath, final String imageType) {
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
                    saveImagePath(imageUrl, imageType);
                }
            }
        });
    }

    private void saveImagePath(String imageUrl, String type) {
        if ("10E".equals(type)) {
            StorageCustomerInfo02Util.putInfo(this, "infoImageUrl_10E", imageUrl);
            idcardUrl_a = imageUrl;
            GlideUtils.loadNoChacheImage(context, idcardUrl_a, ivId1);
        } else {
            StorageCustomerInfo02Util.putInfo(this, "infoImageUrl_10F", imageUrl);
            idcardUrl_b = imageUrl;
            GlideUtils.loadNoChacheImage(context, idcardUrl_b, ivId2);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_real_name_first;
    }

    @Override
    public void initData() {
        tvTitle.setText("实名认证");
        isInfoComplete = getIntent().getBooleanExtra("isInfoComplete", false);

        if (isInfoComplete) {
            setData();
            String examineResult = StorageCustomerInfo02Util.getInfo("examineResult", this);
            tvCheckedInfo.setText(examineResult);
            llCheckedInfo.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 给数据赋值
     */
    private void setData() {
        String merchant_name = StorageCustomerInfo02Util.getInfo("merchantCnName", context);

        String idCardNumber = StorageCustomerInfo02Util.getInfo("idCardNumber", context);
        if (!StringUtil.isEmpty(merchant_name)) {
            etName.setText(merchant_name);
        }

        if (!StringUtil.isEmpty(idCardNumber)) {
            etIdcard.setText(idCardNumber);
        }
        idcardUrl_a = StorageCustomerInfo02Util.getInfo("infoImageUrl_10E", context);
        if (!StringUtil.isEmpty(idcardUrl_a)) {
            GlideUtils.loadNoChacheImage(context, idcardUrl_a, ivId1);
        }
        idcardUrl_b = StorageCustomerInfo02Util.getInfo("infoImageUrl_10F", context);
        if (!StringUtil.isEmpty(idcardUrl_b)) {
            GlideUtils.loadNoChacheImage(context, idcardUrl_b, ivId2);
        }
        llCustomerStatus.setVisibility(View.VISIBLE);
        tvCustomerStatusDesc.setText("审核拒绝");
    }
}
