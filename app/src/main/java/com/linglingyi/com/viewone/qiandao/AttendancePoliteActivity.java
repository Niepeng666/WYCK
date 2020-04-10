package com.linglingyi.com.viewone.qiandao;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.utils.LogUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import butterknife.OnClick;

public class AttendancePoliteActivity extends BaseActivity {
    private static final String TAG="AttendancePolite";
    private SignDate signDate;
    private android.widget.Button btnqiandao;
    boolean isDate;
    String value;
    private ImageView iv_back;
    @Override
    public int initLayout() {
        return R.layout.attendance_polite;
    }

    @Override
    public void initData() {
        initView();

        postInfo(getMerNo());
        btnqiandao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                post(getMerNo());
            }
        });
    }

    private void initView(){
        iv_back= (ImageView) findViewById(R.id.iv_back);
        this.btnqiandao = (Button) findViewById(R.id.btn_qiandao);
        signDate = (SignDate) findViewById(R.id.signDate);
    }


    @OnClick(R.id.iv_back)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                    finish();
                break;
        }
    }
    private int dpToPx(int dp) {
        return (int) (Resources.getSystem().getDisplayMetrics().density * dp + 0.5f);
    }


    private void postInfo(String str){
        HttpParams map = OkClient.getParamsInstance().getParams();
        map.put("3","153260");
        map.put("42",str);
        loadingDialog.show();
        OkClient.getInstance().post(map,new OkClient.EntityCallBack<BaseEntity>(context,BaseEntity.class){
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                Log.i(TAG,"onError"+response.toString());
                loadingDialog.dismiss();
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                BaseEntity body = response.body();
                Log.i(TAG,"onSuccess"+body.toString());
                String result = body.getStr39();
                if (result.equals("00")){
                    String str40 = body.getStr40();
                    String[] str=str40.split("\\,");
                    for (int i=0;i<str.length;i++){
                        LogUtil.i(TAG,str[i]);
                    }
                    signDate.setAttendance(str);
                }else{
                    ViewUtils.makeToast(context,result,500);
                }
            }
        });
    }

    private void post(String str){
        HttpParams map = OkClient.getParamsInstance().getParams();
        map.put("3","153261");
        map.put("42",str);
        loadingDialog.show();
        OkClient.getInstance().post(map,new OkClient.EntityCallBack<BaseEntity>(context,BaseEntity.class){
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                Log.i(TAG,"onError"+response.toString());
                loadingDialog.dismiss();
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                BaseEntity body = response.body();
                Log.i(TAG,"onSuccess"+body.toString());
                String result = body.getStr39();
                if (result.equals("00")){
                    Date curDate = new Date(System.currentTimeMillis());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
                    String format = simpleDateFormat.format(curDate);
                    LogUtil.i("AdapterDate","addAttendance--"+format+"");

                    String substring = format.substring(0, 1);
                    LogUtil.i("AdapterDate","addAttendance--"+substring+"");
                    if (substring.equals("0")){
                        format=format.substring(format.length()-1,format.length());
                    }

                    signDate.setAddAttendance(format);
                    ViewUtils.makeToast(context,body.getStr38(),500);
                }else{
                    ViewUtils.makeToast(context,result,500);
                }
            }
        });
    }
}
