package com.linglingyi.com.viewone;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.youth.banner.loader.ImageLoader;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/17
 */
public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

//设置图片圆角角度
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context).load(path).into(imageView);

    }
}
