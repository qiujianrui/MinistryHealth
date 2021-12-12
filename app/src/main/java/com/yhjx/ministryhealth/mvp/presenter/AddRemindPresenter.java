package com.yhjx.ministryhealth.mvp.presenter;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.mvp.BasePresenter;
import com.library.basemodule.net.RxHelper;
import com.yhjx.ministryhealth.api.AppHttpUtils;
import com.yhjx.ministryhealth.api.BaseObserver;
import com.yhjx.ministryhealth.bean.DrugNameBean;
import com.yhjx.ministryhealth.mvp.contract.AddRemindContract;

import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AddRemindPresenter extends BasePresenter<AddRemindContract.View> implements AddRemindContract.Presenter {
    public AddRemindPresenter(AddRemindContract.View iView, Activity activity) {
        super(iView, activity);
    }

    @Override
    public void addRemind(String type, String remindData,String dateCreate) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("type", type);
        hashMap.put("remindData", remindData);
        hashMap.put("dateCreate", dateCreate);
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

    @Override
    public void addRemindDrug(HashMap<String, Object> hashMap) {
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

    @Override
    public void getDrug(String name) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", name);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JSON.toJSONString(hashMap));
        AppHttpUtils.getApiService().getDrug(requestBody)
                .compose(RxHelper.ioMain())
                .subscribe(new BaseObserver<BaseEntity<List<DrugNameBean>>>(activityRef.get(),false,false) {

                    @Override
                    public void onSuccess(BaseEntity<List<DrugNameBean>> result) {
                        getView().getDrugSuccess(result.getData());
                    }

                    @Override
                    public void onFailure(int errCode, String msg) {
                        getView().loadFailure(errCode, msg, "");
                    }
                });
    }

    @Override
    public void revampRemindInfo(HashMap<String, Object> hashMap) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JSON.toJSONString(hashMap));
        AppHttpUtils.getApiService().revampRemindInfo(requestBody)
                .compose(RxHelper.ioMain())
                .subscribe(new BaseObserver<BaseEntity>(activityRef.get()) {

                    @Override
                    public void onSuccess(BaseEntity result) {
                        getView().revampRemindInfoSuccess();
                    }

                    @Override
                    public void onFailure(int errCode, String msg) {
                        getView().loadFailure(errCode, msg, "");
                    }
                });
    }
}
