package com.yhjx.ministryhealth.api;


import com.library.basemodule.entity.BaseEntity;
import com.yhjx.ministryhealth.bean.LoginBean;
import com.yhjx.ministryhealth.bean.ProtocolPrivacyBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiService {


    //用户协议与隐私
    @POST(UrlConstants.PROTOCOL_AND_PRIVACY)
    Observable<BaseEntity<ProtocolPrivacyBean>> protocolAndPrivacy();

    //注册获取验证码
    @POST(UrlConstants.REGISTER_VERIFICATION)
    Observable<BaseEntity> registerVerification(@Body RequestBody requestBody);

    //修改密码获取验证码
    @POST(UrlConstants.UPD_VERIFICATION)
    Observable<BaseEntity> updVerification(@Body RequestBody requestBody);

    //注册
    @POST(UrlConstants.ADD_LOGIN)
    Observable<BaseEntity> addLogin(@Body RequestBody requestBody);

    //忘记密码
    @POST(UrlConstants.RESET_PASSWORD)
    Observable<BaseEntity> resetPassword(@Body RequestBody requestBody);

    //登录
    @POST(UrlConstants.LOGIN)
    Observable<BaseEntity<LoginBean>> login(@Body RequestBody requestBody);

    //修改密码
    @POST(UrlConstants.APP_UPD_PWD)
    Observable<BaseEntity> appUpdPAW(@Body RequestBody requestBody);

    //退出登录
    @POST(UrlConstants.EXIT_LOGIN)
    Observable<BaseEntity> exitLogin();
}
