package com.yhjx.ministryhealth.mvp.contract;

import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.mvp.IView;

public interface ForgetPasswordContract {
    interface Presenter {
        void updVerification(String phone);
        void resetPassword(String phone,String pwd,String data);
    }

    interface View extends IView {
        void updVerificationSuccess(BaseEntity data);
        void resetPasswordSuccess(BaseEntity data);
    }
}
