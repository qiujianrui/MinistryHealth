package com.yhjx.ministryhealth.mvp.presenter;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.mvp.BasePresenter;
import com.library.basemodule.net.RxHelper;
import com.yhjx.ministryhealth.api.AppHttpUtils;
import com.yhjx.ministryhealth.api.BaseObserver;
import com.yhjx.ministryhealth.mvp.contract.AddRemindContract;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AddRemindPresenter extends BasePresenter<AddRemindContract.View> implements AddRemindContract.Presenter {
    public AddRemindPresenter(AddRemindContract.View iView, Activity activity) {
        super(iView, activity);
    }

    @Override
    public void addRemind(String type, String remindData, String dateStart) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("type", type);
        hashMap.put("remindData", remindData);
        hashMap.put("dateStart", dateStart);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JSON.toJSONString(hashMap));
        AppHttpUtils.getApiService().addRemindInfo(requestBody)
                .compose(RxHelper.ioMain())
                .subscribe(new BaseObserver<BaseEntity>(activityRef.get()) {

                    @Override
                    public void onSuccess(BaseEntity result) {
                        getView().addRemindSuccess(result);
                    }

                    @Override
                    public void onFailure(int errCode, String msg) {
                          getView().loadFailure(errCode, msg, "");
                    }
                });
    }
}
