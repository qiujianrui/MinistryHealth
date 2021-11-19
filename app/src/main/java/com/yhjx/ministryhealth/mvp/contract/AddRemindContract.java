package com.yhjx.ministryhealth.mvp.contract;

import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.mvp.IView;

public interface AddRemindContract {
        interface Presenter {
                void addRemind(String type, String remindData, String dateStart);
            }

            interface View extends IView {
                void addRemindSuccess(BaseEntity data);

            }
}
