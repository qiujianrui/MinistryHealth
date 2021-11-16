package com.yhjx.ministryhealth.bean;


public class ProtocolPrivacyBean  {
    public String protocolTitle;
    public String protocolContent;     // 用户协议内容
    public String privacyTitle;
    public String privacyContent; // 用户隐私内容

    public String getProtocolTitle() {
        return protocolTitle;
    }

    public void setProtocolTitle(String protocolTitle) {
        this.protocolTitle = protocolTitle;
    }

    public String getProtocolContent() {
        return protocolContent;
    }

    public void setProtocolContent(String protocolContent) {
        this.protocolContent = protocolContent;
    }

    public String getPrivacyTitle() {
        return privacyTitle;
    }

    public void setPrivacyTitle(String privacyTitle) {
        this.privacyTitle = privacyTitle;
    }

    public String getPrivacyContent() {
        return privacyContent;
    }

    public void setPrivacyContent(String privacyContent) {
        this.privacyContent = privacyContent;
    }
}
