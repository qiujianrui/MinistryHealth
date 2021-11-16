package com.yhjx.ministryhealth.mvp.presenter;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.mvp.BasePresenter;
import com.library.basemodule.net.RxHelper;
import com.yhjx.ministryhealth.api.AppHttpUtils;
import com.yhjx.ministryhealth.api.BaseObserver;
import com.yhjx.ministryhealth.bean.LoginBean;
import com.yhjx.ministryhealth.mvp.contract.LoginContract;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {
    public LoginPresenter(LoginContract.View iView, Activity activity) {
        super(iView, activity);
    }

    @Override
    public void login(String phone, String pwd) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("phone", phone);
        hashMap.put("pwd", pwd);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JSON.toJSONString(hashMap));
        AppHttpUtils.getApiService().login(requestBody)
                .compose(RxHelper.ioMain())
                .subscribe(new BaseObserver<BaseEntity<LoginBean>>(activityRef.get()) {

                    @Override
                    public void onSuccess(BaseEntity<LoginBean> result) {
                        getView().loginSuccess(result.getData());
                    }

                    @Override
                    public void onFailure(int errCode, String msg) {
                          getView().loadFailure(errCode, msg, "");
                    }
                });
    }
}
