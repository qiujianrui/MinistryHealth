package com.yhjx.ministryhealth.mvp.presenter;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.mvp.BasePresenter;
import com.library.basemodule.net.RxHelper;
import com.library.basemodule.util.SPUtils;
import com.yhjx.ministryhealth.api.AppHttpUtils;
import com.yhjx.ministryhealth.api.BaseObserver;
import com.yhjx.ministryhealth.constants.SpConstants;
import com.yhjx.ministryhealth.mvp.contract.ChangePAWContract;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ChangePAWPresenter extends BasePresenter<ChangePAWContract.View> implements ChangePAWContract.Presenter {
    public ChangePAWPresenter(ChangePAWContract.View iView, Activity activity) {
        super(iView, activity);
    }

    @Override
    public void changePassword(String pwd,String newPassword,String surePassword) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("pwd",pwd );
        hashMap.put("newPassword",newPassword );
        hashMap.put("surePassword",surePassword );
        hashMap.put("patientId", SPUtils.getInstance().getString(SpConstants.SP_KEY_PATIENT_ID));
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JSON.toJSONString(hashMap));
        AppHttpUtils.getApiService().appUpdPAW(requestBody)
                .compose(RxHelper.ioMain())
                .subscribe(new BaseObserver<BaseEntity>(activityRef.get()) {

                    @Override
                    public void onSuccess(BaseEntity result) {
                        getView().changePasswordSuccess(result);
                    }

                    @Override
                    public void onFailure(int errCode, String msg) {
                          getView().loadFailure(errCode, msg, "");
                    }
                });
    }
}
