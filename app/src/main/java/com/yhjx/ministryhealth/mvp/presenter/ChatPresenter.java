package com.yhjx.ministryhealth.mvp.presenter;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.mvp.BasePresenter;
import com.library.basemodule.net.RxHelper;
import com.library.basemodule.util.SPUtils;
import com.yhjx.ministryhealth.api.AppHttpUtils;
import com.yhjx.ministryhealth.api.BaseObserver;
import com.yhjx.ministryhealth.bean.ChatMsgBean;
import com.yhjx.ministryhealth.constants.SpConstants;
import com.yhjx.ministryhealth.mvp.contract.ChatContract;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ChatPresenter extends BasePresenter<ChatContract.View> implements ChatContract.Presenter {
    private final  int PAGE_SIZE=20;
    public ChatPresenter(ChatContract.View iView, Activity activity) {
        super(iView, activity);
    }

    @Override
    public void getHistoryMsg(int pageNum, String patientId) {
    HashMap<String, Object> hashMap = new HashMap<>();
    hashMap.put("pageNum", pageNum);
    hashMap.put("pageSize", PAGE_SIZE);
    hashMap.put("patientId", SPUtils.getInstance().getString(SpConstants.SP_KEY_PATIENT_ID));
    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JSON.toJSONString(hashMap));
    AppHttpUtils.getApiService().getHistoryMsg(requestBody)
            .compose(RxHelper.ioMain())
            .subscribe(new BaseObserver<BaseEntity<List<ChatMsgBean>>>(activityRef.get()) {

                @Override
                public void onSuccess(BaseEntity<List<ChatMsgBean>> result) {
                    getView().getHistoryMsgSuccess(result.getRows());
                }

                @Override
                public void onFailure(int errCode, String msg) {
                      getView().loadFailure(errCode, msg, "");
                }
            });
    }

    @Override
    public void getEndMsg() {
        AppHttpUtils.getApiService().getEndMsg()
                .compose(RxHelper.ioMain())
                .subscribe(new BaseObserver<BaseEntity<List<ChatMsgBean>>>(activityRef.get(),false,false) {

                    @Override
                    public void onSuccess(BaseEntity<List<ChatMsgBean>> result) {
                        getView().getEndMsgSuccess(result.getRows());
                    }

                    @Override
                    public void onFailure(int errCode, String msg) {
                        getView().loadFailure(errCode, msg, "");
                    }
                });
    }

    @Override
    public void sendMsg(String sendData) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sendData", sendData);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JSON.toJSONString(hashMap));
        AppHttpUtils.getApiService().addMsg(requestBody)
                .compose(RxHelper.ioMain())
                .subscribe(new BaseObserver<BaseEntity>(activityRef.get()) {

                    @Override
                    public void onSuccess(BaseEntity result) {
                        ChatMsgBean bean=new ChatMsgBean();
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                        Date date=new Date(System.currentTimeMillis());
                        bean.setDateSend(simpleDateFormat.format(date));
                        bean.setDataMsg(sendData);
                        bean.setMsgType("1");
                       getView().sendMsgSuccess(bean);
                    }

                    @Override
                    public void onFailure(int errCode, String msg) {
                        getView().loadFailure(errCode, msg, "");
                    }
                });
    }
}
