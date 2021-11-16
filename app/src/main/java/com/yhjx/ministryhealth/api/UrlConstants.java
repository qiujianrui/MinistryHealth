package com.yhjx.ministryhealth.api;


import com.yhjx.ministryhealth.BuildConfig;

public class UrlConstants {
    public static final String APP_HOST = BuildConfig.APP_HOST;

    //用户协议与隐私
    public static final String PROTOCOL_AND_PRIVACY="/prod-api/system/user/userAgreement";

    //注册获取验证码
    public static final String REGISTER_VERIFICATION="/prod-api/system/user/registerVerification";

    //注册
    public static final String ADD_LOGIN="/prod-api/system/user/addLogin";

    //修改密码获取验证码
    public static final String UPD_VERIFICATION="/prod-api/system/user/updVerification";

    //忘记密码
    public static final String RESET_PASSWORD="/prod-api/system/user/appResetPassword";

    //登录
    public static final String LOGIN="/prod-api/system/patientInfo/appLogin";

    //修改密码
    public static final String APP_UPD_PWD="/prod-api/system/patientInfo/appUpdPwd";

    //退出登录
    public static final String EXIT_LOGIN="/prod-api/system/patientInfo/exitLogin";

}
