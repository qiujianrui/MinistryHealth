package com.yhjx.ministryhealth.mvp.contract;

import com.library.basemodule.mvp.IView;
import com.yhjx.ministryhealth.bean.ChatMsgBean;

import java.util.List;

public interface ChatContract {
        interface Presenter {
                void getHistoryMsg(int pageNum,String patientId);
                void getEndMsg();
                void sendMsg(String sendData);
            }

            interface View extends IView {
                void getHistoryMsgSuccess(List<ChatMsgBean> data);
                void getEndMsgSuccess(List<ChatMsgBean> data);
                void sendMsgSuccess(ChatMsgBean data);
            }
}
