package com.yhjx.ministryhealth.mvp.contract;

import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.mvp.IView;

public interface AddMedicineContract {
        interface Presenter {
                void addMedicine(String medicineClass, String medicineName, String medicineNum,String medicineHz);
            }

            interface View extends IView {
                void addMedicineSuccess(BaseEntity data);

            }
}
