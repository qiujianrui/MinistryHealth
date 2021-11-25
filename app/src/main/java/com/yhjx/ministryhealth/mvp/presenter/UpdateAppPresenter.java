package com.yhjx.ministryhealth.mvp.presenter;

import android.app.Activity;


import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.mvp.BasePresenter;
import com.library.basemodule.net.RxHelper;
import com.yhjx.ministryhealth.api.AppHttpUtils;
import com.yhjx.ministryhealth.api.BaseObserver;
import com.yhjx.ministryhealth.bean.UpdateAppBean;
import com.yhjx.ministryhealth.mvp.contract.UpdateAppContract;


public class UpdateAppPresenter extends BasePresenter<UpdateAppContract.View> implements UpdateAppContract.Presenter {
    public UpdateAppPresenter(UpdateAppContract.View iView, Activity activity) {
        super(iView, activity);
    }

    @Override
    public void getUpDateApp() {
        AppHttpUtils.getApiService().UpdateApp()
                .compose(RxHelper.ioMain())
                .subscribe(new BaseObserver<BaseEntity<UpdateAppBean>>(activityRef.get()) {

                    @Override
                    public void onSuccess(BaseEntity<UpdateAppBean> result) {
                        getView().UpDateAppSuccess(result.getData());
                    }

                    @Override
                    public void onFailure(int errCode, String msg) {
                        getView().loadFailure(errCode,msg,"");
                    }
                });
    }
}
