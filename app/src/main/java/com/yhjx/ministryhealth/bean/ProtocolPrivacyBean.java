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
        return protocolContent.replace("深圳市镜象科技有限公司","深圳市仰和镜象技术有限公司");
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
        return privacyContent.replace("深圳市镜象科技有限公司","深圳市仰和镜象技术有限公司");
    }

    public void setPrivacyContent(String privacyContent) {
        this.privacyContent = privacyContent;
    }
}
