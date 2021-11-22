package com.yhjx.ministryhealth.mvp.presenter;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.mvp.BasePresenter;
import com.library.basemodule.net.RxHelper;
import com.library.basemodule.util.SPUtils;
import com.yhjx.ministryhealth.api.AppHttpUtils;
import com.yhjx.ministryhealth.api.BaseObserver;
import com.yhjx.ministryhealth.bean.MsgDetailBean;
import com.yhjx.ministryhealth.bean.MsgListBean;
import com.yhjx.ministryhealth.constants.SpConstants;
import com.yhjx.ministryhealth.mvp.contract.MessageContract;

import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MessagePresenter extends BasePresenter<MessageContract.View> implements MessageContract.Presenter {
    private final  int PAGE_SIZE=10;
    public MessagePresenter(MessageContract.View iView, Activity activity) {
        super(iView, activity);
    }

    @Override
    public void getMessageList(int pageNum) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("patientId", SPUtils.getInstance().getString(SpConstants.SP_KEY_PATIENT_ID));
        hashMap.put("pageNum", pageNum);
        hashMap.put("pageSize", PAGE_SIZE);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JSON.toJSONString(hashMap));

        AppHttpUtils.getApiService().getMsgList(requestBody)
                .compose(RxHelper.ioMain())
                .subscribe(new BaseObserver<BaseEntity<List<MsgListBean>>>(activityRef.get()) {

                    @Override
                    public void onSuccess(BaseEntity<List<MsgListBean>> result) {
                        getView().getMessageListSuccess(result.getRows());
                    }

                    @Override
                    public void onFailure(int errCode, String msg) {
                          getView().loadFailure(errCode, msg, "");
                    }
                });
    }

    @Override
    public void getMessageDetail(String id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", id);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JSON.toJSONString(hashMap));

        AppHttpUtils.getApiService().getMsgDetails(requestBody)
                .compose(RxHelper.ioMain())
                .subscribe(new BaseObserver<BaseEntity<MsgDetailBean>>(activityRef.get()) {

                    @Override
                    public void onSuccess(BaseEntity<MsgDetailBean> result) {
                        getView().getMessageDetailSuccess(result.getData());
                    }

                    @Override
                    public void onFailure(int errCode, String msg) {
                        getView().loadFailure(errCode, msg, "");
                    }
                });
    }
}
