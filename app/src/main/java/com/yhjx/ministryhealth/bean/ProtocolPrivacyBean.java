package com.yhjx.ministryhealth.bean;

import com.library.basemodule.entity.BaseEntity;

public class ProtocolPrivacyBean extends BaseEntity {
    public String ProtocolTitle;
    public String ProtocolContent;     // 用户协议内容
    public String PrivacyTitle;
    public String PrivacyContent; // 用户隐私内容

    public String getProtocolTitle() {
        return ProtocolTitle;
    }

    public void setProtocolTitle(String protocolTitle) {
        ProtocolTitle = protocolTitle;
    }

    public String getProtocolContent() {
        return ProtocolContent;
    }

    public void setProtocolContent(String protocolContent) {
        ProtocolContent = protocolContent;
    }

    public String getPrivacyTitle() {
        return PrivacyTitle;
    }

    public void setPrivacyTitle(String privacyTitle) {
        PrivacyTitle = privacyTitle;
    }

    public String getPrivacyContent() {
        return PrivacyContent;
    }

    public void setPrivacyContent(String privacyContent) {
        PrivacyContent = privacyContent;
    }
}
