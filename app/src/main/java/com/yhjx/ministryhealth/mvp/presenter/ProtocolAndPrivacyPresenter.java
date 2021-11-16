package com.yhjx.ministryhealth.mvp.presenter;

import android.app.Activity;

import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.mvp.BasePresenter;
import com.library.basemodule.net.RxHelper;
import com.yhjx.ministryhealth.api.AppHttpUtils;
import com.yhjx.ministryhealth.api.BaseObserver;
import com.yhjx.ministryhealth.bean.ProtocolPrivacyBean;
import com.yhjx.ministryhealth.mvp.contract.ProtocolAndPrivacyContract;


public class ProtocolAndPrivacyPresenter extends BasePresenter<ProtocolAndPrivacyContract.View> implements ProtocolAndPrivacyContract.Presenter {
    public ProtocolAndPrivacyPresenter(ProtocolAndPrivacyContract.View iView, Activity activity) {
        super(iView, activity);
    }

    @Override
    public void getProtocolAndPrivacy() {
        AppHttpUtils.getApiService().protocolAndPrivacy()
                .compose(RxHelper.ioMain())
                .subscribe(new BaseObserver<BaseEntity<ProtocolPrivacyBean>>(activityRef.get()) {


                    @Override
                    public void onSuccess(BaseEntity<ProtocolPrivacyBean> result) {
                        getView().protocolAndPrivacySuccess(result.getData());
                    }

                    @Override
                    public void onFailure(int errCode, String msg) {
                          getView().loadFailure(errCode, msg, "");
                    }
                });
    }
}
