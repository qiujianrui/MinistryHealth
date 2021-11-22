package com.yhjx.ministryhealth.mvp.contract;

import com.library.basemodule.mvp.IView;
import com.yhjx.ministryhealth.bean.QuestionnaireBean;

public interface PaperContract {
        interface Presenter {
                void getPaperDetail(String id);
                void addPaper(String msgId,String paperData);
            }

            interface View extends IView {
                void getPaperDetailSuccess(QuestionnaireBean data);
                void addPaperSuccess();
            }
}
