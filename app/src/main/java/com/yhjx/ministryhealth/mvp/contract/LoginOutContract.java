package com.yhjx.ministryhealth.mvp.contract;

import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.mvp.IView;

public interface LoginOutContract {
        interface Presenter {
                void loginOut();
            }

            interface View extends IView {
                void loginOutSuccess(BaseEntity data);

            }
}
