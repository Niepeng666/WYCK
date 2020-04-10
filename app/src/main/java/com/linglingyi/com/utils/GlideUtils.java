package com.linglingyi.com.utils;

import android.content.Context;
import android.widget.ImageView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.viewone.GlideCircleTransforms;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/3/29
 */
public class GlideUtils {
    private GlideUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 正常加载图片
     *
     * @param mContext
     * @param url
     * @param imageView
     */
    public static void loadImageCenterCrop(Context mContext, String url, ImageView imageView) {
        Glide.with(mContext)
                .load(url)
                .error(R.drawable.no_img)
                .centerCrop()
                .into(imageView);
    }

    /**
     * 正常加载图片
     *
     * @param mContext
     * @param url
     * @param imageView
     */
    public static void loadImage(Context mContext, String url, ImageView imageView) {
        Glide.with(mContext)
                .load(url)
                .error(R.drawable.no_img)
                .into(imageView);
    }

    /**
     * 加载头像
     *
     * @param mContext
     * @param url
     * @param avatar
     */
    public static void loadAvatar(Context mContext, String url, ImageView avatar) {
        if (mContext != null) {
            Glide.with(mContext)
                    .load(StringUtil.isEmpty(url) ? R.drawable.main_new_logo : url)
                    .error(R.drawable.main_new_logo)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .transform(new GlideCircleTransform(mContext), new CenterCrop(mContext))
                    .into(avatar);
        }
    }


    public static void loadThumnailLocalImage(Context mContext, Integer drawable, ImageView img) {
        if (mContext != null) {
            Glide.with(mContext)
                    .load(drawable)
                    .thumbnail(0.5f)
                    .into(img);
        }
    }

    public static void loadTLocalAvatar(Context mContext, Integer drawable, ImageView img) {
        if (mContext != null) {
            Glide.with(mContext)
                    .load(drawable)
                    .thumbnail(0.5f)
                    .transform(new GlideCircleTransform(mContext), new CenterCrop(mContext))
                    .into(img);
        }
    }

    /**
     * 加载无缓存图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadNoChacheImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .error(R.drawable.no_img)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }

    /**
     * 加载缩略图图片
     *
     * @param mContext
     * @param url
     * @param imageView
     */
    public static void loadThumbnailImageCenterCrop(Context mContext, String url, ImageView imageView) {
        Glide.with(mContext)
                .load(url)
                .thumbnail(0.5f)
                .error(R.drawable.no_img)
                .centerCrop()
                .into(imageView);
    }

    /**
     * 加载缩略图图片
     *
     * @param mContext
     * @param url
     * @param imageView
     */
    public static void loadThumbnailImage(Context mContext, String url, ImageView imageView) {
        Glide.with(mContext)
                .load(StringUtil.isEmpty(url) ? R.drawable.no_img : url)
                .thumbnail(0.5f)
                .error(R.drawable.no_img)
                .into(imageView);
    }

    /**
     * 加载缩略图图片
     *
     * @param mContext
     * @param url
     * @param imageView
     */
    public static void loadThumbnailCircleImage(Context mContext, String url, ImageView imageView) {
        Glide.with(mContext)
                .load(url)
                .thumbnail(0.5f)
                .error(R.drawable.no_img)
                .transform(new GlideCircleTransform(mContext), new CenterCrop(mContext))
                .into(imageView);
    }


    /**
     * 加载圆形图片
     *
     * @param mContext
     * @param url
     * @param imageView
     */
    public static void loadCircleImage(Context mContext, String url, ImageView imageView) {
        Glide.with(mContext).load(url)
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.place_holder)
                .transform(new CenterCrop(mContext), new GlideCircleTransform(mContext))
                .into(imageView);
    }

    /**
     * 加载原型带边框图片
     *
     * @param mContext
     * @param url
     * @param borderWidth
     * @param borderColor
     * @param imageView
     */
    public static void loadCircleBorderImage(Context mContext, String url, int borderWidth, int borderColor, ImageView imageView) {
        Glide.with(mContext).load(url)
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.place_holder)
                .transform(new CenterCrop(mContext), new GlideCircleTransforms(mContext, borderWidth, borderColor))
                .into(imageView);
    }
}
