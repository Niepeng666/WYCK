package com.linglingyi.com.viewone.dialog;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.activity.WebViewActivity;
import com.linglingyi.com.model.ImageModel;
import com.linglingyi.com.utils.GlideUtils;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @作者 chenlanxin
 * @创建日期 2019/5/6 9:18
 * @功能
 **/
public class AdvertisingDialog extends DialogFragment {


    @BindView(R.id.iv_advertising)
    ImageView ivAdvertising;
    Unbinder unbinder;

    private Context context;
    private String url;

    public static AdvertisingDialog getIntence(String url) {
        AdvertisingDialog dialog = new AdvertisingDialog();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.MyProgressDialog);
        setCancelable(false);
        context = getActivity();
        url=getArguments().getString("url");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_advertising, container);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        GlideUtils.loadImage(context,url,ivAdvertising);
    }

    @Override
    public void onStart() {
        super.onStart();
        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        params.width = (int) (dm.widthPixels * 0.8);
        getDialog().getWindow().setAttributes(params);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_advertising, R.id.iv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_advertising:
                break;
            case R.id.iv_close:
                dismiss();
                break;
        }
    }

}
