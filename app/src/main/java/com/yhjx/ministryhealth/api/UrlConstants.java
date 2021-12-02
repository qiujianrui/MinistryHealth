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

    //首页
    public static final String INDEX="/prod-api/system/index/getIndex";

    //根据时间获取服药记录
    public static final String GET_MEDICINE="/prod-api/system/index/getMedicine";

    //获取全部服药记录时间信息
    public static final String GET_MEDICINE_DATE="/prod-api/system/index/getMedicineDate";

    //获取全部提醒时间信息
    public static final String GET_REMIND_DATE="/prod-api/system/index/getRemindDate";

    //根据时间获取提醒记录
    public static final String GET_REMIND="/prod-api/system/index/getRemind";

    //添加剂量
    public static final String ADD_MEDICINE_RECORD="/prod-api/system/index/addMedicineRecord";

    //添加提醒
    public static final String ADD_REMIND_INFO="/prod-api/system/index/addRemindInfo";

    //消息列表
    public static final String GET_MSG_LIST="/prod-api/system/index/getMsgList";

    //消息详情
    public static final String GET_MSG_DETAILS="/prod-api/system/index/getMsgDetails";

    //问卷详情
    public static final String GET_PAPER_DETAILS="/prod-api/system/index/getPaperDetails";

    //提交问卷
    public static final String ADD_PAPER="/prod-api/system/index/addPaper";

    //咨询历史记录
    public static final String GET_HISTORY_MSG="/prod-api/system/index/getHistoryMsg";

    //轮询消息
    public static final String GET_END_MSG="/prod-api/system/index/getEndMsg";

    //发送消息
    public static final String ADD_MSG="/prod-api/system/index/addMsg";

    //版本升级
    static final String UPDATE_APP="/prod-api/system/index/getVersion";
}
