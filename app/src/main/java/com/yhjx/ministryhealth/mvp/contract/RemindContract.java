package com.yhjx.ministryhealth.mvp.contract;

import com.library.basemodule.mvp.IView;
import com.yhjx.ministryhealth.bean.RemindDateBean;
import com.yhjx.ministryhealth.bean.RemindListBean;

import java.util.List;

public interface RemindContract {
        interface Presenter {
                void getRemindDate();
                void getRemind(String dateCreate);
            }

            interface View extends IView {
                void getRemindDateSuccess(RemindDateBean data);
                void getRemind(List<RemindListBean> data);
            }
}
