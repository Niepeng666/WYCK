package com.linglingyi.com.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.event.FriendMessage;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.utils.LogUtil;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.viewone.MyGridView;
import com.linglingyi.com.viewone.common.CommonAdapter;
import com.linglingyi.com.viewone.common.CommonViewHolder;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/8/20
 */
public class FriendListActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    Bitmap bitmap;
    private FriendMessageAdapter adapter;
    private int mPage = 1, count;//页码，总页数
    private List<FriendMessage> data = new ArrayList<>();
    private File file;
    private int textCount;
    private int lenth = 1;
    private List<Bitmap> bitmapList;

    private String[] url;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                String message = (String) msg.obj;
                ViewUtils.makeToast(context, message.toString(), 1000);
            } else if (msg.what == 2) {
                FriendMessage friendMessage = (FriendMessage) msg.obj;
                submitRequestData(friendMessage.getId());
            }
        }
    };

    @Override
    public int initLayout() {
        return R.layout.act_friend_list;
    }

    @Override
    public void initData() {
        tvTitle.setText("酷友圈");
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                adapter = null;
                mPage = 1;
                requestData(mPage + "");
            }
        });
        requestData(mPage + "");
    }

    /**
     * 获取朋友圈信息
     *
     * @param page 页码
     */
    private void requestData(final String page) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "690032");
        httpParams.put("43", page);
        loadingDialog.show();
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {

            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
                smartRefreshLayout.finishRefresh();
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                loadingDialog.dismiss();
                smartRefreshLayout.finishRefresh();
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    if (!StringUtil.isEmpty(model.getStr55())) {
                        count = Integer.parseInt(model.getStr55());
                    }
                    data = new ArrayList<>();
                    String f57 = model.getStr57();
                    try {
                        JSONArray jsonArray = new JSONArray(f57);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String createTime = jsonObject.getString("createTime");
                            JSONObject json = new JSONObject(createTime);
                            String time = json.getString("time");
                            FriendMessage friendMessage = new FriendMessage();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                            Date date = null;
                            try {
                                date = sdf.parse(sdf.format(new Date(Long.parseLong(time))));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            friendMessage.setTime(sdf.format(date));
                            friendMessage.setMessage(jsonObject.getString("context"));
                            friendMessage.setImages(jsonObject.getString("picPaths"));
                            friendMessage.setDownsload(jsonObject.getString("downloadCount"));
                            friendMessage.setId(jsonObject.getString("id"));
                            data.add(friendMessage);
                        }
                        mPage++;
                        if (adapter == null) {
                            adapter = new FriendMessageAdapter(data);
                            adapter.bindToRecyclerView(recyclerView);
                            adapter.setEnableLoadMore(true);//开启下拉加载
                            adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                                @Override
                                public void onLoadMoreRequested() {
                                    if (mPage > count) {//加载完毕
                                        adapter.loadMoreEnd();
                                        mPage = 1;

                                    } else {
                                        requestData(String.valueOf(mPage));
                                        LogUtil.v("mPage:", mPage + "");
                                        LogUtil.v("count:", count + "");
                                    }
                                }
                            });
                        } else {//加数据成功
                            LogUtil.v("mPage1111:", mPage + "");
                            adapter.addData(data);
                            adapter.loadMoreComplete();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ViewUtils.makeToast(context, model.getStr39(), 1000);
                    if (adapter != null) {
                        adapter.loadMoreFail();
                    }
                }
            }
        });
        HashMap<Integer, String> requestData = new HashMap<>();
    }

    /**
     * 下载图片
     *
     * @param id
     */
    private void submitRequestData(String id) {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "690033");
        httpParams.put("24", id);
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    loadingDialog.dismiss();
                    Message message = new Message();
                    message.what = 1;
                    message.obj = "下载图片成功";
                    handler.sendMessage(message);
                } else {
                    Message message = new Message();
                    message.what = 1;
                    message.obj = context.getString(R.string.server_error);
                    handler.sendMessage(message);
                }
            }
        });
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    public Bitmap returnBitMap(final String[] urlList, final FriendMessage item) {

        new Thread(new Runnable() {
            @Override
            public void run() {


//                 List<Bitmap> bitmapList = new ArrayList<Bitmap>();

                for (int i = 0; i < urlList.length; i++) {
                    Bitmap bitmapTemp = null;
                    try {
                        bitmapTemp = downloadImage(urlList[i]);
                        Log.d("urlTemp", bitmapTemp.toString());
                        saveCurrentImage(bitmapTemp, urlList[i]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                Message message = new Message();
                message.what = 2;
                message.obj = item;
                handler.sendMessage(message);


//                URL imageurl = null;
//
//                try {
//                    imageurl = new URL(url1);
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                }
//                try {
//
//                    bitmap = downloadImage( url1 );
//                    bitmapList.add(bitmap);
//                    if (lenth+1 <= url.length) {
//                        returnBitMap(url[lenth-1],item);
//                        lenth++;
//                    }
//                    if (lenth > url.length) {
//                        if (bitmapList != null && !bitmapList.equals("") && bitmapList.size()>0) {
//                            for (int i = 0; i<bitmapList.size();i++) {
//                                saveCurrentImage(bitmapList.get(i),i);
//                            }
//                            submitRequestData(item.getId());
//                        } else {
//                            ViewUtils.makeToast(context, "没有找到图片", 1000);
//                        }
//                    }
////                    HttpURLConnection conn = (HttpURLConnection)imageurl.openConnection();
////                    conn.setDoInput(true);
////                    conn.connect();
////                    InputStream is = conn.getInputStream();
////                    bitmap = BitmapFactory.decodeStream(is);
////                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
            }
        }).start();

        return bitmap;
    }

    /**
     * 保存图片
     */
    private void saveCurrentImage(Bitmap bg, String url) {
       /* //获取当前屏幕的大小
        int width = getWindow().getDecorView().getRootView().getWidth();
        int height = getWindow().getDecorView().getRootView().getHeight();
        //生成相同大小的图片
        Bitmap temBitmap = Bitmap.createBitmap( width, height, Bitmap.Config.ARGB_8888 );
        //找到当前页面的根布局
        View view =  getWindow().getDecorView().getRootView();
        //设置缓存
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();*/
        //从缓存中获取当前屏幕的图片
//        temBitmap = view.getDrawingCache();
//        Bitmap temBitmap = saveQRCodeImage(bg);
//        SimpleDateFormat df = new SimpleDateFormat("yyyymmddhhmmss" );
//        String time = df.format(new Date());
        String[] PERMISSIONS = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"};
        //检测是否有写的权限
        int permission = ContextCompat.checkSelfPermission(this,
                "android.permission.WRITE_EXTERNAL_STORAGE");
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // 没有写的权限，去申请写的权限，会弹出对话框
            ActivityCompat.requestPermissions(this, PERMISSIONS, 1);
        }

        if (url == null || url.length() <= 0) {
            return;
        }

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {

            String urltemp = "";

//            urltemp = url.replace(".jpg", "");
//            urltemp = url.replace(".png", "");
//            urltemp = url.replace(".jpeg", "");
//
//            urltemp = url.replace(".JPG", "");
//            urltemp = url.replace(".PNG", "");
//            urltemp = url.replace(".JPEG", "");


            String bitName = url.substring(url.lastIndexOf("/"));

            String fileName = "";
            if (Build.BRAND.equals("Xiaomi")) { // 小米手机
                fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/" + bitName;
            } else {  // Meizu 、Oppo
                fileName = Environment.getExternalStorageDirectory().getPath() + "/wyck/" + bitName;
            }
            file = new File(fileName);
//            file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/ryjf/"  + fileName);
            Log.d("file", file.toString());
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
                bg.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
                // 插入图库
                // MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "title", "description");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                    Uri.fromFile(new File(fileName))));
        }

    }

    private Bitmap downloadImage(String urlstr) throws Exception {
        Bitmap bitmap = null;

        //把传过来的路径转成URL
        URL url = new URL(urlstr);
        //获取连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //使用GET方法访问网络
        connection.setRequestMethod("GET");
        //超时时间为10秒
        connection.setConnectTimeout(10000);
        //获取返回码
        int code = connection.getResponseCode();
        if (code == 200) {
            InputStream inputStream = connection.getInputStream();
            //使用工厂把网络的输入流生产Bitmap
            bitmap = BitmapFactory.decodeStream(inputStream);
        }


        return bitmap;

    }

    class FriendMessageAdapter extends BaseQuickAdapter<FriendMessage, BaseViewHolder> {


        public FriendMessageAdapter(@Nullable List<FriendMessage> data) {
            super(R.layout.friend_information_list, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final FriendMessage item) {
            lenth = 0;
            url = item.getImages().split(",");
            helper.setText(R.id.time, item.getTime());
            helper.setText(R.id.tv_download, "下载量" + item.getDownsload() + "次");
            final TextView more = helper.getView(R.id.more);
            TextView saveImage = helper.getView(R.id.save_image);
            TextView copyMessage = helper.getView(R.id.copy_message);

            final TextView message = helper.getView(R.id.message);
            message.setText(item.getMessage());
            ViewTreeObserver vto = message.getViewTreeObserver();
            vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    Layout layout = message.getLayout();
                    if (layout == null) {
//                        LogUtil.v(position + "message:", message.getText().toString());
                        return;
                    }
                    textCount = layout.getLineCount();
                    if (textCount > 3 && more.getText().toString().equals("更多")) {
                        lenth = textCount;
                        more.setVisibility(View.VISIBLE);
                        message.setMaxLines(3);
                    } else if (textCount < 3) {
                        more.setVisibility(View.GONE);
                    }
                }
            });
            MyGridView gridView = helper.getView(R.id.gridView);
            final List<String> urlList = new ArrayList<>();
            bitmapList = new ArrayList<>();
            urlList.clear();
            bitmapList.clear();
            if (url != null && !url[0].equals("") && url.length > 0) {
                for (String i : url) {
                    urlList.add(i);
                }
            }else {
                helper.setGone(R.id.tv_download,false);
                helper.setGone(R.id.save_image,false);
            }
            LogUtils.i("urlList=" + urlList.size());
            gridView.setAdapter(new MyAdapter(context, urlList, R.layout.image_list));
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    startActivity(new Intent(context, ImageActivity.class).putExtra("url", urlList.get(position)));
                }
            });
            saveImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String[] urlTemp = item.getImages().split(",");
                    if (StringUtil.isEmpty(item.getImages())) {
                        ViewUtils.makeToast(context, "暂无图片", 500);
                        return;
                    }
                    returnBitMap(urlTemp, item);

                }
            });
            copyMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //获取剪贴板管理器：
                    ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    // 创建普通字符型ClipData
                    ClipData mClipData = ClipData.newPlainText("Label", message.getText().toString());
                    // 将ClipData内容放到系统剪贴板里。
                    cm.setPrimaryClip(mClipData);
                    ViewUtils.makeToast(context, "复制成功", 1000);
                }
            });
            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (message.getMaxLines() == 3) {
                        more.setText("收起");
                        message.setMaxLines(999);
                    } else if (message.getMaxLines() == 999) {
                        more.setText("更多");
                        message.setMaxLines(3);
                    }


                }
            });
        }
    }

    class MyAdapter extends CommonAdapter<String> {

        public MyAdapter(Context context, List datas, int layoutResId) {
            super(context, datas, layoutResId);
        }

        @Override
        public void convert(CommonViewHolder holder, String url) {
            holder.setImageURI(R.id.image, url);
        }
    }
}
