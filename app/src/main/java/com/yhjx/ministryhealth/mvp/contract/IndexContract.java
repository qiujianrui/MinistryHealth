package com.yhjx.ministryhealth.mvp.contract;

import com.library.basemodule.mvp.IView;
import com.yhjx.ministryhealth.bean.IndexBean;

public interface IndexContract {
        interface Presenter {
                void index();
            }

            interface View extends IView {
                void indexSuccess(IndexBean data);
            }
}
