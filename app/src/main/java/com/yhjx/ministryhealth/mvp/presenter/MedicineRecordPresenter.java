package com.yhjx.ministryhealth.mvp.presenter;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.mvp.BasePresenter;
import com.library.basemodule.net.RxHelper;
import com.yhjx.ministryhealth.api.AppHttpUtils;
import com.yhjx.ministryhealth.api.BaseObserver;
import com.yhjx.ministryhealth.bean.MedicineListBean;
import com.yhjx.ministryhealth.bean.RemindDateBean;
import com.yhjx.ministryhealth.bean.RemindListBean;
import com.yhjx.ministryhealth.mvp.contract.MedicineRecordContract;

import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MedicineRecordPresenter extends BasePresenter<MedicineRecordContract.View> implements MedicineRecordContract.Presenter{
    public MedicineRecordPresenter(MedicineRecordContract.View iView, Activity activity) {
        super(iView, activity);
    }

    @Override
    public void getMedicineDate() {
        AppHttpUtils.getApiService().getMedicineDate()
                .compose(RxHelper.ioMain())
                .subscribe(new BaseObserver<BaseEntity<List<String>>>(activityRef.get()) {

                    @Override
                    public void onSuccess(BaseEntity<List<String>> result) {
                        getView().getMedicineDateSuccess(result.getData());
                    }

                    @Override
                    public void onFailure(int errCode, String msg) {
                        getView().loadFailure(errCode, msg, "");
                    }
                });
    }

    @Override
    public void getMedicine(String dateCreate) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("dateCreate", dateCreate);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JSON.toJSONString(hashMap));
        AppHttpUtils.getApiService().getMedicine(requestBody)
                .compose(RxHelper.ioMain())
                .subscribe(new BaseObserver<BaseEntity<List<MedicineListBean>>>(activityRef.get()) {

                    @Override
                    public void onSuccess(BaseEntity<List<MedicineListBean>> result) {
                        getView().getMedicine(result.getData());
                    }

                    @Override
                    public void onFailure(int errCode, String msg) {
                        getView().loadFailure(errCode, msg, "");
                    }
                });
    }
}
