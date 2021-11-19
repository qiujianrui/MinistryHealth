package com.yhjx.ministryhealth.mvp.contract;

import com.library.basemodule.mvp.IView;
import com.yhjx.ministryhealth.bean.MedicineListBean;
import com.yhjx.ministryhealth.bean.RemindDateBean;
import com.yhjx.ministryhealth.bean.RemindListBean;

import java.util.List;

public interface MedicineRecordContract {
    interface Presenter {
        void getMedicineDate();
        void getMedicine(String dateCreate);
    }

    interface View extends IView {
        void getMedicineDateSuccess(List<String> data);
        void getMedicine(List<MedicineListBean> data);
    }
}
