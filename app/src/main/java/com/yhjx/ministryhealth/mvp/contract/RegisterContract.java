package com.yhjx.ministryhealth.mvp.contract;

import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.mvp.IView;

public interface RegisterContract {
        interface Presenter {
                void registerVerification(String phone);
                void addLogin(String phone,String pwd,String data);
            }

            interface View extends IView {
                void registerVerificationSuccess(BaseEntity data);
                void addLoginSuccess(BaseEntity data);
            }
}
