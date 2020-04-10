package com.linglingyi.com.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.utils.Constant;
import com.linglingyi.com.utils.FileUtils;
import com.linglingyi.com.utils.LogUtil;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.wuyouchuangke.com.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;
import zxing.encoding.EncodingUtils;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/6/24
 */
public class QrCodePayActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.iv_pay_type)
    ImageView ivPayType;
    @BindView(R.id.linear1)
    LinearLayout linear1;
    @BindView(R.id.tv_toast)
    TextView tvToast;
    @BindView(R.id.iv_code)
    ImageView ivCode;
    @BindView(R.id.linear2)
    LinearLayout linear2;
    @BindView(R.id.linear3)
    LinearLayout linear3;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.iv_save)
    LinearLayout ivSave;
    @BindView(R.id.iv_share)
    LinearLayout ivShare;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_pay;
    }

    @Override
    public void initData() {

        String title = getIntent().getStringExtra("title");
        tvTitle.setText(StringUtil.isEmpty(title) ? "扫码支付" : title);
        String paytype = getIntent().getStringExtra("paytype");
        String money = getIntent().getStringExtra("money");
        String type = getIntent().getStringExtra("type");
        if (!"M".equals(type)) {
            text1.setVisibility(View.INVISIBLE);
            ivPayType.setVisibility(View.INVISIBLE);
        }
        if ("w".equals(paytype)) {
            ivPayType.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.pay_weixin));
        } else {
            ivPayType.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.pay_zhifubao));
        }
        Double dMoney = Double.parseDouble(money) * 100;
        requestData(dMoney.toString(), paytype, type);
    }

    /**
     * 获取二维码
     *
     * @param money   金额
     * @param paytype 支付类型   w   z
     * @param type    充值类型   w   z
     */
    private void requestData(final String money, String paytype, String type) {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "190111");
        httpParams.put("5", money);
        httpParams.put("8", paytype);
        httpParams.put("41", type);

        httpParams.put("42", getMerNo());
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
                    String url = Constant.BASE_URL + "/lly-posp-proxy/payView.app?m=" + model.getStr57();
                    ViewUtils.makeToast(context,
                            "请扫描二维码", 1000);
                    LogUtil.d("lzm-url", url);
                    BigDecimal bd = new BigDecimal(money);
                    String pricel = bd.divide(new BigDecimal(100)).toString();
                    String allmoney = pricel;
                    tvToast.setText("￥" + allmoney);
                    Bitmap bitmap = EncodingUtils.createQRCode(url, 500, 500, null);
                    ivCode.setImageBitmap(bitmap);
                } else {
                    ViewUtils.makeToast(context, "获取二维码失败", 1000);
                }
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.iv_save, R.id.iv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.iv_save:
//                保存图片
                try {
                    saveCurrentImage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri uri = Uri.fromFile(file);
                intent.setData(uri);
                getApplicationContext().sendBroadcast(intent);
                ViewUtils.makeToast(context,
                        "保存图片成功", 1000);
                break;
            case R.id.iv_share:
//                loadingDialog.show();
                try {
                    saveCurrentImage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                weixinShare();
                break;
        }
    }

    /**
     * 微信分享
     */
    private void weixinShare() {
        Platform.ShareParams sp1 = new Platform.ShareParams();
        sp1.setShareType(Platform.SHARE_IMAGE);
        sp1.setImagePath(Environment.getExternalStorageDirectory().getAbsolutePath() + "/wyck/qr.png");
        Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
        wechat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                loadingDialog.dismiss();
                ViewUtils.makeToast(context, "分享成功", 500);
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                loadingDialog.dismiss();
                ViewUtils.makeToast(context, "分享失败", 500);
            }

            @Override
            public void onCancel(Platform platform, int i) {
                loadingDialog.dismiss();
                ViewUtils.makeToast(context, "分享取消", 500);
            }
        }); // 设置分享事件回调
        // 执行分享
        wechat.share(sp1);
    }

    private void saveCurrentImage() throws IOException {
        //获取当前屏幕的大小
        int width = getWindow().getDecorView().getRootView().getWidth();
        int height = getWindow().getDecorView().getRootView().getHeight();
        //生成相同大小的图片
        Bitmap temBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //找到当前页面的根布局
        View view = getWindow().getDecorView().getRootView();
        //设置缓存
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        //从缓存中获取当前屏幕的图片
        temBitmap = view.getDrawingCache();
        SimpleDateFormat df = new SimpleDateFormat("yyyymmddhhmmss");
        String time = df.format(new Date());
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/wyck", "qr.png");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                temBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
