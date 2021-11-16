package com.yhjx.ministryhealth.mvp.contract;

import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.mvp.IView;

public interface ChangePAWContract {
        interface Presenter {
                void changePassword(String pwd,String newPassword,String surePassword);
            }

            interface View extends IView {
                void changePasswordSuccess(BaseEntity data);

            }
}
