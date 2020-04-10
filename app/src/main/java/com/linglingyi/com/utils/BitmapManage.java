package com.linglingyi.com.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;


/**
 * @author hkrt
 * 处理图片
 */
public class BitmapManage {

    public static String bitmapToString(String filePath, int reqWidth, int reqHeight) {
        LogUtils.i("size="+getSmallBitmap(filePath, reqWidth, reqHeight).getByteCount());
        return bitmapToString(getSmallBitmap(filePath, reqWidth, reqHeight));
    }

    // 根据路径获得图片并压缩，返回bitmap用于显示
    public static Bitmap getSmallBitmap(String filePath, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }

    //计算图片的缩放值
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    //把bitmap转换成String
    public static String bitmapToString(Bitmap bm) {
        Bitmap bitmap = compsressImageByQuality(bm);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    /**
     * 质量压缩方法
     *
     * @param image
     * @return
     */
    private static Bitmap compsressImageByQuality(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 100;
        image.compress(Bitmap.CompressFormat.JPEG, options, baos);
        while (baos.toByteArray().length / 1024 > 100 && options > 0) {
            baos.reset();
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
            options -= 10;
//            Logger.i("comparess", "质量压缩一次：" + baos.toByteArray().length / 1024 + "kb");
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        return BitmapFactory.decodeStream(isBm, null, null);
    }

    public static Bitmap zoomImage(Bitmap bgimage, double newWidth,
                                   double newHeight) {

        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        LogUtil.syso("scaleWidth=" + scaleWidth);
        LogUtil.syso("scaleHeight=" + scaleHeight);
        // 缩放图片动作
        matrix.postScale(0.5f, 0.4f);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                (int) height, matrix, true);

        Canvas canvas = new Canvas(bitmap);//用bm创建一个画布 可以往bm中画入东西了
        canvas.drawBitmap(bgimage, matrix, null);

        return bitmap;
    }

    /*
     *
     * 当我们需要圆角的时候，调用这个方法，第一个参数是传入需要转化成圆角的图片，
     * 第二个参数是圆角的度数，数值越大，圆角越大
     *
     */
    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;

    }

    /**
     * 尺寸压缩
     *
     * @param mContext
     * @param imgPath
     * @return
     */
    public static String getCompressedImgPath(Context mContext, String imgPath) {
        File file = new File(imgPath);
        LogUtils.i("原图文件大小：" + file.length() / 1024 + "kb");
        int degree = readPictureDegree(imgPath);
        Bitmap bitmap = null;

        if (file.length() < 1024 * 100 && degree == 0) {
            return imgPath;
        }

        if (file.length() > 1024 * 100) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(imgPath, options);

            if (options.outWidth > options.outHeight) {
                options.inSampleSize = calculateInSampleSize(options, 1280, 720);
            } else {
                options.inSampleSize = calculateInSampleSize(options, 720, 1280);
            }
            options.inJustDecodeBounds = false;

            bitmap = BitmapFactory.decodeFile(imgPath, options);
//            Logger.i("comparess", "比例压缩Bitmap大小：" + bitmap.getByteCount() / 1024 + "kb");
        }

        if (degree != 0) {
            bitmap = rotaingImageView(degree, bitmap);
//            Logger.i("comparess", "旋转角度Bitmap大小：" + bitmap.getByteCount() / 1024 + "kb");
        }

        bitmap = compsressImageByQuality(bitmap);
        LogUtil.i("comparess", "质量压缩后Bitmap大小：" + bitmap.getByteCount() / 1024 + "kb");
        String path = mContext.getCacheDir() + File.separator + System.currentTimeMillis() + ".jpg";
        try {
            FileOutputStream fileout = new FileOutputStream(path);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, fileout);
            fileout.flush();
//            Logger.i("comparess", "最终文件大小：" + new File(path).length() / 1024 + "kb");
            return path;
        } catch (IOException e) {
            e.printStackTrace();
            System.gc();
            return "";
        }
    }

    /**
     * 读取图片属性：旋转的角度
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    private static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    private static Bitmap rotaingImageView(int degree, Bitmap bitmap) {
        //旋转图片 动作
        Matrix matrix = new Matrix();

        matrix.postRotate(degree);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    /**
     * 4.尺寸压缩（通过缩放图片像素来减少图片占用内存大小）
     *
     * @param bmp
     * @param file
     */

    public static void sizeCompress(Bitmap bmp, File file) {
        // 尺寸压缩倍数,值越大，图片尺寸越小
        int ratio = 8;
        // 压缩Bitmap到对应尺寸
        Bitmap result = Bitmap.createBitmap(bmp.getWidth() / ratio, bmp.getHeight() / ratio, Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Rect rect = new Rect(0, 0, bmp.getWidth() / ratio, bmp.getHeight() / ratio);
        canvas.drawBitmap(bmp, null, rect, null);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 把压缩后的数据存放到baos中
        result.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 截屏
     *
     * @param activity
     * @return
     */
    public static Bitmap activityShot(Activity activity) {
        /*获取windows中最顶层的view*/
        View view = activity.getWindow().getDecorView();
        //允许当前窗口保存缓存信息
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        //获取状态栏高度
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        int statusBarHeight = rect.top;
        WindowManager windowManager = activity.getWindowManager();
        //获取屏幕宽和高
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;
        //去掉状态栏
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache(), 0, statusBarHeight, width, height - statusBarHeight);
        //销毁缓存信息
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }

    /*
     * Drawable转化为Bitmap
     */
    public Bitmap drawableToBitmap(Drawable drawable) {

        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888 : Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);

        return bitmap;
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
            options.inSampleSize =2;
            Rect rect = new Rect(0, 0,0,0);
            bitmap = BitmapFactory.decodeStream(bis,rect,options);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
