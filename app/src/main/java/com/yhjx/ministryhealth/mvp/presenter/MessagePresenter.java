package com.yhjx.ministryhealth.mvp.presenter;

import android.app.Activity;

import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.mvp.BasePresenter;
import com.library.basemodule.net.RxHelper;
import com.yhjx.ministryhealth.api.AppHttpUtils;
import com.yhjx.ministryhealth.api.BaseObserver;
import com.yhjx.ministryhealth.bean.MsgListBean;
import com.yhjx.ministryhealth.mvp.contract.MessageContract;

import java.util.HashMap;
import java.util.List;

public class MessagePresenter extends BasePresenter<MessageContract.View> implements MessageContract.Presenter {
    public MessagePresenter(MessageContract.View iView, Activity activity) {
        super(iView, activity);
    }

    @Override
    public void getMessageList() {
        AppHttpUtils.getApiService().getMsgList()
                .compose(RxHelper.ioMain())
                .subscribe(new BaseObserver<BaseEntity<List<MsgListBean>>>(activityRef.get()) {

                    @Override
                    public void onSuccess(BaseEntity<List<MsgListBean>> result) {
                        getView().getMessageListSuccess(result.getData());
                    }

                    @Override
                    public void onFailure(int errCode, String msg) {
                          getView().loadFailure(errCode, msg, "");
                    }
                });
    }
}
