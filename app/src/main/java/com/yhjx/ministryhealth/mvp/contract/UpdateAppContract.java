package com.yhjx.ministryhealth.mvp.contract;

import com.library.basemodule.mvp.IView;
import com.yhjx.ministryhealth.bean.UpdateAppBean;


public interface UpdateAppContract {
        interface Presenter {
                void getUpDateApp();
            }

            interface View extends IView {
                void UpDateAppSuccess(UpdateAppBean bean);

            }
}
