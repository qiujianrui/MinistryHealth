package com.yhjx.ministryhealth.api;


import com.library.basemodule.util.SPUtils;
import com.library.basemodule.util.StringUtils;
import com.yhjx.ministryhealth.BuildConfig;
import com.yhjx.ministryhealth.constants.SpConstants;


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
       String patientId = SPUtils.getInstance().getString(SpConstants.SP_KEY_PATIENT_ID);

       Request.Builder build = chain.request().newBuilder();
       // 添加token
       if (!StringUtils.isEmpty(token)) {
           build.header("token", token);
       }
       if (!StringUtils.isEmpty(Phone)) {
           build.header("phone", Phone);
       }
       if (!StringUtils.isEmpty(Phone)) {
           build.header("patientId", patientId);
       }
       build.addHeader("version", BuildConfig.VERSION_NAME);
       build.addHeader("channel", "1");
       return chain.proceed(build.build());
   }
}
