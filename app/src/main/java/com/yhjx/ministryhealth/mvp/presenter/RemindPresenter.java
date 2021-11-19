package com.yhjx.ministryhealth.mvp.presenter;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.mvp.BasePresenter;
import com.library.basemodule.net.RxHelper;
import com.yhjx.ministryhealth.api.AppHttpUtils;
import com.yhjx.ministryhealth.api.BaseObserver;
import com.yhjx.ministryhealth.bean.RemindDateBean;
import com.yhjx.ministryhealth.bean.RemindListBean;
import com.yhjx.ministryhealth.mvp.contract.RemindContract;

import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RemindPresenter extends BasePresenter<RemindContract.View> implements RemindContract.Presenter{
    public RemindPresenter(RemindContract.View iView, Activity activity) {
        super(iView, activity);
    }

    @Override
    public void getRemindDate() {
        AppHttpUtils.getApiService().getRemindDate()
                .compose(RxHelper.ioMain())
                .subscribe(new BaseObserver<BaseEntity<RemindDateBean>>(activityRef.get()) {

                    @Override
                    public void onSuccess(BaseEntity<RemindDateBean> result) {
                            getView().getRemindDateSuccess(result.getData());
                    }

                    @Override
                    public void onFailure(int errCode, String msg) {
                          getView().loadFailure(errCode, msg, "");
                    }
                });
    }

    @Override
    public void getRemind(String dateCreate) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("dateCreate", dateCreate);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JSON.toJSONString(hashMap));
        AppHttpUtils.getApiService().getRemind(requestBody)
                .compose(RxHelper.ioMain())
                .subscribe(new BaseObserver<BaseEntity<List<RemindListBean>>>(activityRef.get()) {

                    @Override
                    public void onSuccess(BaseEntity<List<RemindListBean>> result) {
                            getView().getRemind(result.getData());
                    }

                    @Override
                    public void onFailure(int errCode, String msg) {
                          getView().loadFailure(errCode, msg, "");
                    }
                });
    }
}
