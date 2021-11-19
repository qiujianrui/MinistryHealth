package com.yhjx.ministryhealth.mvp.contract;

import com.library.basemodule.mvp.IView;
import com.yhjx.ministryhealth.bean.MsgListBean;

import java.util.List;

public interface MessageContract {
        interface Presenter {
                void getMessageList();
            }

            interface View extends IView {
                void getMessageListSuccess(List<MsgListBean> data);

            }
}
