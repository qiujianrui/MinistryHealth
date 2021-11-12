package com.library.basemodule.util.glide;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.library.basemodule.R;
import com.library.basemodule.util.FileUtils;
import com.library.basemodule.util.LogUtils;
import com.library.basemodule.util.ObjectUtils;
import com.library.basemodule.util.StringUtils;


import java.io.File;

import static com.bumptech.glide.request.RequestOptions.fitCenterTransform;



public final class GlideUtils {

    public static void intoCircle(ImageView imageView, String url) {
        Context context = imageView.getContext();
        RequestOptions requestOptions = new RequestOptions()
                .transform(new CircleTransformImpl());
        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }

    public static void intoRadius(ImageView imageView, String url,int roundingRadius) {
        RoundedCorners roundedCorners = new RoundedCorners(roundingRadius);
        Context context = imageView.getContext();
        RequestOptions requestOptions = RequestOptions.bitmapTransform(roundedCorners);
        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }

    public static void intoImage(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

    public static void intoGif(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context)
                .asGif()
                .load(url)
                .into(imageView);
    }
}
