package com.yhjx.ministryhealth.api;


import com.mirrorego.counselor.BuildConfig;

public class UrlConstants {
    public static final String BASE_URL = BuildConfig.BASE_URL;//API HOST
    public static final String H5_HOST = BuildConfig.H5_HOST;
    public static final String APP_HOST = BuildConfig.APP_HOST;

    //个案建立及草稿H5
    public static final String H5_EDIT_USER_CASE=H5_HOST+"reportpage/#/userCase/editingcase/basic"+"?app=android";
    //个案报告
    public static final String H5_USER_CASE_INDEX=H5_HOST+"reportpage/#/userCase/userCaseReport/index"+"?app=android";
    //量表管理
    public static final String H5_SCALEMANAGER=BASE_URL+"scaleManagerVue/#/?platformType=2&app=android";
    //测评报告
    public static final String H5_USER_REPORT=H5_HOST+"reportpage/#/basePage/redirectPage?pageType=app&&app=android";
    //一键转逐字稿
    public static final String H5_AUDIO_TO_TEXT=APP_HOST+"audiototext/";


    /*--------------------------------------------------------------------------*/
    //上传图片
    public static final String UPDATELOAD_PHOTOBOX="svr/UpdateLoadPhotoboxReq";

    //获取验证码
    public static final String VERIFY_CODE="svr/VerifyCodeReq";

    //咨询师登陆
    public static final String COUNSE_LORLOGIN ="svr/CounselorLoginReq";

    //咨询师退出登录
    public static final String COUNSE_LOGOUT ="svr/CounselorLogOutReq";

    //咨询师注册前的参数获取
    public static final String REGISTERED_BEFORE ="svr/RegisteredBeforeReq";

    //咨询师注册
    public static final String REGISTERED ="svr/RegisteredReq";

    //咨询师产品认证
    public static final String TERRACE_AUTHENTICATION ="svr/TerraceAuthenticationReq";

    //消息历史记录
    public static final String MSG_RECORD_HISTORY ="svr/MsgRecordReq2";

    //消息轮播
    public static final String MSG_RECORD="svr/RecvMsgReq2";

    //发送消息
    public static final String MSG_RECORD_SEND="svr/SendMsgReq2";

    //文字咨询消息列表  ConsultRecordReq
    public static final String TEXT_CONSULT_RECORD="svr/MsgListReq";

    //未读消息数量 BadgeNumReq
    public static final String BADGE_NUM="svr/StatusCountReq";

    //移除聊天记录（文字咨询聊天）
    public static final String REMOVE_TIM_MSG="svr/RemoveTImMsgReq";

    //咨询师我的主页面
    public static final String COUNSELOR_MINE_INFO="svr/CounselorMineInfoReq";

    //个人信息
    public static final String CONSULT_INFO="svr/ConsultInfoReq";

    //订单详情
    public static final String CONSULT_ORDER_INFO="svr/ConsultOrderInfoReq";

    //进入设置工作状态页面
    public static final String WORK_STATUS_BEFORE="svr/WorkStatusBeforeReq";

    //设置工作状态
    public static final String WORK_STATUS="svr/WorkStatusReq";

    //详细收入
    public static final String INCOME_INFO="svr/InComeInfoReq";

    //进入提现
    public static final String WITHDRAW_DEPOSIT_BEFORE="svr/WithdrawDepositBeforeReq";

    //提现
    public static final String WITHDRAW_DEPOSIT="svr/WithdrawDepositReq";

    //提现记录
    public static final String WITHDRAWAL_RECORD="svr/WithdrawalRecordReq";

    //意见反馈
    public static final String FEED_BACK ="svr/FeedbackReq";

    //关于我们
    public static final String ABOUT_US ="svr/AboutUsReq";

    //用户协议与隐私
    public static final String PROTOCOL_AND_PRIVACY="svr/ProtocolAndPrivacyReq";

    //判断是否推送
    public static final String IS_PUSH_MESSAGE="svr/IsPushMessageReq";

    //版本升级
    public static final String UPDATE_APP="svr/UpdateAppReq";

    //咨询师系统通知列表
    public static final String SYSTEM_NOTICE_LIST_BOX="svr/SystemNoticeListBoxReq";

    //咨询师系统通知列表详情
    public static final String SYSTEM_NOTICE_INFO="svr/AndroidSystemNoticeInfoReq";

    //咨询师系统通知已读
    public static final String READED_SYSTEM_NOTICE="svr/ReadedSystemNoticeReq";

    //咨询师排班工作日情况
    public static final String MY_WORK_DAY_INFO="svr/MyWorkDayInfoReq";

    //咨询师排班信息
    public static final String RESERVE_INFO="svr/ReserveInfoReq2";

    //排班时间 与 预约时间
    public static final String SCHEDULING_TABLE="svr/SchedulingTableReq";

    //排班数据
    public static final String CONFIRM_SCHEDULING="svr/ConfirmSchedulingReq";

    //确认排班
    public static final String MODIFY_CONFIRM_SCHEDULING  ="svr/ModifyConfirmSchedulingReq2";

    //确认语音订单
    public static final String VOICE_NOTICE_CONFIRM  ="svr/VoiceNoticeConfirmReq";

    //工作台入口列表 备份：/svr/selectBench 不显示逐字稿
    public static final String SELECT_BENCH  ="svr/androidBench";

    //个案-查询未建立个案订单列表
    public static final String SELECT_ORDER  ="mirrorego/userCaseBasic/selectOrder";

    //个案-（草稿或已发布）列表
    public static final String USER_CASE_BASIC  ="mirrorego/userCaseBasic/list";

    //个案-基本信息-删除-1代表ucId
    public static final String DELETE_CASE_DRAFTS  ="mirrorego/userCaseBasic/remove";

    //个案-基本信息-删除-1代表ucId
    public static final String UDPATE_UC_VISIBILITY  ="mirrorego/userCaseBasic/udpateUcVisibility";

    //个案-咨询师-可见性列表-4代表ucId
    public static final String UC_VISIBILITY_LIST  ="mirrorego/userCaseBasic/ucVisibility";

    //个案管理-插入即时消息
    public static final String INSERT_IM_MSG  ="mirrorego/userCaseBasic/insertImMsg";

    //患者详情
    public static final String PATIENT_DETAIL ="svr/selectSick";

    //患者详情-个案列表
    public static final String USER_DETAIL_CASE_LIST ="mirrorego/userCaseBasic/userDetailList";

    //危机预警-列表
    public static final String RISK_ACCESSMENT_USERCASE_LIST ="mirrorego/userCaseBasic/riskAccessmentUserCaseList";

    //危机预警-上报个案
    public static final String REPORT_RISK_ACCESSMENT ="mirrorego/userCaseBasic/reportRiskAccessment";

    //订单详情
    public static final String SELECT_CONSULT_ORDER ="svr/selectConsultOrder";

    //名片
    public static final String CODE_COUNSELOR  ="minappCode4Counselor";

    //排班-修改发布的排班
    public static final String MODIFY_PUBLISH_DUTY  ="svr/ModifyPublishDutyReq";
}
