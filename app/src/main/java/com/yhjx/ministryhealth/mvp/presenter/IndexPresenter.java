package com.yhjx.ministryhealth.mvp.presenter;

import android.app.Activity;

import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.mvp.BasePresenter;
import com.library.basemodule.net.RxHelper;
import com.yhjx.ministryhealth.api.AppHttpUtils;
import com.yhjx.ministryhealth.api.BaseObserver;
import com.yhjx.ministryhealth.bean.IndexBean;
import com.yhjx.ministryhealth.mvp.contract.IndexContract;


public class IndexPresenter extends BasePresenter<IndexContract.View> implements IndexContract.Presenter {
    public IndexPresenter(IndexContract.View iView, Activity activity) {
        super(iView, activity);
    }

    @Override
    public void index() {
        AppHttpUtils.getApiService().index()
                .compose(RxHelper.ioMain())
                .subscribe(new BaseObserver<BaseEntity<IndexBean>>(activityRef.get()) {

                    @Override
                    public void onSuccess(BaseEntity<IndexBean> result) {
                        getView().indexSuccess(result.getData());
                    }

                    @Override
                    public void onFailure(int errCode, String msg) {
                          getView().loadFailure(errCode, msg, "");
                    }
                });
    }
}
