package com.yhjx.ministryhealth.api;


import com.yhjx.ministryhealth.bean.ProtocolPrivacyBean;

import io.reactivex.Observable;
import retrofit2.http.POST;


public interface ApiService {


    //用户协议与隐私
    @POST(UrlConstants.PROTOCOL_AND_PRIVACY)
    Observable<ProtocolPrivacyBean> protocolAndPrivacy();


}
