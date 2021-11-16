package com.yhjx.ministryhealth.mvp.presenter;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.mvp.BasePresenter;
import com.library.basemodule.net.RxHelper;
import com.yhjx.ministryhealth.api.AppHttpUtils;
import com.yhjx.ministryhealth.api.BaseObserver;

import com.yhjx.ministryhealth.mvp.contract.LoginOutContract;


public class LoginOutPresenter extends BasePresenter<LoginOutContract.View> implements LoginOutContract.Presenter {
    public LoginOutPresenter(LoginOutContract.View iView, Activity activity) {
        super(iView, activity);
    }


    @Override
    public void loginOut() {
        AppHttpUtils.getApiService().exitLogin()
                .compose(RxHelper.ioMain())
                .subscribe(new BaseObserver<BaseEntity>(activityRef.get()) {

                    @Override
                    public void onSuccess(BaseEntity result) {
                        getView().loginOutSuccess(result);
                    }

                    @Override
                    public void onFailure(int errCode, String msg) {
                          getView().loadFailure(errCode, msg, "");
                    }
                });
    }
}
