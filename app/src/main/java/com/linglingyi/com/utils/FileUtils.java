package com.linglingyi.com.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Build;
import android.os.Environment;

import com.lzy.okgo.convert.Converter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/3/27
 */
public class FileUtils {
    private static final String files =   "wyck/";
    /**
     * /storage/emulated/0/Android/data/packname/cache
     * /data/user/0/packname/cache
     *
     * @param mContext
     * @return 获取包名下的缓存路径
     */
    public static String getAppExternalCachePath(Context mContext) {
        String filePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            filePath = mContext.getExternalCacheDir().getPath();
        } else {
            filePath = mContext.getCacheDir().getPath();
        }

        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    /**
     * /storage/emulated/0/Android/data/packname/files
     * /data/user/0/packname/files
     *
     * @param mContext
     * @return 获取包名下的外部存储file文件
     */
    public static String getAppExternalFilePath(Context mContext,String dir) {
        String filePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            filePath = mContext.getExternalFilesDir(dir).getAbsolutePath();
        } else {
            filePath = mContext.getFilesDir() + File.separator + dir;
        }

        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }


    /**
     * /storage/emulated/0
     * /data
     *
     * @param
     * @return 获取根目录下文件夹地址
     */
    public static String getAppExternalStorageDirectory() {
        String filePath;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + files;
        } else {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                    || !Environment.isExternalStorageRemovable()) {
                filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + files;
            } else {
                filePath = Environment.getDataDirectory().getAbsolutePath() + File.separator + files;
            }
        }

        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }

        return file.getAbsolutePath();
    }


    /**
     * 将Bitmap转换成文件
     * 保存文件
     * @param bm
     * @param fileName
     * @throws IOException
     */
    public static File saveFile(Bitmap bm, String fileName) throws IOException {
//        String path = ;
        File myCaptureFile = new File(fileName);
        if (!myCaptureFile.exists()) {
            try {
                myCaptureFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        return myCaptureFile;
    }

    public  static Bitmap returnBitMap(String url) {
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
            HttpURLConnection conn;
            conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            int length = conn.getContentLength();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            BitmapFactory.Options options = new BitmapFactory.Options();
            // 设置缩放比例
            options.inSampleSize =2;
            Rect rect = new Rect(0, 0,0,0);
            bitmap = BitmapFactory.decodeStream(bis,rect,options);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
