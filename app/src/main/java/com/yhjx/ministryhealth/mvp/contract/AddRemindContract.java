package com.yhjx.ministryhealth.mvp.contract;

import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.mvp.IView;
import com.yhjx.ministryhealth.bean.DrugNameBean;

import java.util.HashMap;
import java.util.List;

public interface AddRemindContract {
        interface Presenter {
                void addRemind(String type, String remindData, String dateStart,String dateCreate);
            void addRemindDrug(HashMap<String, Object> hashMap);
            void getDrug(String name);
            }

            interface View extends IView {
                void addRemindSuccess(BaseEntity data);
                void getDrugSuccess(List<DrugNameBean> data);
            }
}
