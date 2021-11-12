package com.yhjx.ministryhealth.mvp.contract;

import com.library.basemodule.mvp.IView;
import com.yhjx.ministryhealth.bean.ProtocolPrivacyBean;


public interface ProtocolAndPrivacyContract {
        interface Presenter {
                void getProtocolAndPrivacy();
            }

            interface View extends IView {
                void protocolAndPrivacySuccess(ProtocolPrivacyBean data);

            }
}
