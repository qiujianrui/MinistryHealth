package com.yhjx.ministryhealth.mvp.presenter;

import android.app.Activity;

import com.library.basemodule.mvp.BasePresenter;
import com.yhjx.ministryhealth.mvp.contract.ChatContract;

public class ChatPresenter extends BasePresenter<ChatContract.View> implements ChatContract.Presenter {
    public ChatPresenter(ChatContract.View iView, Activity activity) {
        super(iView, activity);
    }

    @Override
    public void getHistoryMsg(String pageNum, String patientId) {

    }

    @Override
    public void getEndMsg() {

    }

    @Override
    public void sendMsg(String sendData) {

    }
}
