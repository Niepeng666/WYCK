package com.linglingyi.com.viewone.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.linglingyi.com.callback.CancelCallback;
import com.linglingyi.com.utils.FileUtils;
import com.linglingyi.com.utils.GlideUtils;
import com.linglingyi.com.utils.ViewUtils;
import com.wuyouchuangke.com.R;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import zxing.encoding.EncodingUtils;

/**
 * @作者 chenlanxin
 * @创建日期 2019/2/1 15:37
 * @功能 贷款 操作流程
 **/
public class SharePicDialog extends DialogFragment {


    public Dialog loadingDialog;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.iv_qr_code)
    ImageView ivQrCode;
    @BindView(R.id.rl_red)
    RelativeLayout rlRed;
    @BindView(R.id.tv_share)
    TextView tvShare;
    Unbinder unbinder;

    private Context mContext;
    private CancelCallback mCancelCallback;
    private String picUrl, qrCodeUrl;

    public static SharePicDialog getInstance(String picUrl, String qrUrl) {
        SharePicDialog dialog = new SharePicDialog();
        Bundle bundle = new Bundle();
        bundle.putString("pic", picUrl);
        bundle.putString("qrcode", qrUrl);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        Bundle bundle = getArguments();
        picUrl = bundle.getString("pic");
        qrCodeUrl = bundle.getString("qrcode");
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.MyProgressDialog);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        unbinder.unbind();
    }

    public void setCancelCallback(CancelCallback cancelCallback) {
        mCancelCallback = cancelCallback;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_share_pic, container);

        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        getDialog().setCanceledOnTouchOutside(false);
        loadingDialog = ViewUtils.createLoadingDialog(getActivity(), getString(R.string.loading_wait), false);
        GlideUtils.loadImage(mContext, picUrl, ivBg);
        Bitmap mBitmap = EncodingUtils.createQRCode(qrCodeUrl, 500, 500, null);
        ivQrCode.setImageBitmap(mBitmap);
    }

    @OnClick({R.id.tv_share, R.id.iv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_share:
                if (mCancelCallback == null) {
                    return;
                }
                try {
                    saveCurrentImage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mCancelCallback.cancel();
                dismiss();
                break;
            case R.id.iv_close:
                dismiss();
                break;
        }
    }

    private void saveCurrentImage() throws IOException {
        Bitmap temBitmap;
        View view = rlRed;
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        //从缓存中获取当前屏幕的图片
        temBitmap = view.getDrawingCache();
        String destination = FileUtils.getAppExternalFilePath(mContext, "share") + File.separator + "share.jpg";
        try {
            FileUtils.saveFile(temBitmap, destination);
        } catch (IOException e) {
//            判断是否保存
            File file = new File(destination);
            if (!file.exists()) {
                FileUtils.saveFile(temBitmap, destination);
            }
            e.printStackTrace();
        }

    }
}
