package com.yhjx.ministryhealth.mvp.presenter;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.mvp.BasePresenter;
import com.library.basemodule.net.RxHelper;
import com.yhjx.ministryhealth.api.AppHttpUtils;
import com.yhjx.ministryhealth.api.BaseObserver;
import com.yhjx.ministryhealth.mvp.contract.ForgetPasswordContract;
import com.yhjx.ministryhealth.mvp.contract.RegisterContract;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ForgetPasswordPresenter extends BasePresenter<ForgetPasswordContract.View> implements ForgetPasswordContract.Presenter {
    public ForgetPasswordPresenter(ForgetPasswordContract.View iView, Activity activity) {
        super(iView, activity);
    }

    @Override
    public void updVerification(String phone) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("phone", phone);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JSON.toJSONString(hashMap));
        AppHttpUtils.getApiService().updVerification(requestBody)
                .compose(RxHelper.ioMain())
                .subscribe(new BaseObserver<BaseEntity>(activityRef.get()) {

                    @Override
                    public void onSuccess(BaseEntity result) {
                        getView().updVerificationSuccess(result);
                    }

                    @Override
                    public void onFailure(int errCode, String msg) {
                        getView().loadFailure(errCode, msg, "");
                    }
                });
    }

    @Override
    public void resetPassword(String phone, String pwd, String data) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("phone", phone);
        hashMap.put("pwd", pwd);
        hashMap.put("data", data);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JSON.toJSONString(hashMap));
        AppHttpUtils.getApiService().resetPassword(requestBody)
                .compose(RxHelper.ioMain())
                .subscribe(new BaseObserver<BaseEntity>(activityRef.get()) {

                    @Override
                    public void onSuccess(BaseEntity result) {
                        getView().resetPasswordSuccess(result);
                    }

                    @Override
                    public void onFailure(int errCode, String msg) {
                        getView().loadFailure(errCode, msg, "");
                    }
                });
    }
}
