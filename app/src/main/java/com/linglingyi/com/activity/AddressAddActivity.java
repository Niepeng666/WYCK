package com.linglingyi.com.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.event.AddressEvent;
import com.linglingyi.com.model.AddressModel;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.KeyBoardUtils;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.viewone.wheelview.ChangeAddressPopwindow;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @作者 chenlanxin
 * @创建日期 2019/7/23 19:54
 * @功能 添加收货地址
 **/
public class AddressAddActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.et_address_name)
    EditText etAddressName;
    @BindView(R.id.et_address_phone)
    EditText etAddressPhone;
    @BindView(R.id.tv_address_txt)
    TextView tvAddressTxt;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.et_address_detail)
    EditText etAddressDetail;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.ly_check)
    LinearLayout lyCheck;
    @BindView(R.id.btn_address_save)
    Button btnAddressSave;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;
    private String addressName, addressPhone, area, addresssDetail;
    private boolean isDefaultAddress = false;
    private AddressModel addressBeanFromClass;
    private boolean isModify;

    @Override
    public int initLayout() {
        return R.layout.activity_address_add;
    }

    @Override
    public void initData() {
        tvTitle.setText("新建地址");

        //是否为默认地址
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isDefaultAddress = isChecked;
            }
        });

        if (getIntent().hasExtra("isModify")) {
            isModify = getIntent().getBooleanExtra("isModify", false);
            if (isModify) {
                tvTitle.setText("修改地址");
                addressBeanFromClass = (AddressModel) getIntent().getSerializableExtra("addressBean");
                etAddressName.setText(addressBeanFromClass.getName());
                etAddressPhone.setText(addressBeanFromClass.getPhone());
                String[] addresses = addressBeanFromClass.getAddress().split(" ");
                tvArea.setText(addresses[0]);
                if (addresses.length>1) {
                    etAddressDetail.setText(addresses[1]);
                }
                checkbox.setChecked(addressBeanFromClass.getStatus().equals("1"));
            }

        }
    }

    @OnClick({R.id.iv_back, R.id.btn_address_save, R.id.tv_area})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_area://选择地区
                KeyBoardUtils.hideKeyboard(llContainer);
                ChangeAddressPopwindow mChangeAddressPopwindow = new ChangeAddressPopwindow(context, false);
                mChangeAddressPopwindow.setAddress("浙江", "杭州", "滨江区");
                mChangeAddressPopwindow.showAtLocation(tvArea, Gravity.BOTTOM, 0, 0);
                mChangeAddressPopwindow.setAddresskListener(new ChangeAddressPopwindow.OnAddressCListener() {
                    @Override
                    public void onClick(String province, String city, String area) {
                        String diqu = province + "省" + "-" + city + "市" + "-" + area;
                        tvArea.setText(diqu);
                    }
                });
                break;
            case R.id.btn_address_save:
                if (CommonUtils.isFastDoubleClick2()){
                    return;
                }
                addressName = etAddressName.getText().toString().trim();
                addressPhone = etAddressPhone.getText().toString().trim();
                area = tvArea.getText().toString().trim();
                addresssDetail = etAddressDetail.getText().toString().trim();
                if (isModify) {
                    if ("".equals(addressName)) {
                        ViewUtils.makeToast(context, "请填写收货人姓名", 1500);
                        return;
                    }
                    if ("".equals(addressPhone)) {
                        ViewUtils.makeToast(context, "请填写联系电话", 1500);
                        return;
                    }
                    if (addressPhone.length() != 11 || !"1".equals(addressPhone.substring(0, 1))) {
                        ViewUtils.makeToast(this, "请输入正确的手机号", 1500);
                        return;
                    }
                    if ("请选择省市区".equals(area)) {
                        ViewUtils.makeToast(context, "请选择收货地区", 1500);
                        return;
                    }
                    if ("".equals(addresssDetail)) {
                        ViewUtils.makeToast(context, "请填写收货详细地址", 1500);
                        return;
                    }
                    try {
                        requestModifyData();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else {
                    commitData();
                }
                break;
        }
    }

    /**
     * 修改地址请求
     */
    private void requestModifyData() throws UnsupportedEncodingException {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "590016");
        httpParams.put("8", URLEncoder.encode(addressName, "UTF-8"));//	收货人姓名URLEncoder.encode
        httpParams.put("9", getMerId());//商户Id
        httpParams.put("10", addressPhone);//收货人手机号
        httpParams.put("11", URLEncoder.encode(area + " " + addresssDetail, "UTF-8"));//收货人地址
        httpParams.put("12", isDefaultAddress ? "1" : "0");//是否默认地址 1默认 0非默认
        httpParams.put("13", addressBeanFromClass.getId());//要修改的地址id
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
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    ViewUtils.makeToast(context, "修改成功", 1000);
                    EventBus.getDefault().post(new AddressEvent());
                    finish();
                }
            }


        });

    }

    /**
     * 添加地址请求
     */
    private void commitData() {

        if (addressName.equals("")) {
            ViewUtils.makeToast(context, "请填写收货人姓名", 1500);
            return;
        }
        if (addressPhone.equals("")) {
            ViewUtils.makeToast(context, "请填写联系电话", 1500);
            return;
        }
        if (addressPhone.length() != 11 || !addressPhone.substring(0, 1).equals("1")) {
            ViewUtils.makeToast(this, "请输入正确的手机号", 1500);
            return;
        }
        if (area.equals("请选择省市区")) {
            ViewUtils.makeToast(context, "请选择收货地区", 1500);
            return;
        }
        if (addresssDetail.equals("")) {
            ViewUtils.makeToast(context, "请填写收货详细地址", 1500);
            return;
        }
        try {
            requestAddAddress();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    /**
     * 添加收货地址
     */
    void requestAddAddress() throws UnsupportedEncodingException {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "590011");
        httpParams.put("8", URLEncoder.encode(addressName, "UTF-8"));//	收货人姓名URLEncoder.encode
        httpParams.put("9", URLEncoder.encode(getMerId(), "UTF-8"));//商户Id
        httpParams.put("10", URLEncoder.encode(addressPhone, "UTF-8"));//收货人手机号
        httpParams.put("11", URLEncoder.encode(area + " " + addresssDetail, "UTF-8"));//收货人地址
        httpParams.put("12", isDefaultAddress ? "1" : "0");//是否默认地址 1默认 0非默认
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    ViewUtils.makeToast(context, "添加成功", 1500);
                    EventBus.getDefault().post(new AddressEvent());
                    finish();
                }
            }

            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
