package com.yhjx.ministryhealth.mvp.presenter;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.mvp.BasePresenter;
import com.library.basemodule.net.RxHelper;
import com.yhjx.ministryhealth.api.AppHttpUtils;
import com.yhjx.ministryhealth.api.BaseObserver;
import com.yhjx.ministryhealth.bean.QuestionnaireBean;
import com.yhjx.ministryhealth.mvp.contract.PaperContract;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class PaperPresenter extends BasePresenter<PaperContract.View> implements PaperContract.Presenter {
    public PaperPresenter(PaperContract.View iView, Activity activity) {
        super(iView, activity);
    }

    @Override
    public void getPaperDetail(String id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("msgId", "1");
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JSON.toJSONString(hashMap));
        AppHttpUtils.getApiService().getPaperDetails(requestBody)
                .compose(RxHelper.ioMain())
                .subscribe(new BaseObserver<BaseEntity<QuestionnaireBean>>(activityRef.get()) {

                    @Override
                    public void onSuccess(BaseEntity<QuestionnaireBean> result) {
                            getView().getPaperDetailSuccess(result.getData());
                    }

                    @Override
                    public void onFailure(int errCode, String msg) {
                          getView().loadFailure(errCode, msg, "");
                    }
                });
    }

    @Override
    public void addPaper(String msgId,String paperData) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("msgId", msgId);
        hashMap.put("paperData", paperData);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JSON.toJSONString(hashMap));
        AppHttpUtils.getApiService().addPaper(requestBody)
                .compose(RxHelper.ioMain())
                .subscribe(new BaseObserver<BaseEntity>(activityRef.get()) {

                    @Override
                    public void onSuccess(BaseEntity result) {
                            getView().addPaperSuccess();
                    }

                    @Override
                    public void onFailure(int errCode, String msg) {
                      getView().loadFailure(errCode, msg, "");
                    }
                });
    }
}
