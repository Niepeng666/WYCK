package com.linglingyi.com.viewone.dialog;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.NumberPicker;

import com.alibaba.fastjson.JSONArray;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.model.Area;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.utils.LogUtil;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @作者 chenlanxin
 * @创建日期 2019/5/6 9:18
 * @功能
 **/
public class AreaSelectionDialog extends DialogFragment {

    @BindView(R.id.np_province)
    NumberPicker npProvince;
    @BindView(R.id.np_city)
    NumberPicker npCity;
    @BindView(R.id.np_area)
    NumberPicker npArea;

    private Context context;
    private boolean isSelectArea=true;
    private List<Area> list;

    public static AreaSelectionDialog getIntence(boolean isSelectArea,List<Area> list) {
        AreaSelectionDialog dialog = new AreaSelectionDialog();
        Bundle bundle = new Bundle();
        bundle.putBoolean("isSelectArea",isSelectArea);
        bundle.putSerializable("list", (Serializable) list);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.MyProgressDialog);
        context = getContext();
        isSelectArea=getArguments().getBoolean("isSelectArea");
        list= (List<Area>) getArguments().getSerializable("list");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_area_selection, container);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        setNumberPickerDividerColor(npProvince);
        setNumberPickerDividerColor(npCity);
        setNumberPickerDividerColor(npArea);

        //设置最大最小值
        npProvince.setMinValue(1);
        //设置默认的位置
        npProvince.setValue(1);
        //这里设置为不循环显示，默认值为true
        npProvince.setWrapSelectorWheel(false);
        npProvince.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        //设置最大最小值
        npCity.setMinValue(1);
        //设置默认的位置
        npCity.setValue(1);
        //这里设置为不循环显示，默认值为true
        npCity.setWrapSelectorWheel(false);
        npCity.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        //设置最大最小值
        npArea.setMinValue(1);
        //设置默认的位置
        npArea.setValue(1);
        //这里设置为不循环显示，默认值为true
        npArea.setWrapSelectorWheel(false);
        npArea.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        if (isSelectArea) {
            npProvince.setVisibility(View.GONE);
            npCity.setVisibility(View.GONE);
        } else {
            npArea.setVisibility(View.GONE);
        }
        initListener();

//        //设置需要显示的内容数组
//        npProvince.setDisplayedValues(numbers);
//        npProvince.setMaxValue(numbers.length);
    }

    private void requestData(String id) {

//        HttpParams httpParams = new HttpParams();
//        httpParams.put("3", "393004");
//        httpParams.put("43", id);
//        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
//            @Override
//            public void onError(Response<BaseEntity> response) {
//                super.onError(response);
//                loadingDialog.dismiss();
//            }
//
//            @Override
//            public void onSuccess(Response<BaseEntity> response) {
//                super.onSuccess(response);
//                loadingDialog.dismiss();
//                BaseEntity model = response.body();
//                if (model == null) {
//                    return;
//                }
//                if ("00".equals(model.getStr39())) {
//                    List<Area> list = JSONArray.parseArray(model.getStr57(), Area.class);
//                    areaList.addAll(list);
//                    myAdapter.setNewData(areaList);
//                }
//            }
//        });
    }

    @OnClick(R.id.tv_determine)
    public void onViewClicked() {
    }

    private void initListener() {
        npProvince.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker view, int scrollState) {
                switch (scrollState) {
                    case SCROLL_STATE_IDLE://空闲状态
                        LogUtil.e("clx", view.getValue() + "--");
                        break;
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getActivity().getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void setNumberPickerDividerColor(NumberPicker numberPicker) {
        NumberPicker picker = numberPicker;
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    //设置分割线的颜色值 透明
                    pf.set(picker, new ColorDrawable(this.getResources().getColor(R.color.gray_line)));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        // 分割线高度
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDividerHeight")) {
                pf.setAccessible(true);
                try {
                    int result = getResources().getDimensionPixelOffset(R.dimen.dp_0_5);
                    pf.set(picker, result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
