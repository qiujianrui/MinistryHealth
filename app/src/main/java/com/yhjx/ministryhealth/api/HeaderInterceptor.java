package com.yhjx.ministryhealth.api;


import com.library.basemodule.util.SPUtils;
import com.library.basemodule.util.StringUtils;
import com.mirrorego.counselor.BuildConfig;
import com.mirrorego.counselor.constants.SpConstants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author jere
 * @time 2020/6/9
 * 说明：
 */
public class HeaderInterceptor implements Interceptor {

   @Override
   public Response intercept(Chain chain) throws IOException {
       String token = SPUtils.getInstance().getString(SpConstants.SP_KEY_USER_TOKEN);
       String Phone = SPUtils.getInstance().getString(SpConstants.SP_KEY_PHONE);
       Request.Builder build = chain.request().newBuilder();
       // 添加token
       if (!StringUtils.isEmpty(token)) {
           build.header("Token", token);
       }
       if (!StringUtils.isEmpty(Phone)) {
           build.header("Phone", Phone);
       }
       build.addHeader("Version", BuildConfig.API_VERSION);
       build.addHeader("Channel", "1");
       return chain.proceed(build.build());
   }
}
