package com.dm.excellent.baselibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.dm.excellent.baselibrary.R;

/**
 * Created by Administrator on 2016/5/11.
 */
public class ImageLoadUtil {

    public static void setImage(String imageUrl, final ImageView imageView, int width, int height, Activity activity) {
        if (AbStrUtil.isEmpty(imageUrl)) {
            return;
        }
//        imageUrl = Urls.AddPATH(imageUrl);
        Glide.with(activity)
                .load(imageUrl)
                .error(R.mipmap.app_no_data_icon)           //设置错误图片
                .placeholder(R.mipmap.app_no_data_icon)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)//缓存全尺寸
                .centerCrop()
                .into(new SimpleTarget<GlideDrawable>(width, height) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        imageView.setImageDrawable(resource);
                    }
                });
    }

    public static void setImage(String imageUrl, final ImageView imageView, int width, int height, Context activity) {
        if (AbStrUtil.isEmpty(imageUrl)) {
            return;
        }
//        imageUrl = Urls.AddPATH(imageUrl);
        Glide.with(activity)
                .load(imageUrl)
                .error(R.mipmap.app_no_data_icon)           //设置错误图片
                .placeholder(R.mipmap.app_no_data_icon)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)//缓存全尺寸
                .into(new SimpleTarget<GlideDrawable>(width, height) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        imageView.setImageDrawable(resource);
                    }
                });
    }

    public static void setImage2(String imageUrl, final ImageView imageView, Context activity) {
        if (AbStrUtil.isEmpty(imageUrl)) {
            return;
        }
//        imageUrl = Urls.AddPATH(imageUrl);
        Glide.with(activity)
                .load(imageUrl)
                .error(R.mipmap.app_no_data_icon)           //设置错误图片
                .placeholder(R.mipmap.app_no_data_icon)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)//缓存全尺寸
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        imageView.setImageDrawable(resource);
                    }
                });
    }

    public static void setImage2(String imageUrl, final ImageView imageView, Activity activity) {
        if (AbStrUtil.isEmpty(imageUrl)) {
            return;
        }
//        imageUrl = Urls.AddPATH(imageUrl);
        Glide.with(activity)
                .load(imageUrl)
                .error(R.mipmap.app_no_data_icon)           //设置错误图片
                .placeholder(R.mipmap.app_no_data_icon)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)//缓存全尺寸
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        imageView.setImageDrawable(resource);
                    }
                });
    }

    public static void setImage(String imageUrl, final ImageView imageView, Activity activity) {
        if (AbStrUtil.isEmpty(imageUrl)) {
            return;
        }
//        imageUrl = Urls.AddPATH(imageUrl);
        Glide.with(activity).load(imageUrl)
                .error(R.mipmap.app_no_data_icon)           //设置错误图片
                .placeholder(R.mipmap.app_no_data_icon)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)//缓存全尺寸
                .into(imageView);
    }

    public static void setImage(String imageUrl, final ImageView imageView, Context activity) {
        if (AbStrUtil.isEmpty(imageUrl)) {
            return;
        }
//        imageUrl = Urls.AddPATH(imageUrl);
        Glide.with(activity).load(imageUrl)
                .error(R.mipmap.app_no_data_icon)           //设置错误图片
                .placeholder(R.mipmap.app_no_data_icon)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)//缓存全尺寸
                .into(imageView);
    }

    public static void setCircleImage(final String imageUrl, final ImageView imageView, Context context) {
        Glide.with(context)
                .load(imageUrl)
                .error(R.mipmap.app_no_data_icon)
                .placeholder(R.mipmap.app_no_data_icon)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        imageView.setImageDrawable(resource);
                    }
                });
    }

    public static void setCircleImage(final String imageUrl, final ImageView imageView, Activity activity) {
        Glide.with(activity)
                .load(imageUrl)
                .error(R.mipmap.app_no_data_icon)
                .placeholder(R.mipmap.app_no_data_icon)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        imageView.setImageDrawable(resource);
                    }
                });
    }

}
