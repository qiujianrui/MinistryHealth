package com.yhjx.ministryhealth.api;


import com.library.basemodule.net.GsonDConverterFactory;
import com.library.basemodule.net.HttpCacheInterceptor;
import com.library.basemodule.util.LogUtils;
import com.library.basemodule.util.Utils;
import com.yhjx.ministryhealth.BuildConfig;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class AppHttpUtils {

    public static final int DEFAULT_TIMEOUT = 20000;

    private ApiService mApiService;

    private AppHttpUtils() {
        HttpLoggingInterceptor logInterceptor = null;

        if (BuildConfig.DEBUG) { // 如果不是正式环境才打印 响应的json
            logInterceptor = new HttpLoggingInterceptor(message -> {
                try {
                    String text = URLDecoder.decode(message.replaceAll("%(?![0-9a-fA-F]{2})", "%25"), "utf-8");
                    LogUtils.dTag("请求==》",text);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    LogUtils.d(message);
                }
            });
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        // 添加缓存
        File cacheFile = new File(Utils.getApp().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                //添加缓存
                .addNetworkInterceptor(new HttpCacheInterceptor())
                // 添加公共参数
                .addInterceptor(new HeaderInterceptor())
                .cache(cache);
        if (BuildConfig.DEBUG) {
            // 添加日志信息
            okHttpClientBuilder.addInterceptor(logInterceptor);
            
        }

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClientBuilder.build())
                .addConverterFactory(GsonDConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(UrlConstants.APP_HOST)
                .build();
        mApiService = retrofit.create(ApiService.class);
    }

    //  创建单例
    private static class SingletonHolder {
        private static final AppHttpUtils INSTANCE = new AppHttpUtils();
    }

    public static ApiService getApiService() {
        return SingletonHolder.INSTANCE.mApiService;
    }
}
