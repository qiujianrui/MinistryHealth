package com.yhjx.ministryhealth.api;


import com.library.basemodule.entity.BaseEntity;
import com.yhjx.ministryhealth.bean.ChatMsgBean;
import com.yhjx.ministryhealth.bean.IndexBean;
import com.yhjx.ministryhealth.bean.LoginBean;
import com.yhjx.ministryhealth.bean.MedicineListBean;
import com.yhjx.ministryhealth.bean.MsgDetailBean;
import com.yhjx.ministryhealth.bean.MsgListBean;
import com.yhjx.ministryhealth.bean.ProtocolPrivacyBean;
import com.yhjx.ministryhealth.bean.QuestionnaireBean;
import com.yhjx.ministryhealth.bean.RemindDateBean;
import com.yhjx.ministryhealth.bean.RemindListBean;

import java.util.List;

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

    //首页
    @POST(UrlConstants.INDEX)
    Observable<BaseEntity<IndexBean>> index();

    //获取全部提醒时间信息
    @POST(UrlConstants.GET_REMIND_DATE)
    Observable<BaseEntity<RemindDateBean>> getRemindDate();

    //根据时间获取提醒记录
    @POST(UrlConstants.GET_REMIND)
    Observable<BaseEntity<List<RemindListBean>>> getRemind(@Body RequestBody requestBody);

    //获取全部提醒时间信息
    @POST(UrlConstants.GET_MEDICINE_DATE)
    Observable<BaseEntity<List<String>>> getMedicineDate();

    //根据时间获取提醒记录
    @POST(UrlConstants.GET_MEDICINE)
    Observable<BaseEntity<List<MedicineListBean>>> getMedicine(@Body RequestBody requestBody);

    //添加剂量
    @POST(UrlConstants.ADD_MEDICINE_RECORD)
    Observable<BaseEntity> addMedicineRecord(@Body RequestBody requestBody);

    //添加提醒
    @POST(UrlConstants.ADD_REMIND_INFO)
    Observable<BaseEntity> addRemindInfo(@Body RequestBody requestBody);

    //消息列表
    @POST(UrlConstants.GET_MSG_LIST)
    Observable<BaseEntity<List<MsgListBean>>> getMsgList(@Body RequestBody requestBody);

    //消息详情
    @POST(UrlConstants.GET_MSG_DETAILS)
    Observable<BaseEntity<MsgDetailBean>> getMsgDetails(@Body RequestBody requestBody);

    //问卷详情
    @POST(UrlConstants.GET_PAPER_DETAILS)
    Observable<BaseEntity<QuestionnaireBean>> getPaperDetails(@Body RequestBody requestBody);

    //提交问卷
    @POST(UrlConstants.ADD_PAPER)
    Observable<BaseEntity> addPaper(@Body RequestBody requestBody);

    //咨询历史记录
    @POST(UrlConstants.GET_HISTORY_MSG)
    Observable<BaseEntity<List<ChatMsgBean>>> getHistoryMsg(@Body RequestBody requestBody);

    //轮询消息
    @POST(UrlConstants.GET_END_MSG)
    Observable<BaseEntity<List<ChatMsgBean>>> getEndMsg(@Body RequestBody requestBody);

    //提交问卷
    @POST(UrlConstants.ADD_MSG)
    Observable<BaseEntity> addMsg(@Body RequestBody requestBody);

}
