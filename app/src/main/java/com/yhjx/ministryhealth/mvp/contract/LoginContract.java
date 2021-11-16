package com.yhjx.ministryhealth.mvp.contract;

import com.library.basemodule.mvp.IView;
import com.yhjx.ministryhealth.bean.LoginBean;

public interface LoginContract {
        interface Presenter {
                void login(String phone,String pwd);
            }

            interface View extends IView {
                void loginSuccess(LoginBean data);

            }
}
